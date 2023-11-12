
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<script type="text/javascript">
	function del(dno) {
		if (window.confirm("亲，删了不可恢复哦！")){
			document.location.href="${pageContext.request.contextPath}/dept/delete?deptno=" +dno;
		}

	}
</script>
<html>
	<head>
		<meta charset="utf-8">
		<title>部门列表页面</title>
<%--		设置整个网页的基础路径--%>
		<base href="http://localhost:8081/oa/">
	</head>
	<body>

<%--	显示一个登录名--%>
	<h3>欢迎${username}</h3>

    <h4>在线人数${onlinecount}</h4>

    <a href="${pageContext.request.contextPath}/user/exit">[退出系统]</a>

		<h1 align="center">部门列表</h1>
		<hr >
		<table border="1px" align="center" width="50%">
		<tr>
			<th>序号</th>
			<th>部门编号</th>
			<th>部门名称</th>
			<th>操作</th>
		</tr>


			<c:forEach items="${deptList}" varStatus="deptStatatus" var="dept">
				<tr>
					<th>${deptStatatus.count}</th>
					<th>${dept.deptno}</th>
					<th>${dept.dname}</th>
					<td>
						<a href="javascript:void(0)" onclick="del(${dept.deptno})">删除</a>
						<a href="${pageContext.request.contextPath}/dept/detail?f=edit&dno=${dept.deptno}">修改</a>
						<a href="${pageContext.request.contextPath}/dept/detail?f=detail&dno=${dept.deptno}">详情</a>
					</td>
				</tr>
			</c:forEach>


		</table>
		
		<hr />
		<a href="${pageContext.request.contextPath}/add.jsp">新增部门</a>
		
	</body>
</html>