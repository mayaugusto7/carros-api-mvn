<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Olá Tomcat ♫♫♫</title>
  </head>
  <body>
    <h1>Olá Tomcat ♫♫♫</h1>
    <form method="post" action="<%=request.getContextPath()%>/hello">
      Nome: <input type="text" name="nome">
      <br/> <br/>
      Sobrenome: <input type="text" name="sobrenome">
      <br/>
      <input type="submit" name="Enviar" />
    </form>
  </body>
</html>