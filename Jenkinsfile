/*
Prerequisites for Jenkins:
1. Install "JDK" tool:
   - Go to Manage Jenkins > Tools
   - Add JDK installation:
     - Name: "jdk17"
     - Install automatically: Check
     - Select "Java SE Development Kit 17"

2. Install "Maven" tool:
   - Go to Manage Jenkins > Tools
   - Add Maven installation:
     - Name: "maven3"
     - Install automatically: Check
     - Select version "3.9.6"

3. Required Plugins:
   - Docker Pipeline
   - Kubernetes CLI
   - Kubernetes
*/

pipeline {
    agent any
    
    environment {
        DOCKER_REGISTRY = 'your-docker-registry'
        DOCKER_IMAGE = "${DOCKER_REGISTRY}/java-app"
        DOCKER_TAG = "${BUILD_NUMBER}"
        KUBECONFIG = credentials('kubernetes-config')
    }
    
    tools {
        maven 'maven3'
        jdk 'jdk17'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean package -Dmaven.compiler.source=17 -Dmaven.compiler.target=17'
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
        
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }
        
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry("https://${DOCKER_REGISTRY}", 'docker-credentials') {
                        docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push()
                        docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push('latest')
                    }
                }
            }
        }
        
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    sh """
                        helm upgrade --install my-java-app helm/java-app \
                        --set image.repository=${DOCKER_IMAGE} \
                        --set image.tag=${DOCKER_TAG} \
                        --namespace production \
                        --create-namespace \
                        --wait --timeout 5m
                    """
                }
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
        success {
            echo 'Build and deployment succeeded!'
        }
        failure {
            echo 'Build or deployment failed!'
        }
    }
}
