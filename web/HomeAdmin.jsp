<%@ page import="java.util.List" %>
<%@ page import="bean.institute" %>
<%@ page import="dao.DBInstitute" %><%--
  Created by IntelliJ IDEA.
  User: 蒲公英之流
  Date: 2019-12-22
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<institute> instituteList = DBInstitute.queryInstituteAll();
    int instituteLength = 0;
    if (instituteList != null && !instituteList.isEmpty()){
        instituteLength = instituteList.size();
    } else {}
%>
<html>
<head>
    <title>管理员首页</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript" src="jQuery/jquery-3.4.1.js"></script>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <style>
        .fakeimg {
            height: 200px;
            background: #aaa;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#changePassword").click(
                function () {
                    var newPwd = window.prompt("请输入新密码","");
                    $.post(
                        "http://localhost:8080/ChangePwd",
                        {
                            pp:newPwd.toString(),
                        },
                        function(data,status){
                            alert("数据: \n" + data);
                        }
                    );
                }
            );
        })
        function changeCoach(id) {
            var newCoach = window.prompt("请输入辅导员姓名","");
            var newCoachTel = window.prompt("请输入辅导员电话","");

            var index = id[0];
            var iName = document.getElementById(index+"institute_name").innerText;
            var iCoach = document.getElementById(index+"institute_coach").innerText;
            var iCoachTel = document.getElementById(index+"institute_coach_tel").innerText;

            $.post(
                "http://localhost:8080/institute",
                {
                    name:iName,
                    coach:newCoach,
                    coachTel:newCoachTel,
                    type:"1"
                },
                function (data,status) {
                    if ("success" == status){
                        window.location.reload();
                    } else {
                        alert("修改失败");
                    }
                }
            );
        }
        function changeCoachTel(id) {
            var newCoachTel = window.prompt("请输入新电话","");

            var index = id[0];
            var iName = document.getElementById(index+"institute_name").innerText;
            var iCoach = document.getElementById(index+"institute_coach").innerText;
            var iCoachTel = document.getElementById(index+"institute_coach_tel").innerText;

            $.post(
                "http://localhost:8080/institute",
                {
                    name:iName,
                    coach:iCoach,
                    coachTel:newCoachTel,
                    type:"2"
                },
                function (data,status) {
                    if ("success" == status){
                        window.location.reload();
                    } else {
                        alert("修改失败");
                    }
                }
            );
        }
    </script>
</head>
<body>

<div class="jumbotron text-center" style="margin-bottom: 0">
    <h1>事务管理</h1>
    <p>管理员您好</p>
</div>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">宿舍管理系统</a>
        </div><!-- navbar-header -->
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="http://localhost:8080/Login">退出登录</a></li>
                <li><a href="#">页面2</a></li>
                <li><a href="#">页面3</a></li>
            </ul><!-- nav navbar-nav -->
        </div><!-- collapse navbar-collapse -->
    </div><!-- container-fluid -->
</nav>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h2>具体功能</h2>
            <h5>我的照片</h5>
            <div class="fakeimg">这边插入图片</div>
            <p>关于我的介绍的咖啡机的 点击放假了时代峰峻按理说单身快乐就发链接sdlkfjsldkfjsldkfjksdlfjlsdfsdj</p>
            <h3>链接</h3>
            <p>描述文本 打开附近拉斯柯达就发了啥快递费金老师的 拉达克福建省来得快</p>
            <ul class="nav nav-pills nav-stacked">
                <li><button type="button" id="changePassword" class="btn btn-primary btn-lg btn-block">修改密码</button></li>
                <br>
                <li><button type="button" class="btn btn-primary btn-lg btn-block">按钮1</button></li>
                <br>
                <li><button type="button" class="btn btn-primary btn-lg btn-block">按钮2</button></li>
            </ul><!-- nav nav-pills nav-stacked -->
            <hr class="hidden-sm hidden-md hidden-lg">
        </div><!-- col-sm-4 -->
        <div class="col-sm-8">
            <h2>学院信息</h2>
            <table id="institute_table" class="table">
                <%--<caption>基本的表格布局</caption>--%>
                <thead>
                <tr>
                    <th>学院</th><th>辅导员</th><th>辅导员联系方式</th><th></th><th></th>
                </tr>
                </thead>
                <tbody id="institute_tbody">
                <%for (int i = 0 ; i < instituteLength ; i++){%>
                    <tr>
                        <td id="<%=i+"institute_name"%>"><%=instituteList.get(i).getInstituteName()%></td>
                        <td id="<%=i+"institute_coach"%>"><%=instituteList.get(i).getCoach()%></td>
                        <td id="<%=i+"institute_coach_tel"%>"><%=instituteList.get(i).getCoachTel()%></td>
                        <td><button type="button" onclick="changeCoach(this.id)" class="btn btn-info" id="<%=i+"changeCoach"%>">更换辅导员</button></td>
                        <td><button type="button" onclick="changeCoachTel(this.id)" class="btn btn-info" id="<%=i+"changeCoachTel"%>">修改联系方式</button></td>
                    </tr>
                <%}%>
                </tbody>
            </table>
            <br>
        </div><!-- col-sm-8 -->
        <div class="col-sm-8">
            <h2>标题</h2>
            <h5>副标题</h5>
            <div class="fakeimg">图像</div>
            <p>一些文本....速度快放假了深刻大姐夫阿萨德路口附近了深刻的房间里萨迪克</p>
            <p>菜鸟教程，学的不仅是技术，更是梦想！！！菜鸟教程，学的不仅是技术，更是梦想！！！菜鸟教程，学的不仅是技术，更是梦想！！！</p>
            <br>
        </div><!-- col-sm-8 -->
    </div><!-- row -->
</div><!-- container -->

<div class="jumbotron text-center" style="margin-bottom: 0">
    <p>底部内容</p>
</div><!-- jumbotron text-center -->

</body>
</html>
