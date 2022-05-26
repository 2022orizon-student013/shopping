<%@ page language="java" contentType="text/html; charset=UTF-8"
	   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome shopping!</title>
</head>
<body style="background-color:#E3D7A3;">

<jsp:include page="/menu.jsp" /><br>
<h3><p style="color:brown">現在のカートの中身</p></h3>

<c:if test="${empty cart.items}">
<h4>現在、カートは空です。</h4>
<img src="https://3.bp.blogspot.com/-P6ZK7_CU1Is/UmuA4EO_8nI/AAAAAAAAZhc/4XWFy6_YTMA/s800/shopping_cart.png" width="200px" height="200px">
</c:if>

<c:if test="${not empty cart.items}">

<table border="1">
<tr><td>商品番号</td><td>商品名</td><td>単価(税込)</td>
    <td>個数</td><td>小計</td><td>削除</td></tr>

<c:forEach items="${cart.items}" var="item">
<tr>
    <td align="center">${item.code}</td>
    <td align="center">${item.name}</td>
    <td align="right">${item.price}円</td>
    <td align="right">${item.quantity}</td>
    <td align="right">${item.price * item.quantity}円</td>
<td>
<form action="/shopping/CartServlet?action=delete" method="post">
    <input type="hidden" name="item_code" value="${item.code}">
    <input type="submit" value="削除">
</form>
</td>
</tr>
</c:forEach>
<tr><td align="right" colspan="6">総計：${cart.total}円</td></tr>
</table>

<form action="/shopping/OrderServlet?action=input_customer" method="post">
    <input type="submit" value="注文する">
</form>
<img src="https://2.bp.blogspot.com/-d2B4qMNWJQ4/Ur1HmKS3oiI/AAAAAAAAcjM/yk8Xg3FJh04/s800/shopping_cart_woman.png" width="200px" height="200px">
</c:if>

</body>
</html>