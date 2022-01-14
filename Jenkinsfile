pipeline {
    agent any

    stages {
        stage('Compile') {
            steps {
                script{
                    
                        sh "./mvnw clean compile -e"
                    
                }
            }
        }

        stage('SonarQube analysis'){
            steps{
                script {
                    def scannerHome = tool 'sonar-scanner';
                    withSonarQubeEnv('sonarqube-server'){
                        sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-maven -Dsonar.sources=src -Dsonar.java.binaries=build"
                    }
                }
            }
        }
        
        stage('Test') {
            steps {
                script{
                    
                        sh "./mvnw clean test -e"
                    
                }
            }
        }
        
        stage('Jar') {
            steps {
                script{
                    
                        sh "./mvnw clean package -e"
                    
                }
            }
        }
        
        stage('Run') {
            steps {
                script{
                    
                        sh "nohup ./mvnw spring-boot:run > server.log 2>&1&"
                        sleep 20
                    
                }
            }
        }

        stage('Curl') {
            steps {
                script{
                    
                        
                        sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
                    
                }
            }
        }
        
    }
}
