<%--
  Created by IntelliJ IDEA.
  User: 蒲公英之流
  Date: 2019-12-27
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>
    <meta charset="utf-8">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/moment.js/2.22.0/moment-with-locales.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

    <script>
        $(function() {
            $('#form1').hide();
            var picker1 = $('#datetimepicker1').datetimepicker({
                format: 'YYYY-MM-DD',
                locale: moment.locale('zh-cn'),
                //minDate: '2016-7-1'
            });
        });
        $('#datetimepicker1').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-cn'),
            defaultDate: "2018-1-1"
        });
        function myButton() {
            alert($("#datetimepicker1").find("input").val());
        }
    </script></head>
<body>
<div class='input-group date' style="width: 14em;" id='datetimepicker1'>
    <input type='text' class="timeInput form-control" />
    <span class="input-group-addon">
        <span class="glyphicon glyphicon-calendar", id="A1"></span>
    </span>
</div>
<button onclick="myButton()">!!!</button>
</body>
</html>
