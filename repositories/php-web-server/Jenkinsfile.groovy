#!/usr/bin/env groovy

properties([
    pipelineTriggers([
        GenericTrigger(
            causeString: 'GitHub Webhook Event',
            token: 'job_php-web-server',
            genericHeaderVariables: [
                [key: 'X-GitHub-Delivery', regexpFilter: ''],
                [key: 'X-GitHub-Event', regexpFilter: '']
            ],
            genericVariables: [
                // [key: 'x_github_payload', value: '$', defaultValue: '', regexpFilter: '', expressionType: 'JSONPath']

                // `push` event variables
                // ...

                // `pull_request` event variables
                [key: 'x_github_pull_request_number', value: '$.number', defaultValue: '', regexpFilter: '', expressionType: 'JSONPath']
            ],
            regexpFilterExpression: '',
            regexpFilterText: '',
            printPostContent: false,
            printContributedVariables: false
        )
    ])
])

node('slave') {
    loginToDockerRegistry()

    Map scmVars = checkout(scm)
// >>>
    echo "isContinuousDeploymentEnabled: ${isContinuousDeploymentEnabled().toString()}"
    echo "isPipelineTriggeredByUser: ${isPipelineTriggeredByUser().toString()}"
    echo "isPipelineTriggeredByAnyWebhookEvent: ${isPipelineTriggeredByAnyWebhookEvent().toString()}"
    echo "isPipelineTriggeredByPushWebhookEvent: ${isPipelineTriggeredByPushWebhookEvent().toString()}"
    echo "isPipelineTriggeredByPullRequestWebhookEvent: ${isPipelineTriggeredByPullRequestWebhookEvent().toString()}"
    echo "getVersionNumber: ${getVersionNumber().toString()}"
    sh 'printenv | sort'
    return
    sh 'sleep 1'
// <<<
    dir('repositories/php-web-server') {
        String buildNumber
        String uniqueVersionNumber
        String versionNumber
        String dockerImageName = 'damlys/webdock-php-web-server'

        currentBuild.displayName = "BUILD#${env.BUILD_NUMBER}@${scmVars.GIT_COMMIT}"
        currentBuild.description = "Jenkins build number: ${env.BUILD_NUMBER}"
        currentBuild.description += "\nGit commit hash: ${scmVars.GIT_COMMIT}"
        if (isPipelineTriggeredByAnyWebhookEvent()) {
            currentBuild.description += "\nGitHub webhook delivery identifier: ${env.x_github_delivery}"

            if (isPipelineTriggeredByPullRequestWebhookEvent()) {
                currentBuild.displayName = "PR#${env.x_github_pull_request_number}@${scmVars.GIT_COMMIT}"
                commentPullRequest("Jenkins build URL: ${env.BUILD_URL}".toString())
            }
        }

        /**
         * It prevents unnecessary execution of pipeline stages.
         */
        if (sh(script: "docker pull ${dockerImageName}:${scmVars.GIT_COMMIT}", returnStatus: true) == 0) {
            echo 'This commit has already reached release stage.'
            sh "docker tag ${dockerImageName}:${scmVars.GIT_COMMIT} ${dockerImageName}:${scmVars.GIT_COMMIT}-gamma"
            sh "docker push ${dockerImageName}:${scmVars.GIT_COMMIT}-gamma"
        }
        if (sh(script: "docker pull ${dockerImageName}:${scmVars.GIT_COMMIT}-gamma", returnStatus: true) == 0) {
            echo 'Manual testing stage will be skipped.'
            sh "docker tag ${dockerImageName}:${scmVars.GIT_COMMIT}-gamma ${dockerImageName}:${scmVars.GIT_COMMIT}-beta"
            sh "docker push ${dockerImageName}:${scmVars.GIT_COMMIT}-beta"
        }
        if (sh(script: "docker pull ${dockerImageName}:${scmVars.GIT_COMMIT}-beta", returnStatus: true) == 0) {
            echo 'Automated testing stage will be skipped.'
            sh "docker tag ${dockerImageName}:${scmVars.GIT_COMMIT}-beta ${dockerImageName}:${scmVars.GIT_COMMIT}-alfa"
            sh "docker push ${dockerImageName}:${scmVars.GIT_COMMIT}-alfa"
        }
        if (sh(script: "docker pull ${dockerImageName}:${scmVars.GIT_COMMIT}-alfa", returnStatus: true) == 0) {
            echo 'Build stage will be skipped.'
        }

        /**
         * Stage goals:
         * 1. Run source code tests:
         *   - static tests:
         *     - code syntax checker,
         *     - coding standards checker,
         *     - variables dumps detector,
         *   - unit tests.
         * 2. Build application source code:
         *   - download packages,
         *   - build assets,
         *   - warm up cache.
         * 3. Build Docker image.
         * 4. Push artifacts (alfa).
         */
        stage('Build') {
            if (sh(script: "docker pull ${dockerImageName}:${scmVars.GIT_COMMIT}-alfa", returnStatus: true) == 0) {
                echo 'Image is already built.'
            } else {
                sh 'cp .env.example .env'
                sh 'make download-packages'
                // run tests...
                // build application...
                sh "docker build --no-cache --tag ${dockerImageName}:${scmVars.GIT_COMMIT}-alfa ./image/"
                sh "docker push ${dockerImageName}:${scmVars.GIT_COMMIT}-alfa"
            }
        }

        /**
         * Stage goals:
         * 1. Setup testing environment and run environment smoke tests.
         * 2. Deploy services to testing environment and run services smoke tests.
         * 3. Run end-to-end tests:
         *   - integration tests:
         *     - modules & components integration tests,
         *     - services integration tests,
         *   - acceptance tests:
         *     - functional tests:
         *       - scenario testing,
         *       - localization testing,
         *       - accessibility testing,
         *     - non-functional tests:
         *       - security testing,
         *       - performance testing,
         *       - stress testing,
         *       - volume testing,
         *       - recovery testing.
         * 4. Destroy services and delete data from testing environment.
         * 5. Terminate testing environment.
         * 6. Push artifacts (beta).
         */
        stage('Automated tests') {
            if (sh(script: "docker pull ${dockerImageName}:${scmVars.GIT_COMMIT}-beta", returnStatus: true) == 0) {
                echo 'Project already passed automated tests.'
            } else {
                try {
                    // setup env...
                    // deploy services...
                    // run tests...
                } finally {
                    // destroy services...
                    // terminate env...
                }
                sh "docker tag ${dockerImageName}:${scmVars.GIT_COMMIT}-alfa ${dockerImageName}:${scmVars.GIT_COMMIT}-beta"
                sh "docker push ${dockerImageName}:${scmVars.GIT_COMMIT}-beta"
            }
        }

        /**
         * Conditionally - approve pull request and finish pipeline.
         */
        if (isPipelineTriggeredByPullRequestWebhookEvent()) {
            approvePullRequest()
            return
        }

        /**
         * Stage goals:
         * 1. Setup UAT environment and run environment smoke tests.
         * 2. Deploy services to UAT environment and run services smoke tests.
         * 3. Share access data (links and credentials) with the QA team.
         * 4. Wait for approval by QA team. Manual tests may include:
         *   - acceptance tests:
         *     - functional tests:
         *       - regression testing,
         *       - scenario testing,
         *       - exploratory testing,
         *       - vulnerability testing,
         *       - usability testing,
         *     - non-functional tests:
         *       - stress testing,
         *       - volume testing.
         * 5. Destroy services and delete data from UAT environment.
         * 6. Terminate UAT environment.
         * 7. Push artifacts (gamma).
         */
        stage('Manual tests') {
            if (sh(script: "docker pull ${dockerImageName}:${scmVars.GIT_COMMIT}-gamma", returnStatus: true) == 0) {
                echo 'Project already passed manual tests.'
            } else {
                try {
                    // setup env...
                    // deploy services...
                    // share access data...
                    timeout(time: 7, unit: 'DAYS') {
                        input(
                            message: 'Waiting for approval by QA team.',
                            ok: 'Approve'
                        )
                    }
                } finally {
                    // destroy services...
                    // terminate env...
                }
                sh "docker tag ${dockerImageName}:${scmVars.GIT_COMMIT}-beta ${dockerImageName}:${scmVars.GIT_COMMIT}-gamma"
                sh "docker push ${dockerImageName}:${scmVars.GIT_COMMIT}-gamma"
            }
        }

        /**
         * Stage goals:
         * 1. Ensure that Git commit is tagged with version number.
         * 2. Push artifacts (commit without label).
         * 3. Push artifacts (tagged with version number).
         */
        stage('Release') {
            if (sh(script: "docker pull ${dockerImageName}:${scmVars.GIT_COMMIT}", returnStatus: true) == 0) {
                echo 'This image has already reached release stage.'
            } else {
                sh "docker tag ${dockerImageName}:${scmVars.GIT_COMMIT}-gamma ${dockerImageName}:${scmVars.GIT_COMMIT}"
                sh "docker push ${dockerImageName}:${scmVars.GIT_COMMIT}"
            }

            versionNumber = getVersionNumber()
            tagGitCommit(versionNumber)

            sh "docker tag ${dockerImageName}:${scmVars.GIT_COMMIT} ${dockerImageName}:${versionNumber}"
            sh "docker push ${dockerImageName}:${versionNumber}"

            currentBuild.displayName = "RELEASE#${versionNumber}@${scmVars.GIT_COMMIT}"
            currentBuild.description += "\nVersion number: ${versionNumber}"
        }

        /**
         * Stage goals:
         * 1. Setup staging environment and run environment smoke tests.
         * 2. Deploy services to staging environment and run services smoke tests.
         * 3. Setup production environment and run environment smoke tests.
         * 4. Deploy services to production environment and run services smoke tests.
         */
        stage('Deploy') {
            // setup staging env...
            // deploy services to staging env...

            if (!isContinuousDeploymentEnabled()) {
                timeout(time: 7, unit: 'DAYS') {
                    input(
                        message: 'Project is ready to production deployment. Continue?',
                        ok: 'Deploy'
                    )
                }
            }

            // setup production env...
            // deploy services to production env...
        }
    }
}

