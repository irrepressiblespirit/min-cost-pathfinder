FROM gradle:6.5.1-jdk8 as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
ARG DVERSION=none
ENV DVERSION=$DVERSION
RUN mkdir /home/gradle/src/src/main/resources/static
RUN gradle build

FROM openjdk:8
EXPOSE 8080
COPY --from=builder /home/gradle/src/files/start.sh /opt/
COPY --from=builder /home/gradle/src/build/libs/mincostpathfinder-1.0.0.jar /opt/app.jar
ENV TZ=Europe/Kiev
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENV LANG=ru_RU.UTF-8 \
    LANGUAGE=ru_RU.UTF-8 \
    LC_CTYPE=ru_RU.UTF-8 \
    LC_ALL=ru_RU.UTF-8
RUN ["chmod", "755", "/opt/start.sh"]
CMD exec /opt/start.sh