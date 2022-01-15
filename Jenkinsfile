pipeline {
    agent any 
    stages {
        stage("Inicio del Pipeline 3") {
            steps {
                echo "inicializando"
            }
        }
        stage("Analisis de SonarQube") {
            steps {
				echo "1"
  		    }
        }
        stage("Pruebas unitarias y de integración con JUnit"){            
            steps{
                echo "2"
            }
        }
        stage("Fin del Pipeline") {
            steps {
                echo "3"
            }
        }
    }
}