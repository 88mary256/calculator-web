pipeline {
    agent {
        dockerfile true
    }
    stages {
      stage('Build') {
          steps {
              sh "./gradlew build"
          }
      }
      stage('Test') {
          steps {
              sh "./gradlew test"
          }
      }
      stage('Package') {
          steps {
              sh "./gradlew war"
          }
       }
      stage('Deploy') {
          steps {
              sh "./gradlew appRun"
          }
      }
    }
    post {
        always {
            publishHTML target: [
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'build/reports/tests/',
                reportFiles: 'index.html',
                reportName: 'Unit Test Report'
            ]
        }
        success {
            archiveArtifacts "build/libs/*.war"
        }
    }
}
