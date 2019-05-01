pipeline {
    agent {
        dockerfile {
            args '--entrypoint=\'\''
        }
    }
    stages {
      stage('Build') {
          steps {
              sh "pwd"
              sh "ls -la ./**"
              sh "./gradlew build"
          }
      }
      stage('Validate code quality') {
          steps {
              sh "./gradlew sonarqube \
                  -Dsonar.projectKey=88mary256_calculator-web \
                  -Dsonar.organization=88mary256-github \
                  -Dsonar.host.url=https://sonarcloud.io \
                  -Dsonar.login=0cff712fd948d82dd64bcb5c0ffa840c75a47f31"
          }
      }
      stage('Test') {
          steps {
              echo 'Unit Test already executed in Build stage'
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
