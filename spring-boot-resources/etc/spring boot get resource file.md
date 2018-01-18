# Spring Boot get resource file example
I have written the blog [Read values from properties file in Spring](http://www.henryxi.com/read-values-from-properties-file-in-spring) to 
show how to get values from properties file. In this blog I will show you how to get resource file (not only properties file)
in Spring. As using maven build the project all resource file are put in resources folder. 

**project structure**
```
├─main
│  ├─java
│  │  └─com
│  │      └─henryxi
│  │          └─resources
│  │                  ResourcesController.java
│  │
│  └─resources
│          application.properties
│          test.txt
│
└─test
    └─java
```

**pom.xml**
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>2.4</version>
    </dependency>
</dependencies>
```

**resourcesController.java**
```java
@RestController
@EnableAutoConfiguration
public class ResourcesController {
    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping(value = "/get-resources", method = RequestMethod.GET)
    public String getResources() throws IOException {
        String content = IOUtils.toString(resourceLoader.getResource("classpath:test.txt").getInputStream());
        return "the content of resources:" + content;
    }

    public static void main(String[] args) {
        SpringApplication.run(ResourcesController.class, args);
    }
}
```
Run this class and access http://localhost:8080/get-resources the output like following
```
the content of resources:this is a test file
```

**About `classpath`**

In Maven project all resource will be copied to classes folder (if you not specify the resources folder). `classpath`
in Tomcat contains "<project_path>/classes", "<project_path>/lib" and other path in Tomcat. After injecting `ResourceLoader` into controller
use `resourceLoader.getResource("classpath:test.txt")` to find the file "test.txt" in `classpath`.

