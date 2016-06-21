<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="adminCommon.jsp" %>
<%-- <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理者登録</title>

</head>
 --%>
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