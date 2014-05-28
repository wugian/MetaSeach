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
	DuplicateRemoval resultAlls = search.getResult(searchContent);
	List<Result> all = resultAlls.getResults();
	int count = 0;
%>

<html>
<head>
<style>
#content {
	width: 500px;
	height: 1900px;
	margin: 20px auto;
	padding: 5px;
	line-height: 20px;
	overflow: hidden;
	word-spacing: normal;
}

#footer {
	background-color: #99bbbb;
	clear: both;
	text-align: center;
	margin-bottom: 0;
}

#pages {
	height: 20px;
	width: 500px;
	margin: 5px auto;
}
</style>
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

	<div id="content">
		<%
			for (int i = count; (i < all.size()); i++) {
			Result curResult = all.get(  i);
		%>
		<a href=<%=curResult.getUrl()%>><%=curResult.getTitle()+":"+curResult.getWeight()%></a>
		<br></br>
		<%
			out.println(curResult.getSumary());
		%>
		</br> <a href=<%=curResult.getUrl()%>><%=curResult.getUrl()%></a>
		</p>

		<br></br>
		<%
			}
				count += 10;
				System.out.print("count" + count);
		%>
	</div>
	<div id="pages"></div>
	<script language="javascript">
		var obj = document.getElementById("content");
		var pages = document.getElementById("pages");
		window.onload = function() {
			var allpages = Math.ceil(parseInt(obj.scrollHeight)
					/ parseInt(obj.offsetHeight));//换取分页数
			pages.innerHTML = "<b>共" + allpages + "页</b>";
			for ( var i = 1; i <= allpages; i++) {
				pages.innerHTML += "<a href=\"javascript:show('" + i
						+ "');\">第" + i + "页</a>&nbsp;";
			}
		}
		function show(pageIndex) {
			obj.scrollTop = (pageIndex - 1) * parseInt(obj.offsetHeight);
			javascript: scroll(0, 0);
		}
	</script>
	<div id="footer">……版权所有，有违必究……</div>
</body>
</html>
