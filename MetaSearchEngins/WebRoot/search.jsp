<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<title>search in result</title>
<style type="text/css">
div#container {
	width: 610px;
	margin-top: 300px;
	margin-left: 430px;
}

div#log_pic {
	width: 500px;
	margin-top: 300px;
	margin-left: 630px;
}

div#header {
	background-color: #99bbbb;
}

div#body {
	padding-left: 300;
	bgcolor: #99bbbb;
	clear: both;
	text-align: center;
	float: left;
	align-top: 300px;
	gcolor: #99bbbb;
	bgcolor: #99bbbb;
}

div#content {
	background-color: #EEEEEE;
	height: 200px;
	width: 350px;
	float: left;
}

div#footer {
	background-color: #99bbbb;
	clear: both;
	text-align: center;
	margin-bottom: 0;
	margin-top: 175;
}
</style>
</head>

<body id="body" style="text-align:center;" bgcolor="white">
	<div id="log_pic">
		<a href="search.jsp"><img border="0" src="log.jpg"
			alt="metacrawler" width="195" height="60"
			style="float:left;width:195px; height:60px;" /> </a>
	</div>

	<div id="container">
		<form name="f" id="form1" action="results.jsp">
			<input type="text" name="keyword" id="keyword1" maxlength="100"
				style="width:474px;"> <span class="btn_wr"> <input
				type="submit" value="搜 索" id="su1" class="btn"> </span></br>
			<div align=center>
				<label><input name="google_cb" type="checkbox"
					value="checked" checked=true />Google </label><select name="google_se"
					paddinLeft="30dp">
					<%
						for (int i = 0; i < 12; i++) {
					%>

					<option value=<%="\"" + (i + 1) + "\""%>
						<%=i == 2 ? "selected" : ""%>><%=(i + 1)%></option>

					<%
						}
					%>
				</select> <label><input name="baidu_cb" type="checkbox"
					value="checked" checked=true />百度 </label><select name="baidu_se"
					padding-left:2cm>
					<%
						for (int i = 0; i < 12; i++) {
					%>

					<option value=<%="\"" + (i + 1) + "\""%>
						<%=i == 2 ? "selected" : ""%>><%=(i + 1)%></option>

					<%
						}
					%>
				</select> <label><input name="Fruit" type="checkbox" value="" /><font
					color="gray" clickable=false>yahoo </font> </label> <select name="yahoo">
					<%
						for (int i = 0; i < 12; i++) {
					%>

					<option value=<%="\"" + (i + 1) + "\""%>
						<%=i == 2 ? "selected" : ""%>><%=(i + 1)%></option>

					<%
						}
					%>
				</select><label><input name="Fruit" type="checkbox" value=""
					clickable=false /><font color="gray">360</font> </label><select name="360">
					<%
						for (int i = 0; i < 12; i++) {
					%>

					<option value=<%="\"" + (i + 1) + "\""%>
						<%=i == 2 ? "selected" : ""%>><%=(i + 1)%></option>

					<%
						}
					%>
				</select>
			</div>

			<div align=center|left>你可以选择每个引擎所对应的返回页数</div>
		</form>

	</div>
	<div id="footer" align="bottom">……版权所有，有违必究……</div>
</body>
</html>
