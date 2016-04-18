# Spring Boot upload file with form example
For most project uploading file is a commonly-used function. In this page I will show you how to upload
a file in Spring Boot. It works if you just use SpringMVC in your project. I use jsp as page, so if you
don't kow how to use jsp in Spring Boot you can click [here](http://www.henryxi.com/spring-boot-jsp-examples) for more detail.

**Project structure**
```
├─main
│  ├─java
│  │  └─com
│  │      └─henry
│  │          └─upload
│  │                  SampleUploadController.java
│  │
│  └─resources
│      │  application.properties
│      │
│      └─META-INF
│          └─resources
│              └─WEB-INF
│                  └─jsp
│                          upload.jsp
```

**dependencies in pom file**
```
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>${spring.boot.version}</version>
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
</dependencies>
```

**java code**
```
@Controller
@EnableAutoConfiguration
public class SampleUploadController extends SpringBootServletInitializer {

    @RequestMapping("/")
    public String index() {
        return "upload";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            String content = new String(file.getBytes());
            return "file name:" + file.getOriginalFilename() + "<br> content:" + content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "file name:" + file.getOriginalFilename() +"<br> read file content error.";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleUploadController.class, args);
    }
}
```

**application properties file**
```
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
multipart.maxFileSize: 15MB
multipart.maxRequestSize: 15MB
```

``multipart.maxFileSize`` specifies the maximum size permitted for uploaded files. The default is 1Mb.
``multipart.maxRequestSize`` specifies the maximum size allowed for multipart/form-data requests. The default is 10Mb

Run the main method and visit http://localhost:8080/, click upload button choose the file you want upload then 
click submit. You will get the file name and content.