<%@ page import="java.util.List" %>
<%@ page import="com.w.oa3.bean.Dept" %>


<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<script type="text/javascript">
	function del(dno) {
		if (window.confirm("亲，删了不可恢复哦！")){
			document.location.href="<%=request.getContextPath()%>/dept/delete?deptno=" +dno;
		}

	}
</script>
<html>
	<head>
		<meta charset="utf-8">
		<title>部门列表页面</title>
	</head>
	<body>

<%--	显示一个登录名--%>
	<h3>欢迎<%=session.getAttribute("username")%></h3>

    <a href="<%=request.getContextPath()%>/user/exit">[退出系统]</a>

		<h1 align="center">部门列表</h1>
		<hr >
		<table border="1px" align="center" width="50%">
		<tr>
			<th>序号</th>
			<th>部门编号</th>
			<th>部门名称</th>
			<th>操作</th>
		</tr>

			<%
				//从request域当中取出集合
				List<Dept> deptList=(List<Dept>)request.getAttribute("deptList");

				int i=0;
				//循环遍历
				for (Dept dept:deptList) {
					//输出到后台
					//System.out.println(dept.getDname());
					//输出到浏览器
					//out.print(dept.getDname());
			%>

			<tr>
				<th><%=++i%></th>
				<th><%=dept.getDeptno()%></th>
				<th><%=dept.getDname()%></th>
				<td>
					<a href="javascript:void(0)" onclick="del(<%=dept.getDeptno()%>)">删除</a>
					<a href="<%=request.getContextPath()%>/dept/detail?f=edit&dno=<%=dept.getDeptno() %>">修改</a>
					<a href="<%=request.getContextPath()%>/dept/detail?f=detail&dno=<%=dept.getDeptno() %>">详情</a>
				</td>
			</tr>

			<%
				}
			%>


		</table>
		
		<hr />
		<a href="<%=request.getContextPath()%>/add.jsp">新增部门</a>
		
	</body>
</html>