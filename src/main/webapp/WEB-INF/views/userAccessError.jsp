<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="userCommon.jsp" %>
<body>
<div class="main">
<h1>アクセス権限がありません</h1>
<p>以降のアドレスにアクセスする権限がありません。一度ログアウトして再度お試しください。</p>
<p><a href="<%=request.getContextPath() %>/admin/logout">ログアウト</a><p>
</div>
</body>
</html>