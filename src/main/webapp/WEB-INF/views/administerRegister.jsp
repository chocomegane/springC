<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="adminCommon.jsp" %>
<body>
<div align="center">
 
<br>
<h1>新規利用者登録画面</h1>
<br>
管理者の情報を入力し、「管理者情報を登録する」ボタンをクリックしてください。
<br>
<br>
<form:form action="<%=request.getContextPath() %>/admin/register/adminRegister" modelAttribute="adminUserRegisterForm">
	<table border="1">
		<tr>
			<td align="center">名前</td>
			<td>
				<form:errors path="name" cssStyle="color:red" element="div"/>
				<form:input path="name"/>
			</td>
		</tr>
		<tr>
			<td align="center">アドレス</td>
			<td>
				<form:errors path="email" cssStyle="color:red" element="div" />
				<font><c:out value="${err2}"/></font>
				<form:input path="email"/>
			</td>
		</tr>
		<tr>
			<td align="center">パスワード</td>
			<td>
				<form:errors path="password" cssStyle="color:red" element="div"/>
				<form:password path="password"/>
			</td>
		</tr>
		<tr>
			<td align="center">確認用パスワード</td>
			<td>
				<form:errors path="confirmationPassword" cssStyle="color:red" element="div"/>
				<font color="red"><c:out value="${err1}"/></font>
				<form:password path="confirmationPassword"/>
			</td>
		</tr>
	</table>
	<br>
	<input type="submit" value="登録" onclick="DisableButton(this)">&nbsp;&nbsp;<input type="reset" value="クリア">
</form:form>

</div>

<br>

<div align="center">
	<a href="<%=request.getContextPath() %>/admin/menu">メニューへ戻る</a>
</div>

</body>
</html>