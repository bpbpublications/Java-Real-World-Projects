# Chapter 04 - Preventing unexpected behaviors with unit and integration tests
Following are the instructions to test the code samples.

You need to run these commands from within the chapter 04 directory:

**To run unit tests**
```
$ mvn test
```

**To run integration tests**
```
$ mvn integration-test
```

**To generate a JAR file**
```
$ mvn clean package
```

**To execute the application**
```
$ docker-compose up -d
$ java -jar target/chapter04-1.0-SNAPSHOT-jar-with-dependencies.jar  

```