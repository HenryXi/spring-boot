# Spring boot parse POST json example
You can post a form to server and parse the data. Json is a lightweight data format, it's better to 
post json when your data is complex. In the page I will show you how to parse json data in Spring Boot.
You can use Postman or other REST clients to send a POST request. I prefer to use REST Client in Intellij
IDEA. Click [Use Intellij IDEA REST Client POST json](http://www.henryxi.com/use-intellij-idea-rest-client-post-json)
for more detail.

**project structure**
```bash
├─main
│  ├─java
│  │  └─com
│  │      └─henryxi
│  │          └─post
│  │              └─json
│  │                      SimpleController.java
│  │                      UserVo.java
│  │
│  └─resources
└─test
    └─java
```
**SimpleController**
```java
@RestController
@EnableAutoConfiguration
public class SimpleController {
    @RequestMapping(value = "/get-json", method = RequestMethod.POST)
    public String getJson(@RequestBody UserVo userVo) {
        return userVo.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
```
**UserVo**
```java
public class UserVo implements Serializable {

    private static final long serialVersionUID = 3267101817151303146L;

    private String name;
    private int age;
    
    //getter and setter method

    @Override
    public String toString() {
        return "UserVo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```
Run `SimpleController` and post json to `/get-json`(Click [Use Intellij IDEA REST Client POST json](http://www.henryxi.com/use-intellij-idea-rest-client-post-json)
to learn how to post json) Spring will help you parse json to `UserVo`. But you need make sure the 
properties in vo class are same with json. If they are not match the properties in `UserVo` will be null.