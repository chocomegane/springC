<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="adminCommon.jsp" %>

 <body>
	<form:form action="/administer/administerRegister"
		modelAttribute="adminUserRegisterForm">
		<table border="">
	
		<c:out value="${err}"/><br>
		<tr>
		<td>
		<form:errors path="name" cssStyle="color:red" element="div"/>

		
名前</td><td><form:input path="name"/></td>
		</tr>
		<tr>
		<td>
		<form:errors path="email" cssStyle="color:red" element="div" />
アドレス</td><form:input path="email"/></tr>
		
		<form:errors path="password" cssStyle="color:red" element="div"/>
パスワード<form:password path="password"/>
		<br>
		<form:errors path="confirmationPassword" cssStyle="color:red" element="div"/>
確認用パスワード<form:password path="confirmationPassword"/>
		<br>
		<input type="submit" value="登録">
	</form:form>
</body>
</html>