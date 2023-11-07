<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--jsp中EL表达式的隐含对象
      1.pageContext
      2.param
      3.paramValues
      4.initParam
      5.其他（不是重点）
--%>


<%=pageContext.getRequest()%>
<br>
<%=request%>
<br>

<%=pageContext.getRequest()%>
这段java代码对应的EL表达式：
${pageContext.request}


<br>
<%--获取应用的根--%>
<%=request.getContextPath()%>
<%=((HttpServletRequest)pageContext.getRequest()).getContextPath()%>

这段java代码对应的EL表达式：
${pageContext.request.contextPath}

