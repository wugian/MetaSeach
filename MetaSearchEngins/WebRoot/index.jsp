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
				type="submit" value="搜 索" id="su1" class="btn"> </span></br>
			<div align=center>
				<label><input name="google_cb" type="checkbox" value="checked"
					checked=true />Google </label><select name="google_se" paddinLeft="30dp">
					<%
						for (int i = 0; i < 12; i++) {
					%>

					<option value=<%="\"" + (i + 1) + "\""%>
						<%=i == 2 ? "selected" : ""%>><%=(i + 1)%></option>

					<%
						}
					%>
				</select> <label><input name="baidu_cb" type="checkbox" value="checked"
					checked=true />百度 </label><select name="baidu_se" padding-left:2cm>
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

</body>
</html>
