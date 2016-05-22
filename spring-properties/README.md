# Read values from properties file in Spring
There are several ways read values from properties in Spring. You can choose one way in your application 
or change some code to fit your needs. In this tutorial I will show you different ways to read 
values from properties in Spring.

1. [using util tag in spring config file](#using-util-tag-in-spring-config-file)
2. [using PropertiesFactoryBean create Properties object](#using-propertiesfactorybean-create-properties-object)
3. [using PropertyPlaceholderConfigurer and @Value inject value into bean](#using-propertyplaceholderconfigurer-and-value-inject-value-into-bean)
4. [using @Configuration and @PropertySource inject Environment object into bean](#using-configuration-and-propertysource-inject-environment-object-into-bean)

properties file in all these 4 project like following
```
server.address=192.168.6.6
server.port=6666
```
the version of Spring is ``4.2.4.RELEASE`` pom file like following
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>4.2.4.RELEASE</version>
</dependency>
```
web.xml in all these projects are same. We need add springMVC support
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://java.sun.com/xml/ns/javaee"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
         id="WebApp_ID" version="2.5">
    <servlet>
        <servlet-name>springMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-all.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springMVC</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    <welcome-file-list>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
</web-app>
```

#### using util tag in spring config file
---
project structure
```
├─main
│  ├─java
│  │  └─com
│  │      └─henry
│  │          └─xml
│  │              └─properties
│  │                  └─util
│  │                      └─tag
│  │                              SimpleController.java
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
    └─java        
```
spring-all.xml file
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
						http://www.springframework.org/schema/beans/spring-beans-3.2.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd"
       default-lazy-init="false">

    <context:component-scan base-package="com.henry.xml.properties"/>
    <mvc:annotation-driven/>
    <util:properties id="configProperties" location="classpath*:/system.properties"/>

</beans>
```
SimpleController.java
```java
@Controller
public class SimpleController {
    @Resource(name = "configProperties")
    private Properties properties;

    @RequestMapping("/test-util-tag")
    @ResponseBody
    public String testProperties() {
        return "serverAddress: " + properties.getProperty("server.address") +
                " serverPort: " + properties.getProperty("server.port");
    }
}
```

#### using PropertiesFactoryBean create Properties object
---
project structure
```
├─main
│  ├─java
│  │  └─com
│  │      └─henry
│  │          └─xml
│  │              └─properties
│  │                  └─factory
│  │                      └─bean
│  │                              SimpleController.java
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
    └─java
```
spring-all.xml file
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
						http://www.springframework.org/schema/beans/spring-beans-3.2.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd"
       default-lazy-init="false">

    <context:component-scan base-package="com.henry.xml.properties"/>
    <mvc:annotation-driven/>
    <bean id="configProperties"
          class="org.springframework.beans.factory.config.PropertiesFactoryBean">
        <property name="ignoreResourceNotFound" value="true"/>
        <property name="locations">
            <list>
                <value>classpath*:/system.properties</value>
            </list>
        </property>
    </bean>

</beans>
```
SimpleController.java
```java
@Controller
public class SimpleController {
    @Resource(name = "configProperties")
    private Properties properties;

    @RequestMapping("/test-properties-factory-bean")
    @ResponseBody
    public String testProperties() {
        return "serverAddress: " + properties.getProperty("server.address") +
                " serverPort: " + properties.getProperty("server.port");
    }
}
```
#### using PropertyPlaceholderConfigurer and @Value inject value into bean
---
project structure
```
├─main
│  ├─java
│  │  └─com
│  │      └─henry
│  │          └─xml
│  │              └─properties
│  │                  └─property
│  │                      └─placeholder
│  │                          └─configurer
│  │                                  SimpleController.java
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
    └─java               
```
spring-all.xml file
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
						http://www.springframework.org/schema/beans/spring-beans-3.2.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd"
       default-lazy-init="false">

    <context:component-scan base-package="com.henry.xml.properties.property.placeholder.configurer"/>
    <mvc:annotation-driven/>
    <bean id="configProperties"
          class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        <property name="ignoreResourceNotFound" value="true"/>
        <property name="locations">
            <list>
                <value>classpath*:/system.properties</value>
            </list>
        </property>
    </bean>
</beans>
```
SimpleController.java
```xml
@Controller
public class SimpleController {
    @Value("${server.address}")
    private String serverAddress;
    @Value("${server.port}")
    private String serverPort;
    @RequestMapping("/test-property-placeholder-configurer")
    @ResponseBody
    public String testProperties(){
        return "serverAddress: " + serverAddress + " serverPort: " + serverPort;
    }
}
```
#### using @Configuration and @PropertySource inject Environment object into bean
---
project structure
```
├─main
│  ├─java
│  │  └─com
│  │      └─henry
│  │          └─xml
│  │              └─properties
│  │                  └─configuration
│  │                      └─annotation
│  │                              SimpleController.java
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
   └─java        
```
spring-all.xml file
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
						http://www.springframework.org/schema/beans/spring-beans-3.2.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd"
       default-lazy-init="false">

    <context:component-scan base-package="com.henry.xml.properties.configuration.annotation"/>
    <mvc:annotation-driven/>

</beans>
```
SimpleController.java
```java
@Controller
@Configuration
@PropertySource("classpath:system.properties")
public class SimpleController {
    @Autowired
    private Environment env;

    @RequestMapping("/test-configuration-annotation")
    @ResponseBody
    public String testProperties() {
        return "serverAddress: " + env.getProperty("server.address") +
                " serverPort: " + env.getProperty("server.port");
    }
}
```


