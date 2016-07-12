<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="userCommon.jsp" %>

<div align="center">
<form:form action = "${pageContext.request.contextPath}/myPage/userUpdate/execuse" modelAttribute="userUpdateForm" >
<br><br>

<br>

<h1>ユーザー情報変更</h1>
<br>

<br>
<%-- <table class="table table-striped">
<tr><td align="right">名前</td><td align="center"><form:input path="name" style="width:20em; height:2em" value="${user.name}"/><form:errors path="name" cssStyle="color:red" element="div"/></td></tr>
			<tr><td align="right">住所</td><td align="center"><form:input path="address" style="width:20em; height:2em" value="${user.address}"/><form:errors path="address" cssStyle="color:red" element="div"/></td></tr>
			<tr><td align="right">メールアドレス</td><td align="center"><form:input path="email" style="width:20em; height:2em" value="${user.email}"/><form:errors path="email" cssStyle="color:red" element="div"/></td></tr>
			<tr>
			<td align="right">電話番号</td>
			<td align="center">
				<form:errors path="telephoneTop" cssStyle="color:red" element="div"/>
				<form:errors path="telephoneMiddle" cssStyle="color:red" element="div"/>
				<form:errors path="telephoneLast" cssStyle="color:red" element="div"/>
				<form:input path="telephoneTop"/> - <form:input path="telephoneMiddle"/> - <form:input path="telephoneLast"/>
			</td>
		</tr>
</table>
 --%>
 
 <table class="table">
<tr><td class="active" align="center">名前:<form:input path="name" style="width:20em; height:2em" value="${user.name}"/><form:errors path="name" cssStyle="color:red" element="div"/></td></tr>
			<tr><td class="active" align="center">住所:<form:input path="address" style="width:20em; height:2em" value="${user.address}"/><form:errors path="address" cssStyle="color:red" element="div"/></td></tr>
			<tr><td class="active" align="center">メールアドレス:<form:input path="email" style="width:20em; height:2em" value="${user.email}"/><form:errors path="email" cssStyle="color:red" element="div"/></td></tr>
			<tr>
			<td class="active" align="center"><form:errors path="telephoneTop" cssStyle="color:red" element="div"/>
				<form:errors path="telephoneMiddle" cssStyle="color:red" element="div"/>
				<form:errors path="telephoneLast" cssStyle="color:red" element="div"/>電話番号:
				<form:input path="telephoneTop" value="${user.telephoneTop}" /> - <form:input value="${user.telephoneMiddle}" path="telephoneMiddle"/> - <form:input value="${user.telephoneLast}" path="telephoneLast"/>
			</td>
		</tr>
</table>
 
<br>
<br>
<form:hidden path="id" value="${user.id}" />
<input class="btn btn-default btn-lg" type="submit" value="更新"><br>
<c:out value="${message}" />
</form:form> 
</div>


</body>
</html>