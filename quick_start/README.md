# Build a restful spring project in 1 minute.
Before spring boot came out if you want build a spring project
you have to download tomcat and write some spring config file.
But now you can put them away! In this tutorial I will show you
how to build spring project in one minute .

1. create a maven project add below content in your pom.xml file
```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <version>1.3.2.RELEASE</version>
  </dependency>
```
2. create a controller class like below
```xml
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
3. run main method in this controller and access this link
<http://localhost:8080/hello/> , you can see like below:
```
Hello Spring Boot
```

As you see spring boot help us build restful project quickly,
no more tomcat no more configuration files! Next
time if you want just test something or build a sample project
you can use spring boot.