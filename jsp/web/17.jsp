<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.w.javaweb.bean.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--引入标签库。这里引入jstl的核心标签库  --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--格式化标签库，专门负责格式化操作  --%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%--sql标签库  --%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>


<%--var用来指定循环中的变量--%>
<c:forEach var="i" begin="1" end="10" step="1">
    ${i}<br>
</c:forEach>


<%


    //创建List集合
    List<Student> stuList=new ArrayList<>();

    Student s1=new Student("110","警察");
    Student s2=new Student("120","救护车");
    Student s3=new Student("119","消防车");

    stuList.add(s1);
    stuList.add(s2);
    stuList.add(s3);

    request.setAttribute("stuList",stuList);
%>


<%
    List<Student> stus=(List<Student>)request.getAttribute("stuList");
    for (Student stu : stus){
%>
        id:<%=stu.getId()%>,name:<%=stu.getName()%><br>
<%
    }
%>

<hr>

<%--core标签库中forEach标签。对List集合进行遍历--%>
<%--EL表达式只能从域取数据--%>
<c:forEach items="${stuList}" var="s">
    id:${s.id},name:${s.name}<br>
</c:forEach>

<hr>

<%--varStatus代表var的状态--%>
<c:forEach items="${stuList}" var="s" varStatus="stuStatus">
<%--    varStatus的count值从1开始，以1递增，主要用于编号--%>
    编号：${stuStatus.count},id:${s.id},name:${s.name}<br>
</c:forEach>