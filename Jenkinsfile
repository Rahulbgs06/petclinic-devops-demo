pipeline {
    agent any

    triggers {
        githubPush()
    }

    environment {
        DOCKER_IMAGE = "petclinic-demo"
        WEB_PORT = "8082"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Rahulbgs06/petclinic-devops-demo.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Stop Existing Container') {
            steps {
                sh "docker rm -f ${DOCKER_IMAGE} || true"
            }
        }

        stage('Run Docker Container') {
            steps {
                sh "docker run -d -p ${WEB_PORT}:8080 --name ${DOCKER_IMAGE} ${DOCKER_IMAGE}"
            }
        }

        stage('Wait for App to Start') {
            steps {
                sh 'sleep 15'
            }
        }

        stage('Run Tests') {
            steps {
                // Pass port as system property to tests
                sh "./gradlew test -Dapp.port=${WEB_PORT}"
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
            echo "App is running on http://localhost:${WEB_PORT}"
        }
        failure {
            echo 'Pipeline failed. Check logs.'
        }
    }
}
