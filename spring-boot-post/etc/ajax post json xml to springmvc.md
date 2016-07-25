# Ajax post json xml to SpringMVC
We can use json and xml to store complex data in our application. In this post I will show you how to use Ajax post
xml and json to springMVC. For quickly starting I use Spring Boot in my example. The code also works in SpringMVC.

**Structure of project**
```
├─main
│  ├─java
│  │  └─com
│  │      └─henryxi
│  │          └─post
│  │                  SamplePostController.java
│  │                  User.java
│  │
│  └─resources
│      │  application.properties
│      │
│      └─META-INF
│          └─resources
│              │  jquery.1.12.0.js
│              │
│              └─WEB-INF
│                  └─jsp
│                          post.jsp
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
**User.java**

Don't forget getter and setter methods. If you missing them the variable `user` will be empty.
```java
@XmlRootElement
public class User {

    private String name;

    private String age;

    //getter and setter methods

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
```
**SamplePostController.java**
There are two method in controller. `postXml` will handle the xml and `postJson` will handle the json.
```java
@Controller
@EnableAutoConfiguration
public class SamplePostController extends SpringBootServletInitializer {

    @RequestMapping("/")
    public String index() {
        return "post";
    }

    @RequestMapping(path = "/post-xml", method = RequestMethod.POST)
    @ResponseBody
    public String postXml(@RequestBody User user) throws IOException {
        return user.toString();
    }

    @RequestMapping(path = "/post-json", method = RequestMethod.POST)
    @ResponseBody
    public String postJson(User user) {
        return user.toString();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SamplePostController.class, args);
    }
}
```
**post.jsp**
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>post</title>
</head>
<body>
<script type="application/javascript" src="jquery.1.12.0.js"></script>
<input id="postXmlBtn" type="button" value="post xml">
<br>
<input id="postJsonBtn" type="button" value="post json">
<div id="content"></div>
<script type="application/javascript">
    $('#postXmlBtn').click(function () {
        var xml = '';
        $.ajax({
            url: 'post-xml',
            type: 'POST',
            contentType: "application/xml",
            data: '<user><name>henryxi</name><age>28</age></user>',
            success: function (data) {
                $('#content').html(data);
            },
            error: function (xhr, status, error) {
                var err = eval("(" + xhr.responseText + ")");
                alert(err.Message);
            }
        });
    });
    $('#postJsonBtn').click(function () {
        $.post('post-json', {"name": "henry", "age": 28}, function (data) {
            $('#content').html(data);
        })
    });
</script>
</body>
</html>
```
**application.properties**
```ini
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
server.port=8090
```

Run the main method of controller and access localhost:8090 you will see two buttons. The one is for posting xml the
other one is for posting json. After clicking the button the content of page will change.