pipeline {
    agent any
    stages {
        stage('Build in Docker image')  {
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
                      sh "gradle build"
                    }
                  }
                  /* stage('Validate code quality') {
                      steps {
                          sh "gradle sonarqube \
                          -Dsonar.projectKey=88mary256_calculator-web \
                          -Dsonar.organization=88mary256-github \
                          -Dsonar.host.url=https://sonarcloud.io \
                          -Dsonar.login=0cff712fd948d82dd64bcb5c0ffa840c75a47f31"
                    }
                  } */
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
        stage('Deploy') {
          steps {
              sh "gradle appRun"
              sh "docker build -t tomcat_for_gui_tests:1.1 -f DockerfileProd ."
              sh "docker run --rm -d -p 8888:8080 tomcat_for_gui_tests:1.1"
          }
        }
        stage('GUI tests') {
            steps {
                sh "git clone https://github.com/88mary256/helloPage-selenium-tests.git"
                sh "gradle executeFeatures"
            }
        }
    }
}