pipeline {
    agent any

    triggers {
        pollSCM('H/2 * * * *')
        cron('H/5 * * * *')
    }

    stages {

        stage('Clone Repository') {
            steps {
                echo "Cloning Repository..."
                git url: 'https://github.com/Ushosipal/Pop-Smash.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                echo "Building Project..."
                bat 'mvn clean package'
            }
        }

        stage('Echo Build Status') {
            steps {
                echo "Build Successful!"
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
}
