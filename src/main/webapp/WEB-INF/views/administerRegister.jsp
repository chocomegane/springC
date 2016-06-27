<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="adminCommon.jsp" %>
<body>
<div align="center">
 
<br>
<h1>新規利用者登録画面</h1>
<br>管理者の情報を入力し、「管理者情報を登録する」ボタンをクリックしてください。 <br>
	<form:form action="/admin/register/adminRegister" modelAttribute="adminUserRegisterForm">
		
		
		<table border="1">
		<tr>
		<td>
		<form:errors path="name" cssStyle="color:red" element="div"/>
		名前</td><td><form:input path="name"/></td>
		</tr>
		<tr>
		<td>
		<form:errors path="email" cssStyle="color:red" element="div" />
		アドレス</td><td><font><c:out value="${err2}"/></font><br><form:input path="email"/></td></tr>
		<tr>
		<td><form:errors path="password" cssStyle="color:red" element="div"/>
		パスワード</td><td><form:password path="password"/></td>
		<tr>
		<td>
		<form:errors path="confirmationPassword" cssStyle="color:red" element="div"/>
		確認用パスワード</td><td><font color="red"><c:out value="${err1}"/></font><br><br><form:password path="confirmationPassword"/></td></tr>
		</table>
		<input type="submit" value="登録">
	</form:form>
	<form action="/admin/register/">
		<input type="submit" value="クリア">
	</form>
</div>
<br>
<div align="center">
	<a href="/admin/menu">メニューへ戻る</a>
</div>

</body>
</html>