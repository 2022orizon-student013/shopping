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
	<marquee behavior="alternate">
		<h1>
			<p style="color: brown">★★★ご注文ありがとうございました。★★★ご注文ありがとうございました。★★★</p>
		</h1>
	</marquee>
	<br>
	<br>
	<h2>お客様の注文番号は</h2>
	<h3>
		<font color="red">${orderNumber}</font>
	</h3>
	<h2>になります。</h2>
	<br>
	<br>
	<marquee behavior="scroll">
		<img
			src="https://3.bp.blogspot.com/-LhG8uw8I0ws/WI1zKsSqtlI/AAAAAAABBW4/QG2iYV0L1BQpiHAODx9w_t0WpuNvBsfdACLcB/w1200-h630-p-k-no-nu/car_side_truck.png"
			width="400px" height="200px">
	</marquee>
</body>
</html>