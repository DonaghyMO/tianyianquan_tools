FROM openjdk:8

COPY ./app.jar /tianyianquan/app.jar
COPY ./install.sh /tianyianquan/install.sh
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone&&bash /tianyianquan/install.sh
CMD ["--server.port=8080"]

EXPOSE 8080

ENTRYPOINT ["java","-jar","/tianyianquan/app.jar"]
