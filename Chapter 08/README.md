# Chapter 08 - Running your application in cloud-native environments
Following are the instructions to test the code samples.

You need to run these commands from within the chapter 05 directory:

**To generate a JAR file**
```
$ mvn clean package
```

**To create a Docker image**
```
$ docker build . -t {YOUR_DOCKER_HUB_ACCOUNT}/sample-spring-boot-app:1.0   
```

**To push a Docker image**
```
$ docker push {YOUR_DOCKER_HUB_ACCOUNT}/sample-spring-boot-app:1.0  
```

**To install Kubernetes objects**
```
$ kubectl apply -f k8s/
```

**Getting the Minikube IP address**
```
$ minikube ip
```

**To create a new person**
```
$ curl -X POST {MIKIKUBE_IP_ADDRESS}:30080/person -H 'Content-type:application/json' -d '{"email": "john.doe@davivieira.dev", "name": "John Doe"}'
```

**To retrieve an existing person**
```
curl -s {MIKIKUBE_IP_ADDRESS}:30080/person/john.doe@davivieira.dev
```