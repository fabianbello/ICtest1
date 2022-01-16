pipeline {
    agent any 
    stages {
        stage("Inicio del Pipeline") {
            steps {
                echo "Iniciando Pipeline: ${env.JOB_NAME}"
            }
        }

        stage("Creacion de Docker1") {
        steps {

                dir( "${env.WORKSPACE}"){
                    sh 'docker build --build-arg JAR_FILE=build/libs/evaluacion2-0.0.1-SNAPSHOT.jar -t pruebafinal .'
                    sh 'docker run --name pruebafinal -d -p 8091:8090 pruebafinal'

                }
 
            }
        }

        stage("Subida de imagen a dockerhub"){

            steps {
                dir( "${env.WORKSPACE}"){
                    sh 'docker logout'
                    sh 'docker login'
                    sh 'fabianbello'
                    sh 'fingesoGrupo6'
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