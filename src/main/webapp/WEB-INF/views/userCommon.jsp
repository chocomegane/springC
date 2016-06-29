<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="zeusCommon.jsp" %>
<link rel="stylesheet" type="text/css" href="/css/ecHeader.css" />
<title>ECサイト</title>
</head>
<body>
<header>
		<div id="linkHeader" align="left">
		<h1 align ="left">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;
			<a href="/">
				<img src="/img/rogo.png" width="50" height="50" alt="ロゴ画像">ECシネマショップ
			</a>
		</h1>
		</div>

		<div id="userHeader" align="right">
		<br>
			<sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
	 			<sec:authentication var="user" property="principal.user.name" />
				<p>こんにちは<c:out value="${user}"/>さん</p>
				<p><a href="/logout">ログアウト</a><p>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<p>こんにちはゲストさん</p>
				<p><a href="/registerForm">新規登録</a><p>
				<p><a href="/login">ログイン</a><p>
			</sec:authorize>
			<p><a href="/cart/view">カートの中身を見る</a></p>
		</div>

</header>
