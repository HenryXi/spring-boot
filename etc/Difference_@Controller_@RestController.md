# Difference between @Controller and @RestController annotation
* @Controller is common annotation in Spring MVC. It is used to mark classes as Spring MVC Controller.

* @RestController is a new annotation in Spring4. 
It equals @Controller and @ResponseBody annotations use together.
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
Want understand @ResponseBody you can click
 [here](./understand_@RequestBody_and_@ResponseBody.md).
 
dog | bird | cat
:-- | :--: | --:
foo | foo  | foo
bar | bar  | bar
baz | baz  | baz