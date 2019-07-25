<%--
  Created by IntelliJ IDEA.
  User: JX
  Date: 2019/6/3
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>布克书城</title>
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../resources/css/bootstrap-theme.min.css"/>
    <link href="../../resources/css/filter.css" rel="stylesheet" type="text/css">
    <link href="../../resources/css/header.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <div class="logo">
        <a href="/user?state=index">布客网</a>
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
</header>
<main class="container">
    <div class="row">
        <div class="col-md-3">
            <h4>按类型分类</h4>
            <ul>
                <li><a href="/book?state=originPage">全部</a> </li>
                <li><a href="/book?state=listByType&type=小说">小说</a></li>
                <li><a href="/book?state=listByType&type=画集">画集</a></li>
                <li><a href="/book?state=listByType&type=工具">工具</a></li>
            </ul>
            <hr/>
            <h4>按风格分类</h4>
            <ul>
                <li><a href="/book?state=originPage">全部</a> </li>
                <li><a href="/book?state=listByStyle&style=人文">人文</a></li>
                <li><a href="/book?state=listByStyle&style=科幻">科幻</a></li>
                <li><a href="/book?state=listByStyle&style=科技">科技</a></li>
            </ul>
        </div>
        <div class="col-md-9">
            <ul class="book-list">
                <c:forEach items="${list}" varStatus="status" var="book">
                    <li>
                        <div class="book-item">

                            <a href="/book?state=bookInfo&bookId=${book.id}">
                                <img src="../../${book.imageUrl}" alt="${book.name}" class="cover">
                            </a>
                            <div class="detail">
                                <div class="title">
                                    <a href="/book?state=bookInfo&bookId=${book.id}">${book.name}</a>
                                </div>
                                <div class="info">${book.writername}</div>
                                <div class="info">定价：${book.price}.00￥</div>
                                <div class="tag">
                                    <div class="info">
                                        <span class="label label-default">${book.type}</span>
                                        <span class="label label-info">${book.style}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</main>
<footer>

</footer>
</body>
</html>
