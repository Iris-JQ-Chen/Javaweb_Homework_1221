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
                $.post("http://localhost:8080/ChangePwd",{
                        name:"dfjlsdkflaskdjflak",
                        url:"http://www.runoob.com"
                    },
                    function(data,status){
                        alert("数据: \n" + data + "\n状态: " + status);
                    });
            });

            alert(document.getElementById("in").innerText);
        });
    </script>
</head>
<body>

<table class="table">
    <%--<caption>基本的表格布局</caption>--%>
    <thead>
    <tr>
        <th>学院</th><th>辅导员</th><th>辅导员联系方式</th><th></th><th></th>
    </tr>
    </thead>
    <tbody id="institute_tbody">
    <tr>
        <td id="institute_name">学院</td>
        <td id="institute_coach">辅导员</td>
        <td id="institute_coach_tel">联系方式</td>
        <td><button type="button" id="changeCoach" class="btn btn-info">更换辅导员</button></td>
        <td><button type="button" id="changeCoachTel" class="btn btn-info">修改联系方式</button></td>
    </tr>
    </tbody>
</table>

<button>发送一个 HTTP POST 请求页面并获取返回内容</button>

</body>
</html>
