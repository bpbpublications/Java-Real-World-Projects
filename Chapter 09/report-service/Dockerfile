FROM openjdk:21-slim
ENV JAR_FILE report-service-1.0-SNAPSHOT.jar
ENV JAR_HOME /usr/apps
COPY target/$JAR_FILE $JAR_HOME/
WORKDIR $JAR_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $JAR_FILE"]
EXPOSE 9090