<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  </head>
  
  <body>
    fail<br>
    <% 
    	String name = (String)request.getAttribute("test");
    	out.println(name);
    %> 
    	
    
  </body>
</html>