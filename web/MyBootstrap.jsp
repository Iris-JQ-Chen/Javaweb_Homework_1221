<%--
  Created by IntelliJ IDEA.
  User: 蒲公英之流
  Date: 2019-12-22
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap 模板</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</head>
<body>
<div class="header-panel">
    <h1>Bootstrap4使用</h1>
</div>
<div class="container">
    <p><button class="btn btn-primary" value="添加岗位信息" >添加岗位信息</button></p>
    <p><button class="btn btn-primary" value="查看全部岗位信息" >查看全部岗位信息</button></p>

    <p>
	    <span class="input-group-addon">
	        <input name="workername" type="text" class="form-control" placeholder="请输入要查询的岗位名称" >
	    </span>
        <button class="btn btn-info" value="按照岗位名称查询" >按照岗位名称查询</button>
    </p>
</div>
<div class="graphic-container">

    <table class="table">
        <thead>
        <tr><th>编号</th><th>姓名</th><th>年龄</th></tr>
        </thead>
        <tbody>
        <tr class="active"><td>001</td><td>郭靖</td><td>25</td></tr>
        <tr class="success"><td>002</td><td>黄蓉</td><td>23</td></tr>
        <tr class="info"><td>003</td><td>杨过</td><td>24</td></tr>
        <tr class="warning"><td>004</td><td>黄老邪</td><td>54</td></tr>
        <tr class="danger"><td>005</td><td>欧阳锋</td><td>42</td></tr>
        </tbody>
    </table>
</div>
<script src="jQuery/jquery-3.4.1.js"></script>
<%--<script src="js/popper.min.js"></script>--%>
<%--<script src="js/bootstrap.min.js"></script>--%>
</body>
</html>
