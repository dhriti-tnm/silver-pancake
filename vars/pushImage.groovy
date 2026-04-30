def call(String imageName, String tag, String credentialsId){
    echo "Pushing image to Docker Hub"

    withCredentials([usernamePassword(
        credentialsId: credentialsId,
        usernameVariable: 'DOCKER_USER',
        passwordVariable: 'DOCKER_PASS'
    )]){
        sh "docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}"
        sh "docker image tag ${imageName}:${tag} ${DOCKER_USER}/${imageName}:${tag}"
        sh "docker push ${DOCKER_USER}/${imageName}:${tag}"
    }
}
