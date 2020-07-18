<%--
  Created by IntelliJ IDEA.
  User: Maycon
  Date: 18/07/2020
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload Base64</title>
</head>
<body>
<form enctype="application/x-www-form-urlencoded" action="<%=request.getContextPath()%>/rest/carros/postFotoBase64"
      method="post">
    FileName:
    <input type="text" name="fileName" >

    <br> <br>

    Base64:
    <textarea name="base64" cols="60" rows="10"></textarea>

    <br> <br>

    <input type="submit" value="Enviar arquivo">
</form>
</body>
</html>
