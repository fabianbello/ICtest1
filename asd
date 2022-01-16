
   
def CONTAINER_NAME="jenkins-pipeline"
def CONTAINER_TAG="latest"
def DOCKER_HUB_USER="hakdogan"
def HTTP_PORT="8090"

node {

    stage('Initialize'){
        def dockerHome = tool 'myDocker'
        def mavenHome  = tool 'myMaven'
        env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
    }

    stage('Checkout') {
        checkout scm
    }

    stage('Build'){
        sh "mvn clean install"
    }

    stage('Sonar'){
        try {
            sh "mvn sonar:sonar"
        } catch(error){
            echo "The sonar server could not be reached ${error}"
        }
     }

    stage("Image Prune"){
        imagePrune(CONTAINER_NAME)
    }

    stage('Image Build'){
        imageBuild(CONTAINER_NAME, CONTAINER_TAG)
    }

    stage('Push to Docker Registry'){
        withCredentials([usernamePassword(credentialsId: 'dockerHubAccount', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
            pushToImage(CONTAINER_NAME, CONTAINER_TAG, USERNAME, PASSWORD)


pushToImage(CONTAINER_NAME, CONTAINER_TAG, USERNAME, PASSWORD)



    sh "docker login -u $USERNAME -p $PASSWORD"
    sh "docker tag $CONTAINER_NAME:$CONTAINER_TAG $USERNAME/$CONTAINER_NAME:$CONTAINER_TAG"
    sh "docker push $USERNAME/$CONTAINER_NAME:$CONTAINER_TAG"
    echo "Image push complete"



        }
    }

    stage('Run App'){
        runApp(CONTAINER_NAME, CONTAINER_TAG, DOCKER_HUB_USER, HTTP_PORT)
    }

}

def imagePrune(containerName){
    try {
        sh "docker image prune -f"
        sh "docker stop $containerName"
    } catch(error){}
}

def imageBuild(containerName, tag){
    sh "docker build -t $containerName:$tag  -t $containerName --pull --no-cache ."
    echo "Image build complete"
}

def pushToImage(containerName, tag, dockerUser, dockerPassword){
    sh "docker login -u $dockerUser -p $dockerPassword"
    sh "docker tag $containerName:$tag $dockerUser/$containerName:$tag"
    sh "docker push $dockerUser/$containerName:$tag"
    echo "Image push complete"
}

def runApp(containerName, tag, dockerHubUser, httpPort){
    sh "docker pull $dockerHubUser/$containerName"
    sh "docker run -d --rm -p $httpPort:$httpPort --name $containerName $dockerHubUser/$containerName:$tag"
    echo "Application started on port: ${httpPort} (http)"
}


ESTE ES EL OTRO --------------------------------------------------------

pipeline {
agent {
    label 'my-agent'
}

stages {
    stage ('Docker version') {
        steps {
            sh 'docker --version'
        }
    }

    stage ('Docker Login Test') {
        steps {
            script {
                withCredentials([usernamePassword(
                    credentialsId: 'mycredentials', 
                    usernameVariable: 'DOCKER_USER', 
                    passwordVariable: 'DOCKER_PASSWORD')]) {

                    echo "docker login naked"
                    sh "docker login myAzureRepo.azurecr.io -u admin -p 1234"

                    echo "docker login protected"
                    sh "docker login myAzureRepo.azurecr.io -u $DOCKER_USER -p $DOCKER_PASSWORD" 
                }
            }
        }
    }
}