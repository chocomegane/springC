<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp" %>

<form:form action="/myPage/passWordUpdate" modelAttribute="UserRegisterForm"> 
<table border="">
<tr><td>パスワード</td><td><form:input path="password"/></td></tr>
<tr><td>確認用パスワード</td><td><form:input path="confirmPassword"/></td></tr> 
</table>
</form:form>


</body>
</html>