pipeline {
    agent any 
    stages {
        stage("Inicio del Pipeline") {
            steps {
                echo "Iniciando Pipeline: ${env.JOB_NAME}"
            }
        }

        stage("Creacion de Docker") {
        steps {
                sh 'git pull https://github.com/fabianbello/ICtest1 main'
                sh 'docker build --build-arg JAR_FILE=build/libs/evaluacion2-0.0.1-SNAPSHOT.jar -t myorg/myapp .'
                sh 'docker run --name prueba2 -d -p 8092:8090 myorg/myapp'

      
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