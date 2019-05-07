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
              sh "docker build -t tomcat_for_gui_tests:1.1 -f DockerfileProd ."
              sh "docker run --rm -d -p 8888:8080 tomcat_for_gui_tests:1.1"
          }
        }
        stage('GUI tests') {
            steps {
                sh "pwd"
                sh "rm -r helloPage-selenium-tests"
                sh "git clone https://github.com/88mary256/helloPage-selenium-tests.git"
                sh "git checkout master"
                sh "pwd"
                sh "ls"
                sh "cd ./helloPage-selenium-tests; gradle executeFeatures"
            }
        }
    }
    post {
        always{
            sh "docker stop \$(docker ps -a -q --filter ancestor=tomcat_for_gui_tests:1.1 --format='{{.ID}}')"
            sh 'docker images -q -f dangling=true | xargs --no-run-if-empty docker rmi'
        }
    }
}
