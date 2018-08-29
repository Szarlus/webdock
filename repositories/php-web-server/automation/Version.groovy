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

return this
