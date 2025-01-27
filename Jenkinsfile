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
                sh '''
                    echo "Java version:"
                    java -version
                    echo "Maven version:"
                    mvn -version
                    mvn clean package
                '''
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.war', fingerprint: true
                }
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}