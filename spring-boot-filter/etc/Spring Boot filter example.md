# Spring Boot filter WebFilter annotation example
There is an embedded tomcat in Spring Boot. We can not edit `web.xml` to add a filter. In this page I will show you how to
add filter in Spring Boot without `web.xml`. I use `WebFilter` annotation to add filter. After adding this annotation you
have to add `ServletComponentScan` annotation to make Spring Boot find this filter.

Here is the complete code.
```java
@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
@ServletComponentScan(basePackages = "com.henryxi.filter")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@WebFilter(filterName = "myFilter",urlPatterns = "/filter")
public class MyFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        PrintWriter writer = servletResponse.getWriter();
        writer.print("this is filter");
        servletResponse.setContentType("text/html;charset=UTF-8");
    }

    public void destroy() {

    }
}

@Controller
public class MyController {


    @ResponseBody
    @RequestMapping("/**")
    public String anyRequest(){
        return "this is available page.";
    }
}
```

Request `localhost:8080/filter` you will get the message "this is filter".

Request any other path you will get the message "this is available page.".

Note that the `WebFilter` annotation is not a spring annotation. It is in `javax.servlet.annotation` package. If you don't
use embed tomcat you can also use this annotation to add filter. Servlet container handles this annotation.
Adding this annotation has the same effect as declaring a filter in the web.xml file.

EOF