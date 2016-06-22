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
<form:form modelAttribute="cinemaForm" action="/admin/insert">
	<form:errors path="title" cssStyle="color:red" element="div"/>
	タイトル：<form:input path="title"/><br>
	<form:errors path="price" cssStyle="color:red" element="div"/>
	価格：<form:input path="price"/><br>
	<form:errors path="genre" cssStyle="color:red" element="div"/>
	ジャンル：<form:input path="genre"/><br>
	<form:errors path="time" cssStyle="color:red" element="div"/>
	上映時間：<form:input path="time"/><br>
	<form:errors path="releaseDate" cssStyle="color:red" element="div"/>
	公開日：<form:input path="releaseDate"/><br>
	<form:errors path="mediaType" cssStyle="color:red" element="div"/>
	メディアタイプ：<form:input path="mediaType"/><br>
	<form:errors path="company" cssStyle="color:red" element="div"/>
	制作会社：<form:input path="company"/><br>
	<form:errors path="directedBy" cssStyle="color:red" element="div"/>
	監督：<form:input path="directedBy"/><br>
	<form:errors path="rating" cssStyle="color:red" element="div"/>
	レーディング：<form:input path="rating"/><br>
	<form:errors path="description" cssStyle="color:red" element="div"/>
	概要（ストーリー）：<br><form:textarea path="description" rows="5" cols="30"/><br>
	<form:errors path="imagePath" cssStyle="color:red" element="div"/>
	イメージ画像：<form:input path="imagePath"/><br>
	<input type="submit" value="登録">
</form:form>
<c:out value="${message}"/>
</body>
</html>