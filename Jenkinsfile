pipeline {
    agent {
        node{
            label 'mvn'
        }
    }
    
    stages {
        stage('Clone-code') {
            steps {
                git branch: 'main', url: 'https://github.com/jksoam/java-app.git'
            }
        }
    }
}