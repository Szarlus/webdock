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

boolean isDockerImageExists(String imageName, String tag) {
    return sh(script: "docker pull ${imageName}:${tag}", returnStatus: true) == 0
}

void retagDockerImage(String imageName, String currentTag, String newTag) {
    sh "docker tag ${imageName}:${currentTag} ${imageName}:${newTag}"
    sh "docker push ${imageName}:${newTag}"
}

return this
