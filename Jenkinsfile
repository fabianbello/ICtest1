pipeline {
    agent any

    environment {

        DOCKER_USER = "fabianbello"
        DOCKER_PASSWORD = "mingesoGrupo6"
    
    }

    stages {
        stage("Inicio del Pipeline") {
            steps {
                echo "Iniciando Pipeline: ${env.JOB_NAME}"
            }
        }

        stage("Creacion de Docke4") {
        steps {

                dir( "${env.WORKSPACE}"){
                    sh 'docker rm pruebafinal -f'
                    sh 'docker rmi pruebafinal -f'       
                    sh 'docker build --build-arg JAR_FILE=build/libs/evaluacion2-0.0.1-SNAPSHOT.jar -t pruebafinal .'
                    sh 'docker run --name pruebafinal -d -p 8091:8090 pruebafinal'

                }
 
            }
        }

        stage("Deslogea usuario anterior"){
            steps{
                dir( "${env.WORKSPACE}"){   
                    sh 'docker logout'
                }

            }
        }

        stage ('Docker Login Test') {
            steps {
    
                    echo "docker login protected"
                    sh "docker login -u $DOCKER_USER -p $DOCKER_PASSWORD" 
                    
                
            }
        }

        stage("Subida de imagen a dockerhub"){

            steps {
                dir( "${env.WORKSPACE}"){

                    sh 'docker tag pruebafinal fabianbello/pruebafinal:v1.0'
                    sh 'docker push fabianbello/pruebafinal:v1.0'
                }
 
            }

        }


        stage("Analisis de SonarQube") { 
            steps{
                dir("${env.WORKSPACE}/backend"){
                    withSonarQubeEnv('sonarqube'){
                        sh 'chmod +x ./gradle'
                        sh './gradle sonarqube'
                    }
                }
            }
        }
        stage("Pruebas unitarias y de integración con JUnit"){   

            steps{

                dir("${env.WORKSPACE}/backend/build/test-results/test"){
                    sh 'touch pruebas.xml'
                    sh 'rm * .xml'
                }
            
                cathcError(buildRsult: 'SUCCESS', stageResult: 'FAILURE'){

                    dir("${env.WORKSPACE}/backend"){
                        sh './gradlew test'
                    }
                }    

                dir("${env.WORKSPACE}/backend/build/test-results/test"){
                    junit '*.xml'
                }
            }    
        }


        stage("Fin del Pipeline") {
            steps {
                echo "Finalizando Pipeline: ${env.JOB_NAME}"
            }
        }
    }
}