boolean isContinuousDeploymentEnabled() {
    return false
}

void loginToDockerRegistry(String credentialsId = 'docker-hub-credentials', String registryServerUrl = 'https://registry.hub.docker.com') {
    withCredentials([
        usernamePassword(
            credentialsId: credentialsId,
            usernameVariable: 'USERNAME',
            passwordVariable: 'PASSWORD'
        )
    ]) {
        sh "docker login --username '${env.USERNAME}' --password '${env.PASSWORD}' '${registryServerUrl}'"
    }
}

//boolean isDockerImageExists(String imageName, String tag) {
//    return sh(script: "docker pull ${imageName}:${tag}", returnStatus: true) == 0
//}

//void retagDockerImage(String imageName, String currentTag, String newTag) {
//    sh "docker tag ${imageName}:${currentTag} ${imageName}:${newTag}"
//    sh "docker push ${imageName}:${newTag}"
//}

boolean isPipelineTriggeredByUser() {
    return currentBuild.rawBuild.getCause(hudson.model.Cause$UserIdCause) != null
}

boolean isPipelineTriggeredByAnyWebhookEvent() {
    return currentBuild.rawBuild.getCause(org.jenkinsci.plugins.gwt.GenericCause) != null
}

boolean isPipelineTriggeredByGivenWebhookEvent(String webhookEventName) {
    return isPipelineTriggeredByAnyWebhookEvent() && env.x_github_event == webhookEventName
}

