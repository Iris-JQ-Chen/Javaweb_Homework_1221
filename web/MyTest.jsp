<%--
  Created by IntelliJ IDEA.
  User: 蒲公英之流
  Date: 2019-12-23
  Time: 2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>菜鸟教程(runoob.com)</title>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
    </script>
    <script>
        $(document).ready(function(){
            $("button").click(function(){
                $.post("http://localhost:8080/change",{
                        name:"dfjlsdkflaskdjflak",
                        url:"http://www.runoob.com"
                    },
                    function(data,status){
                        alert("数据: \n" + data + "\n状态: " + status);
                    });
            });
        });
    </script>
</head>
<body>

<button>发送一个 HTTP POST 请求页面并获取返回内容</button>

</body>
</html>
