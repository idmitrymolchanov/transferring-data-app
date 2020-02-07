# psychotestJNDI
## Information

The application is designed to transfer data from one database ("source") to another ("target"). 
One specific table is used. The application works autonomously, transferring over a certain period of time. 
To do this, the date of the last record in the database is checked, then, 
if there are records made later in the "target", they are transferred (copied) from "source". 
The process starts at intervals of 5 days.

On github: [psychotestJNDI](https://github.com/idmitrymolchanov/psychotestJNDI).

## Databases
The project has two profiles ("local" and "prod"). 
The application contains a connection to two databases. Therefore, in the properties we see two data sources.
The local profile contains mysql connection with the following parameters: 

* target\
spring.target.jdbcUrl=jdbc:mysql://localhost:3306/testdb?useUnicode=true&serverTimezone=UTC\
spring.target.username=root\
spring.target.password=root\
spring.target.driverClassName=com.mysql.cj.jdbc.Driver

* source\
spring.source.jdbcUrl=jdbc:mysql://localhost:3306/testdb2?useUnicode=true&serverTimezone=UTC\
spring.source.username=root\
spring.source.password=root\
spring.source.driverClassName=com.mysql.cj.jdbc.Driver

Connection in the "prod" profile is carried out according to JNDI. 
We have only reserved headers for databases in properties.

* psychotest.jndi.datasource.one=java:comp/env/jdbc/target
* psychotest.jndi.datasource.two=java:comp/env/jdbc/source 

The names of the tables are indicated in the project properties.

## Testing
For testing database connections and to test some methods using them, the "mysql-container" was used:

    \<dependency>\
        \<groupId>org.testcontainers\</groupId>\
        \<artifactId>mysql\</artifactId>\
        \<version>1.12.2\</version>\
        \<scope>test\</scope>\
    \</dependency>