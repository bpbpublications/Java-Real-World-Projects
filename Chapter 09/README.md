# Chapter 09 - Learning monitoring and observability fundamentals
Following are the instructions to test the code samples.

You need to run these commands from within the chapter 09 directory:

**To generate the JAR file for the report and inventory services**
```
$ mvn clean package
```

**To bring up the environment**
```
$ docker-compose up -d --build 
```

**To send a sample request**
```
$ curl localhost:9090/report/generate
```

**Jaeger URL**
```
http://localhost:16686
```

**Kibana URL**
```
http://localhost:5601
```