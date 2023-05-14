# DevelopmentBooks

There is a series of books about software development that have been read by a lot of developers who want to improve their development skills. Let’s say an editor, in a gesture of immense generosity to mankind (and to increase sales as well), is willing to set up a pricing model where you can get discounts when you buy these books. The available books are :

1. Clean Code (Robert Martin, 2008)
2. The Clean Coder (Robert Martin, 2011)
3. Clean Architecture (Robert Martin, 2017)
4. Test Driven Development by Example (Kent Beck, 2003)
5. Working Effectively With Legacy Code (Michael C. Feathers, 2004)


# Rules

One copy of the five books costs 50 EUR.

- If, however, you buy two different books from the series, you get a 5% discount on those two books.
- If you buy 3 different books, you get a 10% discount.
- If you buy 4 different books, you get a 20% discount.
- If you go for the whole hog, and buy all 5, you get a huge 25% discount.
- Note that if you buy, say, 4 books, of which 3 are different titles, you get a 10% discount on the 3 that form part of a set, but the 4th book still costs 50 EUR.

# For Example
If the shopping basket contains the below books.

2 copies of the “Clean Code” book
2 copies of the “Clean Coder” book
2 copies of the “Clean Architecture” book
1 copy of the “Test Driven Development by Example” book
1 copy of the “Working effectively with Legacy Code” book
We can avail the discounts for above shopping basket containing 8 books by grouping [5,3] or [4,4] or [3,3,2], etc. Output should be 320 EUR as the best price by applying [4,4] as below.

(4 * 50 EUR) - 20% [first book, second book, third book, fourth book]
(4 * 50 EUR) - 20% [first book, second book, third book, fifth book]
= (160 EUR + 160 EUR) => 320 EUR
# Purpose

Develop a java application in TDD fashion (**Test-Driven-Development**) for shopping cart books with discount and get the best calculated price based on the available discounts with respect to unique number of books quantity

# Tools used for developing this application

- **Backend** : Java 8 & Springboot 2.7

- **Build tool**: Maven 4.x

## How to build the application


* Clone this repository
```
https://github.com/2023-DEV1-074/DevelopmentBooks.git
```
*  ```mvn clean install``` for building this project

## How to run the application

```java -jar target\developmentbooks-0.0.1-SNAPSHOT.jar```



* By default the application will start on **port 8080**. 
  you can update the **server.port** in **application.properties** for different port
* Once successfully built, you can run the service by below commands:

```java -jar target\developmentbooks-1.0.0-SNAPSHOT.jar --server.port=<port number>```

Once the application runs you should see below message in console log

```
2023-05-13 04:16:58.167  INFO 17444 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2023-05-13 04:16:58.186  INFO 17444 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-05-13 04:16:58.186  INFO 17444 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.65]
2023-05-13 04:16:58.442  INFO 17444 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-05-13 04:16:58.442  INFO 17444 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2497 ms
2023-05-13 04:16:59.576  INFO 17444 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-05-13 04:16:59.907  INFO 17444 --- [           main] c.b.f.d.DevelopmentbooksApplication      : Started DevelopmentbooksApplication in 4.918 seconds (JVM running for 5.709)

```

* Once the application started successfully, please use the below url in the browser:

  `http://localhost:<port number>`

# Request & Response

`Launch this Swagger URL :
**http://localhost:8080/v2/api-docs**
Swagger is available to see the request response model with api documentation


`
Post Man Request json is available for both rest apis , **resource/PostMan-DevelopmentBookApi-Request-Json/DevelopmentBooks-Apis.postman_collection.json** 
1.GetAllBooks
2.CalculateBookDiscountPrice`

# Test reports
Once after successful build of mvn clean install,
under the target folder:

**Jacoco code coverage report ** will be available in target\site\jacoco folder.  index.html to see the complete report

**Pitest coverage report: ** will be available in target\pit-reports\<TIMESTAMP_FOLDER>  index.html to see the complete report

		