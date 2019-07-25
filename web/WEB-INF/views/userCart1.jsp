<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: JX
  Date: 2019/6/5
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.get("sessionUsername")}的购物车</title>
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css"/>
    <link href="../resources/css/cart.css" rel="stylesheet" type="text/css">
    <link href="../resources/css/header.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        function clearcartitem(bookid) {
            var result = window.confirm("您确定要删除该商品么?");
            if(result) {
                window.location.href = "/cart?state=del&bookId="+bookid;
            }
        }
        function clearcart() {
            var result = window.confirm("您确定要清空购物车么？");
            if(result) {
                window.location.href = "/cart?state=clear";
            }
        }
        function updateCart(input,id,oldvalue) {
            var number = input.value;
            var reg= /^\+?[1-9][0-9]*$/;
            var result = window.confirm("请确认改为：" + number);
            if(!reg.test(number)) {
                alert("参数不合法！");
                input.value = oldvalue;
                return;
            }

            if(result) {
                window.location.href="/cart?state=update&bookId="+id + "&number=" + number;
            } else {
                input.value = oldvalue;
            }
        }
    </script>
</head>

<body>
<header>
    <div class="logo">
        <a href="/user?state=index">布客网</a>
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
<hr/>
<main class="container">
    <h3>购物车</h3>
    <a href="javascript:clearcart()" class="flr btn btn-danger">清空</a>
    <ul class="item-list">
        <c:forEach var="me" items="${cart.map }">
            <li>
                <img class="cover" src="${ me.value.book.imageUrl }">
                <div class="detail">
                        <span>
                                ${ me.value.book.name }
                        </span>
                    <span class="flr">
                            <a href="javascript:clearcartitem(${me.value.book.id })">删除</a>
                        </span>
                    <br/>
                    <span>
                            价格：${ me.value.book.price }￥
                    </span>
                    <br/>
                    <span>
                            作者：${ me.value.book.writername }
                    </span>
                    <br/>
                    <span class="flr">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <a href="javascript:updateCart(this,${ me.value.book.id },${ me.value.number-1 })" role="button">-</a>
                                </span>
                                <input type="number" class="form-control" value="${ me.value.number }" onchange="updateCart(this,${me.value.book.id },${ me.value.number })">
                                <span class="input-group-addon">
                                    <a href="javascript:updateCart(this,${ me.value.book.id },${ me.value.number+1 })" role="button">+</a>
                                </span>
                            </div>
                    </span>
                </div>
            </li>
        </c:forEach>
    </ul>
    <hr/>
    <span>合计 ${cart.price }￥</span>
    <span class="flr">您的余额：${sessionScope.get("sessionUserAccount")}￥</span>
    <br/>
    <a href="/cart?state=buyAll" class="flr btn btn-primary">全部购买</a>
    <c:if test="${empty(cart.map) }">
    <h3>对不起，您还没有购买任何商品！<h3>
        </c:if>
</main>
</body>
</html>
