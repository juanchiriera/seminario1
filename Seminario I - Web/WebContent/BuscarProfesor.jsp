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
					Consultar profesor
				</h1>

				<!-- Formularios-->

				<div class="col-xs-12">
					<p>Complete uno o varios campos para buscar a un profesor.</p>
					<form class="col-sm-3 formulario" action="BuscarProfesorServlet" id="BuscarProfesorForm">
	 					<div class="izquierda">	
	 					 <label for="estado" class="control-label">Apellido: </label>: 
	  					<input id="apellido" type="text" name="apellido" class="form-control">
	  					<br>
	  					<br>
	  					 <label for="estado" class="control-label">Nombre: </label>  
	  					<input id="nombre" type="text" name="nombre" class="form-control">
	  					<br>
	  					<br>
	  					 <label for="estado" class="control-label">CUIL: </label>: 
	  					<input id="cuil" type="text" name="cuil" class="form-control">
	  					<br>
	  					<br>
	  					 <label for="estado" class="control-label">DNI: </label> 
	  					<input id="dni" type="text" name="dni" class="form-control">
	  					</div>	
	  					<div class="derecha">	
		  					<div class="form-group">
								<label for="estado" class="control-label">Materia: </label>
								<select id="materia" name="materia" class="form-control">
									<c:forEach var="materia" items="${materias}">
										<option>${materia.toString()}</option>
									</c:forEach>
								</select>
							</div>
		  					<br>
	<!-- 	  					FIJATE SI LO TRAES DE LA CLASE "clases" o si lo cargamos nosotros a mano -->
		  						<div class="form-group">
								<label for="estado" class="control-label">Curso: </label>
								<select id="curso" name="curso" class="form-control">
									<c:forEach var="curso" items="${cursos}">
										<option>${curso.toString()}</option>
									</c:forEach>
								</select>
							</div>
		  					<br>
		  						<div class="form-group">
								<label for="estado" class="control-label">División: </label>
								<select id="division" name="division" class="form-control">
									<c:forEach var="division" items="${divisiones}">
										<option>${division.toString()}</option>
									</c:forEach>
								</select>
							</div>
		  					<br>
		  					<div class="form-group">
								<label for="estado" class="control-label">Estado</label>
								<select class="form-control">
	 									<option id= "act" value="act" >Activo</option>
	 									<option id= "inact" value="inact">Inactivo</option>
								</select>
							</div>
							<br>
							<br>
							<div class="col-sm-offset-2 col-sm-10">
								<button  type="submit" class="btn btn-default">Buscar</button>
							</div>
						</div>
						<br>
						<br>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
 	<%@ include file="/Bottom.html" %>
</html>

