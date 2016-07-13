<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp" %>

<form:form action="${pageContext.request.contextPath}/myPage/passWordUpdate/execuse" modelAttribute="passWordForm"> 
<div align="center">
<br>
<br>
<br>
<h1>パスワード変更</h1>
<br>
<br>
<table class="table" >

<tr class="active"><td align="center">パスワード<form:errors  path = "password"  cssStyle="color:red" element="div"/><form:password align="center" path="password"/></td></tr>
<tr  class="active"><td align="center">確認用パスワード<form:errors  path =  "confirmPassword" cssStyle="color:red" element="div"/><form:password align="center" path="confirmPassword"/></td></tr> 
</table>
</div>
<div align="center">
<br>
<br>
<input type="submit" class="btn btn-default btn-lg" value="更新">
<sec:authentication var = "id" property="principal.user.id"/><!-- ユーザーid -->
<div align="center" ><c:out value="${result}"/></div>
<div align="center" ><c:out value="${newPassWordErr}"/></div>
<form:hidden path="id" value="${id}"/>
</div>
</form:form>


</body>
</html>