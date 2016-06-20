<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="adminCommon.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品登録画面</title>
</head>
<body>
<h2>商品登録画面</h2>
<form:form modelAttribute="cinemaForm" action="/insert/execute">
	タイトル：<form:input path="title"/><br>
	価格：<form:input path="price"/><br>
	ジャンル：<form:input path="genre"/><br>
	上映時間：<form:input path="time"/><br>
	公開日：<form:input path="releaseDate"/><br>
	メディアタイプ：<form:input path="mediaType"/><br>
	制作会社：<form:input path="company"/><br>
	監督：<form:input path="directedBy"/><br>
	レーディング：<form:input path="rating"/><br>
	概要（ストーリー）：<br><form:textarea path="description" rows="5" cols="30"/><br>
	イメージ画像：<form:input path="imagePath"/><br>
	<input type="submit" value="登録">
</form:form>
<c:out value="${message}"/>
</body>
</html>