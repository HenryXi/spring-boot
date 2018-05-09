# Spring Boot log4j2 example
It is easy to use log4j2 in spring boot. Create a maven project, structure like following.
```
├─main
│  ├─java
│  │  └─com
│  │      └─henryxi
│  │          └─log4j2
│  │                  AppConfig.java
│  │                  AppController.java
│  │
│  └─resources
│          log4j2.xml
```
Add the dependency in the pom file. **In order to use log4j2 you have to exclusion `spring-boot-starter-logging`**
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-logging</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-log4j2</artifactId>
        <version>1.3.3.RELEASE</version>
    </dependency>
</dependencies>
``` 
The content of AppConfig and AppController:
```java
@EnableAutoConfiguration
@Configuration
@ComponentScan("com.henryxi.log4j2")
public class AppConfig extends WebMvcConfigurerAdapter {
    private static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    public static void main(String[] args) throws Exception {
        try {
            SpringApplication.run(AppConfig.class, args);
        } catch (Exception e) {
            logger.error("start error:", e);
        }
    }
}

@Controller
public class AppController {
    private static Logger logger = LoggerFactory.getLogger(AppController.class);

    @RequestMapping(value = "/testLog4j2", method = RequestMethod.GET)
    @ResponseBody
    public String testLog4j2() {
        String content = "hello spring boot log4j2.";
        logger.info("this is info log!");
        logger.error("this is error log!");
        logger.warn("this is warm log!");
        return content;
    }
}
```
Put log4j2.xml in resources directory.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN" monitorInterval="30">
    <Properties>
        <Property name="LOG_PATTERN">
            %d{yyyy-MM-dd HH:mm:ss.SSS} %5p ${hostName} --- [%15.15t] %-40.40c{1.} : %m%n%ex
        </Property>
    </Properties>
    <Appenders>
        <Console name="ConsoleAppender" target="SYSTEM_OUT" follow="true">
            <PatternLayout pattern="${LOG_PATTERN}"/>
        </Console>
        <File name="FileAppender" fileName="/tmp/spring_boot.log" >
            <PatternLayout pattern="${LOG_PATTERN}"/>
        </File>
    </Appenders>
    <Loggers>
        <Logger name="com" level="info" additivity="false">
            <AppenderRef ref="ConsoleAppender" />
            <AppenderRef ref="FileAppender" />
        </Logger>

        <Root level="info">
            <AppenderRef ref="ConsoleAppender" />
            <AppenderRef ref="FileAppender" />
        </Root>
    </Loggers>
</Configuration>
```
Run the project and access localhost:8080/testLog4j2. The console will output the following log.
```
2018-05-09 17:56:59.880  INFO henry-PC --- [nio-8080-exec-1] c.h.l.AppController                      : this is info log!
2018-05-09 17:56:59.880 ERROR henry-PC --- [nio-8080-exec-1] c.h.l.AppController                      : this is error log!
2018-05-09 17:56:59.880  WARN henry-PC --- [nio-8080-exec-1] c.h.l.AppController                      : this is warm log!
```
The content of log is in `/tmp/spring_boot.log`.

EOF