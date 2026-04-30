def call(){
    echo "Deploying application"
    sh "docker-compose down"
    sh "docker-compose up -d --build"
}
