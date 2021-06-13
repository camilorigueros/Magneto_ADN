#docker rmi camiloriguer/magneto_meli
#sudo docker build -f Dockerfile -t camiloriguer/magneto_meli .
#docker push  camiloriguer/magneto_meli

FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]