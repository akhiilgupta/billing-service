#!/bin/bash

java -version
java -Djava.security.egd=file:/dev/./urandom $BOOTAPP_JAVA_OPTS -jar $BOOTAPP_PATH $BOOTAPP_OPTS --server.port=$SERVER_PORT > /dev/stdout 2>&1
