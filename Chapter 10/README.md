# Chapter 10 - Implementing application metrics with Micrometer
Following are the instructions to test the code samples.

You need to run these commands from within the chapter 10 directory:

**To generate a JAR file**
```
$ mvn clean package
```

**To execute the application**
```
$ java --enable-preview -jar target/chapter10-1.0-SNAPSHOT.jar   
```

**To upload a file**

After uploading the file, it returns a file ID you can use to download the file.
```
$ curl --form file='@random.txt' localhost:8080/file
```

**To download file**

Open the following URL in your web browser with the file ID returned from executing the previous command.
```
http://localhost:8080/file/{fileId}
```

**To check application metrics**

Open the following URL in your web browser to check the metrics the application produces.
```
http://localhost:8080/actuator/prometheus
```
