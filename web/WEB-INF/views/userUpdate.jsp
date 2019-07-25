<%--
  Created by IntelliJ IDEA.
  User: JX
  Date: 2019/6/2
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/user?state=check"></jsp:include>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>个人中心</title>
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css"/>
    <link href="../../resources/css/header.css" rel="stylesheet" type="text/css">
    <style>
        .input-group {
            margin: 10px 0 10px 15px;
            width: 280px;
        }
        .btn {
            margin: 10px 0 10px 100px;
        }
    </style>
</head>
<body>
<header>
    <div class="logo">
        <a href="https://book.douban.com/">布客网</a>
    </div>
</header>
<hr/>
<main class="container">
    <h4>修改个人信息</h4>
        <div class="input-group">
            <form method="post" enctype="multipart/form-data" action="/upload">
                <input type="file" name="uploadFile"/>
                <input type="submit" value="上传头像">
            </form>
        </div>
        <form action="/user?state=userUpdate_do" method="post">
            <div class="input-group">
                <span class="input-group-addon">用户名</span>
                <input type="text" class="form-control" placeholder="" name="username" value="${sessionScope.get("sessionUsername")}">
            </div>
            <div class="input-group">
                <span class="input-group-addon">密码</span>
                <input type="text" class="form-control" placeholder="" name="password" value="${sessionScope.get("sessionPassword")}">
            </div>
            <div class="input-group">
                <span class="input-group-addon">个人简介</span>
                <input type="text" class="form-control" placeholder="" name="introduction" value="${sessionScope.get("sessionUserIntroduction")}">
            </div>
            <input type="submit" value="Submit"class="btn btn-default" >
        </form>
</main>
<footer>

</footer>
</body>
</html>
