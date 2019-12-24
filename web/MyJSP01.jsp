<%--
  Created by IntelliJ IDEA.
  User: 蒲公英之流
  Date: 2019-12-24
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript" src="jQuery/jquery-3.4.1.js"></script>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script type="text/css">
        html, body {
            height: 100%;    //body默认高度0    height假设300px
            margin: 0px;
            padding: 0px;
        }
        #main {
            height: 100%;   //继承宽度  而不会继承高度
        }
        #nav {
            background-color:green;
            height: 50px;
        }
        #content{
            background-color:pink;
            position:absolute;
            width:100%;       //定位后脱离文档流   不自动继承父元素的宽高
            top:50px;
            bottom:0px;
        }
    </script>
</head>
<body>
<div id="main">
    <div id="nav">nav</div>
    <div id="content">content</div>
</div>
</body>
</html>
