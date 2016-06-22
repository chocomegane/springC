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
<form:form modelAttribute="cinemaForm" action="/admin/updateCinema/execute">
	<form:hidden path="id" value="${cinema.id}"/>
	タイトル：<form:input path="title" value="${cinema.title}"/><br>
	価格：<form:input path="price" value="${cinema.price}"/><br>
	ジャンル：<form:input path="genre" value="${cinema.genre}"/><br>
	上映時間：<form:input path="time" value="${cinema.time}"/><br>
	公開日：<form:input path="releaseDate" value="${cinema.releaseDate}"/><br>
	メディアタイプ：<form:input path="mediaType" value="${cinema.mediaType}"/><br>
	制作会社：<form:input path="company" value="${cinema.company}"/><br>
	監督：<form:input path="directedBy" value="${cinema.directedBy}"/><br>
	レーディング：<form:input path="rating" value="${cinema.rating}"/><br>	
	概要（ストーリー）：<br><textarea name="description" rows="5" cols="30"><c:out value="${cinema.description}"/></textarea><br>
	イメージ画像：<form:input path="imagePath" value="${cinema.imagePath}"/><br>
	<input type="submit" value="更新">
</form:form>
<c:out value="${message}"/>
</body>
</html>