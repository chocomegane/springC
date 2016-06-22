<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="adminCommon.jsp" %>

 <body>
	<form:form action="/administer/administerRegister"
		modelAttribute="adminUserRegisterForm">
		
		<c:out value="${err}"/><br>
		<form:errors path="name" cssStyle="color:red" element="div"/>
名前<form:input path="name"/>
		<br>
		<form:errors path="email" cssStyle="color:red" element="div" />
アドレス<form:input path="email"/>
		<br>
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