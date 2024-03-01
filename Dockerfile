FROM ubuntu:22.04

#COPY docker/app.jar /tianyianquan/app.jar
COPY docker/install.sh /tianyianquan/install.sh
COPY docker/supervisord.conf /etc/supervisord.conf

#COPY ./sources.list /etc/apt/sources.list
#改时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN bash /tianyianquan/install.sh

EXPOSE 8080

CMD ["supervisord","-c","/etc/supervisord.conf"]
