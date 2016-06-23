# Encoding(garbled) problem in SpringMVC tomcat
When I use SpringMVC and tomcat to develop the project I often meet garbled problem. In this page
I will show you the right way to use them. The most important thing is **making the "input" and 
"output" encoding same**.

**project structure**
```
└─main                                                                                                                                                                                                             
    ├─java                                                                                                                                                                                                         
    │  └─com                                                                                                                                                                                                      
    │      └─henryxi                                                                                                                                                                                              
    │          └─tomcat                                                                                                                                                                                           
    │              └─garbled                                                                                                                                                                                      
    │                      SimpleController.java                                                                                                                                                                    
    │                      User.java                                                                                                                                                                                
    │                                                                                                                                                                                                               
    ├─resources                                                                                                                                                                                                    
    │      spring-all.xml                                                                                                                                                                                           
    │                                                                                                                                                                                                               
    └─webapp                                                                                                                                                                                                       
        │  index.jsp                                                                                                                                                                                                
        │                                                                                                                                                                                                           
        └─WEB-INF                                                                                                                                                                                                  
                web.xml

```
**pom.xml**
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>4.2.5.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.6.5</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1-b01</version>
    </dependency>
</dependencies>
```


### GET method
If your use 'GET' method to make a request, the code of controller like following.
```java
@RequestMapping(value = "/http-get", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
@ResponseBody
public String testHttpGet(@RequestParam(value = "name") String name, @RequestParam(value = "address") String address) {
    System.out.println("name:" + name + ",address" + address);
    User user = new User(name, address);
    return user.toString();
}
```
the request header like following(name=中文乱码&address=地址)
```
GET /http-get?name=%e4%b8%ad%e6%96%87%e4%b9%b1%e7%a0%81&address=%E5%9C%B0%E5%9D%80 HTTP/1.1
Host: localhost:8081
Connection: keep-alive
Cache-Control: max-age=0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36
Accept-Encoding: gzip, deflate, sdch
Accept-Language: zh-CN,zh;q=0.8,en;q=0.6
Cookie: JSESSIONID=501E0A814B786AF3C020B2D45929AD32
```
the console output like following
```
name:????????±???,address??°???
```
**Solution1**

change tomcat `server.xml` like following(add `URIEncoding="UTF-8"`) can solve this problem. WHY? (Because
parameters of `GET` method are in URI).  
```xml
<Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" URIEncoding="UTF-8"/>
```
**Solution2**

If you don't want change the config of tomcat you can transcode it by yourself.
```java
@RequestMapping(value = "/http-get", method = RequestMethod.GET)
@ResponseBody
public String testHttpGet(@RequestParam(value = "name") String name, @RequestParam(value = "address") String address) {
    name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
    address = new String(address.getBytes("ISO-8859-1"), "UTF-8");
    System.out.println("name:" + name + ",address" + address);
    User user = new User(name, address);
    return user.toString();
}
```

### POST method
If your use 'POST' method to make a request, the code of controller like following.
```java
@RequestMapping(value = "/http-post-form-urlencoded", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
@ResponseBody
public String testHttpPostFormUrlEncode(@ModelAttribute User user) {
    System.out.println(user.toString());
    return user.toString();
}
```
The Request Header like following
```
POST /http-post-form-urlencoded HTTP/1.1
Host: localhost:8081
Connection: keep-alive
Content-Length: 68
Cache-Control: max-age=0
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Origin: http://localhost:8081
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36
Content-Type: application/x-www-form-urlencoded
Referer: http://localhost:8081/index.jsp
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.8,en;q=0.6
Cookie: JSESSIONID=EC848DC0F4E4C1C650925DBBE88266F4
```
Form data
```
name=%E4%B8%AD%E6%96%87%E4%B9%B1%E7%A0%81&address=%E5%9C%B0%E5%9D%80
```
the console output 
```
User{name='????????±???', address='??°???'}
```

**Solution1(recommend)**

add `CharacterEncodingFilter` to you `web.xml` file like following. 
```xml
<filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
        <param-name>forceEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

**Solution2**

You can also transcode the parameters manually.
```java
@RequestMapping(value = "/http-post-form-urlencoded", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
@ResponseBody
public String testHttpPostFormUrlEncode(@ModelAttribute User user) throws UnsupportedEncodingException {
    user.setName(new String(user.getName().getBytes("ISO-8859-1"),"UTF-8"));
    user.setAddress(new String(user.getAddress().getBytes("ISO-8859-1"),"UTF-8"));
    System.out.println(user.toString());
    return user.toString();
}
```