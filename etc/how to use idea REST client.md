# Use Intellij IDEA REST Client POST json
If you develop RESTful Web Service you can use REST Client to test it. The tool can help you submitting
a request. It is in **Tools** | **Test RESTful Web Service**. After opening the tool you can choose the 
HTTP method. This tool support common methods(`POST`,`GET`,`DELETE` etc.). In this page I will show you 
how to use it POST json.

Before posting json to server you need specify the `Content-Type` in HTTP headers. If you can not decide which
`Content-Type` to use you can see [Common Content-Type when POST data](http://www.henryxi.com/common-content-type-when-post-data).
For post json to server we use `application/json` as `Content-Type`. In the Request Body pane we choose
second option to send json.

After input all parameters click Submit button on the top left can send POST request to server.
![REST Client](http://a.disquscdn.com/uploads/mediaembed/images/3800/5825/original.jpg)