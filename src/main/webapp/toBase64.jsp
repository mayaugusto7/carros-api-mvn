<%--
  Created by IntelliJ IDEA.
  User: Maycon
  Date: 18/07/2020
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ToBase64</title>
</head>
<body>
<form enctype="multipart/form-data" action="<%=request.getContextPath()%>/rest/carros/toBase64" method="post">
    <input type="file" name="file" >
    <br> <br>
    <input type="submit" value="Enviar arquivo">
</form>
</body>
</html>
