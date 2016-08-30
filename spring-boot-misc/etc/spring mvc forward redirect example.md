# Spring MVC forward redirect example
You can return jsp file name in Spring MVC controller when you want client show this page. You can also add `forward`
 or `redirect` keyword at the front of jsp file. I will show you how to "redirect" and "forward" a page in this blog.

**SimpleController.java**
```java
@Controller
@EnableAutoConfiguration
public class SimpleController {

    @RequestMapping("/index-page")
    public String index(@PathParam("name") String name, ModelMap modelMap) {
        modelMap.put("name",name);
        return "index";
    }

    @RequestMapping("/redirect")
    public String redirect() {
        return "redirect:index-page";
    }

    @RequestMapping("/forward")
    public String forward() {
        return "forward:index-page";
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
```

**How it works**

Access localhost:8090/index-page?name=henry you will see the page as following.
```
This is index page. Hello henry
```

Access localhost:8090/redirect?name=henry you will see the page as following and the response will return with 302 code.
But the parameter "name=henry" will not passed to target page.
```
This is index page. Hello
```

```
HTTP/1.1 302 Found
Server: Apache-Coyote/1.1
Location: http://localhost:8090/index-page
Content-Language: zh-CN
Content-Length: 0
Date: Tue, 30 Aug 2016 05:34:40 GMT
```
Access localhost:8090/forward?name=henry you will get the page same with first url.

##### Difference between forward and redirect
**forward**: The result of `forward` will be the same as access target page.
 
**redirect**: `redirect` will not pass the parameter to the target page. Because server send http code 302 to client
then client will request the url(index-page) returned by server.
