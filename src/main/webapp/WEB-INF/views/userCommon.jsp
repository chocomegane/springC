<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/ecHeader.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ECサイト</title>
</head>
<body>
<header>
		<div id="linkHeader" align="left">
		<h1 align ="left"><a href="/cinemaShop/displayList">
			<img src="/img/rakus.jpg" width="50" height="50" alt="ロゴ画像">ＥＣサイトラクス
		</a></h1>
		</div>
<!-- 
		<div id="title" align="center">
		</div>
 -->
		<div id="userHeader" align="right">
			<p>こんにちは<c:out value="${user.name}さん"/></p>
			<p><a href="/cinemaShop/logout">っログアウト<p>
			<p><a href="/cinemaShop/view">カートの中身を見る</a></p>
		</div>
</header>