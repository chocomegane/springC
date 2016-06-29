<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="zeusCommon.jsp" %>
<link rel="stylesheet" type="text/css" href="/css/adminHeader.css" />
<title>ECサイト管理者</title>
</head>
<body>
<header>
		<div id="linkHeader" align="left">
		<h1 align ="left">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="/admin/displayList">
			<img src="/img/rogo.png" width="50" height="50" alt="ロゴ画像">ECシネマショップ(管理者用ページ)
		</a></h1>
		</div>

 		<div id="userHeader" align="right">
			<sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
	 			<sec:authentication var="admin" property="principal.adminUser.name" />
				<p>こんにちは<c:out value="${admin}"/>さん</p>
				<p><a href="/admin/menu">メニュー画面</a></p>
				<p><a href="/admin/logout">ログアウト</a><p>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<p>こんにちは管理者さん</p>
			</sec:authorize>
		</div>
</header>
