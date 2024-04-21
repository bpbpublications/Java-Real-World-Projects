# Chapter 06 - Improving developer experience with Quarkus
Following are the instructions to test the code samples.

You need to run these commands from within the chapter 06 directory:

**To generate a JAR file**
```
$ mvn clean package
```

**To execute the application**
```
$ java -jar target/chapter06-1.0.0-SNAPSHOT-runner.jar  
```

**To create a new account**
```
$ curl -H "Content-Type: application/json" --data '{"email":"user1@davivieira.dev","password":"123"}'  localhost:8080/account
```

**To retrieve an existing account**
```
$ curl localhost:8080/account/user1@davivieira.dev
```