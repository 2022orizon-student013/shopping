<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome shopping!</title>
</head>

<style>
body {
	font-family: 'Itim', cursive;
	background-image:
		url("https://i.pinimg.com/564x/bf/70/88/bf708882949ac2f5701cdfa0c1f12d37.jpg");
}
</style>

<body style="background-color: #E3D7A3;">

	<jsp:include page="/menu.jsp" /><br>
	<h3>
		<font color="brown">下記の内容で注文を行いますか？</font>
	</h3>
	<h3>
		<font color="brown">ご注文商品</font>
	</h3>

	<c:if test="${not empty cart.items}">
		<table border="1">
			<tr>
				<td>商品番号</td>
				<td>商品名</td>
				<td>単価(税込)</td>
				<td>個数</td>
				<td>小計</td>
			</tr>

			<c:forEach items="${cart.items}" var="item">
				<tr>
					<td align="center">${item.code}</td>
					<td align="center">${item.name}</td>
					<td align="right">${item.price}円</td>
					<td align="right">${item.quantity}</td>
					<td align="right">${item.price * item.quantity}円</td>
				</tr>
			</c:forEach>
			<tr>
				<td align="right" colspan="6">総計：${cart.total}円</td>
			</tr>
		</table>

		<h3>
			<font color="brown">お客様情報</font>
		</h3>

		<form action="/shopping/OrderServlet?action=cardInfo" method="post">
			<table border="1">
				<tr>
					<td>お名前</td>
					<td>${customer.name}</td>
				</tr>
				<tr>
					<td>住所</td>
					<td>${customer.address}</td>
				</tr>
				<tr>
					<td>電話番号</td>
					<td>${customer.tel}</td>
				</tr>
				<tr>
					<td>e-mail</td>
					<td>${customer.email}</td>
				</tr>
			</table>
			<br>

			<h3>
				<font color="brown">支払方法</font>
			</h3>
			${message} <br>
			<h5>
				<font color="brown">クレジットカード情報を入力してください</font>
			</h5>
			<font size="4">クレジットカードの名義：</font><input type="text" name="cardName"><br>
			<font size="4">クレジットカード番号：</font><input type="text" name="cardNum"><br>
			<font size="4">有効期限：</font> 月<input type="text" name="cardMonth"
				size="2">/年<input type="text" name="cardYear" size="2"><br>
			<font size="4">セキュリティコード：</font><input type="text" name="cardPass">
			<br> <input type="submit" value="この内容で注文">
		</form>

	</c:if>

	<br>


</body>
</html>