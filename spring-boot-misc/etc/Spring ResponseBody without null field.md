# Spring ResponseBody without null field
The annotation `@ResponseBody` can help you convert object to json. Sometimes if the field of object is null the json returned
like following.
```json
{
  "name": "henry",
  "age": 27,
  "department": null
}
``` 
There are two ways to remove the null field in the returned json.
1. Add `@JsonInclude(JsonInclude.Include.NON_NULL)` on the object you returned.

2. Add `spring.jackson.serialization-inclusion` in application.properties file when you use Spring Boot.

The whole example is like following.

**structure of project**
```
├─main
│  ├─java
│  │  └─com
│  │      └─henryxi
│  │          └─json
│  │              └─nullfield
│  │                      SimpleController.java
│  │                      User.java
│  │                      UserIgnoreNullField.java
│  │
│  └─resources
│          application.properties
```

**pom.xml**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>1.3.3.RELEASE</version>
</dependency>
```
**SimpleController**
```java
@Controller
@EnableAutoConfiguration
public class SimpleController {

    @RequestMapping("/user-info")
    @ResponseBody
    public User userInfo() {
        User user = new User();
        user.setName("henry");
        user.setAge(27);
        return user;
    }

    @RequestMapping("/user-info-without-null-field")
    @ResponseBody
    public UserIgnoreNullField userInfoWithoutNullField() {
        UserIgnoreNullField user = new UserIgnoreNullField();
        user.setName("henry");
        user.setAge(27);
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
```
**UserIgnoreNullField**
```java
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserIgnoreNullField implements Serializable {
    private String name;
    private int age;
    private String department;

    //getter and setter methods
}
```
**User**
```java
public class UserIgnoreNullField implements Serializable {
    private String name;
    private int age;
    private String department;

    //getter and setter methods
}
```
**application.properties**
```properties
#spring.jackson.serialization-inclusion=NON_NULL
```

EOF