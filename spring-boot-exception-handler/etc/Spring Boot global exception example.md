# Spring Boot global exception example
I have shown you how to handle exceptions in controller. Click [here](http://www.henryxi.com/spring-boot-exception-handler-example) 
for more detail. You can use `@ControllerAdvice` to define a global handler. This handler is for handling global exceptions. 
The structure of this example project is like following.
```
└─main
    ├─java
    │  └─com
    │      └─henryxi
    │          └─exception
    │              └─handler
    │                  └─global
    │                          AppConfig.java
    │                          CustomException.java
    │                          GlobalExceptionHandler.java
    │                          SampleController.java
    │
    └─resources
            application.properties
```
**pom.xml**
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
**GlobalExceptionHandler.java**
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        return "this is from exception handler, message:" + e.getMessage();
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public String handleException(CustomException e) {
        return "this is from exception handler, message:" + e.getMessage();
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
}
```
**application.properties**
```ini
server.port = 8090
```
Access `http://localhost:8090/exception`
```
this is from exception handler, message:this is exception
```

Access `http://localhost:8090/custom-exception`
```
this is from exception handler, message:this is custom exception
```

EOF