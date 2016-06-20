<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp" %>
<body>

<h2>商品一覧</h2>
<table>
 <tbody>
  <tr>
  	<th>商品名</th>
  	<th>価格</th>
  </tr>
	<c:forEach var="child" items="${listPage.childPageList}">
		<tr>
			<th><c:out value="${child.title}"/></th>
			<th><c:out value="${child.price}"/>円</th>
		</tr>
	</c:forEach>
 </tbody>
</table>

</body>
</html>