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
background-image:url("https://i.pinimg.com/564x/bf/70/88/bf708882949ac2f5701cdfa0c1f12d37.jpg");
}

h1{
	white-space: nowrap;
	display: inline-block;
 	font-family: "Roboto", sans-serif;
	font-size: 4.9em;
  	font-weight: bold;
  	color: transparent;
  	background-image: linear-gradient(135deg, #ffec61, #f321d7);
  	-webkit-background-clip: text;
	
}

h2{
	color: #696969;
}
</style>

<body>


<jsp:include page="/menu.jsp" />
<h3><Marquee behavior="alternate" direction="up" width="400" height="30" scrollamount="5"><FONT color="tomato">👆ここからお買い物を始めよう♪</FONT></MARQUEE></h3>
<h1><FONT color="#4169e1">Welcome to Hoge Store❣</FONT></h1>
<h2><Marquee behavior="alternate" direction="up" width="400" height="50" scrollamount="5"><FONT color="tomato">↓↓↓おすすめ商品はこちら↓↓↓</FONT></MARQUEE></h2>
<h2>【本】Javaの基本　　　　　【DVD】なつかしのアニメシリーズ</h2>
<br>

<img src="https://4.bp.blogspot.com/-6jLig_Zuhyk/UUhH8z560_I/AAAAAAAAO6A/lFCDFT8S1FM/s1600/shopping_fasion.png" width="200px" height="170px">
<img src="https://3.bp.blogspot.com/-HLe8mKu9lCs/Ugsu_AAFifI/AAAAAAAAXNI/-yW12EVgQ5s/s800/book_yoko.png" width="170px" height="170px">
<img src="https://1.bp.blogspot.com/-Y2ZJLDkwSdk/UqmPTtiUtqI/AAAAAAAAbhM/Is0BcImd_9Y/s800/disc_case.png" width="170px" height="170px">
<img src="http://3.bp.blogspot.com/-5SVCSrAHQxQ/UnXnV7zPNCI/AAAAAAAAaNc/DTk_7XLWN7I/s450/omocha_game.png" width="170px" height="170px">
<img src="https://livedoor.blogimg.jp/okanemotiko/imgs/c/2/c227e504.png" width="190px" height="170px">
</body>
</html>