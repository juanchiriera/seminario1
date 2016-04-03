<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<% 
 if ((session.getAttribute("login") == null) || (session.getAttribute("login") == "")) {
  response.sendRedirect("./index.jsp");
 }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<%@ include file="/head.html" %>
	<script>
	$(function() {
		$('.selectpicker').selectpicker({
			style: 'btn-info',
			 size: 4
		});
		
        $('#fecha').datetimepicker({
            daysOfWeekDisabled: [0, 6],
            format: 'DD/MM/YYYY'
        });
	});
	</script>
</head>

<body>

	<%@ include file="/nav.html"%>

	<div class="container-fluid">
		<div class="row">

			<div class="2 main">
				<div></div>
				<h1 class="page-header" style="margin-top: 15px;">
					<button type="button" style="" class="btn btn-default btn-lg">
						<span class="glyphicon glyphicon-oil"></span>
					</button>
					Buscar empleado
				</h1>

				<!-- Formularios-->

				<div class="col-xs-12">
					<p>Complete uno o varios campos para realizar una búsqueda de empleado.</p>
					<form class="form-horizontal" action="BuscarEmpleadoServlet" id="BuscarEmpleadoForm">
	 					 Apellido: 
	  					<input id="apellido" type="text" name="apellido" >
	  					<br>
	  					<br>
	  					 Nombre: 
	  					<input id="nombre" type="text" name="nombre" >
	  					<br>
	  					<br>
	  					 CUIL: 
	  					<input id="cuil" type="text" name="cuil" >
	  					<br>
	  					<br>
	  					 DNI: 
	  					<input id="dni" type="text" name="dni" >
	  					<br>
	  					<br>
	  					<div class="form-group">
							<label for="estado" class="col-sm-2 control-label">Estado: </label>
							<select>
 									<option id= "act" value="act">Activo</option>
 									<option id= "inact" value="inact">Inactivo</option>
							</select>
						</div>
	  					<br>
	  					<div class="form-group">
							<label for="escuela" class="col-sm-2 control-label">Escuela: </label>
							<select id="escuela" name="escuela" class="form-control">
								<c:forEach var="escuela" items="${escuelas}">
									<option>${escuela.nombre}-${escuela.nro}</option>
								</c:forEach>
							</select>
						</div>
						<br>
						<br>
						<div class="col-sm-offset-2 col-sm-10">
							<button  type="submit" class="btn btn-default">Buscar</button>
						</div>
					</form>
						<button onclick="window.location.href='./AltaEmpleadoServletNew'" class="btn btn-default">Nuevo Empleado</button>
					
				</div>
			</div>
		</div>
	</div>
</body>
 
</html>

