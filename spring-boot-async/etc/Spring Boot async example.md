# Spring Boot async example
Generally we call methods in a synchronous manner. In some cases we need to call methods asynchronously. `@Async` can help 
us call methods asynchronously when using Spring Boot. Before using `@Async` we need to add an `@EnableAsync` on the startup 
class. Here's a simple example that shows how to use `@Async` to implement asynchronous method calls. 
```java
@Controller
@EnableAsync
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.henryxi.async")
public class SimpleController {

    @Autowired
    private MyService myService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(HttpServletRequest request) {
        myService.hello();
        return "finish";
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
```
We inject `MyService` in controller. In this service there is an asynchronous method with `@Async` annotation.
```java
@Service
public class MyService {

    @Async
    public void hello() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish!!");
    }
}
```
In this asynchronous method we let the program sleep for 3 seconds. So we can more clearly see how the asynchronous method 
works. 

Start this app and visit this URL(localhost:8080/hello). The requested page is returned immediately. After 3 seconds, the 
log in the asynchronous method is output in the console in the background.

For a method that adds an async annotation, it is not called synchronously when it is called. In the spring framework there 
is a specific thread pool responsible for calling asynchronous methods and tasks. We can create `ExecutorConfig` class to defined
thread pool size. 
```java
@Configuration
@EnableAsync
public class ExecutorConfig {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

    @Bean(name = "threadPoolExecutor")
    public Executor asyncServiceExecutor() {
        logger.info("start asyncServiceExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(99999);
        executor.setThreadNamePrefix("async-service-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
```
In order to use this thread pool we need to change `@Asyc` to `@Async("threadPoolExecutor")`. Rerun this app
and access the URL. The output is like following.
```
......
2019-02-02 11:53:03.685  INFO 1488 --- [           main] com.henryxi.async.ExecutorConfig         : start asyncServiceExecutor
2019-02-02 11:53:03.687  INFO 1488 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 
2019-02-02 11:53:03.691  INFO 1488 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService  'threadPoolExecutor'
2019-02-02 11:53:03.796  INFO 1488 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@67f639d3: startup date [Sat Feb 02 11:53:02 CST 2019]; root of context hierarchy
2019-02-02 11:53:03.837  INFO 1488 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/hello],methods=[GET]}" onto public java.lang.String com.henryxi.async.SimpleController.hello(javax.servlet.http.HttpServletRequest)
2019-02-02 11:53:03.839  INFO 1488 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2019-02-02 11:53:03.839  INFO 1488 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2019-02-02 11:53:03.866  INFO 1488 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2019-02-02 11:53:03.866  INFO 1488 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2019-02-02 11:53:03.894  INFO 1488 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2019-02-02 11:53:04.014  INFO 1488 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2019-02-02 11:53:04.060  INFO 1488 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2019-02-02 11:53:04.063  INFO 1488 --- [           main] com.henryxi.async.SimpleController       : Started SimpleController in 1.928 seconds (JVM running for 2.502)
2019-02-02 11:53:05.382  INFO 1488 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring FrameworkServlet 'dispatcherServlet'
2019-02-02 11:53:05.383  INFO 1488 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2019-02-02 11:53:05.394  INFO 1488 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 11 ms
async-service-1:finish!!
```
EOF