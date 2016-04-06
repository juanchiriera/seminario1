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
					Baja de Empleado
				</h1>

				<!-- Formularios-->

				<div class="col-xs-12">
					<p>Ingrese el numero de DNI del empleado del cual desea dar la Baja.</p>
					<form class="col-sm-3 formulario" action="BajaEmpleadoServlet" id="BajaEmpleadoForm">
	 					 DNI: 
	  					<input id="dni" type="text" name="dni" >
	  					<br>
	  					<br>
						<div class="col-sm-offset-2 col-sm-10">
							<button  type="submit" class="btn btn-default">Buscar</button>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
</body>

</html>

