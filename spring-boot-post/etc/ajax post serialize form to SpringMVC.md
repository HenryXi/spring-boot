# Ajax post serialized form to SpringMVC
You can submit a form directly or use Ajax to submit data.In this page I will show you how to use Ajax post 
serialized form data to SpringMVC. In this way your application will be more friendly.

**controller**
```java
@RequestMapping(path = "/post-json", method = RequestMethod.POST)
@ResponseBody
public String postJson(User user) {
    return user.toString();
}
```
**JQuery code**
```javascript
$("#serializeAndPost").click(function () {
    x = $("form").serializeArray();
    $.post('post-json', x, function (data) {
        $('#content').html(data);
    })
});
```
**form html**

`serializeArray` method will convert form to json object(not json string). Every input element needs `name` and `value` property.
```html
<form action="">
    name: <input type="text" name="name" value="HenryXi"/><br/>
    age : <input type="text" name="age" value="28"/><br/>
</form>
```