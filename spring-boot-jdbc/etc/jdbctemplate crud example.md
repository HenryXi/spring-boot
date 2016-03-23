# JdbcTemplate examples in Spring Boot
JdbcTemplate is the central class in the JDBC core package. You can use it manipulate database without
handling common errors. We define JdbcTemplate in Spring config file in the past and before using JdbcTemplate
you have to add DataSource config. In this tutorial we will show you how to use JdbcTemplate in Spring Boot.
You don't write a single line of XML. No web.xml file either.

**init database**
```
CREATE TABLE public.tb_user
(
  id SERIAL PRIMARY KEY NOT NULL,
  username VARCHAR(20) NOT NULL,
  comment VARCHAR(500)
)
```

**project structure**

There is not xml file in this project(no spring config, no web.xml)
```
├─main                                                                                                                                                                                                             
│  ├─java                                                                                                                                                                                                         
│  │  └─com                                                                                                                                                                                                      
│  │      └─henry                                                                                                                                                                                                
│  │          └─jdbc                                                                                                                                                                                             
│  │                  SimpleController.java                                                                                                                                                                        
│  │                                                                                                                                                                                                               
│  └─resources                                                                                                                                                                                                    
│          application.properties                                                                                                                                                                                   
│                                                                                                                                                                                                                   
└─test                                                                                                                                                                                                             
    └─java            
```

**code in project**

application.properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/demo
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
```
SimpleController
```

```
``convention over configuration``