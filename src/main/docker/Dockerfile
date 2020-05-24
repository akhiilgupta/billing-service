FROM adoptopenjdk/openjdk8
MAINTAINER Akhil Gupta <akhil.gupta1@oyorooms.com>

ENV BOOTAPP_JAVA_OPTS -Xms256m -Xmx256m

ENV BOOTAPP_USR="bootapp" BOOTAPP_GROUP="app_group" BOOTAPP_PATH="/app.jar" BOOTAPP_LOGS_VOLUME="/app-log" 
ENV SERVER_PORT 8080
ENV APP_PROPS="/app" BOOTAPP_OPTS="/app/application.properties"

EXPOSE $SERVER_PORT
VOLUME $BOOTAPP_LOGS_VOLUME
VOLUME $APP_PROPS

COPY wrapper.sh /wrapper.sh

RUN groupadd -r $BOOTAPP_GROUP && \
      useradd -l -r -g $BOOTAPP_GROUP $BOOTAPP_USR && \
      chmod 555 /wrapper.sh 

USER root
RUN apt-get update -y && apt-get install procps curl wget -y
COPY maven/app.jar $BOOTAPP_PATH
RUN chmod 555 $BOOTAPP_PATH && touch $BOOTAPP_PATH
USER $BOOTAPP_USR

ENTRYPOINT ["/wrapper.sh"]
