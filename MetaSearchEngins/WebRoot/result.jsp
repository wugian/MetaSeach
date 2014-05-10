<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.sym"%>
<%@ page language="java"
	import="java.util.*,com.meta.business.*,com.meta.model.Result"
	pageEncoding="gbk"%>
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
	List<Result> all = search.getResult(searchContent);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	style="width:60%">

	<font color="red">your search content is</font>
	:<%=searchContent%>
	<p></p>
	<%
		for (int i = 0; i < all.size(); i++) {
	%>
	<a href=<%=all.get(i).getUrl()%>><%=all.get(i).getTitle()%></a>
	<p></p>
	<%
		out.println(all.get(i).getSumary());
	%>
	</p>
	<p></p>
	<%
		}
	%>
</body>
</html>
