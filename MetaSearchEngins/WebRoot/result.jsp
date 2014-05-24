<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.sym"%>
<%@ page language="java"
	import="java.util.*,com.meta.business.*,com.meta.model.Result"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	Search search = new Search();
	String searchContent = request.getParameter("keyword");
	if (searchContent != null) {
		searchContent = new String(searchContent.getBytes("8859_1"),
				"GBK");
	}
	System.out.println("lovely====>out:" + searchContent);
	List<Result> all = search.getResult(searchContent);
	//List<Result> all = new ArrayList<Result>();
%>

<html>
<head>
<base href="<%=basePath%>">

<title>result of <%=searchContent%></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body align="center|left" marginwidth="100dp" marginheight="50dp"
	style="width:40%">
	<div style="width:760px;margin:0px auto">
		<a href="index.jsp"><img border="0" src="log.jpg"
			alt="metacrawler" width="195" height="60"
			style="float:left;width:195px; height:60px;" /> </a>
	</div>
	<p></p>
	<div style="width:760px;margin:0px auto">
		<form name="f" id="form1" action="result.jsp">
			<input type="text" name="keyword" id="keyword1" maxlength="100"
				values=<%=searchContent%> style="width:474px;" autocomplete="off">
			<span class="btn_wr"> <input type="submit" value="搜 索"
				id="su1" class="btn"> </span></br>
			<div align=center>
				<label><input name="Fruit" type="checkbox" value="" />Google
				</label> <label><input name="Fruit" type="checkbox" value="" />百度 </label>
				<label><input name="Fruit" type="checkbox" value="" /><font
					color="gray" clickable=false>yahoo </font> </label> <label><input
					name="Fruit" type="checkbox" value="" clickable=false /><font
					color="gray">360</font> </label>
			</div>
		</form>

	</div>
	<%
		for (int i = 0; i < all.size(); i++) {
	%>
	<a href=<%=all.get(i).getUrl()%>><%=all.get(i).getTitle()%></a>
	<br></br>
	<%
		out.println(all.get(i).getSumary());
	%>
	</br>
	<a href=<%=all.get(i).getUrl()%>><%=all.get(i).getUrl()%></a>
	</p>
	 
	<br></br>
	<%
		}
	%>
</body>
</html>
