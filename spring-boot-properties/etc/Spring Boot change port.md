# Spring Boot change default port
The default port in Spring Boot of web application is 8080. There are two ways to change this default port. The first
way is to change `application.properties` file. The second way is to add VM option when start your web application.

**change application.properties**
```ini
server.port=8090
```

**add VM option**
```
-Dserver.port=8090
```

For configuration of Spring Boot you can click [here] for more detail.