boolean isPipelineTriggeredByPushWebhookEvent() {
    return isPipelineTriggeredByGivenWebhookEvent('push')
}

boolean isPipelineTriggeredByPullRequestWebhookEvent() {
    return isPipelineTriggeredByGivenWebhookEvent('pull_request')
}

void commentPullRequest(String content) {
    if (!isPipelineTriggeredByPullRequestWebhookEvent()) {
        error 'This pipeline is not triggered by pull request event.'
    }
    echo "commenting pull request... (\"${content}\")"
}

void approvePullRequest() {
    if (!isPipelineTriggeredByPullRequestWebhookEvent()) {
        error 'This pipeline is not triggered by pull request event.'
    }
    echo 'approving pull request...'
}

String getVersionNumber() {
    String versionNumber = "0.0.${env.BUILD_NUMBER}".toString()
//    guardVersionNumber(versionNumber)
    return versionNumber
}

void tagGitCommit(String versionNumber) {
    if (sh(script: "git fetch origin 'refs/tags/${versionNumber}'", returnStatus: true) == 0) {
        echo 'Tag already exists.'
        guardVersionNumber(versionNumber)
    } else {
        sh "git tag '${versionNumber}'"
        sh "git push origin 'refs/tags/${versionNumber}'"
    }
}

void guardVersionNumber(String versionNumber) {
    String x = sh(script: "git rev-list --max-count=1 'refs/tags/${versionNumber}'", returnStdout: true).trim()
    if (x != scmVars.GIT_COMMIT) {
        error 'Tag is already assigned to another commit.'
    }
}
