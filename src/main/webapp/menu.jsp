<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!--  a href="/shopping/ShowItemServlet?action=top">TOP</a>|-->
<style>
a {
	display: inline-block;
	padding: 10px 40px;
	text-decoration: none;
	color: white;
	background-color: skyblue;
	　border-radius: 10px;
}
</style>
<div class="btn">
	<a href="/shopping/ShowItemServlet?action=top">TOP</a>
</div>
<c:forEach items="${categories}" var="category">
	<a href="/shopping/ShowItemServlet?action=list&code=${category.code}">${category.name}</a>
</c:forEach>
<a href="/shopping/CartServlet?action=show">カートを見る</a>

