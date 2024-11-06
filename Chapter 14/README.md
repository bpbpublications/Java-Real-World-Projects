# Chapter 14 - Using hexagonal architecture to build change-tolerable applications
Following are the instructions to test the code samples.

You need to run these commands from within the chapter 14 directory:

**To generate a JAR file**
```
$ mvn clean package
```

**To create a note using the CLI adapter**
```
$ java -jar -Dspring.main.web-application-type=NONE target/chapter14-1.0-SNAPSHOT.jar createNote
```

**To show notes using the CLI adapter**
```
$ java -jar -Dspring.main.web-application-type=NONE target/chapter14-1.0-SNAPSHOT.jar showNotes
```

**To start the application using the REST adapter**
```
$ java -jar target/chapter14-1.0-SNAPSHOT.jar
```

**To create a note using the REST adapter**
```
$ curl -X POST localhost:8080/note -H 'Content-type:application/json' -d '{"title": "My goal for next week", "content": "I want to create a Java project to practice my Java skills"}'
```

**To show notes using the REST adapter**
```
$ curl localhost:8080/notes
```