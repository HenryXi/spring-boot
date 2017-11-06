# Spring Boot Swagger2 example
[Swagger](https://swagger.io/) can help you generate document for your restful API. This blog will show you how to use it. I assume that
you have known how to use Spring Boot. The structure of example project is like following.
```
├─main
│  ├─java
│  │  └─com
│  │      └─henryxi
│  │          └─swagger2
│  │                  AppConfig.java
│  │                  SimpleController.java
│  │                  User.java
│  │
│  └─resources
└─test
    └─java
```
the content of pom.xml file.
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>1.3.3.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>2.5.0</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>2.5.0</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```
AppConfig.java
```java
@EnableAutoConfiguration
@Configuration
@ComponentScan("com.henryxi.swagger2")
@EnableSwagger2
public class AppConfig extends WebMvcConfigurerAdapter {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppConfig.class, args);
    }
}
```
SimpleController.java
```java
@Controller
@Api(description = "simple controller")
public class SimpleController {

    @RequestMapping(value = "/get-user-info", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "get user info")
    public User userInfo() {
        User user = new User();
        user.setName("henry");
        user.setAge(27);
        return user;
    }

    @RequestMapping(value = "/post-user-info", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "save user info")
    public User userInfo(@RequestBody User user) {
        System.out.println("user info:" + user);
        return user;
    }
}
```
User.java
```java
public class User implements Serializable {
    private String name;
    private int age;
    private String department;

    //getter and setter methods
}
```

Access `http://localhost:8080/swagger-ui.html#/`, you will see the document of restful APIs.

EOF
