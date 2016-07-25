<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>post</title>
</head>
<body>
<script type="application/javascript" src="jquery.1.12.0.js"></script>
<input id="postXmlBtn" type="button" value="post xml">
<br>
<input id="postJsonBtn" type="button" value="post json">
<div id="content"></div>
<script type="application/javascript">
    $('#postXmlBtn').click(function () {
        var xml = '';
        $.ajax({
            url: 'post-xml',
            type: 'POST',
            contentType: "application/xml",
            data: '<user><name>henryxi</name><age>28</age></user>',
            success: function (data) {
                $('#content').html(data);
            },
            error: function (xhr, status, error) {
                var err = eval("(" + xhr.responseText + ")");
                alert(err.Message);
            }
        });
    });
    $('#postJsonBtn').click(function () {
        $.post('post-json', {"name": "henry", "age": 28}, function (data) {
            $('#content').html(data);
        })
    });
</script>
</body>
</html>
