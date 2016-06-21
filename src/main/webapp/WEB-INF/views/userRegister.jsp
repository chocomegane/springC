<%@ include file="userCommon.jsp" %>
<body>
新規利用者登録画面<br>
お客様の情報を入力し、「お客様情報を登録する」ボタンをクリックしてください。 <br>
<form:form action="/administerRegister" modelAttribute="userRegisterForm">
<table border="">

			<tr>
				<td>
				名前</td>
				<td>
				
				<form:input path="name"/></td>
			</tr>
			<tr>
				<td>
	
				メールアドレス</td>
				<td><form:input path="email"/></td>
			</tr>
			<tr>
				<td>
				住所</td>
				<td><form:input path="address"/></td>

			</tr>
			<tr>
				<td>
				電話番号
				</td>
				<td><form:input path="telephone"/></td>

			</tr>
			<tr>
				<td>
				パスワード<br>
				</td>
				<td><form:password path="password"/></td>

			</tr>
			<tr>
				<td>
				確認用パスワード<br>
				</td>
				<td><form:password path="confirmPassword"/></td>

			</tr>
		</table>

		<br> <br><input type="submit" value="お客様情報を登録する"> 
		
</form:form>

</body>
</html>