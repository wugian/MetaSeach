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

	DuplicateRemoval resultAlls =search.getResult(searchContent); 
	List<Result> all = resultAlls.getResults();
	int count =0;
	//List<Result> all = new ArrayList<Result>();
%>

<html>
<head>
<base href="<%=basePath%>">

<title><%=searchContent%></title>

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
	<script>
		function myFunction() {
			var x = "";
			var time = new Date().getHours();
			if (time < 20) {
				x = "Good day";
			}
			document.getElementById("demo").innerHTML = x;
		}
	</script>





	<SCRIPT LANGUAGE="JavaScript">
		var a = document.getElementsByName("keyword");
		out.println("****************************");
		v.values = searchContent;
	</SCRIPT>
	<script>
		function myFunction(searchContent) {
			var a = document.getElementsByName("keyword");
			out.println("****************************");
			v.values = searchContent;
		}
		document.getElementById("keyword1").innerHTML = myFunction(searchContent);
	</script>

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
	<p>
		搜索结果总数<%=resultAlls.getResults().size()%>重复数目：<%=resultAlls.getResultRepeat()%>
	</p>
	<%
		for (int i = count; (i < count + 10) && (i < all.size()); i++) {
			Result curResult = all.get(count + i);
	%>
	<a href=<%=curResult.getUrl()%>><%=curResult.getTitle()%></a>
	<br></br>
	<%
		out.println(curResult.getSumary());
	%>
	</br>
	<a href=<%=curResult.getUrl()%>><%=curResult.getUrl()%></a>
	</p>

	<br></br>
	<%
		}
		count += 10;
	%>
	<div id="Loading" align=center click>Loading...</div>
</body>
</html>
