<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import = "com.tarena.form.*" %>

<%
	LoginForm form = (LoginForm)session.getAttribute("LoginForm");
	if(form.getName()!=null){
%>
<h1>Welcome,
<%=form.getName()%>！
<%
	}else{
%>
您无权访问本页面!
<%}%>
</h1><br>
<a href="login.jsp">重新登录</a><br>
