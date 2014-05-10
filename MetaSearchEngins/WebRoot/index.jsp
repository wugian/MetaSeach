<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>search in result</title>
</head>

<body>
	<div align="center">
		<form name="f" id="form1" action="result.jsp">
			<input type="text" name="keyword" id="keyword1" maxlength="100"
				style="width:474px;" autocomplete="off"> <span
				class="btn_wr"> <input type="submit" value="search" id="su1"
				class="btn" onmousedown="this.className='btn btn_h'"
				onmouseout="this.className='btn'"> </span>
		</form>
	</div>


</body>
</html>
