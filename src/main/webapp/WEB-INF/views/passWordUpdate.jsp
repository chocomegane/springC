<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp" %>

<form:form action="${pageContext.request.contextPath}/myPage/passWordUpdate/execuse" modelAttribute="userForm"> 
<table border="">
<tr><td>パスワード</td><td><form:input path="password"/></td></tr>
<tr><td>確認用パスワード</td><td><form:input path="confirmPassword"/></td></tr> 
</table>
<input type="submit" value="更新">
<sec:authentication var = "id" property="principal.user.id"/><!-- ユーザーid -->
<div><c:out value="${result}"/></div>
<div><c:out value="${newPassWordErr}"/></div>
<form:hidden path="id" value="${id}"/>

</form:form>


</body>
</html>