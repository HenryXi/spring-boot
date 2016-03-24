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

dependencies in pom file
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>1.3.2.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>4.2.4.RELEASE</version>
</dependency>
<dependency>
    <groupId>postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>9.1-901.jdbc4</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
    <version>1.3.2.RELEASE</version>
</dependency>
```
application.properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/demo
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
```
You can define your own properties in ``application.properties`` or in other properties file and get the value to init ``DataSource``.
(Do not know how to read value in **Spring Boot**? Click [here](http://www.henryxi.com/spring-boot-configurationproperties-example). Want know how to read
value from properties in **Spring**? Click [here](http://www.henryxi.com/read-values-from-properties-file-in-spring))

SimpleController
```
@RestController
@EnableAutoConfiguration
public class SimpleController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        jdbcTemplate.update("INSERT INTO tb_user (username, comment) VALUES (?,?)", new Object[]{"username1", "comment1"});
        return "insert completes!";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete() {
        jdbcTemplate.update("DELETE FROM tb_user where id>?", 100);
        return "delete completes!";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update() {
        jdbcTemplate.update("UPDATE tb_user SET username=?, comment=? ", new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, "update_username");
                ps.setString(2, "update_comment");
            }
        });
        return "update completes!";
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query() {
        String name = jdbcTemplate.queryForObject("SELECT username FROM tb_user where id = ?", String.class, 100);
        return "username " + name;
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
```
Run the main method in ``SimpleController`` and access different address you can view the run result.
You might ask "how do I know the default key in ``application.properties``". You can see [here](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html).
This is ``convention over configuration``. It is means less code do same thing without xml config file. 
