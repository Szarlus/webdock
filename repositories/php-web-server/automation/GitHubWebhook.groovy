def getWebhookTriggerDefinition(String token) {
    return GenericTrigger(
        causeString: 'GitHub Webhook Event',
        token: token,
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
}

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

return this
