pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "petclinic-demo"
        WEB_PORT = "8081"  // Port mapped to host for Spring Boot app
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Rahulbgs06/petclinic-devops-demo.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Run Tests') {
            steps {
                sh './gradlew test'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Stop Existing Container') {
            steps {
                // Stop previous container if exists
                sh "docker ps -q --filter name=${DOCKER_IMAGE} | xargs -r docker stop"
                sh "docker ps -a -q --filter name=${DOCKER_IMAGE} | xargs -r docker rm"
            }
        }

        stage('Run Docker Container') {
            steps {
                // Map container port 8080 → host port 8081
                sh "docker run -d -p ${WEB_PORT}:8080 --name ${DOCKER_IMAGE} ${DOCKER_IMAGE}"
            }
        }
    }

    post {
        success {
            echo 'CI/CD pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs for errors.'
        }
    }
}

