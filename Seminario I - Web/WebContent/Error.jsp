<%@page import="java.awt.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<% 
 if ((session.getAttribute("login") == null) || (session.getAttribute("login") == "")) {
  response.sendRedirect("/index.jsp");
 }

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/head.html"%>
</head>

<body>
	<%@ include file="/nav.html"%>
	
	<div class="container-fluid">
		<div class="row">

			<div class="2 main">
				<div></div>
				<h1 class="page-header" style="margin-top: 15px; color: red">
				<img src="./Images/exclamation_sign.png" style="width:10%">
					OPS! HA OCURRIDO UN ERROR
				</h1>
			</div>
			
			 <div class="2 main">
				<div></div>
				<h2 class="page-header" style="margin-top: 15px; color: pink;">
					Lo sentimos, trabajamos para arreglar este problema :(
									<script>
									</script>
									
				</h2>
			</div>
			
			<div class="modal-body"> </div>
			
		</div>
	</div>




</body>
	<%@ include file="/Bottom.html" %>
</html>