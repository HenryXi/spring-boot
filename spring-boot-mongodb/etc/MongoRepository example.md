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

**application.properties**
```ini
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=user_database
```
**User**
```java
public class User {
    @Id
    private String id;
    private String name;
    private int age;
    private Address address;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    //getter and setter methods

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
**address**
```java
public class Address {
    private String country;
    private String city;
    //getter and setter method
}
```
**UserRepository**
```java
interface UserRepository extends MongoRepository<User, String> {
    List<User> findByAge(Long age);
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
        // save data
        User henry = new User("Henry", 27);
        userRepository.save(henry);
        Address beijing = new Address("China", "Beijing");
        Address shanghai = new Address("China", "Shanghai");
        User justin = new User("Justin", 28, beijing);
        User mathew = new User("Mathew", 23,shanghai);
        List<User> users = new ArrayList<User>();
        users.add(justin);
        users.add(mathew);
        userRepository.save(users);

        //delete data
        String justinId = justin.getId();
        justin.setId("123456");
        userRepository.delete(justin);//delete fail when change the id
        userRepository.delete(justinId);

        //query data
        List<User> usersInDB = userRepository.findByAge(23L);
        String id = usersInDB.get(0).getId();
        User user = userRepository.findOne(id);
        System.out.println(user);

        //update data
        User updateUser = userRepository.findOne(id);
        updateUser.setName("new name");//update date need query it first
        userRepository.save(updateUser);
        User newUser = userRepository.findOne(id);
        System.out.println(newUser);
    }
}
```
You can save date(document) one by one(`save(S entity`) or save multiple in one time(`save(Iterable<S> entites)`).
Don't change the id of entity when you delete document by `delete(T entity)`. Delete by id always works
if you make sure the id is correct. For updating document you need query it first then change the properties
and save it again.