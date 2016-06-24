<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/ecHeader.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Bootstrapのために追加 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="../../css/bootstrap.min.css" rel="stylesheet">
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

<title>ECサイト</title>

	<!-- Bootstrapの値段検索 -->
	<script type="text/javascript">
		function findByPrice(){
			document.searchCinemaPriceForm.submit();
	}
	</script>
	
	<!-- Bootstrapのジャンル検索 -->
	<script type="text/javascript">
		function findByGenre(){
			document.searchCinemaGenreForm.submit();
		}
	</script>

	<!-- Bootstrapのメディアタイプ検索	 -->
	<script type="text/javascript">
		function findByMediaType(){
			document.searchCinemaMediaTypeForm.submit();
		}
	</script>

</head>
<body>
<header>
		<div id="linkHeader" align="left">
		<h1 align ="left"><a href="/cinemaShop/displayList">
			<img src="/img/rogo.png" width="50" height="50" alt="ロゴ画像">ECシネマショップ
		</a></h1>
		</div>
<!-- 
		<div id="title" align="center">
		</div>
 -->
		<div id="userHeader" align="right">
			<sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
	 			<sec:authentication var="user" property="principal.user.name" />
				<p>こんにちは<c:out value="${user}"/>さん</p>
				<p><a href="/cinemaShop/logout">ログアウト</a><p>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<p>こんにちはゲストさん</p>
				<p><a href="/cinemaShop/registerForm">新規登録</a><p>
				<p><a href="/cinemaShop/loginForm">ログイン</a><p>
			</sec:authorize>
			<p><a href="/cinemaShop/view">カートの中身を見る</a></p>
		</div>

</header>

<!-- Bootstarapで使うために追加 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	

