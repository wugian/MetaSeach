<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.sym"%>
<%@ page language="java"
	import="java.util.*,com.meta.business.*,com.meta.model.Result"
	pageEncoding="utf-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>分页显示效果</title>
<style>
#content {
	width: 500px;
	height: 300px;
	margin: 20px auto;
	font-size: 12px;
	padding: 5px;
	line-height: 20px;
	border: #CCCCCC solid 1px;
	overflow: hidden;
	word-spacing: normal;
}

#pages {
	height: 20px;
	width: 500px;
	margin: 5px auto;
}

a {
	text-decoration: none;
	color: #999900;
}

a:hover {
	color: #333333;
	text-decoration: underline;
}
</style>
</head>
<body>
	<div id="content">
		<strong>绿岛网络团队管理法案</strong>

		<%
			for (int i = 0; i < 10; i++) {
		%>
		<p>
			宗旨：<%=i%></p>
		展现自我 服务他人<br> 全力打造一流的在校大学生网络技术开发团队<br> 全力打造一流的大学生门户网站<br>
					口号：<br> 技术改变生活，绿岛改变校园<br>

							<p>
								<%
									}
								%>
							
		<p>宗旨：</p>
		展现自我 服务他人<br> 全力打造一流的在校大学生网络技术开发团队<br> 全力打造一流的大学生门户网站<br>
		口号：<br> 技术改变生活，绿岛改变校园<br>

		<p>
	</div>
	<p>
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
			}
		</script>
</body>
</html>
