<%@ page import="java.util.List" %>
<%@ page import="bean.*" %>
<%@ page import="dao.*" %><%--
  Created by IntelliJ IDEA.
  User: 蒲公英之流
  Date: 2019-12-22
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userNo = new String();
    Cookie[] cookies = request.getCookies();
    student std = new student();
    dormitoryManager dormitoryManager = new dormitoryManager();
    if (cookies != null){
        for (Cookie cookie : cookies){
            if ("name".equalsIgnoreCase(cookie.getName())) {
                if (!"".equalsIgnoreCase(cookie.getName())){
                    userNo = cookie.getValue();
                }
            }
        }
    }
    if (!"".equalsIgnoreCase(userNo)){
        std = DBStudentInfo.queryStudentByStudentNo(userNo);
    }
    List<dormitoryManager> dormitoryManagerList = DBDormitoryManager.queryDormitoryManagerByStudentNo(userNo);
    List<leaveRecord> leaveRecordList = DBLeaveRecord.queryLeaveRecordByStudentNo(userNo);
    List<hygieneRecord> hygieneRecordList = DBHygieneRecord.queryHygieneRecordByStudentNo(userNo);

    List<institute> instituteList = DBInstitute.queryInstituteAll();
    int instituteLength = 0;
    if (instituteList != null && !instituteList.isEmpty()){
        instituteLength = instituteList.size();
    } else {}
%>
<html>
<head>
    <title>学生首页</title>
    <meta charset="utf-8">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/moment.js/2.22.0/moment-with-locales.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
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
                    $.post("http://localhost:8080/ChangePwd",
                        {
                            pp:newPwd.toString(),
                        },
                        function(data,status){
                            alert("数据: \n" + data + "\n状态: " + status);
                        });
                }
            );
            var picker1 = $('#leaveDate').datetimepicker({
                format: 'YYYY-MM-DD',
                locale: moment.locale('zh-cn'),
                //minDate: '2016-7-1'
            });
            var picker2 = $('#leaveExbackDate').datetimepicker({
                format: 'YYYY-MM-DD',
                locale: moment.locale('zh-cn'),
                //minDate: '2016-7-1'
            });
        })
        $('#leaveDate').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-cn'),
            defaultDate: "2018-1-1"
        });
        $('#leaveExbackDate').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-cn'),
            defaultDate: "2018-1-1"
        });
        function confirmBack(id) {
            var index = id[0];
            alert(index);
            $.post(
                "http://localhost:8080/ConfirmBack",
                {
                    leaveId:id
                },
                function (data, status) {
                    alert(data+status);
                    window.location.href = "http://localhost:8080/HomeStudent.jsp"
                }
            );
        }
        function myRetire(id) {
            $.post(
                "http://localhost:8080/RetireDormitory",
                {
                    studentNo: id,
                },
                function (data,status) {
                    alert(data);
                    window.location.href = "http://localhost:HomeStudent.jsp"; 
                }
            );
        }
    </script>
</head>
<body>

<div class="jumbotron text-center" style="margin-bottom: 0">
    <h1>学生使用</h1>
    <p>这位同学，您好</p>
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
                <%--<li><a href="#">页面2</a></li>--%>
                <%--<li><a href="#">页面3</a></li>--%>
            </ul><!-- nav navbar-nav -->
        </div><!-- collapse navbar-collapse -->
    </div><!-- container-fluid -->
</nav>

