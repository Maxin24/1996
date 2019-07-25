<%--
  Created by IntelliJ IDEA.
  User: JX
  Date: 2019/6/2
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/user?state=check"></jsp:include>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>你好！${sessionUsername}</title>
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css"/>
    <link href="../../resources/css/user.css" rel="stylesheet" type="text/css">
    <link href="../../resources/css/header.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <div class="logo">
        <a href="/user?state=index">布客网</a>
    </div>
    <div class="items">
        <ul>
            <li>
                ${loginSituation2}
            </li>
            <li>
                ${loginSituation1}
            </li>
        </ul>
    </div>
</header>
<hr/>
<main class="container">
    <h2>${sessionUsername}</h2>
    <div id="profitPicContainer">
<%--                <img src="../../resources/img/defaultUser.jpg">--%>
        <img src="<%=request.getContextPath()%>/resources/img/${sessionUserIconUrl}">
    </div>
    <hr/>
    <h4>个人信息</h4>
    <ul>
        <li>用户名： ${sessionUsername}</li>
        <li>个人介绍： ${sessionUserIntroduction}</li>
        <li>余额： ${sessionUserAccount}</li>
        <li><a class="btn btn-default" href="/user?state=userUpdate">修改</a></li>
    </ul>
</main>
<footer>

</footer>
</body>
</html>

