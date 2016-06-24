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
<h2 align="center">商品登録画面</h2>
<div align="center">
	<form:form modelAttribute="cinemaForm" action="/admin/updateCinema/execute" enctype="multipart/form-data">
		<table border="1">
			<form:hidden path="id" value="${cinema.id}"/>
			<tr><th>タイトル</th><td><form:input path="title" rows="1" cols="40" value="${cinema.title}"/></td></tr>
			<tr><th>価格</th><td><form:input path="price" rows="1" cols="40" value="${cinema.price}"/></td></tr>
			<tr><th>ジャンル</th><td><form:input path="genre" rows="1" cols="40" value="${cinema.genre}"/></td></tr>
			<tr><th>上映時間</th><td><form:input path="time" rows="1" cols="40" value="${cinema.time}"/></td></tr>
			<tr><th>公開日</th><td><form:input path="releaseDate" rows="1" cols="40" value="${cinema.releaseDate}"/></td></tr>
			<tr><th>メディアタイプ</th><td><form:input path="mediaType" rows="1" cols="40" value="${cinema.mediaType}"/></td></tr>
			<tr><th>制作会社</th><td><form:input path="company" rows="1" cols="40" value="${cinema.company}"/></td></tr>
			<tr><th>監督</th><td><form:input path="directedBy" rows="1" cols="40" value="${cinema.directedBy}"/></td></tr>
			<tr><th>レーディング</th><td><form:input path="rating" rows="1" cols="40" value="${cinema.rating}"/></td></tr>	
			<tr><th>概要（ストーリー）</th><td><form:input path="description" rows="15" cols="40" value="${cinema.description}"/></td></tr>
			<tr><th>イメージ画像</th><td><form:input type="file" path="imagePath"/></td></tr>
		</table>
		<input type="submit" value="更新">
	</form:form>
</div>
<div align="center">
	<c:out value="${message}"/>
</div>
</body>
</html>