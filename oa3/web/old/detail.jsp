<%@ page import="com.w.oa3.bean.Dept" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<%
	//从request域当中取出数据
	Dept d=(Dept) request.getAttribute("dept");
%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>部门详情</title>
	</head>
	<body>
	<%--	显示一个登录名--%>
	<h3>欢迎<%=session.getAttribute("username")%></h3>
		<h1>部门详情</h1>
		<hr />
		部门编号：<%=d.getDeptno()%> <br>
		部门名称：<%=d.getDname()%> <br>
		部门位置：<%=d.getLoc()%> <br>
		
		<input type="button" value="后退" onclick="window.history.back()"/>
	</body>
</html>