<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="userCommon.jsp" %>
<body>
新規利用者登録画面<br>
お客様の情報を入力し、「お客様情報を登録する」ボタンをクリックしてください。 <br>
<form:form action="/register" modelAttribute="userRegisterForm">
<table border="">
<c:out value="${err}"/>

			<tr>
				<td>
				名前</td>
				<td>
				<form:errors path = "name" cssStyle="color:red" element="div"/>
				<form:input path="name"/></td>
			</tr>
			<tr>
				<td>
	
				メールアドレス</td>
				<td>
				<form:errors path = "email" cssStyle="color:red" element="div"/>
				<form:input path="email"/></td>
			</tr>
			<tr>
				<td>
				住所</td>
				<td>
				<form:errors path = "address" cssStyle="color:red" element="div"/>
				<form:input path="address"/></td>

			</tr>
			<tr>
				<td>
				電話番号
				</td>
				<td>
				<form:errors path = "telephone" cssStyle="color:red" element="div"/>
				<form:input path="telephone"/></td>

			</tr>
			<tr>
				<td>
				パスワード<br>
				</td>
				<td>
				<form:errors path = "password" cssStyle="color:red" element="div"/>
				<form:password path="password"/></td>

			</tr>
			<tr>
				<td>
				確認用パスワード<br>
				</td>
				<td>
				<form:errors path = "confirmPassword" cssStyle="color:red" element="div"/>
				<form:password path="confirmPassword"/></td>

			</tr>
		</table>

		<br> <br><input type="submit" value="お客様情報を登録する"> 
		
</form:form>
<form:form action="/userRegister/" >
<input type="submit" value="クリア">
</form:form>
</body>
</html>