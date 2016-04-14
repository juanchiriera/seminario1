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
// 	$(function() {
// 		$('.selectpicker').selectpicker({
// 			style: 'btn-info',
// 			 size: 4
// 		});
		
//         $('#fecha').datetimepicker({
//             daysOfWeekDisabled: [0, 6],
//             format: 'DD/MM/YYYY'
//         });
// 	});
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
					Alta inasistencia
				</h1>

				<!-- Formularios-->

				<div class="col-xs-12">
					<p>Complete todos los campos para ingresar una nueva inasistencia.</p>
					<form class="col-sm-3 formulario" action="AltaInasistenciaProfesorServlet" id="altaInasistenciaForm">
						Docente: 
	  					<select id="dni" name="dni" class="form-control">
									<c:forEach var="docente" items="${docentes}">
										<option value="${docente.dni}">${docente.nombre} ${docente.apellido}</option>
									</c:forEach>

						</select>
						<br>
						<br>
						Licencia:
						<select id="licencia" name="licencia" class="form-control">
									<c:forEach var="licencia" items="${licencias}">
										<option value="${licencia.codigo}">${licencia.tipo} - ${licencia.motivo}</option>
									</c:forEach>
						</select>
	  					<br>
	  					<br>
						Clase:
						<select id="clase" name="clase" class="form-control">
									<c:forEach var="clase" items="${docente.clases}">
										<option value="${clase.nroClase}">${clase.getNombre()} - ${clase.getCurso()} - ${clase.getDivision()}</option>
									</c:forEach>
						</select>
	  					<br>
	  					<br>
						Fecha:
						<input id="fecha" type="date" name="classDate" onselect="google.script.run.dateSelect()" class="form-control">
						<br>
						<br>						
						Horas de clase ausente:
						<input id="horasCatedraAusente" type="float" name="horasCatedraAusente" class="form-control">
						<br>
						<br>
						Cantidad de dias ausente:
						<input id="cantDiasAusente" type="int" name="cantDiasAusente" class="form-control">
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
 	<%@ include file="/Bottom.html" %>
</html>

