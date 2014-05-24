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
</head>

<body align="center" color="#FFcacaca">
	<div style="width:760px;margin:90px auto">
		<a href="index.jsp"><img border="0" src="log.jpg"
			alt="metacrawler" width="195" height="60"
			style="float:left;width:195px; height:60px;" /> </a>
	</div>
	<p></p>
	<div style="width:760px;margin:90px auto">
		<form name="f" id="form1" action="result.jsp">
			<input type="text" name="keyword" id="keyword1" maxlength="100"
				style="width:474px;"> <span class="btn_wr"> <input
				type="submit" value="ËÑ Ë÷" id="su1" class="btn"> </span></br>
			<div align=center>
				<label><input name="Fruit" type="checkbox" value="" />Google
				</label> <label><input name="Fruit" type="checkbox" value="" />°Ù¶È </label>
				<label><input name="Fruit" type="checkbox" value="" /><font
					color="gray" clickable=false>yahoo </font> </label> <label><input
					name="Fruit" type="checkbox" value="" clickable=false /><font
					color="gray">360</font> </label>
			</div>
		</form>

	</div>

</body>
</html>
