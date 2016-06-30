<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="userCommon.jsp" %>
<body>
<br>
<br>
<br>
<br>


<CENTER>
<div class="well">
<h1>404 Not Found</h1>
<h2>お探しのページは見つかりませんでした</h2>
</div>

<script type="text/javascript">
	img = new Array();
	img[0] = "/img/コマンドー.jpg";
	img[1] = "/img/ヒトラー.jpg";
	img[2] = "/img/DeathNote.jpg";
	n=Math.floor(Math.random()*img.length);
	document.write("<img src=\"" + img[n] + "\" width=\"600\">");
</script>

<br>
<br>
<br>
<br>
<a href="/">トップページへ戻る</a>
</CENTER>
</body>
</html>