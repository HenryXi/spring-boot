# spring boot handlerexceptionresolver example
There are many ways to handle the exception in Spring Boot. You can click [Spring Boot exception handler example](http://www.henryxi.com/spring-boot-exception-handler-example)
or [Spring Boot global exception example](http://www.henryxi.com/spring-boot-global-exception-example) to see how to handle
exception in controller or handler. I will show you how to handle exception by implementing `HandlerExceptionResolver`.

The dependency is like following.
```xml
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-web</artifactId>
     <version>1.3.3.RELEASE</version>
 </dependency>
```
The structure of this project.
```
└─main
    ├─java
    │  └─com
    │      └─henryxi
    │          └─exception
    │              └─resolver
    │                      AppConfig.java
    │                      CustomException.java
    │                      MyExceptionHandler.java
    │                      SampleController.java
    │
    └─resources
            application.properties
```
AppConfig.java
```java
@EnableAutoConfiguration
@Configuration
@ComponentScan("com.henryxi.exception.resolver")
public class AppConfig {
    @Bean
    HandlerExceptionResolver customExceptionResolver () {
        return new MyExceptionHandler();
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppConfig.class, args);
    }
}
```
CustomException.java
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
MyExceptionHandler.java
```java
public class MyExceptionHandler implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView model = new ModelAndView();
        model.setView(new MappingJackson2JsonView());
        model.addObject("exception", ex.getMessage());
        return model;
    }
}
```
SampleController.java
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

Run the main method of AppConfig. Access "http://localhost:8090/custom-exception" and "http://localhost:8080/exception" to
see the result.

EOF