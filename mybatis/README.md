# Use Mybatis in Spring Boot

In this tutorial I will show how to use Mybatis in Spring Boot. Spring Boot can help you build
RESTful project quickly, Mybatis is one of the best ORM framework. Use Mybatis in Spring Boot
can help you build persistence layer of your project quickly and easily. I will provide a complete
example for using MyBatis in Spring Boot step by step.

**Require**

I use following software and tools to build this project.
Java 7 + Maven + PostgreSQL + IntelliJ

**Project Structure**

Create a maven project in Intellij and the structure like below.

![Project Structure](./Spring_Mybatis.png?raw=true )

**Create table in your Database**

I use following SQL to create a sample table in my Database. After
creation insert a record in it.
```
CREATE TABLE public.users (
  id INTEGER,
  name CHARACTER VARYING(20),
  age INTEGER,
  password CHARACTER VARYING(10)
);
INSERT INTO users VALUES (1, 'user1', 27, 'password');
```
**Dependencies in pom.xml file**
```
<properties>
    <spring.boot.version>1.3.2.RELEASE</spring.boot.version>
</properties>
<dependencies>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.2.8</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>1.2.2</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>${spring.boot.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
        <version>${spring.boot.version}</version>
    </dependency>
    <dependency>
        <groupId>postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>9.1-901-1.jdbc4</version>
    </dependency>
</dependencies>
```
**Define SqlSessionFactoryBean, DataSource and DataSourceTransactionManager in Configuration class**

In normal Spring project we are used to define SqlSessionFactoryBean, DataSource and
DataSourceTransactionManager in Spring configuration file(*.xml). Here we use ``@Bean``
define them in class. The annotation ``@MapperScan`` will scan the interfaces in appointed
directory. In DataSource Bean we get the Database configuration in properties file.
The whole code of ``Application``:
```
@EnableAutoConfiguration
@Configuration
@ComponentScan(value = "com.henry.xi.mybatis")
@MapperScan("com.henry.xi.mybatis")
public class Application {
    private static Logger logger = Logger.getLogger(Application.class);

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.info("SpringBoot Start Success");
    }
}
```
**Create mapper xml file and interface**

In Mybatis we use interface and mapper xml file to get record from database.

UserMapper.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.henry.xi.mybatis.mapper.UserMapper">

    <select id="findUserInfo" resultType="com.henry.xi.mybatis.model.User">
      select name, age,password from users;
    </select>

</mapper>
```
UserMapper interface
```
public interface UserMapper {
    User findUserInfo();
}
```
**Create controller and model**

controller and model is sample.
```
@Controller
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo() {
        User user = userService.getUserInfo();
        if(user!=null){
            System.out.println("user.getName():"+user.getName());
            logger.info("user.getAge():"+user.getAge());
        }
        return user;
    }
}
```
model as following
```
public class User {
    private String name;
    private Integer age;
    private String password;

    //getter and setter
}

```
Now you can start your project by running the main method in ``Application`` class.