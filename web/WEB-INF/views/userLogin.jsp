<%--
  Created by IntelliJ IDEA.
  User: JX
  Date: 2019/6/2
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
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
        .loginOption {
            text-align: left;
        }
        input[type="text"] {
            margin-top: 32px;
        }
        input[type="password"] {
            margin-bottom: 8px;
        }
        input[type="checkbox"] {
            margin: 0 120px 5px 0;
        }
        input[type="submit"] {
            margin-top: 8px;
        }
        a {
            margin-left: 18px;
            margin-top: 8px;
        }
    </style>
</head>
<body>
<div class="app">
    <h2>布客网</h2>
    <form action="/user?state=userLogin_do" name="form1" method="post">
        <input type="text" name="account" placeholder="账号" value="${sessionUsername1}" class="form-control"/>
        <input type="password" name="password" placeholder="密码" value="${sessionPassword1}" class="form-control"/>
        <label>记住密码</label>
        <input type="checkbox" value="on" name="ck"/><br/>
        <input type="submit" value="登录" class="btn btn-primary"/>
        <a class="btn btn-default" href="/user?state=userRegister">注册</a>
    </form>
</div>
</footer>
</body>
<script>
    var account = document.querySelector('[type="text"]');
    var password = document.querySelector('[type="password"]');
    function error(type) {
        if (type == 1) {
            account.style.borderColor = "#DD0000";
            alert("用户不存在");
        } else if (type == 2) {
            password.style.borderColor = "#DD0000";
            alert("密码错误");
        }
    }
    function reset() {
        account.style.borderColor = "#CCCCCC";
        password.style.borderColor = "#CCCCCC";
    }
    account.onclick = password.onclick = reset;
</script>
<script>
    error(${sessionScope.get("loginResult")});
</script>
</html>
