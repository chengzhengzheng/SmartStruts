<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ page import = "com.tarena.form.*" %>

<%
	LoginForm form = (LoginForm)session.getAttribute("LoginForm");
	if(form.getName()!=null){
%>
<h1>Welcome,
<%=form.getName()%>��
<%
	}else{
%>
����Ȩ���ʱ�ҳ��!
<%}%>
</h1><br>
<a href="login.jsp">���µ�¼</a><br>
