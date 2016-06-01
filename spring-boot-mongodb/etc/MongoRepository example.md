# Spring MongoRepository example
In this page I will show you how to use `MongoRepository` insert, delete, update and query document. I assume
you have installed MongoDB in your computer. If you don't know how to work with MongoDB you can learn basic methods
from [MongoDB tutorial](http://www.henryxi.com/mongodb-tutorial). `MongoRepository` is a interface which can help
you CRUD document in MongoDB.

**project structure**

For quickly start I use Spring boot to run the demo.
```
└─main
    ├─java
    │  └─mongo
    │      └─keywords
    │          └─repository
    │                  QueryClient.java
    │                  User.java
    │                  UserRepository.java
    │
    └─resources
            application.properties
```

**User**
```java
public class User {
    @Id
    private String id;
    private String name;
    private int age;
    private Date createDate;

    //getter setter method

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```
**QueryClient**
```java
@SpringBootApplication
public class QueryClient implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(QueryClient.class, args);
    }

    public void run(String... strings) throws Exception {
        List<User> users = userRepository.findByName("test1");
        System.out.println(users.toString());
    }
}
```
**UserRepository**
```java

```