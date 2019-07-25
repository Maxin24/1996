<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: JX
  Date: 2019/5/28
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/user?state=reset"></jsp:include>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>布客网</title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css"/>
    <link href="resources/css/index.css" rel="stylesheet" type="text/css">
    <link href="resources/css/header.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <div class="logo">
        <a href="/book?state=originPage">布客网</a>
    </div>
    <div class="searchBar">
        <form action="/book?state=fuzzyQuery" method="post" name="form">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="书名/作者" aria-describedby="basic-addon2" name="book">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="submit" name="搜索">
                        <span class="glyphicon glyphicon-search"></span>
                    </button>
                </span>
            </div>
        </form>
    </div>
    <div class="items">
        <ul>
            <li>
                ${loginSituation}
            </li>
            <li>
                ${loginSituation2}
            </li>
            <li>
                ${loginSituation1}
            </li>
        </ul>
    </div>
    <hr/>
</header>
<main>
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ul id="mainCI" class="carousel-indicators">
            <!-- 轮播模板-->
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active">
                <img src="resources/img/ci01.jpg" alt="">
            </li>
            <li data-target="#carousel-example-generic" data-slide-to="1">
                <img src="resources/img/ci02.jpg" alt="">
            </li>
            <li data-target="#carousel-example-generic" data-slide-to="2">
                <img src="resources/img/ci03.jpg" alt="">
            </li>
            <li data-target="#carousel-example-generic" data-slide-to="3">
                <img src="resources/img/ci04.jpg" alt="">
            </li>
            <li data-target="#carousel-example-generic" data-slide-to="4">
                <img src="resources/img/ci05.jpg" alt="">
            </li>

        </ul>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <!-- 轮播模板-->
            <div class="item active">
                <a href="/book?state=bookInfo&bookId=8">
                    <img src="resources/img/c01.jpg" alt="可视三体">
                </a>
            </div>
            <div class="item">
                <a href="/book?state=bookInfo&bookId=9">
                    <img src="resources/img/c02.jpg" alt="雪国列车">
                </a>
            </div>
            <div class="item">
                <a href="/book?state=bookInfo&bookId=10">
                    <img src="resources/img/c03.jpg" alt="用画笔征服全世界">
                </a>
            </div>
            <div class="item">
                <a href="/book?state=bookInfo&bookId=12">
                    <img src="resources/img/c04.jpg" alt="黑泽明的罗生门">
                </a>
            </div>
            <div class="item">
                <a href="/book?state=bookInfo&bookId=11">
                    <img src="resources/img/c05.jpg" alt="2081">
                </a>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

    <!-- 原本有分页-->
</main>
<footer>
    <div class="aboutme">
        <a href="/user?state=adminLogin">管理员登录</a>
    </div>
</footer>
</body>
<script src="resources/js/jquery-3.4.1.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</html>