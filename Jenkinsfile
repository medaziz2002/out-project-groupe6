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
            dir('product-management') {
                script {
                    echo "Building and testing backend"
                    bat 'mvn clean test'
                }
            }
        }
            post {
                always {
                dir('product-management') {
                    junit 'target/surefire-reports/*.xml'
                }
                }
                failure {
                    error "Backend unit tests failed"
                }
            }
        }

        stage('Frontend - Build') {
            when {
                expression { fileExists('front-end/package.json') }
            }
            steps {
                dir('front-end') {
                    bat 'npm install'
                    bat 'npm run build'
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