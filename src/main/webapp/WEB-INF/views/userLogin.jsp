<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="userCommon.jsp"%>
<body>
<div align="center">
  <h2>ログイン</h2>
  <c:if test="${error}"><div>メールアドレスまたはパスワードが不正です。</div></c:if>
  <form:errors path="userLoginForm.*" />
  <form:form action="/user/login" method="post" modelAttribute="userLoginForm">
    <form:label path="email">メールアドレス：<form:input path="email"/></form:label><br>
    <form:label path="password">パスワード：<form:password path="password"/></form:label><br>
    <input type="submit" value="ログイン">
  </form:form>
</div>
</body>
</html>