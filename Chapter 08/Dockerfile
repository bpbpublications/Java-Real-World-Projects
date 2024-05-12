FROM openjdk:21-slim
ENV JAR_FILE sample-spring-boot-app.jar
ENV JAR_HOME /usr/apps
COPY target/$JAR_FILE $JAR_HOME/
WORKDIR $JAR_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $JAR_FILE"]
EXPOSE 8080