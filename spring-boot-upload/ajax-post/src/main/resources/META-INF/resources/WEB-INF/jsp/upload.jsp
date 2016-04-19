<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>upload file</title>
</head>
<body>
<script type="application/javascript" src="../jquery.1.12.0.js"></script>
<form action="upload" enctype="multipart/form-data">
    <input type="file" id="file" name="file">
    <input id="uploadBtn" type="button" value="upload">
</form>
<div id="content"></div>
<script type="application/javascript">
    $('#uploadBtn').click(function(){
        var formData = new FormData();
        formData.append('file', $('#file')[0].files[0]);

        $.ajax({
            url : 'upload',
            type : 'POST',
            data : formData,
            processData: false,  // tell jQuery not to process the data
            contentType: false,  // tell jQuery not to set contentType
            success : function(data) {
                $('#content').html(data);
            }
        });
    });
</script>
</body>
</html>
