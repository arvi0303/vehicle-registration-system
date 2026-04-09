pipeline {
    agent any

    triggers {
        githubPush()
    }

    tools {
        jdk 'jdk17'
        maven 'maven3'
    }

    environment {
        IMAGE_NAME = 'vehicle-registration-system'
        IMAGE_TAG = "${BUILD_NUMBER}"
        CONTAINER_NAME = 'vehicle-registration-system-demo'
        HOST_PORT = '8081'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Branch Validation') {
            when {
                anyOf {
                    branch 'develop'
                    branch 'deploy'
                }
            }
            steps {
                echo "Running pipeline for branch: ${env.BRANCH_NAME}"
            }
        }

        stage('Build And Test') {
            when {
                anyOf {
                    branch 'develop'
                    branch 'deploy'
                }
            }
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
            when {
                anyOf {
                    branch 'develop'
                    branch 'deploy'
                }
            }
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Build Docker Image') {
            when {
                branch 'deploy'
            }
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

        stage('Run Docker Container Locally') {
            when {
                branch 'deploy'
            }
            steps {
                script {
                    if (isUnix()) {
                        sh 'docker rm -f ${CONTAINER_NAME} || true'
                        sh 'docker run -d --name ${CONTAINER_NAME} -p ${HOST_PORT}:8080 ${IMAGE_NAME}:${IMAGE_TAG}'
                        sh 'docker ps --filter "name=${CONTAINER_NAME}"'
                    } else {
                        bat 'docker rm -f %CONTAINER_NAME%'
                        bat 'docker run -d --name %CONTAINER_NAME% -p %HOST_PORT%:8080 %IMAGE_NAME%:%IMAGE_TAG%'
                        bat 'docker ps --filter "name=%CONTAINER_NAME%"'
                    }
                }
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
        }
        success {
            echo "Pipeline completed successfully for branch ${env.BRANCH_NAME}"
        }
    }
}
