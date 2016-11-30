# Spring Boot interceptor example
In this page I will show you how to use interceptor in Spring Boot. You can use interceptor handling common business
logic. The structure of project is like following.

```
├─main
│  ├─java
│  │  └─com
│  │      └─henryxi
│  │          └─interceptor
│  │                  AppConfig.java
│  │                  SampleController.java
│  │                  TestInterceptor.java
│  │
│  └─resources
│          application.properties
│
└─test
    └─java
```

**The content of pom file**
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
@ComponentScan("com.henryxi.interceptor")
public class AppConfig extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/intercept");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppConfig.class, args);
    }
}
```

**TestInterceptor.java**
```java
@Component
public class TestInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("this is interceptor, preHandle method");
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        System.out.println("this is interceptor, postHandle method");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("this is interceptor, afterCompletion method");
    }
}
```
**SampleController.java**
```java
@Controller
public class SampleController extends SpringBootServletInitializer {

    @RequestMapping("/intercept")
    @ResponseBody
    public String intercept() {
        System.out.println("this is controller, request path is intercept");
        return "hello spring boot interceptor, request path is intercept";
    }

    @RequestMapping("/not-intercept")
    @ResponseBody
    public String notIntercept() {
        System.out.println("this is controller, request path is not intercept");
        return "hello spring boot interceptor, request path is not intercept";
    }
}
```

Run the main method in `AppConfig` class and access "localhost:8080/intercept". The log like following.
```
this is interceptor, preHandle method
this is controller, request path is intercept
this is interceptor, postHandle method
this is interceptor, afterCompletion method
```
Access "localhost:8090/not-intercept". The log like following.
```
this is controller, request path is not intercept
```
You can add your own business logic in interceptor.