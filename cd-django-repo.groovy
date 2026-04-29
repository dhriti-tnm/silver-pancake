pipeline {
    agent {label "vinod"}
    stages {
        stage("Code"){
            steps{
                echo "Cloning code"
                git url:"https://github.com/dhriti-tnm/Recipe-Blog.git", branch:"main"
            }
        }
        stage("Build"){
            steps{
                echo "Creating docker image"
                sh "whoami"
                sh "docker build -t recipe-blog:latest ."
            }
        }
        stage("Push"){
            steps{
                echo "Pushing image to Docker Hub"
                withCredentials([usernamePassword(
                    credentialsId: 'DockerHubCred', 
                    usernameVariable: 'Docker_user', 
                    passwordVariable:"Docker_pass")]){
                    sh "docker login -u ${env.Docker_user} -p ${env.Docker_pass}"
                    sh "docker image tag recipe-blog:latest ${env.Docker_user}/recipe-blog:latest"
                    sh "docker push ${env.Docker_user}/recipe-blog:latest"
                }
            }
        }
        stage("Deploy"){
            steps{
                echo "deply"
                sh "docker-compose down"
                sh "docker-compose up -d --build"
            }
        }
        
    }
}
