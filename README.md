# employeeDetails

a dummy spring-boot application that lets the user fetch details from a hypothetical employee dataBase.
the end-points are exposed via **spring-cloud** functionalities of the Spring framework; instead of REST end-points.
**mybatis** framework has been used for Object-Relational-Mapping.

## About the Application

* this Application provides the user with two end-points to query a dataBase.
* the DataBase houses details of employees of a hypothetical Organization.
* the full list of employees can be viewed at the h2-console (http://localhost:8081/h2-console).
  
## Using the Application

* the source can be downloaded to a user's local and run
* the user would then invoke the following REST end-points available at (http://localhost:8081//EmployeeDetails/v1)
 
#### GET/getEmployees

* fetches details of all the Employees

#### POST/getEmployeeById

* fetches the details of an Employee by their EmployeeID
* the JSON in the src/main/resources/input path can be used as an input

## Managing the Application

* once the server is up, the management end-points will be available at (http://localhost:8082//EmployeeDetails/v1) 

## Built With
the following frameworks/libraries were used in construction of the application

* [java SE 15](https://www.oracle.com/java/technologies/javase/15-relnotes.html)
* [maven 3.8.2](https://maven.apache.org/)
* [spring-boot](https://spring.io/projects/spring-boot)
* [jackson](https://github.com/FasterXML/jackson)
* [lombok](https://projectlombok.org/)
* [mapstruct](https://mapstruct.org/)
* [mybatis](https://mybatis.org/mybatis-3/)
* [H2 DB](https://www.h2database.com/html/main.html)
* [commons-lang](https://commons.apache.org/proper/commons-lang/)

* [JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
* [equalsVerifier](https://jqno.nl/equalsverifier/)
* [to-string-verifier](https://github.com/jparams/to-string-verifier)
* [maven-surefire](https://maven.apache.org/surefire/maven-surefire-plugin/)
* [maven-failsafe](https://maven.apache.org/surefire/maven-failsafe-plugin/)
* [jacoco](https://www.eclemma.org/jacoco/)


## Contributors
* [Debanshu P](https://github.com/Devil001600)
