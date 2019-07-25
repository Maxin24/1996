<%--
  Created by IntelliJ IDEA.
  User: JX
  Date: 2019/6/4
  Time: 1:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${book.name}</title>
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css"/>
    <link href="../../resources/css/books.css" rel="stylesheet" type="text/css">
    <link href="../../resources/css/header.css" rel="stylesheet" type="text/css">
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
    <h1 class="book-title">${book.name}</h1>
    <div class="indent">
        <div class="subjectwrap clearfix">
            <div class="subject clearfix">
                <div id="mainpic" class="">
                    <img src="${book.imageUrl}" title="${book.name}" alt="${book.name}" style="width: 135px;max-height: 200px;">
                </div>
                <div id="info" class="">
                    <span class="pl">作者:</span><span>${book.name}</span><br/>
                    <span class="pl">定价:</span> ${book.price}<br/>
                </div>
            </div>
        </div>
        <div class="gtleft">
                <span class="rr">
                    <a class="j a_show_login" href="/cart?state=add&bookId=${book.id}" rel="nofollow">加入购物车</a>
                </span>
        </div>
    </div>
    <br clear="all">
    <div class="related_info">
        <h2>
            <span class="">内容简介</span>&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·
        </h2>
        <div class="indent" id="link-report">
            <div class="intro">
                <p>${book.introduction}</p>
            </div>
            <div class="report" style="visibility: hidden;"><a rel="nofollow" href="https://book.douban.com/subject/30281429/?icn=index-latestbook-subject#">举报</a></div>
        </div>
    </div>
    <div class="related_info">
        <h3>
            <span class="">读者评论</span>&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·
        </h3>
        <div class="indent" id="link-report1">
            <div class="intro">
                <p>${book.comment}</p>
            </div>
            <div class="report" style="visibility: hidden;"><a rel="nofollow" href="https://book.douban.com/subject/30281429/?icn=index-latestbook-subject#">举报</a></div>
        </div>
    </div>
</main>
</body>
</html>
