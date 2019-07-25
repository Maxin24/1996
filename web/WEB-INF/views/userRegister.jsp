<%--
  Created by IntelliJ IDEA.
  User: JX
  Date: 2019/6/2
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css"/>
    <style>
        html, body {
            margin: 0;
        }
        .app {
            width: 300px;
            height: 280px;
            margin: 200px auto;
            border: solid 1px #CCCCCC;
            border-radius: 20px;
            box-shadow: #777777 0px 3px 3px;
        }
        form {
            width: 200px;
            margin: auto;
            text-align: center;
        }
        h2 {
            text-align: center;
        }
        input {
            margin: 20px 0px;
        }
        input[type="password"] {
            margin-bottom: 8px;
        }
        input[type="submit"] {
            margin-top: 8px;
        }
    </style>
</head>
<body>
<div class="app">
    <h2>布客网</h2>
    <form action="/user?state=userRegister_do" name="form" method="post">
        <input type="text" name="account" placeholder="账号" class="form-control" required/>
        <input type="password" name="password" placeholder="密码" class="form-control" required/>
        <input type="password" name="password" placeholder="再次输入密码" class="form-control" required/>
        <input type="submit" value="注册" class="btn btn-primary"/>
    </form>
</div>
</body>
<script>
    var account = document.querySelector('[type="text"]');
    function error(type) {
        if (type == 1) {
            account.style.borderColor = "#DD0000";
            alert("注册失败， 用户已存在");
        }
    }
    function reset002() {
        account.style.borderColor = "#CCCCCC";
    }
    account.onclick = reset002;
    var pws = document.querySelectorAll('[type="password"]');
    var submit = document.querySelector('[type="submit"]');
    function vaildate(e) {
        if (pws[0].value != pws[1].value) {
            pws[1].style.borderColor = "#DD0000";
            e.preventDefault();
            alert("两次密码必须一致")
        }
    }
    submit.addEventListener("click", vaildate);
    function reset() {
        pws[1].style.borderColor = "#CCCCCC";
    }
    pws[1].onclick = reset;
</script>
<script>
    error(${sessionScope.get("addUserResult")})
</script>
</html>