<div class="container">
    <div class="row">
        <div class="col-sm-4" style="background-color: lightcyan">
            <h2>河海大学</h2><br>
            <h5></h5>
            <br><br>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;河海大学（Hohai University），简称“河海（HHU）”，位于江苏省会南京市，是以水利为特色，工科为主，多学科协调发展的教育部直属，教育部、水利部、国家海洋局与江苏省人民政府共建的全国重点大学，是国家首批具有博士、硕士、学士三级学位授予权的单位，国家“211工程”重点建设、”985工程优势学科创新平台“建设以及设立研究生院的高校，是国家世界一流学科建设高校和国家卓越工程师教育培养计划高校。</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;河海大学的前身可以追溯到1915年创建于南京的“河海工程专门学校”，是中国第一所培养水利人才的高等学府。1924年与国立东南大学工科合并成立河海工科大学，1927年并入国立第四中山大学，后更名为国立中央大学、南京大学。1952年南京大学水利系、交通大学水利系、同济大学土木系水利组、浙江大学土木系水利组以及华东水利专科学校合并成立“华东水利学院”。1960年被中共中央认定为全国重点大学。1985年恢复传统校名“河海大学”。</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;根据2019年4月学校官网信息显示，学校在南京市、常州市设有西康路校区、江宁校区和常州校区，占地面积近2580亩；开设56个本科专业；有教职工3433名，各类学历教育在校学生51499名，其中研究生17142名，普通本科生19841名，成人教育学生13052名，留学生1464名。</p>
            <br><p>&nbsp;&nbsp;&nbsp;&nbsp;河海大学常州校区的前身为1986年成立的河海大学机械学院，1996年5月更名为河海大学常州分校，2000年6月更名为河海大学常州校区。</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;根据2019年4月常州校区官网信息显示，常州校区地处江苏省苏锡常经济高速发展地区，占地436亩。常州校区设有机电工程学院、物联网工程学院、企业管理学院和基础学部；有2个二级学科博士点，1个一级学科硕士点，7个二级学科硕士点，4个专业学位硕士研究生授权点，及15个本科专业；有本科生5503人，全日制博硕士研究生667人，留学研究生54人，继续教育学生5879人，教职工604人；有3个省部级科研平台。</p>
            <br><br><br><br><br>
            <h3>相关功能</h3>
            <br><br>
            <ul class="nav nav-pills nav-stacked">
                <li><button type="button" id="changePassword" class="btn btn-primary btn-lg btn-block">修改密码</button></li>
                <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            </ul><!-- nav nav-pills nav-stacked -->
            <hr class="hidden-sm hidden-md hidden-lg">
        </div><!-- col-sm-4 -->
        <div class="col-sm-8">
            <h2>个人信息</h2><br>
            <table id="student_table" class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>学号</th><th>姓名</th><th>性别</th><th>学院</th><th>年级</th><th>宿舍号</th><th>联系方式</th>
                </tr>
                </thead>
                <tbody id="student_tbody">
                <tr>
                    <td><%=std.getStudentNo()%></td>
                    <td><%=std.getStudentName()%></td>
                    <td><%=std.getStudentSex()%></td>
                    <% if (std.getInstituteNo() == 1) { %>
                    <td>物联网工程学院</td>
                    <% } else if (std.getInstituteNo() == 2) { %>
                    <td>企业管理学院</td>
                    <% } else if (std.getInstituteNo() ==3) {%>
                    <td><机电院></机电院></td>
                    <% } %>
                    <td><%=std.getStudentGrade()%></td>
                    <td><%=std.getDormitoryNo()%></td>
                    <td><%=std.getStudentTel()%></td>
                </tr>
                </tbody>
            </table>
            <br>
        </div><!-- col-sm-8 -->
        <div class="col-sm-8">
            <h2>学院信息</h2><br>
            <table id="institute_table" class="table table-striped table-hover">
                <%--<caption>基本的表格布局</caption>--%>
                <thead>
                <tr>
                    <th>学院</th><th>辅导员</th><th>辅导员联系方式</th>
                </tr>
                </thead>
                <tbody id="institute_tbody">
                <%for (int i = 0 ; i < instituteLength ; i++){%>
                <tr>
                    <td id="<%=i+"institute_name"%>"><%=instituteList.get(i).getInstituteName()%></td>
                    <td id="<%=i+"institute_coach"%>"><%=instituteList.get(i).getCoach()%></td>
                    <td id="<%=i+"institute_coach_tel"%>"><%=instituteList.get(i).getCoachTel()%></td>
                </tr>
                <%}%>
                </tbody>
            </table>
            <br>
        </div><!-- col-sm-8 -->
        <div class="col-sm-8">
            <h2>宿舍楼宿管员信息</h2><br>
            <table id="d_manager_table" class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>工号</th><th>姓名</th><th>楼号</th><th>辅导员联系方式</th>
                </tr>
                </thead>
                <tbody id="d_manager_tbody">
                <%for (int j = 0 ; j < dormitoryManagerList.size() ; j++){%>
                <tr>
                    <td><%=dormitoryManagerList.get(j).getManagerNo()%></td>
                    <td><%=dormitoryManagerList.get(j).getManagerName()%></td>
                    <td><%=dormitoryManagerList.get(j).getBuildingNo()%></td>
                    <td><%=dormitoryManagerList.get(j).getManagerTel()%></td>
                </tr>
                <%}%>
                </tbody>
            </table>
            <br>
        </div><!-- col-sm-8 -->
        <div class="col-sm-8">
            <h2>卫生检查信息</h2><br>
            <table id="hygiene_record_table" class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>卫生检查成绩</th><th>卫生检查时间</th><th>处理人</th>
                </tr>
                </thead>
                <tbody id="hygiene_record_tbody">
                <%for (int h = 0 ; h < hygieneRecordList.size() ; h++){%>
                <tr>
                    <td><%=hygieneRecordList.get(h).getHygieneGrade()%></td>
                    <td><%=hygieneRecordList.get(h).getRecordDate()%></td>
                    <td><%=hygieneRecordList.get(h).getManagerNo()%></td>
                </tr>
                <%}%>
                </tbody>
            </table>
            <br>
        </div><!-- col-sm-8 -->
        <div id="applyForLeaveDiv" class="col-sm-8">
            <h2>申请离校</h2><br>
            <form class="form-horizontal" role="form" action="/ApplyForLeave" method="post">
                <%--<div class="form-group">--%>
                    <%--<label class="col-sm-2 control-label">学号</label>--%>
                    <%--<div class="col-sm-10">--%>
                        <%--<input type="text" class="form-control" id="leaveStudentNo" name="leaveStudentNo" value="<%=userNo%>">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-group">
                    <label class="col-sm-2 control-label">目的地</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="leavePlace" name="leavePlace" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">外出原因</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="leaveReason" name="leaveReason" value="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">出发时间</label>
                    <div class="col-sm-10">
                        <div class='input-group date' style="width: 14em;" id='leaveDate'>
                            <input type='text' class="timeInput form-control" name="leaveDate" />
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">预计返回时间</label>
                    <div class="col-sm-10">
                        <div class='input-group date' style="width: 14em;" id='leaveExbackDate'>
                            <input type='text' class="timeInput form-control" name="leaveExbackDate" />
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar", id="A1"></span>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">提交</button>
                    </div>
                </div>
            </form>
            <br>
        </div><!-- col-sm-8 -->
        <div class="col-sm-8">
            <h2>离校记录</h2><br>
            <table id="leave_record_table" class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>目的地</th><th>外出原因</th><th>出发时间</th><th>预计返回时间</th><th>实际返回时间</th><th>审核情况</th><th>返校操作</th>
                </tr>
                </thead>
                <tbody id="leave_record_tbody">
                <%for (int k = 0 ; k < leaveRecordList.size() ; k++){%>
                <tr>
                    <td><%=leaveRecordList.get(k).getPlace()%></td>
                    <td><%=leaveRecordList.get(k).getReason()%></td>
                    <td><%=leaveRecordList.get(k).getLeaveDate()%></td>
                    <td><%=leaveRecordList.get(k).getExbackDate()%></td>

                    <% if (leaveRecordList.get(k).getAcbackDate() == null) { %>
                    <td>尚未返校</td>
                    <% } else { %>
                    <td><%=leaveRecordList.get(k).getAcbackDate()%></td>
                    <% } %>

                    <% if (leaveRecordList.get(k).getIsAprove() == 1) { %>
                    <td>已通过审核</td>
                    <% } else { %>
                    <td>未审核</td>
                    <% } %>

                    <% if (leaveRecordList.get(k).getAcbackDate() != null) {%>
                        <td>已返校</td>
                    <% } else { %>
                        <td><button type="button" onclick="confirmBack(this.id)" class="btn btn-info" id="<%=leaveRecordList.get(k).getLeaveId()%>">确认返校</button></td>
                    <% } %>
                </tr>
                <%}%>
                </tbody>
            </table>
            <br>
        </div><!-- col-sm-8 -->
        <div class="col-sm-8">
            <button type="button" onclick="myRetire(this.id)" id="<%=userNo%>" class="btn btn-primary btn-lg btn-block">退宿</button>
        </div>
    </div><!-- row -->
</div><!-- container -->

<div class="jumbotron text-center" style="margin-bottom: 0">
    <p>我是有底线的</p>
</div><!-- jumbotron text-center -->

</body>
</html>
