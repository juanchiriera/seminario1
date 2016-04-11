<%@page import="negocio.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<% 
	if ((session.getAttribute("login") == null) || (session.getAttribute("login") == "")) {
		response.sendRedirect("/index.jsp");
	}

	Usuario u = (Usuario) session.getAttribute("usuario");
	String nombre = u.getNombreUsuario(); 

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
		<%@ include file="/head.html" %>
</head>

<body>
  <%@ include file="/nav.html" %>
  
   	
 	<div 
 		style="width:100%;
  		height:60%;
  		filter:alpha(opacity=25);
  		-moz-opacity:.25;
  		opacity:.25;
  		background:url(./Images/LogoVialidad.jpg);
  		background-position: 50% 50%;
  		background-repeat: no-repeat ">
 	</div>

</body>
	<%@ include file="/Bottom.html" %>
</html>

