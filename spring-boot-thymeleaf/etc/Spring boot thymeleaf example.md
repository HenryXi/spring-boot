# Spring Boot Thymeleaf example
Thymeleaf is a Java template engine. In this page I will show you how to use it in Spring Boot. You can use it
to display the page. The structure of project is like following.
```
├─main
│  ├─java
│  │  └─com
│  │      └─henryxi
│  │          └─thymeleaf
│  │                  SimpleController.java
│  │
│  └─resources
│      │  application.properties
│      │
│      └─templates
│              thymeleaf.html
│
└─test
    └─java
```
**pom.xml**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
    <spring.boot.version>1.3.3.RELEASE</spring.boot.version>
</dependency>
```
**SimpleController.java**
```java
@SpringBootApplication
@Controller
public class SimpleController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "thymeleaf";
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
```
**thymeleaf.html**
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Thymeleaf example</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<!--/*@thymesVar id="name" type="java.lang.String"*/-->
<p th:text="'Hello, ' + ${name} + '!'"></p>
</body>
</html>
```
**application.properties**
```ini
server.port=8090
#thymeleaf start
spring.thymeleaf.mode=HTML5
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.content-type=text/html
#close cache to display page in real time
spring.thymeleaf.cache=false
#thymeleaf end
```
Access http://localhost:8090/hello?name=henry, the output is like following.
```
Hello, henry!
```

EOF