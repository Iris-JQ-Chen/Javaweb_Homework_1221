<%--
  Created by IntelliJ IDEA.
  User: 蒲公英之流
  Date: 2019-12-08
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="MyServlet.*" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    new Servlet_01();
%>

    <form action="index.jsp">
        <input type="text" value="Value" name="Name">
        <input type="submit" value="Submit">
    </form>


</body>
</html>
