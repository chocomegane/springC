<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="zeusCommon.jsp" %>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script><link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/ecHeader.css" />

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
			<a href="<%=request.getContextPath() %>/">
				<img src="<%=request.getContextPath() %>/img/rogo.png" width="50" height="50" alt="ロゴ画像">ECシネマショップ
			</a>
		</h1>
		</div>

		<div id="userHeader" align="right">
		<br>
			<sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
	 			<sec:authentication var="name" property="principal.user.name" />
	 			<sec:authentication var = "id" property="principal.user.id"/>
				<p>こんにちは<c:out value="${name}"/>さん</p>
				<p><a href="<%=request.getContextPath() %>/logout">ログアウト</a><p>
				<p><a href="<%=request.getContextPath() %>/myPage/?id=${id}">マイページ</a><p>
			</sec:authorize>
			<sec:authorize access="isAnonymous()">
				<p>こんにちは<c:out value="${guest.name}"/>さん</p>
				<p><a href="<%=request.getContextPath() %>/registerForm">新規登録</a><p>
				<p><a href="<%=request.getContextPath() %>/login">ログイン</a><p>
				
			</sec:authorize>
			<p><a href="<%=request.getContextPath() %>/cart/view">カートの中身を見る</a></p>
		</div>

</header>
