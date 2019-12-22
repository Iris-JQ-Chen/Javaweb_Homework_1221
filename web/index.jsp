<%--
  Created by IntelliJ IDEA.
  User: 蒲公英之流
  Date: 2019-12-21
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String msg = (String) session.getAttribute("msg");
    if ("".equals(msg) || msg == null){
    } else if ("登录失败".equals(msg)){
        %>
        <script type="text/javascript">
            alert("登录失败\n请重试");
        </script>
<%
    }
%>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script type="text/javascript" src="jQuery/jquery-3.4.1.js"></script>
    <script>
    $(function () {
      // $("#hide").click(function () {
      //   $("p").hide();
      // });
      // $("#show").click(function () {
      //   $("p").show();
      // });
      $("#button01").click(
              function () {
                $.post(
                        "http://localhost:8080/Servlet_04",
                        {
                          name:"lfjlsdflsdkjflsdfjlsdjf",
                          url:"http://www.runoob.com"
                        },
                        function (data,status) {
                          alert(data+"\n"+status);
                        }
                );
              }
      );
    });
    </script>
</head>
<body>
<div class="modal-dialog" style="margin-top: 10%">
    <form action="/Login" method="get">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title text-center" >学生管理系统登录</h4>
            </div>
            <div class="modal-body" id="model-body">
                <div class="form-group">
                    <input type="text" class="form-control" name="userName" placeholder="用户名">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="userPassword" placeholder="密码">
                </div>
                <div class="form-group">
                    <label class="radio-inline">
                        <input type="radio" name="optionsRadiosinline" id="optionsRadios1" value="3" checked> 学生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="optionsRadiosinline" id="optionsRadios2" value="1" > 管理员&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="optionsRadiosinline" id="optionsRadios3" value="2" > 宿管员
                    </label>
                </div>
            </div>
            <div class="modal-footer">
                <div class="form-group">
                    <button type="submit" class="btn btn-primary form-control" >登录</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </form>
</div><!-- /.modal-dialog -->
</body>
</html>

