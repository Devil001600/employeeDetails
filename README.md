# employeeDetails

a dummy spring-boot application that lets the user manipulate a data-base via REST end-points

## About the Application

* this Application provides the user with various REST end-points to interact with (perform CRUD operations) on a DataBase
* the DataBase houses details of hypothetical employees of an Organization
* the full list of employees can be viewed at the h2-console (http://localhost:8081/h2-console)
  
## Using the Application

* the source can be downloaded to a user's local and run
* the user would then invoke the following REST end-points available at (http://localhost:8081//EmployeeDetails/v1)
 
#### /getEmployeeById

* fetches the details of an Employee by their EmployeeID
* the JSON in the src/main/resources/input path can be used as an input

## Built With
the following frameworks/libraries were used to during construction

* [spring-boot](https://spring.io/projects/spring-boot)
* [maven](https://maven.apache.org/)
* [jackson](https://github.com/FasterXML/jackson)
* [lombok](https://projectlombok.org/)
* [mapstruct](https://mapstruct.org/)
* [mybatis](https://mybatis.org/mybatis-3/)
* [H2 DB](https://www.h2database.com/html/main.html)


## Contributors
* [Debanshu P](https://github.com/Devil001600)
