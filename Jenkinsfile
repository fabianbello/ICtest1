pipeline {
    agent any 
    stages {
        stage("Inicio del Pipeline 3") {
            steps {
                echo "Iniciando Pipeline: ${env.JOB_NAME}"
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

        stage("Creacion de Docker") {
            steps {
                echo "creando docker"

            }
        }


        stage("Fin del Pipeline") {
            steps {
                echo "Finalizando Pipeline: ${env.JOB_NAME}"
            }
        }
    }
}