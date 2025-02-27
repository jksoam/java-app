pipeline {
    agent {
        node {
            label 'mvn'
        }
    }
    
    environment {
        PATH = "/opt/maven/bin:$PATH"
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk-amd64"
    }   
    
    stages {
        stage('Build') {
            steps {
                script {
                    sh '''
                        echo "Java version:"
                        java -version
                        echo "Maven version:"
                        mvn -version
                        mvn clean package
                    '''
                }
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    sh """
                        mvn sonar:sonar \\
                            -Dsonar.projectKey=jksoam_java-app \\
                            -Dsonar.organization=jksoam \\
                            -Dsonar.host.url=https://sonarcloud.io \\
                            -Dsonar.token=${SONAR_TOKEN}
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    timeout(time: 2, unit: 'MINUTES') {
                        waitForQualityGate abortPipeline: true
                    }
                }
            }
        }
    }
    
    post {
        always {
            node('mvn') {
                cleanWs()
            }
        }
        success {
            node('mvn') {
                echo 'Build succeeded!'
            }
        }
        failure {
            node('mvn') {
                echo 'Build failed!'
            }
        }
    }
}