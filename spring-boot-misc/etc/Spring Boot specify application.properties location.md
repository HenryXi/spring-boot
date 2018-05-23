# Spring Boot specify application.properties location
When I run Spring Boot jar in different environment I need different configuration. I use command line to assign the location
of `application.properties`. Let's say you have a project without `application.properties` file and you package it as jar. 
You can use `java -jar YOUR_PACKAGE_NAME.jar` to start this project. The output is like following.
```bash
[root@virtual tmp]# java -jar post-json-1.0-SNAPSHOT.jar 

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.3.3.RELEASE)

2018-05-23 11:24:41.743  INFO 3446 --- [           main] com.henryxi.post.json.SimpleController   : Starting SimpleController with PID 3446 (/tmp/post-json-1.0-SNAPSHOT.jar started by root in /tmp)
2018-05-23 11:24:41.752  INFO 3446 --- [           main] com.henryxi.post.json.SimpleController   : No active profile set, falling back to default profiles: default
2018-05-23 11:24:41.833  INFO 3446 --- [           main] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@650a5db1: startup date [Wed May 23 11:24:41 CST 2018]; root of context hierarchy
2018-05-23 11:24:42.687  INFO 3446 --- [           main] o.s.b.f.s.DefaultListableBeanFactory     : Overriding bean definition for bean 'beanNameViewResolver' with a different definition: replacing [Root bean: class [null]; scope=; abstract=false; lazyInit=false; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration$WhitelabelErrorViewConfiguration; factoryMethodName=beanNameViewResolver; initMethodName=null; destroyMethodName=(inferred); defined in class path resource [org/springframework/boot/autoconfigure/web/ErrorMvcAutoConfiguration$WhitelabelErrorViewConfiguration.class]] with [Root bean: class [null]; scope=; abstract=false; lazyInit=false; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter; factoryMethodName=beanNameViewResolver; initMethodName=null; destroyMethodName=(inferred); defined in class path resource [org/springframework/boot/autoconfigure/web/WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter.class]]
2018-05-23 11:24:43.420  INFO 3446 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): 8080 (http)
2018-05-23 11:24:43.443  INFO 3446 --- [           main] o.apache.catalina.core.StandardService   : Starting service Tomcat
2018-05-23 11:24:43.444  INFO 3446 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.0.32
2018-05-23 11:24:43.569  INFO 3446 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2018-05-23 11:24:43.570  INFO 3446 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 1742 ms
2018-05-23 11:24:43.989  INFO 3446 --- [ost-startStop-1] o.s.b.c.e.ServletRegistrationBean        : Mapping servlet: 'dispatcherServlet' to [/]
2018-05-23 11:24:43.997  INFO 3446 --- [ost-startStop-1] o.s.b.c.embedded.FilterRegistrationBean  : Mapping filter: 'characterEncodingFilter' to: [/*]
2018-05-23 11:24:43.998  INFO 3446 --- [ost-startStop-1] o.s.b.c.embedded.FilterRegistrationBean  : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
2018-05-23 11:24:43.998  INFO 3446 --- [ost-startStop-1] o.s.b.c.embedded.FilterRegistrationBean  : Mapping filter: 'httpPutFormContentFilter' to: [/*]
2018-05-23 11:24:43.998  INFO 3446 --- [ost-startStop-1] o.s.b.c.embedded.FilterRegistrationBean  : Mapping filter: 'requestContextFilter' to: [/*]
```
We can see the default port is 8080. I add the config file `application.properties` in "spring_config" directory and assign it when
run `java -jar`.
The content of `application.properties` is like following.
```
server.port=8090
```
run `java -Dspring.config.location=file:/tmp/spring_config/application.properties -jar YOUR_PACKAGE_NAME.jar `. As the log
output the port is changed tobe 8090.
```bash
[root@virtual tmp]# java -Dspring.config.location=file:/tmp/spring_config/application.properties -jar post-json-1.0-SNAPSHOT.jar 

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.3.3.RELEASE)

2018-05-23 11:30:02.558  INFO 3505 --- [           main] com.henryxi.post.json.SimpleController   : Starting SimpleController with PID 3505 (/tmp/post-json-1.0-SNAPSHOT.jar started by root in /tmp)
2018-05-23 11:30:02.581  INFO 3505 --- [           main] com.henryxi.post.json.SimpleController   : No active profile set, falling back to default profiles: default
2018-05-23 11:30:02.745  INFO 3505 --- [           main] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@2559796b: startup date [Wed May 23 11:30:02 CST 2018]; root of context hierarchy
2018-05-23 11:30:03.682  INFO 3505 --- [           main] o.s.b.f.s.DefaultListableBeanFactory     : Overriding bean definition for bean 'beanNameViewResolver' with a different definition: replacing [Root bean: class [null]; scope=; abstract=false; lazyInit=false; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration$WhitelabelErrorViewConfiguration; factoryMethodName=beanNameViewResolver; initMethodName=null; destroyMethodName=(inferred); defined in class path resource [org/springframework/boot/autoconfigure/web/ErrorMvcAutoConfiguration$WhitelabelErrorViewConfiguration.class]] with [Root bean: class [null]; scope=; abstract=false; lazyInit=false; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter; factoryMethodName=beanNameViewResolver; initMethodName=null; destroyMethodName=(inferred); defined in class path resource [org/springframework/boot/autoconfigure/web/WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter.class]]
2018-05-23 11:30:04.602  INFO 3505 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): 8090 (http)
2018-05-23 11:30:04.631  INFO 3505 --- [           main] o.apache.catalina.core.StandardService   : Starting service Tomcat
2018-05-23 11:30:04.633  INFO 3505 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.0.32
2018-05-23 11:30:04.805  INFO 3505 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2018-05-23 11:30:04.805  INFO 3505 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 2070 ms
2018-05-23 11:30:05.289  INFO 3505 --- [ost-startStop-1] o.s.b.c.e.ServletRegistrationBean        : Mapping servlet: 'dispatcherServlet' to [/]
2018-05-23 11:30:05.297  INFO 3505 --- [ost-startStop-1] o.s.b.c.embedded.FilterRegistrationBean  : Mapping filter: 'characterEncodingFilter' to: [/*]
2018-05-23 11:30:05.299  INFO 3505 --- [ost-startStop-1] o.s.b.c.embedded.FilterRegistrationBean  : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
2018-05-23 11:30:05.299  INFO 3505 --- [ost-startStop-1] o.s.b.c.embedded.FilterRegistrationBean  : Mapping filter: 'httpPutFormContentFilter' to: [/*]
2018-05-23 11:30:05.299  INFO 3505 --- [ost-startStop-1] o.s.b.c.embedded.FilterRegistrationBean  : Mapping filter: 'requestContextFilter' to: [/*]
```

EOF