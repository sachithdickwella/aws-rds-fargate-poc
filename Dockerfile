FROM openjdk:20-jdk-oraclelinux8

RUN groupadd auspost && useradd auspost-admin -G auspost
USER auspost-admin:auspost

ARG CLASSPATH_LIB=target/lib
ARG SOURCE_JAR=target/*.jar
ARG TARGET_JAR=auspost-paf-poc.jar

WORKDIR /app

COPY ${SOURCE_JAR} ${TARGET_JAR}
COPY ${CLASSPATH_LIB} ./lib

ENV PORT=8090
ENV EXEC_JAR=${TARGET_JAR}
ENV MAIN_CLASS=com.auspost.paf.Main

ENTRYPOINT java -cp ${EXEC_JAR}:./lib/* \
            -Xms64m \
            -Xmx128m \
            -XX:+UseZGC \
            -XX:+UseStringDeduplication \
            ${MAIN_CLASS}