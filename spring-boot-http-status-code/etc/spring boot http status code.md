# Spring Boot http status code
It is easy to custom your own 404 or 500 page in SpringMVC(I use Spring Boot to make an example). It is not friendly
for browser and search engine to return an error page without http status code. There are several ways to return page
 with http status code. I will show you the easiest way in this blog.

**project structure**
```
├─main
│  ├─java
│  │  └─com
│  │      └─henryxi
│  │          └─http
│  │              └─status
│  │                  └─code
│  │                          SimpleController.java
│  │
│  └─resources
│      │  application.properties
│      │
│      └─META-INF
│          └─resources
│              └─WEB-INF
│                  └─jsp
│                          404.jsp
│
└─test
    └─java
```

**pom**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
    <version>1.2</version>
</dependency>
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
    <version>8.0.28</version>
</dependency>
```

**application.properties**
```ini
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
server.port=8090
```
**SimpleController.java**
```java
@Controller
@EnableAutoConfiguration
public class SimpleController {

    @RequestMapping(value = "/http-status-404", method = RequestMethod.GET)
    public String httpStatus404(HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return "404";
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
```

Run main method and access "http://localhost:8090/http-status-404" you will get a 404 page with "404" http code.