# Spring Boot upload file with ajax example
Last blog [Spring Boot upload file with form example](http://www.henryxi.com/spring-boot-upload-file-with-form-example) shows how to upload
 file with form. As we all know upload file with Ajax can improves user experience. Today I will show you
  how to upload file with Ajax. Same as last blog we use jsp as page. You can click [here](http://www.henryxi.com/spring-boot-jsp-examples) to learn how to
  use jsp in Spring Boot.

**Project Structure**
```
├─main
│  ├─java
│  │  └─com
│  │      └─henryxi
│  │          └─upload
│  │              └─ajax
│  │                      SampleUploadController.java
│  │
│  └─resources
│      │  application.properties
│      │
│      └─META-INF
│          └─resources
│              │  jquery.1.12.0.js
│              │
│              └─WEB-INF
│                  └─jsp
│                          upload.jsp
```
The dependencies in pom file are same as [Spring Boot upload file with form example](http://www.henryxi.com/spring-boot-upload-file-with-form-example).
To use ajax upload file you need add ``jquery`` in the ``META_INF/resource`` folder. Following paths
 in Spring Boot defined as default resource directories.

* /META-INF/resources/
* /resources/
* /static/
* /public/

Here we use ``/META-INF/resources/`` as static resource directory. 

**jsp file**
```

```