<%@ page import="java.util.List" %>
<%@ page import="bean.institute" %>
<%@ page import="dao.DBInstitute" %>
<%@ page import="bean.student" %>
<%@ page import="dao.DBStudentInfo" %>
<%@ page import="java.util.LinkedList" %><%--
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


    List<student> studentList = new LinkedList<>();
    int studentLength = 0;
    String studentShow = request.getParameter("studentShow");
    if (null == studentShow){
        studentList = DBStudentInfo.queryStudentAll();
    } else if ("1".equalsIgnoreCase(studentShow)){
        studentList = DBStudentInfo.queryStudentByinstituteNo(1);
    } else if ("2".equalsIgnoreCase(studentShow)){
        studentList = DBStudentInfo.queryStudentByinstituteNo(2);
    } else if ("3".equalsIgnoreCase(studentShow)){
        studentList = DBStudentInfo.queryStudentByinstituteNo(3);
    } else if ("4".equalsIgnoreCase(studentShow)){
        studentList = DBStudentInfo.queryStudentByStudentSex("男");
    } else if ("5".equalsIgnoreCase(studentShow)){
        studentList = DBStudentInfo.queryStudentByStudentSex("女");
    } else {
        studentList = DBStudentInfo.queryStudentAll();
    }
    if (studentList != null && !studentList.isEmpty()){
        studentLength = studentList.size();
    } else {}
    System.out.println(studentLength);
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
            $("#changeStudentInfoDiv").hide();
            $("#showOneStudentInfoDiv").hide();
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
        function showAll() {
            window.location.href = "http://localhost:8080/HomeAdmin.jsp";
        }
        function changeStudentInfo(id) {
            var index = id[0];
            var studentNo = document.getElementById(index+"student_no").innerText;
            var studentName = document.getElementById(index+"student_name").innerText;
            var studentSex = document.getElementById(index+"student_sex").innerText;
            var instituteNo = document.getElementById(index+"student_institute").innerText;
            var studentGrade = document.getElementById(index+"student_grade").innerText;
            var studentDormitoryNo = document.getElementById(index+"student_dormitory_no").innerText;
            var studentTel = document.getElementById(index+"student_tel").innerText;

            $("#changeStudentNo").val(studentNo);
            $("#changeStudentName").val(studentName);
            $("#changeStudentSex").val(studentSex);
            $("#changeInstituteName").val(instituteNo);
            $("#changeStudentGrade").val(studentGrade);
            $("#changeDormitoryNo").val(studentDormitoryNo);
            $("#changeStudentTel").val(studentTel);
            $("#changeStudentInfoDiv").show();
        }
        function queryStudentByStudentNo() {

            var sNo = $("#queryStudentByStudentNo").val();
            $.post(
                "http://localhost:8080/QueryAllUsers",
                {
                    studentNo:sNo
                },
                function (data,status) {
                    alert(status);
                    var obj = JSON.parse(data);
                    $("#one_student_no").html(obj.studentNo.toString());
                    $("#one_student_name").html(obj.studentName);
                    $("#one_student_sex").html(obj.studentSex);
                    $("#one_student_institute").html(obj.instituteNo);
                    $("#one_student_grade").html(obj.studentGrade);
                    $("#one_student_dormitory").html(obj.dormitoryNo);
                    $("#one_student_tel").html(obj.studentTel);
                    switch (obj.instituteNo) {
                        case 1:
                            $("#one_student_institute").html("物联网工程学院");
                            break;
                        case 2:
                            $("#one_student_institute").html("企业管理学院");
                            break;
                        case 3:
                            $("#one_student_institute").html("机电院");
                            break;
                    }
                    $("#showOneStudentInfoDiv").show();
                }
            );
        }
        function changeStudentInfoByOne() {
            $("#changeStudentNo").val($("#one_student_no").text());
            $("#changeStudentName").val($("#one_student_name").text());
            $("#changeStudentSex").val($("#one_student_sex").text());
            $("#changeInstituteName").val($("#one_student_institute").text());
            $("#changeStudentGrade").val($("#one_student_grade").text());
            $("#changeDormitoryNo").val($("#one_student_dormitory").text());
            $("#changeStudentTel").val($("#one_student_tel").text());
            $("#changeStudentInfoDiv").show();
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
                <br><br><br><br>
                <li><button type="button" class="btn btn-primary btn-lg btn-block">按钮1</button></li>
                <br><br><br><br>
                <li><button type="button" class="btn btn-primary btn-lg btn-block">按钮2</button></li>
                <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            </ul><!-- nav nav-pills nav-stacked -->
            <hr class="hidden-sm hidden-md hidden-lg">
        </div><!-- col-sm-4 -->
        <div class="col-sm-8">
            <h2>学院信息</h2><br>
            <table id="institute_table" class="table table-striped table-hover">
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
            <h2>学生信息</h2>
            <div class="btn-group" >
                <button onclick="showAll(this)" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">查看全部学生信息</button>
            </div>&nbsp;&nbsp;&nbsp;
            <div class="btn-group" >
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">按学院查看学生信息
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li>
                        <a href="http://localhost:8080/HomeAdmin.jsp?studentShow=1">物联网工程学院</a>
                    </li>
                    <li>
                        <a href="http://localhost:8080/HomeAdmin.jsp?studentShow=2">企业管理学院</a>
                    </li>
                    <li>
                        <a href="http://localhost:8080/HomeAdmin.jsp?studentShow=3">机电院</a>
                    </li>
                </ul>
            </div>&nbsp;&nbsp;&nbsp;
            <div class="btn-group" >
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">按性别查看学生信息
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li>
                        <a href="http://localhost:8080/HomeAdmin.jsp?studentShow=4">男</a>
                    </li>
                    <li>
                        <a href="http://localhost:8080/HomeAdmin.jsp?studentShow=5">女</a>
                    </li>
                </ul>
            </div>
            <br><br>
            <div class="pre-scrollable" style="height: 280px; margin-top: -22px;">
                <table id="student_table" class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>学号</th><th>姓名</th><th>性别</th><th>学院</th><th>年级</th><th>宿舍号</th><th>联系方式</th>
                    </tr>
                    </thead>
                    <tbody id="student_tbody">
                    <%for (int j = 0 ; j < studentLength ; j++){%>
                    <tr>
                        <td id="<%=j+"student_no"%>"><%=studentList.get(j).getStudentNo()%></td>
                        <td id="<%=j+"student_name"%>"><%=studentList.get(j).getStudentName()%></td>
                        <td id="<%=j+"student_sex"%>"><%=studentList.get(j).getStudentSex()%></td>

                        <% if (studentList.get(j).getInstituteNo() == 1) { %>
                        <td id="<%=j+"student_institute"%>">物联网工程学院</td>
                        <% } else if (studentList.get(j).getInstituteNo() == 2) { %>
                        <td id="<%=j+"student_institute"%>">企业管理学院</td>
                        <% } else { %>
                        <td id="<%=j+"student_institute"%>">机电院</td>
                        <% } %>

                        <td id="<%=j+"student_grade"%>"><%=studentList.get(j).getStudentGrade()%></td>
                        <td id="<%=j+"student_dormitory_no"%>"><%=studentList.get(j).getDormitoryNo()%></td>
                        <td id="<%=j+"student_tel"%>"><%=studentList.get(j).getStudentTel()%></td>
                        <td><button type="button" onclick="changeStudentInfo(this.id)" class="btn btn-info" id="<%=j+"changeStudentInfo"%>">修改信息</button></td>

                    </tr>
                    <%}%>
                    </tbody>
                </table>
            </div>
            <br>
        </div><!-- col-sm-8 -->
        <div class="col-sm-8">
            <h2>学号查询学生信息</h2>
            <div class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-sm-2 control-label">学号</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="queryStudentByStudentNo" name="studentNo" placeholder="请输入学号">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button onclick="queryStudentByStudentNo()" class="btn btn-default">查询</button>
                    </div>
                </div>
            </div>
        </div><!-- cool-sm-8 -->
        <div id="showOneStudentInfoDiv" class="col-sm-8">
            <h2>您所查询的学生信息如下</h2>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>学号</th><th>姓名</th><th>性别</th><th>学院</th><th>年级</th><th>宿舍号</th><th>联系方式</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td id="one_student_no"></td>
                    <td id="one_student_name"></td>
                    <td id="one_student_sex"></td>
                    <td id="one_student_institute"></td>
                    <td id="one_student_grade"></td>
                    <td id="one_student_dormitory"></td>
                    <td id="one_student_tel"></td>
                    <td><button type="button" onclick="changeStudentInfoByOne()" class="btn btn-info" >修改信息</button></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div id="changeStudentInfoDiv" class="col-sm-8">
            <h2>学生信息修改</h2><br>
            <form class="form-horizontal" role="form" action="/StudentTransaction" method="post">
                <div class="form-group">
                    <label class="col-sm-2 control-label">学号</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="changeStudentNo" name="changeStudentNo" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="changeStudentName" name="changeStudentName" disabled="disabled" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="changeStudentSex" name="changeStudentSex" disabled="disabled" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">学院</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="changeInstituteName" name="changeInstituteName" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">年级</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="changeStudentGrade" name="changeStudentGrade" disabled="disabled" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">宿舍号</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="changeDormitoryNo" name="changeDormitoryNo" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">联系方式</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="changeStudentTel" name="changeStudentTel" value="">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">提交修改</button>
                    </div>
                </div>
            </form>
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
