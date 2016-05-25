# Display PDF in Spring example
In this tutorial I will show you how to display and download pdf file in Spring(Spring MVC). For quick
beginning we use Spring Boot, and for displaying pdf we use jsp as view.(How to use jsp in Spring Boot, click
[Spring Boot JSP examples](http://www.henryxi.com/spring-boot-jsp-examples)). 

##### Project structure
```
├─main
│  ├─java
│  │  └─com
│  │      └─henryxi
│  │          └─pdf
│  │                  DisplayDownloadPDFController.java
│  │
│  └─resources
│      │  application.properties
│      │
│      └─META-INF
│          └─resources
│              │  Accepted.pdf
│              │
│              └─WEB-INF
│                  └─jsp
│                          index.jsp
│
└─test
    └─java
```
##### pom file
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
##### Java code
```java
@Controller
@EnableAutoConfiguration
public class DisplayDownloadPDFController{

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response) {
        try {
            DefaultResourceLoader loader = new DefaultResourceLoader();
            InputStream is = loader.getResource("classpath:META-INF/resources/Accepted.pdf").getInputStream();
            IOUtils.copy(is, response.getOutputStream());
            response.setHeader("Content-Disposition", "attachment; filename=Accepted.pdf");
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DisplayDownloadPDFController.class, args);
    }
}
```
##### application.properties
```ini
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
server.port = 8090
```
##### index.jsp
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
<embed src="/Accepted.pdf" width="500" height="375" type='application/pdf'>
<a href="/download">download</a>
</body>
</html>
```

Run main method in ``DisplayDownloadPDFController`` and access http://localhost:8090/. You can see the 
pdf content and download link. This example works both in chrome and firefox.