pipeline {
    agent any

    stages {
        stage('Compile') {
            steps {
                script{
                    dir('/home/samlopez/Desarrollo/taller-pipeline'){
                        sh "./mvnw clean compile -e"
                    }
                }
            }
        }
        
        stage('Test') {
            steps {
                script{
                    dir('/home/samlopez/Desarrollo/taller-pipeline'){
                        sh "./mvnw clean test -e"
                    }
                }
            }
        }
        
        stage('Jar') {
            steps {
                script{
                    dir('/home/samlopez/Desarrollo/taller-pipeline'){
                        sh "./mvnw clean package -e"
                    }
                }
            }
        }
        
        stage('Run') {
            steps {
                script{
                    dir('/home/samlopez/Desarrollo/taller-pipeline'){
                        //sh "nohup mvn spring-boot:run &"
                        //sh "./mvnw spring-boot:run"
                        //sh "nohup bash mvnw spring-boot:run >> server.log 2>&1&"
                        //sh "nohup mvn spring-boot:run >> server.log 2>&1&"
                        sh "nohup ./mvnw spring-boot:run > server.log 2>&1&"
                    }
                }
            }
        }

        stage('Curl') {
            steps {
                script{
                    dir('/home/samlopez/Desarrollo/taller-pipeline'){
                        
                        sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
                    }
                }
            }
        }
        
    }
}
