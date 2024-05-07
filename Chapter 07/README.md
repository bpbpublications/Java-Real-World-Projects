# Chapter 07 - Building enterprise applications with Jakarta EE and MicroProfile
Following are the instructions to test the code samples.

You need to run these commands from within the chapter 06 directory:

**To run the application**
```
$ mvn clean package cargo:run
```

**OpenAPI UI URL**
```
http://localhost:8080/license-management/api/openapi-ui/
```

**Health Check URL**
```
http://casanova:8080/health
```

**To create a new license**
```
$  curl -v -H "Content-Type: application/json" --data '{"name":"Premium License", "startDate": "2024-01-10", "endDate": "2025-01-18"}'  localhost:8080/license-management/api/license
```

**To get licenses**
```
$ curl -v localhost:8080/license-management/api/license | jq
```