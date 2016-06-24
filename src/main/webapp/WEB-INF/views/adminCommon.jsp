<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/adminHeader.css" />
<%-- <sec:authentication property="principal.account" var="account" /> --%>
<title>ECサイト管理者</title>
</head>
<body>
<header>
		<div id="linkHeader" align="left">
		<h1 align ="left"><a href="/admin/menu">
			<img src="/img/rakus.jpg" width="50" height="50" alt="ロゴ画像">ＥＣサイトラクス
		</a></h1>
		</div>
<!--
 		<div id="title" align="center">
		</div>
 -->
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
