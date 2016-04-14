# Spring Boot JSP examples

Before learning how to use JSP in Spring Boot you need know there are several limitations. You can click [here](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-jsp-limitations)
to learn more. In order to show the page you need add ``tomcat-embed-jasper`` dependency in pom file. Spring Boot 
uses an embedded servlet container which is not rendering JSP as default.

**pom file**
```
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>${spring.boot.version}</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
            <version>8.0.28</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${spring.boot.version}</version>
            </plugin>
        </plugins>
    </build>
```
the maven plugin is necessary, I try to run the project with main method but get 404 page. It works when I 
run ``clean spring-boot:run``.