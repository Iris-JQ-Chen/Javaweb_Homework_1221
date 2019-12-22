<%--
  Created by IntelliJ IDEA.
  User: 蒲公英之流
  Date: 2019-12-08
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String msg = (String)session.getAttribute("msg");
    if ("".equals(msg) && msg == null){
    } else {
        %>
        <script type="text/javascript">
            alert("<%=msg%>");
        </script>
<%
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>


    <form action="index.jsp">
        <input type="text" value="Value" name="Name">
        <input type="submit" value="Submit">
    </form>


</body>
</html>
