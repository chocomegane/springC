<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="userCommon.jsp" %>
<body>
<br>
<br>
<br>
<br>


<CENTER>
<h1>404 Not Found</h1>
<h2>お探しのページは見つかりませんでした</h2>


<script type="text/javascript">
	img = new Array();
	img[0] = "/img/コマンドー.jpg";
	img[1] = "/img/ヒトラー.png";
	img[2] = "/img/DeathNote.jpg";
	n=Math.floor(Math.random()*img.length);
	document.write("<img src=\"" + img[n] + "\" width=\"600\">");
</script>
</CENTER>
<br>
<br>
<br>
<br>
</body>
</html>