pipeline {
    agent {
        node{
            label 'mvn'
        }
    }
environment {
    PATH = "/opt/maven/bin:$PATH"
}   
    
    stages {
        stage( "build" ) {
            steps {
                sh 'mvn clean package'
            }
        }
        
    }
}