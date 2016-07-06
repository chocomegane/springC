<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp" %>

<div align="center">
<form:form action = "${pageContext.request.contextPath}/myPage/userUpdate/execuse" modelAttribute="userForm" >

<table border="">
<tr><th><center>名前</center></th><td align="center"><form:input path="name" style="width:20em; height:2em" value="${user.name}"/><form:errors path="name" cssStyle="color:red" element="div"/></td></tr>
			<tr><th><center>住所</center></th><td align="center"><form:input path="address" style="width:20em; height:2em" value="${user.address}"/><form:errors path="address" cssStyle="color:red" element="div"/></td></tr>
			<tr><th><center>メールアドレス</center></th><td align="center"><form:input path="email" style="width:20em; height:2em" value="${user.email}"/><form:errors path="email" cssStyle="color:red" element="div"/></td></tr>
			<tr><th><center>電話番号</center></th><td align="center"><form:input path="telephone" style="width:20em; height:2em" value="${user.telephone}"/><form:errors path="telephone" cssStyle="color:red" element="div"/></td></tr>
</table>
<form:hidden path="id" value="${user.id}" />
<input type="submit" >
<c:out value="${message}"/>
</form:form> 
</div>
</body>
</html>