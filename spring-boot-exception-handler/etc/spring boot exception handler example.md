# Spring Boot exception handler example
In this page I will show you how to use `ExceptionHandler` annotation handle exceptions. The structure of project is 
like following.
```
main
  │  ├─java
  │  │  └─com
  │  │      └─henryxi
  │  │          └─exception
  │  │              └─handler
  │  │                  └─controller
  │  │                          AppConfig.java
  │  │                          CustomException.java
  │  │                          SampleController.java
  │  │
  │  └─resources
  │          application.properties
  │
  └─test
      └─java
```
The pom file is like following.
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>1.3.3.RELEASE</version>
</dependency>
```
**AppConfig.java**
```java
@EnableAutoConfiguration
@Configuration
@ComponentScan("com.henryxi.exception.handler")
public class AppConfig extends WebMvcConfigurerAdapter {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppConfig.class, args);
    }
}
```
**CustomException.java**
```java
public class CustomException extends Exception{

    private static final long serialVersionUID = 2490632169435714046L;

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }
}
```
**SampleController.java**
```java
@Controller
public class SampleController extends SpringBootServletInitializer {

    @RequestMapping("/exception")
    public String exception() throws Exception {
        throw new Exception("this is exception");
    }

    @RequestMapping("/custom-exception")
    public String customException() throws Exception {
        throw new CustomException("this is custom exception");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public String handleCustomException(CustomException e) {
        return e.getMessage();
    }
}
```
You can define which exception will be handled by using `ExceptionHandler`. Access `http://localhost:8090/exception` 
the method `exception` will throw `Exception` and `handleException` will handle this exception.
Access `http://localhost:8090/exception`
```
this is exception
```
Access `http://localhost:8090/custom-exception`
```
this is custom exception
```