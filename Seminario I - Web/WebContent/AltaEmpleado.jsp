<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

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
					Nuevo Empleado
				</h1>

				<!-- Formularios-->

				<div class="col-xs-12">
					<p>Complete todos los campos para ingresar un empleado.</p>
					<form class="col-sm-3 formulario" action="AltaEmpleadoServlet" id="altaEmpleadoForm">
	 					 Apellido: 
	  					<input id="apellido" type="text" name="apellido" class="form-control">
	  					<br>
	  					<br>
	  					 Nombre: 
	  					<input id="nombre" type="text" name="nombre" class="form-control">
	  					<br>
	  					<br>
	  					 CUIL: 
	  					<input id="cuil" type="text" name="cuil" class="form-control">
	  					<br>
	  					<br>
						Fecha de nacimiento:
						<input type="date" name="classDate" onselect="google.script.run.dateSelect()" class="form-control">
						<br>
						<br>
	  					 DNI: 
	  					<input id="dni" type="text" name="dni" class="form-control">
	  					<br>
	  					<br>
	  					Antigüedad: 
						<input id="antiguedad" type="text" name="antiguedad" class="form-control">
	  					<br>
						<br>
	  					<div class="form-group">
							<label for="escuela" class="control-label">Escuela: </label>
							<select id="escuela" name="escuela" class="form-control">
								<c:forEach var="escuela" items="${escuelas}">
									<option>${escuela.nombre}-${escuela.nro}</option>
								</c:forEach>
							</select>
						</div>
						<br>
						<br>
						<div class="col-sm-offset-2 col-sm-10">
							<button  type="submit" class="btn btn-default">Cargar</button>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
</body>

</html>

