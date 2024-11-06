# Chapter 11 - Creating useful dashboards with Prometheus and Grafana
Following are the instructions to test the code samples.

You need to run these commands from within the chapter 11 directory:

**To generate a JAR file**
```
$ mvn clean package
```

**To execute the application**
```
$ java -jar target/chapter11-1.0-SNAPSHOT.jar   
```

**To bring up Alertmanager, Prometheus, and Grafana**
```
$ docker-compose up -d 
```

**To upload a file**

After uploading the file, it returns a file ID you can use to download the file.
```
$ curl --form file='@random.txt' localhost:8080/file
```

**To download a file**

Open the following URL in your web browser with the file ID returned from executing the previous command.
```
http://localhost:8080/file/{fileId}
```

**Grafana**

Use <b>admin</b> as the default user and password:
```
http://localhost:3000
```
You can import the dashboard file `monitoring/file-storage-grafana-dashboard.json`. Upload and download some files to generate metris.


**Prometheus**

Use the URL below to open the Prometheus UI on the browser:
```
http://localhost:9090
```

**Alertmanager**

Open the following URL in your web browser to check alerts from Alertmanager.
```
http://localhost:9093
```
Keep uploading files every 10 seconds for 1 minute to trigger a new alert.