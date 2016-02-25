# Difference between @Controller and @RestController

Annotations of Spring Framework greatly simplifies the RESTful project developing process.
@Controller is used to indicates that an annotated class is a "Controller".
In Spring documentation "Controller" defines as follow
>A Controller is typically responsible for preparing a model Map with data and selecting a view name
but it can also write directly to the response stream and complete the request.

In Spring MVC if you want "Controller" write directly to the response stream
 you can use @ResponseBody. @Controller and @ResponseBody are commonly used together
 for returning json/xml, so we combine them as @RestController.

Annotations | Description 
:-- | :-- 
@Controller | is common annotation in Spring MVC. It is used to mark classes as Spring MVC Controller.
@RestController | is a new annotation in Spring4. It is equivalent to using @Controller and @ResponseBody together.

So the following two code snippets of controller should do the same thing.

```java
    @Controller
    @ResponseBody
    public class SimpleController { }
```

```java
    @RestController
    public class SimpleController { }
```
As you can see, using @RestController simplify the developing RESTful project. But if methods in Controller return
different (some return json/xml, other return view name) you have to use @Controller and @ResponseBody instead of @RestController.
 
