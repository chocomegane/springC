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
	<form:form modelAttribute="cinemaForm" action="/admin/insert" enctype="multipart/form-data">
		<table border="1">
			
			<tr><th>タイトル</th><td><form:textarea path="title" rows="1" cols="40"/><form:errors path="title" cssStyle="color:red" element="div"/></td></tr>
			
			<tr><th>価格</th><td><form:textarea path="price" rows="1" cols="40"/><form:errors path="price" cssStyle="color:red" element="div"/></td></tr>
			
			<tr><th>ジャンル</th><td><form:textarea path="genre" rows="1" cols="40"/><form:errors path="genre" cssStyle="color:red" element="div"/></td></tr>
			
			<tr><th>上映時間</th><td><form:textarea path="time" rows="1" cols="40"/><form:errors path="time" cssStyle="color:red" element="div"/></td></tr>
			
			<tr><th>公開日</th><td><form:textarea path="releaseDate" rows="1" cols="40"/><form:errors path="releaseDate" cssStyle="color:red" element="div"/></td></tr>
			
			<tr><th>メディアタイプ</th><td><form:textarea path="mediaType" rows="1" cols="40"/><form:errors path="mediaType" cssStyle="color:red" element="div"/></td></tr>
			
			<tr><th>制作会社</th><td><form:textarea path="company" rows="1" cols="40"/><form:errors path="company" cssStyle="color:red" element="div"/></td></tr>
			
			<tr><th>監督</th><td><form:textarea path="directedBy" rows="1" cols="40"/><form:errors path="directedBy" cssStyle="color:red" element="div"/></td></tr>
			
			<tr><th>レーディング</th><td><form:textarea path="rating" rows="1" cols="40"/><form:errors path="rating" cssStyle="color:red" element="div"/></td></tr>
			
			<tr><th>概要（ストーリー）</th><td><form:textarea path="description" rows="15" cols="40"/><form:errors path="description" cssStyle="color:red" element="div"/></td></tr>
			
			<tr><th>イメージ画像</th><td><form:input type="file" path="imagePath"/><form:errors path="imagePath" cssStyle="color:red" element="div"/></td></tr>
		
		</table>
		<input type="submit" value="登録">
	</form:form>
</div>

<div align="center">
	<c:out value="${message}"/>
</div>

</body>
</html>