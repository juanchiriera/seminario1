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
  
  <div class="container-fluid">
		<div class="row">
			<div class="2 main">
				<div></div>
				<h3 style="margin-top: 25px;">
					<span>Bienvenido ${usuario.nombreUsuario}</span>
				</h3>						
			</div>
		</div>
	</div>
</body>
</html>

