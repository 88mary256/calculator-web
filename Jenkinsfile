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
              sh "pwd"
              sh "ls -la ./**"
              sh "./gradlew appRun"
          }
      }
    }
}
