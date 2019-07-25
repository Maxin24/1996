<%--
  Created by IntelliJ IDEA.
  User: JX
  Date: 2019/5/27
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="/user?state=check1"></jsp:include>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>管理用户</title>
    <link href="../../resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <style>
        .panel tr a {
            display: none;
        }
        .panel td:hover a {
            display: inline-block;
        }
        #search {
            width: 300px;
            float: right;
            margin-right: 20px;
        }
        #add {
            float: right;
            margin-right: 20px;
        }
    </style>
</head>
<body>
<header>
    <h2>管理系统</h2>
    <p>欢迎您！${sessionScope.get("sessionUsername")}<a href="user?state=adminLogoff">[退出登录]</a></p>
</header>
<main>
    <ul class="nav nav-tabs">
        <li role="presentation" onclick="switchPanel(0)" class="active"><a href="#">用户管理</a></li>
        <div class="input-group" id="search">
            <form action="/user?state=fuzzyQuery" method="post">
                <input type="text" class="form-control" name="fuzzyQuery">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">Go!</button>
                    </span>
            </form>
        </div>
    </ul>
    <table class="table table-hover panel">
        <thead>
        <tr>
            <td width="10%">序号</td>
            <td width="10%">id</td>
            <td width="20%">用户名</td>
            <td width="20%">密码</td>
            <td width="20%">余额</td>
            <td width="20%"></td>
        </tr>
        </thead>
        <c:forEach items="${list}" var="user" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.account}</td>
            <td>
                <a class="btn btn-xs" href="/user?state=update&id=${user.id}">修改</a>
                <a class="btn btn-danger btn-xs" href="/user?state=delete&id=${user.id}">删除</a>
            </td>
        </tr>
        </c:forEach>
    </table>
    <table class="table table-hover panel" style="display: none">
        <tr>
            <td><span>88</span><button class="btn">修改</button></td>
            <td><button>修改</button></td>
            <td><button>修改</button></td>
            <td><button>修改</button></td>
            <td></td>
        </tr>
    </table>
    <table class="table table-hover panel" style="display: none">
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </table>
    <a class="btn btn-success" id="add" href="/user?state=addUser">增加</a>
</main>
<footer>

</footer>
</body>
<script src="../../resources/js/jquery-3.4.1.min.js"></script>
<script src="../../resources/js/bootstrap.min.js"></script>
<script>
    var panels = document.getElementsByClassName("panel");
    var switchs = document.querySelectorAll(".nav li");
    function switchPanel(index) {
        for (var i = 0; i < 3; i++) {
            switchs[i].classList.remove("active");
            panels[i].style.display = "none";
        }
        switchs[index].classList.add("active");
        panels[index].style.display = "table";
    }
    function deleteRow(e) {
        console.log(e.target);
        if (!(e.target instanceof HTMLButtonElement) || e.target.innerHTML != '删除') return;
        e.target.parentNode.parentNode.remove();
    }
    for (var i = 0; i < 3; i++) {
        panels[i].addEventListener('click', deleteRow);
    }
</script>
</html>