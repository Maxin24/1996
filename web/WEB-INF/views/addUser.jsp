<%--
  Created by IntelliJ IDEA.
  User: JX
  Date: 2019/6/1
  Time: 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/user?state=check1"></jsp:include>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>用户增加</title>
    <link href="../../resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <h2>管理系统</h2>
    <p>${sessionScope.get("sessionUsername")}<a href="/user?state=index">[退出登录]</a></p>
</header>
<hr/>
<main>
    <h4>添加新用户</h4>
    <form action="/user?state=add_do" method="post" name="form">
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon1">用户名</span>
            <input type="text" class="form-control" placeholder="" name="username" aria-describedby="basic-addon1">
        </div>
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon2">密&nbsp&nbsp&nbsp&nbsp码</span>
            <input type="text" class="form-control" placeholder="" name="password" aria-describedby="basic-addon1">
        </div>
        <div class="input-group">
            <span class="input-group-addon" id="basic-addon3">余&nbsp&nbsp&nbsp&nbsp额</span>
            <input type="text" class="form-control" placeholder="" name="balance" aria-describedby="basic-addon1">
        </div>
        <label>&nbsp&nbsp是否是管理员</label>
        <input type="checkbox" name="isAdmin" value="是">
        <input class="btn btn-default" type="submit" value="提交">
    </form>
</main>
</body>
<style>
    main {
        width: 300px;
        height: 300px;
        margin: auto;
    }
    div {
        margin: 8px;
    }
    [type="submit"] {
        display: block;
        margin: auto;
    }
</style>
<script>
    var account = document.querySelector('[type="text"]');
    var password = document.querySelector('[type="password"]');
    function error(type) {
        if (type == 1) {
            account.style.borderColor = "#DD0000";
            alert("这个用户名已经被注册为管理员");
        }else if(type == 2){
            account.style.borderColor = "#DD0000";
            alert("这个用户名已经被注册为普通用户");
        }
    }
    function reset() {
        account.style.borderColor = "#CCCCCC";
    }
    account.onclick = password.onclick = reset;
</script>
<script>
    error(${sessionScope.get("addUserResult")});
</script>
</html>
