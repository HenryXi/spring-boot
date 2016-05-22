# Build a restful spring project in 1 minute.
If you want build a spring project you have to download 
servlet container(tomcat, jetty, glassfish etc.) write some 
spring config file. But now you can throw them away! In this 
tutorial I will show you how to build spring project in one 
minute with "spring boot".

1. create a maven project add dependencies below in your pom.xml file.
    ```xml
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
          <version>1.3.2.RELEASE</version>
        </dependency>
        
    ```
2. create a controller class.
    ```java
        @Controller
        @EnableAutoConfiguration
        public class SimpleController {
            @RequestMapping(value = "/hello", method = RequestMethod.GET)
            @ResponseBody
            public String hello() {
                return "Hello Spring Boot";
            }
    
            public static void main(String[] args) {
                SpringApplication.run(SimpleController.class, args);
            }
        }
    ```
3. run main method in this controller then access this link
       <http://localhost:8080/hello/> , you can see like below.
    ```
    Hello Spring Boot
    
    ```
As you see "spring boot" help us build restful project quickly.
There is no servlet container, no configuration files! If you want 
just test something or build a sample project you can use "spring boot". 
Easy and quick.
