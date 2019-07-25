<%--
  Created by IntelliJ IDEA.
  User: JX
  Date: 2019/5/28
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/user?state=reset"></jsp:include>
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
            height: 260px;
            margin: 200 auto;
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
        [type="text"] {
            margin-top: 32px;
        }
        [type="submit"] {
            margin-top: 8px;
        }
    </style>
</head>
<body>
<div class="app">
    <h2>管理员登录</h2>
    <form action="/user?state=login" name="form" method="post">
        <input type="text" name="account" placeholder="账号" class="form-control" value="${sessionUsername}" required/>
        <input type="password" name="password" placeholder="密码" class="form-control" required/>
        <input type="submit" value="登录" class="btn btn-primary">
    </form>
</div>
</footer>
</body>
<script>
    error(${sessionScope.get("loginResult")});
</script>
<script>
    var account = document.querySelector('[type="text"]');
    var password = document.querySelector('[type="password"]');
    function error(type) {
        if (type == 1) {
            account.style.borderColor = "#DD0000";
            alert("管理员不存在");
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

</html>
