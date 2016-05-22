# Spring Boot JSP examples

Before learning how to use JSP in Spring Boot you need know there are several limitations. You can click [here](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-jsp-limitations)
to learn more. In order to show the page you need add ``tomcat-embed-jasper`` dependency in pom file. Spring Boot
uses an embedded servlet container which is not rendering JSP as default.

**Project Structure**
```
└─main
    ├─java
    │  └─com
    │      └─henry
    │          └─jsp
    │                  SampleWebJspController.java
    │
    └─resources
        │  application.properties
        │
        └─META-INF
            └─resources
                └─WEB-INF
                    └─jsp
                            welcome.jsp
```

**pom file**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>${spring.boot.version}</version>
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

**java code**
```java
@Controller
@EnableAutoConfiguration
public class SampleWebJspController extends SpringBootServletInitializer {

    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        return "welcome";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleWebJspController.class, args);
    }
}
```

**welcome.jsp**
```html
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	${message}
	<div>${time}</div>
</body>
</html>
```

Start the project with main method and access localhost:8080 you can see the page.

**Notice**

At first I make a directory in src/main like ``webapp/WEB-INF/jsp`` and put all jsp in it. When I try to access root path
with main method I got 404 page. I found there is no jsp file in jar. I changed the pom file like following
```xml
<packaging>war</packaging>
<dependencies>
    <!-- dependency here -->
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>${spring.boot.version}</version>
        </plugin>
    </plugins>
</build>
```

Add ``spring-boot-maven-plugin`` in pom file, run maven command ``mvn clean spring-boot:run`` and access 'localhost:8080' it works.
It means you have to make servlet container found the jsp files. Use maven plugin to package jsp in war or put jsp in META-INF 
folder.
