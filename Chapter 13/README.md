# Chapter 13 - Fast application development with layered architecture
Following are the instructions to test the code samples.

You need to run the following command from within the chapter 13 directory:

**To generate a JAR file**
```
$ mvn clean package
```

**To execute the application**
```
$ java -jar target/chapter13-1.0-SNAPSHOT.jar   
```

**Create a new account**
```
$ curl -XPOST  localhost:8080/account -H 'Content-type:application/json' -d '{"email": "john.doe@davivieira.dev", "password": "pass"}'
```

**Create categories**
```
$ curl -XPOST  localhost:8080/category -H 'Content-type:application/json' -d '{"accountId": "{ACCOUNT_ID}", "name": "Grocery"}'
$ curl -XPOST  localhost:8080/category -H 'Content-type:application/json' -d '{"accountId": "{ACCOUNT_ID}", "name": "Fitness"}'
```

**Create a new transaction**
```
$ curl -XPOST  localhost:8080/transaction -H 'Content-type:application/json' -d '{"accountId": "{ACCOUNT_ID}", "name": "Super Market", "amount": 16.20, "type": "DEBIT"}'
```

**Get account details**
```
$ curl localhost:8080/account/john.doe@davivieira.dev
```

**Add a transaction to a category**
```
$ curl -XPUT localhost:8080/{CATEGORY_ID}/{TRANSACTION_ID}
```

**Remove a transaction from a category**
```
$ curl -XDELETE localhost:8080/{CATEGORY_ID}/{TRANSACTION_ID}
```