<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="userCommon.jsp" %>
<body>
<div align="center">
<br>
<h1>新規利用者登録画面</h1>
<br>
新規利用者登録画面<br>
お客様の情報を入力し、「お客様情報を登録する」ボタンをクリックしてください。 
<br>
<br>
<form:form action="/cinemaShop/register" modelAttribute="userRegisterForm">
	<table border="1">
		<c:out value="${err}"/>
		<tr>
			<td>名前</td>
			<td>
				<form:errors path = "name" cssStyle="color:red" element="div"/>
				<form:input path="name"/>
			</td>
		</tr>
		<tr>
			<td>メールアドレス</td>
			<td>
				<form:errors path = "email" cssStyle="color:red" element="div"/>
				<form:input path="email"/>
			</td>
		</tr>
		<tr>
			<td>住所</td>
			<td>	
				<form:errors path = "address" cssStyle="color:red" element="div"/>
				<form:input path="address"/>
			</td>
		</tr>
		<tr>
			<td>電話番号</td>
			<td>
				<font color="red"><c:out value="${telephoneErr1}"/></font>
				<font color="red"><c:out value="${telephoneErr2}"/></font>
				<form:input path="telephoneTop"/> - <form:input path="telephoneMiddle"/> - <form:input path="telephoneLast"/>
			</td>
		</tr>
		<tr>
			<td>パスワード</td>
			<td>
				<form:errors path = "password" cssStyle="color:red" element="div"/>
				<form:password path="password"/>
			</td>
		</tr>
		<tr>
			<td>確認用パスワード</td>
			<td>
				<c:out value="${err}"/><br>
				<form:errors path = "confirmPassword" cssStyle="color:red" element="div"/>
				<form:password path="confirmPassword"/>
			</td>
		</tr>
	</table>
	<br>
	<input type="submit" value="お客様情報を登録する">&nbsp;&nbsp;<input type="reset" value="クリア"> 
</form:form>
</div>
</body>
</html>