#!/bin/bash

cd /opt
export LANG=ru_RU.UTF-8
JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8";
exec java ${JAVA_OPTS} -jar app.jar