# Avoiding null check in java
There are lot of null check in my project. They are very ugly. For example If you want print out the address of user.
(ORM)hibernate or mybatis will help you query this user info include address info. You may get an user entity like following.
```java
public class User {
    private String id;
    private String name;
    private int age;
    private Address address;
    //getter and setter 
}
public class Address {
    private String country;
    private String city;
    //getter and setter
}
```
The print code is simple something like `user.getAddress().getCity()`. But before print the address you have to check
the user and the address of user. The code will be like following.
```java
User user = queryUserFromDB(userId);
if(user != null){
    if(user.getAddress() != null){
        System.out.println(user.getAddress().getCity());
    } else {
        System.out.println("address of user is null");
    }
} else {
    System.out.println("user is null");
}
```
You can also make the code prettier by leaving out the detail message.
```java
if(user != null && user.getAddress() != null){
    System.out.println(user.getAddress().getCity());
} else {
    System.out.println("can not get address of user");
}
```
Much better then the first one. I want avoiding null check 100% in my code. After searching on google I found one solution.
##### Using `assert` to avoid NullPointerException(not recommend)
By using assert keyword the code will be like following.
```java
assert user !=null : "user is null";
assert user.getAddress() != null : "address of user is null";
System.out.println(user.getAddress().getCity());
```
Looks very nice. But you have to add `-ea` in java configuration to enable assert. This configuration is disable by default
And if user or the address of user is null this piece of code will throw `AssertionError`. Even more unfortunate is 
`AssertionError` extend `Error`(can not be catch).
##### Using `org.springframework.util.Assert` to avoid null check(recommend)
By using this util class the code will be like following.
```java
try {
    Assert.notNull(user, "user is null");
    Assert.notNull(user.getAddress(), "address of user is null");
    System.out.println(user.getAddress().getCity());
} catch (Execption e){
    System.out.println(e.getMessage());
}
```