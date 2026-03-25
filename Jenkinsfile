pipeline {
    agent any

    environment {
        JAVA_HOME = "C:\\Program Files\\Java\\jdk-17.0.12"
        NODE_HOME = "C:\\Program Files\\nodejs"
         PATH = "${env.JAVA_HOME}\\bin;${env.NODE_HOME};${env.PATH}"
    }

    stages {
        stage('Backend - Unit Tests') {
            steps {
                script {
                    echo "Building and testing backend"
                    bat 'mvn clean test'
                }
            }
            post {
                always {
                    junit 'product-management/target/surefire-reports/*.xml'
                }
                failure {
                    error "Backend unit tests failed"
                }
            }
        }

        stage('Frontend - Build') {
            steps {
                script {
                    echo "Installing frontend dependencies"
                    bat 'npm ci'

                    echo "Building frontend"
                    bat 'npm run build'
                }
            }
            post {
                failure {
                    error "Frontend build failed"
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline finished"
        }
    }
}