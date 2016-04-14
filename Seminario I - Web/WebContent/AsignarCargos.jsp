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
				<h1 class="page-header" style="
					margin-top: 15px;
					margin-left: 18px;">
					Asignación de Cargo
				</h1>

				<!-- Formularios-->

				<div class="col-xs-12">
				<p>Complete todos los campos para asignar un cargo al empleado.</p>
					<form class="form-horizontal col-sm-5 formulario" action="AsignarCargosServletNew" id="asigarCargoForm">
						
						<div class="form-group">
							<label for="profesor" class="col-sm-2 control-label">Empleado</label>
							<div class="col-sm-10 ">
								<select id="empleado" name="empleado" class="form-control">
									<c:forEach var="empleado" items="${empleados}">
										<option>${empleado.nombre} ${empleado.apellido} - ${empleado.dni}</option>
									</c:forEach>

								</select>
							</div>
						</div>
						
						<div class="form-group">
							
							<label for="cargo" class="col-sm-2 control-label">Cargo</label>
							<div class="col-sm-10 ">
								<select id="cargo" name="cargo" class="form-control">
									<c:forEach var="cargo" items="${cargos}">
									   <option>${cargo.idCargo} - ${cargo.nombre}</option>
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
	<%@ include file="/Bottom.html" %>
</html>