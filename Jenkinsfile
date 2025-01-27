pipeline {
    agent any
    
    tools {
        maven 'Maven 3.9.6'
        jdk 'JDK 11'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
        
        stage('Deploy to Tomcat') {
            steps {
                deploy adapters: [tomcat9(credentialsId: 'tomcat_credentials',
                                        path: '',
                                        url: 'http://localhost:8080')],
                        contextPath: 'simple-java-web-app',
                        war: 'target/*.war'
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
        success {
            echo 'Build successful! Deployed to Tomcat server.'
        }
        failure {
            echo 'Build failed! Check the logs for details.'
        }
    }
}
