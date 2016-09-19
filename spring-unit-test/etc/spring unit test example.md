# Spring unit test example
Unit test can help you correct the logic error. In this page I will show you how to use unit test in spring. I create
a simple project to display a sentence in the page. The structure of this project is like following.

**Project structure**
```
├─main
│  ├─java
│  │  └─com
│  │      └─henryxi
│  │          └─unit
│  │                  SimpleController.java
│  │                  SimpleService.java
│  │
│  ├─resources
│  │      spring-all.xml
│  │      system.properties
│  │
│  └─webapp
│      │  index.jsp
│      │
│      └─WEB-INF
│              web.xml
│ 
└─test
    ├─java
    │  └─com
    │      └─henryxi
    │          └─unit
    │                  SimpleServiceTest.java
    │
    └─resources
            test-spring-all.xml
```
The code of `SimpleController.java` is simple. Auto wire the service (`SimpleService`) and invoke the `serviceMethod` 
method. When the controller gets the sentence from service return it to page directly.

**SimpleController**
```java
@Controller
public class SimpleController {
    @Autowired
    private SimpleService simpleService;

    @RequestMapping("/spring-unit-test")
    @ResponseBody
    public String testProperties() {
        return simpleService.serviceMethod();
    }
}
```
Generally speaking, your logic code is in service class like following.

**SimpleService**
```java
@Service
public class SimpleService {
    public String serviceMethod() {
        return "this is service method";
    }
}
```
Now we will test this service class. Create the `SimpleServiceTest` to test the logic of service class.

**SimpleServiceTest**
```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-all.xml")
public class SimpleServiceTest {
    @Autowired
    private SimpleService simpleService;

    @Test
    public void serviceMethod() throws Exception {
        Assert.assertEquals("this is service method", simpleService.serviceMethod());
    }
}
```
One important thing to note about test class is `@ContextConfiguration(locations = "classpath:spring-all.xml")`. Spring
 will find the `spring-all.xml` in "test/resources/" if not found then find it in "main/resources". If Spring won't find
 the xml file in either of them `FileNotFoundException` will be thrown.

Run the test class you can validate the logic is true or not. In our example we just validate the string is equal to
"this is service method". 