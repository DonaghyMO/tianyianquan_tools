FROM ubuntu

COPY ./install.sh ./install.sh

WORKDIR /tools

RUN ["/bin/bash","install.sh"]

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]