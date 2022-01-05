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
