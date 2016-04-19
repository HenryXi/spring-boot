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
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>upload file</title>
</head>
<body>
<script type="application/javascript" src="jquery.1.12.0.js"></script>
<input type="file" id="file" name="file">
<input id="uploadBtn" type="button" value="upload">
<div id="content"></div>
<script type="application/javascript">
    $('#uploadBtn').click(function () {
        var formData = new FormData();
        formData.append('file', $('#file')[0].files[0]);

        $.ajax({
            url: 'upload',
            type: 'POST',
            data: formData,
            processData: false,  // tell jQuery not to process the data
            contentType: false,  // tell jQuery not to set contentType
            success: function (data) {
                $('#content').html(data);
            }
        });
    });
</script>
</body>
</html>
```

Run main method and visit http://localhost:8080 then choose file and upload. You can see the name and 
content of the file. As you can see Ajax is more friendly then form submit.