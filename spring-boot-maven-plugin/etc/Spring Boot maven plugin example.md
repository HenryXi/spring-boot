# Spring Boot maven plugin example
`spring-boot-maven-plugin` can help you build runnable Spring Boot jar. Add following config in your pom file and execute
`clean install` you will get a runnable jar file.
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>1.3.3.RELEASE</version>
    </dependency>
</dependencies>
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                    <configuration>
                        <mainClass>com.henryxi.mavenplugin.TestController</mainClass>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
If you do not add "executions" config in your pom, you need run `mvn package spring-boot:repackage` to generate runnable jar file.

The `TestController` is here
```java
@RestController
@EnableAutoConfiguration
public class TestController {
    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public String get() {
        return "This is Get response";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestController.class, args);
    }
}
```
After executing `mvn clean install` you will get two files in your target directory. The one is runnable jar file and another
is original jar(without dependency libs). Then you can start this Spring Boot application by `java -jar YOUR_RUNNABLE_JAR_PATH`.

EOF