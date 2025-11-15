pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Docker Build & Deploy') {
            steps {
                script {
                    def imageName = "myapp:${env.BRANCH_NAME}"

                    echo "📦 Building Docker image: ${imageName}"
                    sh "docker build -t ${imageName} ."

                    echo "🛑 Stopping and removing old container if running"
                    sh """
                        docker stop myapp-container || true
                        docker rm myapp-container || true
                    """

                    echo "🚀 Starting new container"
                    sh """
                        docker run -d \
                            --name myapp-container \
                            -p 9091:8080 \
                            ${imageName}
                    """
                }
            }
        }

    }

    post {
        success {
            echo "Build & Deploy successful for branch: ${env.BRANCH_NAME}"
        }
        failure {
            echo "Build failed for branch: ${env.BRANCH_NAME}"
        }
    }
}
