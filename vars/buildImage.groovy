def call(string imageName, string tag){
  echo "Building Docker Image"
  sh "docker build -t ${imageName}:${tag} ."
}
