pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'JDK'
    }

    environment {
        APP_NAME = "edu-tlu-oracle"
        VERSION = "0.0.1-SNAPSHOT"
        JAR_NAME = "edu.tlu.oracle-${VERSION}.jar"
        TARGET_PATH = "target/${JAR_NAME}"
        DEPLOY_DIR = "/opt/${APP_NAME}"
        DEPLOY_PATH = "${DEPLOY_DIR}/${JAR_NAME}"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "📥 Cloning source code..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "⚙️ Building Spring Boot JAR..."
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo "✅ Running unit tests..."
                sh 'mvn test'
            }
        }

        stage('Archive Artifact') {
            steps {
                echo "📦 Archiving built JAR..."
                archiveArtifacts artifacts: "${TARGET_PATH}", fingerprint: true
            }
        }

        stage('Deploy') {
            steps {
                echo "🚀 Deploying ${APP_NAME}..."

                sh """
                    echo "📦 Copying new JAR..."
                    sudo cp ${TARGET_PATH} ${DEPLOY_PATH}

                    echo "🔄 Reloading systemd..."
                    sudo systemctl daemon-reload

                    echo "♻️ Restarting ${APP_NAME}..."
                    sudo systemctl restart ${APP_NAME}

                    echo "🔍 Checking status..."
                    sudo systemctl status ${APP_NAME} --no-pager
                """
            }
        }
    }

    post {
        always {
            echo "✅ Pipeline finished."
        }
        success {
            echo "🎉 Deployment succeeded."
        }
        failure {
            echo "❌ Pipeline failed."
        }
    }
}
