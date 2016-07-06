<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp" %>
<form:form action="${pageContext.request.contextPath}/mypage/update" ></form:form>
購入履歴<br>



<sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
	 			<sec:authentication var="id" property="principal.user.id" />
	 		<a href="<%=request.getContextPath() %>/myPage/userUpdate?id=${id}">ユーザー情報変更</a><br>
	 		<a href="<%=request.getContextPath() %>/myPage/passWordUpdate?id=${id}">パスワード変更</a><br>
			</sec:authorize>
			
			






</body>
</html>