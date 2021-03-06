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
<script type="text/javascript">
    var hide = '@Session["hide"]';
</script>
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
				<h1 class="page-header" style="
					margin-top: 15px;
					margin-left: 18px;">				
					Buscar empleado
				</h1>

				<!-- Formularios-->

				<div class="col-xs-12 ">
					<p>Complete uno o varios campos para realizar una búsqueda de empleado.</p>
					<form class="control-label col-sm-3 formulario" action="BuscarEmpleadoServlet" id="BuscarEmpleadoForm">
						<div class="izquierda">	 
							 <label for="estado" class="control-label">Apellido: </label>
		  					<input id="apellido" type="text" name="apellido" class="form-control">
		  					<br>
		  					<br>
		  					 <label for="estado" class="control-label">Nombre: </label> 
		  					<input id="nombre" type="text" name="nombre" class="form-control">
		  					<br>
		  					<br>
		  					 <label for="estado" class="control-label">CUIL: </label> 
		  					<input id="cuil" type="text" name="cuil" class="form-control">
		  				</div>
		  				<div class="derecha">
		  					 <label for="estado" class="control-label">DNI: </label>
		  					<input id="dni" type="text" name="dni"  class="form-control">
		  					<br>
		  					<br>
		  					<div class="form-group">
								<label for="estado" class="control-label">Estado: </label>
								<select class="form-control">
	 									<option id= "true" value="act">Activo</option>
	 									<option id= "false" value="inact">Inactivo</option>
								</select>
							</div>
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
							<div class="col-sm-offset-2">
								<button  type="submit" class="btn btn-default">Buscar</button>
							</div>
						</div>
					</form>
					
				</div>
				<div class="col-sm-2">
						<button onclick="window.location.href='./AltaEmpleadoServletNew'" class="btn btn-default " style="display:${hide ? 'none' : 'block'}">Nuevo Empleado</button>
				</div>
			</div>
		</div>
	</div>
</body>
 	<%@ include file="/Bottom.html" %>
</html>

