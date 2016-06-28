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
			<tr>
				<th><center>タイトル</center></th>
				<td align="center">
					<form:input path="title" style="width:20em; height:2em"/><form:errors path="title" cssStyle="color:red" element="div"/>
					<div><font color="red"><c:out value="${err2}"/></font></div>
				</td>
			</tr>
			<tr><th><center>価格</center></th><td align="center"><form:input path="price" style="width:20em; height:2em"/><form:errors path="price" cssStyle="color:red" element="div"/></td></tr>
			<tr><th><center>ジャンル</center></th><td align="center"><form:input path="genre" style="width:20em; height:2em"/><form:errors path="genre" cssStyle="color:red" element="div"/></td></tr>
			<tr><th><center>上映時間</center></th><td align="center"><form:input path="time" style="width:20em; height:2em"/><form:errors path="time" cssStyle="color:red" element="div"/></td></tr>
			<%-- <tr><th>公開日</th><td><form:textarea path="releaseDate" rows="1" cols="40"/><form:errors path="releaseDate" cssStyle="color:red" element="div"/></td></tr> --%>
			<tr><th><center>公開日</center></th><td align="center"><input type="date" name="releaseDate" style="width:20em; height:2em" ><form:errors path="releaseDate" cssStyle="color:red" element="div"/></td></tr>
			<tr><th><center>メディアタイプ</center></th><td align="center"><form:input path="mediaType" style="width:20em; height:2em"/><form:errors path="mediaType" cssStyle="color:red" element="div"/></td></tr>
			<tr><th><center>制作会社</center></th><td align="center"><form:input path="company" style="width:20em; height:2em"/><form:errors path="company" cssStyle="color:red" element="div"/></td></tr>
			<tr><th><center>監督</center></th><td align="center"><form:input path="directedBy" style="width:20em; height:2em"/><form:errors path="directedBy" cssStyle="color:red" element="div"/></td></tr>
			<tr><th><center>レーディング</center></th><td align="center"><form:input path="rating" style="width:20em; height:2em"/><form:errors path="rating" cssStyle="color:red" element="div"/></td></tr>
			<tr><th><center>概要（ストーリー）</center></th><td align="center"><form:textarea path="description" style="width:20em; height:10em"/><form:errors path="description" cssStyle="color:red" element="div"/></td></tr>
			<tr>
				<th><center>イメージ画像</center></th>
				<td align="center">
					<form:input type="file" path="imagePath"/>
					<div><font color="red"><c:out value="${err}"/></font></div>
					<div><font color="red"><c:out value="${err3}"/></font></div>
				</td>
			</tr>
		</table>
		<br>
		<input type="submit" value="登録" onclick="DisableButton(this)"/>
	</form:form>
</div>

<div align="center">
	<c:out value="${message}"/>
</div>
<br>
<div align="center">
	<a href="/admin/menu">メニューへ戻る</a>
</div>

</body>
</html>