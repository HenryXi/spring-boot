# Spring Boot @ConfigurationProperties example

In Spring you can use ``@PropertySource`` and ``@Value`` to load value from properties file.
You can also use them in Spring Boot. But Spring Boot provide a easy way to get value from
properties file. In this page I will show you how to use ``@ConfigurationProperties`` to get
the value in properties file.

**Require**

* Maven3
* Intellij

**Project Structure**

Create a maven project in Intellij, make structure as following.

```
├─main                                                                                                                                                                                                             
│  ├─java                                                                                                                                                                                                         
│  │  └─com                                                                                                                                                                                                      
│  │      └─henry                                                                                                                                                                                                
│  │          └─properties                                                                                                                                                                                       
│  │                  DefaultProperties.java                                                                                                                                                                       
│  │                  SimpleController.java                                                                                                                                                                        
│  │                  SpecialProperties.java                                                                                                                                                                       
│  │                                                                                                                                                                                                               
│  └─resources                                                                                                                                                                                                    
│          application.properties                                                                                                                                                                                   
│          server.properties                                                                                                                                                                                        
│                                                                                                                                                                                                                   
└─test                                                                                                                                                                                                             
    └─java          
```


**Dependencies in pom.xml file**

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>1.3.2.RELEASE</version>
</dependency>
```

**Properties files**

In this tutorial I will show you loading properties from two properties files.
``application.properties`` is default properties file in Spring Boot. If you do
not want save any properties in ``application.properties`` you can create another one
like ``server.properties``. I recommend you create another properties to save your
own properties.

content of application.properties
```
server.info.address=192.168.1.1
server.info.username=user1
server.info.password=password
```
content of server.properties
```
server.info.address=192.168.1.2
server.info.username=user2
server.info.password=password2
```

**Java code**

In order to load properties in class we create ``DefaultProperties`` class. Make
the properties in ``DefaultProperties`` and ``application.properties`` match.
```
@Configuration
@ConfigurationProperties(prefix = "server.info")
public class DefaultProperties {
    private String address;
    private String username;
    private String password;

    //getter and setter methods

    @Override
    public String toString() {
        return "DefaultProperties{" +
                "address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
```
``@ConfigurationProperties`` annotation tell Spring Boot this class is bound to
properties file. Use ``prefix`` define special prefix to match properties in configuration
file. If you want get properties from special file instead of ``application.properties``.
You can create ``SpecialProperties`` as following.
```
@Configuration
@ConfigurationProperties(locations="classpath:server.properties",prefix = "server.info")
public class SpecialProperties {
    private String address;
    private String username;
    private String password;

    //getter and setter methods

    @Override
    public String toString() {
        return "SpecialProperties{" +
                "address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
```
The only difference is add ``locations`` property in ``@ConfigurationProperties``.

In order to start this project we need a controller.
```
@RestController
@EnableAutoConfiguration
@ComponentScan(value = "com.henry.xi.properties")
public class SimpleController {
    @Autowired
    private DefaultProperties defaultProperties;

    @Autowired
    private SpecialProperties specialProperties;

    @RequestMapping(value = "/properties", method = RequestMethod.GET)
    public String getProperties() {
        return defaultProperties.toString() + "<br>" + specialProperties.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
```
Run main method in ``SimpleController`` and access ``http://localhost:8080/properties``
you will see the result as following.
```
DefaultProperties{address='192.168.1.1', username='user1', password='password'}
SpecialProperties{address='192.168.1.2', username='user2', password='password2'}
```
That's all, happy learning.