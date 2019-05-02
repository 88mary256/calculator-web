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

                timeout(1) {
                    waitUntil {
                        script {
                            fileExists('build/sonar/report-task.txt')
                        }
                    }
                    waitUntil {
                        script {
                            def taskId = readFile('build/sonar/report-task.txt').split("\n")[3].split("=")[1]
                            def task_response = httpRequest "https://sonarUrl.com/api/ce/task?id=${taskId}"
                            def task_data = new JsonSlurperClassic().parseText(task_response.content)
                            return (task_data.task.status.equals("SUCCESS"))
                        }
                    }
                }
                script {
                    def response = httpRequest "https://sonarUrl.com/api/qualitygates/project_status?projectKey=XXX"
                    def data = new JsonSlurperClassic().parseText(response.content)
                    if (data.projectStatus.status == "ERROR") {
                        error("Sonar Quality Gate not met. Check https://sonarUrl.com/overview?id=XXX")
                    }
                }
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
