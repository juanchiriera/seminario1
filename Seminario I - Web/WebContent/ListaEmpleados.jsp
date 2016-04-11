<%@page import="negocio.ConCargo"%>
<%@page import="negocio.Empleado"%>
<%@page import="java.awt.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	if ((session.getAttribute("login") == null) || (session.getAttribute("login") == "")) {
		response.sendRedirect("./index.jsp");
	}
%>
<script type="text/javascript">
	var dni;
</script>
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
				<h1 class="page-header" style="margin-top: 15px;">Empleados</h1>
			</div>

			<div class="col-xs-12">
				<div class="table-responsive">
					<table id="tabla" class="table table-striped">
						<thead>
							<tr>
								<th>Apellido</th>
								<th>Nombre</th>
								<th>DNI</th>
								<th>CUIL</th>
								<th>Fecha Nacimiento</th>
								<th>Antiguedad</th>
								<th>Escuela</th>
								<th>Estado</th>
							</tr>
						</thead>


						<c:forEach var="empleado" items="${empleados}" >
							<tr id="${empleado.getDni()}" class="fila" style='cursor:pointer' onclick="seleccionarFila(id, ${empleado.getDni()});">
								<td>${empleado.getApellido()}</td>
								<td>${empleado.getNombre()}</td>
								<td>${empleado.getDni()}</td>
								<td>${empleado.getCuil()}</td>
								<td>${empleado.getFechaDeNacimiento()}</td>
								<td>${empleado.getAntiguedad()}</td>
								<td>${empleado.getEscuela().getNombre()}</td>
								<td>${empleado.getEstado()}</td>
							</tr>
						</c:forEach>

					</table>

<!-- 					<script type="text/javascript"> -->
<!-- // 					function seleccionarFila(fila, dni){ -->
<!-- // 						if(document.getElementById(fila).className == "filaSeleccionada"){ -->
<!-- // 							document.getElementById(fila).className = "fila"; -->
<!-- // 						}else{ -->
<!-- // 							document.getElementById(fila).className = "filaSeleccionada"; -->
<!-- // 						} -->
						
<!-- // 					} -->
<!-- // 					var valores[8]; -->
<!-- // 					var i=0; -->
<!-- // 						$(".fila").click(function() { -->
<!-- // 							// recorremos todos los valores... -->
<!-- // 							$(".tabla tr").each(function(index){ -->
<!-- // 								$(".fila").className="fila"; -->
<!-- // 							}) -->
<!-- // 							$(".fila td").each(function(index) { -->
<!-- // 								valores[i]=$(this).text(); -->
<!-- // 								i++; -->
<!-- // 							}); -->
<!-- // 						}); -->
<!-- // 					i=0; -->
<!-- 					</script> -->

				</div>
			</div>

		</div>
	</div>


</body>
	<%@ include file="/Bottom.html" %>

</html>

