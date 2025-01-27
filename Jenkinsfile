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
        stage('Check Environment') {
            steps {
                sh '''
                    echo "Finding Java path:"
                    which java
                    readlink -f $(which java)
                    echo "\nJava version:"
                    java -version
                    
                    echo "\nFinding Maven path:"
                    which mvn
                    readlink -f $(which mvn)
                    echo "\nMaven version:"
                    mvn -version
                    
                    echo "\nChecking common Java paths:"
                    ls -l /usr/lib/jvm/
                    
                    echo "\nChecking system PATH:"
                    echo $PATH
                '''
            }
        }
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