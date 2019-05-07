# Simple-web-project - DEV-OPS

This project generate a web page that say hello to someone.

# How to deploy?
## Requirements
- Java 1.8
- Gradle 4
- Tomcat
## Steps
Execute next commands in the shell:
- Install tomcat at /opt/tomcat
```
gradle build
gradle sonarqube \
                  -Dsonar.projectKey=88mary256_calculator-web \
                  -Dsonar.organization=88mary256-github \
                  -Dsonar.host.url=https://sonarcloud.io \
                  -Dsonar.login=0cff712fd948d82dd64bcb5c0ffa840c75a47f31"
gradle appRun
# this step deploy the project with gretty in port 9000,
# if you want to change the port modify the build.gradle file
# in line 22 : httport = 9000
# replace 9000 with the port that you want. 

COPY ./build/libs/helloPage-1.0-SNAPSHOT.war /opt/tomcat/webapps/

sh /opt/tomcat/bin/catalina.sh run
# This step run the project on tomcat in port 8080 by default
```
