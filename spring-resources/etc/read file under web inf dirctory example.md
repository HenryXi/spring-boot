# Read file under WEB-INF directory example
For JavaEE project you can request the file under project directory except the file under WEB-INF. Put the file under
WEB-INF directory if you do not want others informal access it. The question is how to access it in project. This blog
 will show you how to access the file under WEB-INF directory programmatically.

**project structure**
```
├─main
│  ├─java
│  │  └─com
│  │      └─henryxi
│  │          └─resource
│  │                  SimpleController.java
│  │
│  ├─resources
│  │      spring-all.xml
│  │
│  └─webapp
│      │  index.jsp
│      │
│      └─WEB-INF
│              target_file.txt
│              web.xml
│
└─test
    └─java
```

**SimpleController.java**
```java
@Controller
public class SimpleController {
    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping("/WEB-INF-file")
    @ResponseBody
    public String testProperties() throws IOException {
        String content = IOUtils.toString(resourceLoader.getResource("/WEB-INF/target_file.txt").getInputStream());
        return "the content of resources:" + content;
    }
}
```

Start this project and access localhost:8080/WEB-INF-file you will get the result as following.
```
the content of resources:This is the file in the directory /WEB-INF/target_file.txt.
```
The most important part of code is `resourceLoader.getResource("/WEB-INF/target_file.txt")`. This line means read the 
file `target_file.txt`.