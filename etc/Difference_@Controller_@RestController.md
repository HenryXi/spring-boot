# Difference between @Controller and @RestController annotation
* @Controller is used to mark classes as Spring MVC Controller.

* @RestController is a convenience annotation that does nothing more than adding the @Controller and @ResponseBody annotations (see: Javadoc)
So the following two controller definitions should do the same

```java
    @Controller
    @ResponseBody
    public class SimpleController { }
```
```java
    @RestController
    public class SimpleController { }
```
Want understand @ResponseBody you can click
 [here](./understand_@RequestBody_and_@ResponseBody.md).