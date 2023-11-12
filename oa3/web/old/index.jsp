<%@ page contentType="text/html;charset=UTF-8"%>

<%--服务jsp时不生成session对象--%>
<%--写上这个session内置对象不能使用了--%>
<%@page session="false" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>欢迎使用OA系统</title>
	</head>
	<body>
<%--		<a href="<%=request.getContextPath()%>/dept/list">查看部门列表</a>--%>

<%--	<hr>--%>
<%--	<%=request.getContextPath() %>--%>
          <h1>用户登录</h1>
          <hr>
	<form action="<%=request.getContextPath()%>/user/login" method="post">
		username: <input type="username" name="username"> <br>
		password: <input type="password" name="password"> <br>
		<input type="checkbox" name="f" value="1">十天内免登录<br>
		<input type="submit" value="login">
	</form>

	</body>
</html>