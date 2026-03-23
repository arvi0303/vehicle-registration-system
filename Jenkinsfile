pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven3'
    }

    environment {
        IMAGE_NAME = 'vehicle-registration-system'
        IMAGE_TAG = 'v1.0.0'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build And Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn -B clean test package'
                    } else {
                        bat 'mvn -B clean test package'
                    }
                }
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'
                    } else {
                        bat 'docker build -t %IMAGE_NAME%:%IMAGE_TAG% .'
                    }
                }
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
        }
    }
}
