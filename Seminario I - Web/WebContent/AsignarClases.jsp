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
					Asignacion de Clases
				</h1>

				<!-- Formularios-->

				<div class="col-xs-12">
					<form class="form-horizontal col-sm-5 formulario" action="AsignarClasesServletNew" id="asigarClasesForm">

						<div class="form-group">
							<label for="profesor" class="col-sm-2 control-label">Profesor</label>
							<div class="col-sm-10 ">
								<select id="profesor" name="profesor" class="form-control">
									<c:forEach var="profesor" items="${profesores}">
										<option>${profesor.nombre} ${profesor.apellido} - ${profesor.dni}</option>
									</c:forEach>

								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="sucursalOrigen" class="col-sm-2 control-label">
								Clase</label>
							<div class="col-sm-10 selectContainer">
								<select id="clase" name="clase"
									class="form-control">

									<c:forEach var="clase" items="${clases}">
										<option>${clase.nroClase}-${clase.nombre} ${clase.curso} ${clase.division}</option>
									</c:forEach>

								</select>
							</div>
						</div>


						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Asignar</button>
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
</body>

</html>

