<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="adminCommon.jsp" %>

新規利用者登録画面<br>
管理者の情報を入力し、「管理者情報を登録する」ボタンをクリックしてください。 <br>
 <body>
	<form:form action="/administert/register/administerRegister"
		modelAttribute="adminUserRegisterForm">
		
	
		<c:out value="${err}"/><br>
		<table border="">
		<tr>
		<td>
		<form:errors path="name" cssStyle="color:red" element="div"/>

		
名前</td><td><form:input path="name"/></td>
		</tr>
		<tr>
		<td>
		<form:errors path="email" cssStyle="color:red" element="div" />
アドレス</td><td><form:input path="email"/></td></tr>
<tr>
		
		<td><form:errors path="password" cssStyle="color:red" element="div"/>
パスワード</td><td><form:password path="password"/></td>
		<tr><td>
		<form:errors path="confirmationPassword" cssStyle="color:red" element="div"/>
確認用パスワード</td><td><form:password path="confirmationPassword"/></td></tr>
</table>
		<input type="submit" value="登録">
	</form:form>
	<form:form action="/administert/register/allClear/"> <input type="submit" value="クリア"></form:form>
</body>
</html>