# Chapter 05 - Building production-grade systems with Spring Boot
Following are the instructions to test the code samples.

You need to run these commands from within the chapter 05 directory:

**To generate generate a JAR file**
```
$ mvn clean package
```

**To execute the application**
```
$ java -jar target/chapter05-1.0-SNAPSHOT.jar   
```

**To create a new person**
```
$ curl -X POST localhost:8080/person -H 'Content-type:application/json' -d '{"email": "john.doe@davivieira.dev", "name": "John Doe"}'
```

**To retrieve an existing person**
```
curl -s  localhost:8080/person/john.doe@davivieira.dev
```