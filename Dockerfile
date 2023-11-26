FROM openjdk:8

WORKDIR /tianyianquan
COPY ./app.jar /tianyianquan/app.jar
COPY ./install.sh /tianyianquan/install.sh


CMD ["--server.port=8080"]

EXPOSE 8080

ENTRYPOINT ["java","-jar","/tianyianquan/app.jar"]
