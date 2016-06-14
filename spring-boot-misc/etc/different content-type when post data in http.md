# Common Content-Type when POST data
There are 3 common `Content-Type` when POST data to server. We use `Content-Type` to tell server
the type of data. This page will list 3 common `Content-Type` in POST.

**application/x-www-form-urlencoded**

When we submit a form without setting `enctype` property browser will specify `application/x-www-form-urlencoded`
as `Content-Type`. The data in the form will be submitted like this `key1=value1&key2=value2`. The keys
and values are all URL-encoded. 

**multipart/form-data**

When you upload file in a form we need set `enctype` to `multipart/form-data`. The HTTP request headers of
it like following.
```text
POST /imageUpload/actionUpload.do HTTP/1.1
Host: localhost:8080
Connection: keep-alive
Content-Length: 31571
Accept: */*
Origin: http://localhost:8080
X-Requested-With: XMLHttpRequest
User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary2FlzWv0VbAd5M00X
Referer: http://localhost:8080/index.jsp
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.8,en;q=0.6
Cookie: JSESSIONID=66BFE05C042F7CD8D30798ED008AF84A
```

**application/json**

This type tell server the body of this request is JSON. You can use `JSON.stringify` to get a JSON string
and post it to server. In this way you can POST complex data and parse it in server easily. The HTTP 
request headers of it like following.
```text
POST /json.do HTTP/1.1 
Content-Type: application/json;charset=utf-8

{"name":"Henry","sub":[1,2,3]}
```

