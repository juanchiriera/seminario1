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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
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
				<h1 class="page-header" style="margin-top: 15px;">
					Sueldo del Empleado
				</h1>
			</div>
			
			 <div class="2 main">
				<div></div>
				<h2 class="page-header" style="margin-top: 15px;">
					Sueldo del mes de <script>
									var meses = new Array ("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
									var f=new Date();
									document.write(meses[f.getMonth()] );
									</script>
									</form>
				</h2>
			</div>
			
			<div class="modal-body"> Nombre: ${empleado.getNombre()}</div>
			<div class="modal-body"> Apellido: ${empleado.getApellido()}</div>
			<div class="modal-body"> D.N.I.: ${empleado.getDni()}</div>
			<div class="modal-body"> Sueldo: ${sueldo}</div>
			
			
			</div>
		</div>
	</div>
</body>
	<%@ include file="/Bottom.html" %>
</html>
