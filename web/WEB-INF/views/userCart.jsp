<%--
  Created by IntelliJ IDEA.
  User: JX
  Date: 2019/6/4
  Time: 1:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/user?state=check"></jsp:include>
<!DOCTYPE HTML>
<html>
<head>
    <title></title>
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
<c:if test="${!empty(cart.map) }">
    <h1>欢迎您！${sessionScope.get("sessionUsername")}</h1>
    <table border="1px" cellspacing="0" align="center">
        <tr>
            <td>图书名称</td>
            <td>图书作者</td>
            <td>单价</td>
            <td>数量</td>
            <td>小计</td>
            <td>操作</td>
        </tr>
        <c:forEach var="me" items="${cart.map }">
            <tr>
                <td>${me.value.book.name }</td>
                <td>${me.value.book.writername }</td>
                <td>${me.value.book.price }</td>
                <td>
                    <input type="text" name="number" value="${me.value.number }" style="width: 30px" onchange="updateCart(this,${me.value.book.id },${me.value.number })">
                </td>
                <td>${me.value.price }</td>
                <td>
                    <a href="javascript:clearcartitem(${me.value.book.id })">删除</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="2">
                <a href="javascript:clearcart()">清空购物车</a>
            </td>
            <td colspan="2">合计</td>
            <td colspan="2">${cart.price }</td>
        </tr>
        <tr>
            <td colspan="2">您的余额：${sessionScope.get("sessionUserAccount")}</td>
            <td colspan="2"><a href="/cart?state=buyAll">全部购买</a> </td>
        </tr>

    </table>
</c:if>

<c:if test="${empty(cart.map) }">
    对不起，您还没有购买任何商品！
</c:if>
</body>
</html>
