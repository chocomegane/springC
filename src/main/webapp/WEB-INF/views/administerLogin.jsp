<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="adminCommon.jsp"%>
<body>
<div align="center">
	<br>
  <h2>管理者ログイン</h2>
  <form:errors path="adminLoginForm.*" />
  <form:form action="<%=request.getContextPath() %>/admin/doAuth" method="post" modelAttribute="adminLoginForm">
  	<!-- <pre><form:label path="email">    メールアドレス:<form:input path="email"/></form:label>
	<form:label path="password">パスワード:<form:password path="password"/></form:label></pre>-->
	<pre><form:label path="email">メールアドレス： <form:input path="email"/></form:label><br>
    <form:label path="password">パスワード： <form:password path="password"/></form:label></pre>
    <input type="submit" value="ログイン" onclick="DisableButton(this)"/>
  </form:form>
</div>
</body>
</html>