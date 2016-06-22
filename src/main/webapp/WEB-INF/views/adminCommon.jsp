<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%-- <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%> --%>
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
		<h1 align ="left"><a href="/administer/top">
			<img src="/img/rakus.jpg" width="50" height="50" alt="ロゴ画像">ＥＣサイトラクス
		</a></h1>
		</div>
<!--
 		<div id="title" align="center">
		</div>
 -->
 		<div id="userHeader" align="right">
			<p>こんにちは<c:out value="${admin.name}さん"/></p>
			<p><a href="/administer/Logout">ログアウト</a></p>
		</div>
</header>
