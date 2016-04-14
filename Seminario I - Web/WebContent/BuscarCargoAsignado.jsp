<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<% 
 if ((session.getAttribute("login") == null) || (session.getAttribute("login") == "")) {
  response.sendRedirect("./index.jsp");
 }
boolean hide = false;	
 if((session.getAttribute("login"))!= "admin"){
	 hide = true;
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
					Cargos
				</h1>

				<!-- Formularios-->

				<div class="col-xs-12">
					<p>Ingrese el numero de DNI del empleado del cual desea consultar los cargos.</p>
					<form class="col-sm-3 formulario" action="CargosAsignadosServlet" id="BuscarCargosAsignadosForm" name="formulario">
	 					 <label for="estado" class="control-label">DNI: </label>
	  					<input id="dni" type="text" name="dni" >
	  					<br>
	  					<br>
	  					<div class="derecha">
							<div class="col-sm-offset-2 col-sm-10">
								<button  onclick="validarCampo();" type="button" class="btn btn-default" >Buscar</button>
							</div>
						</div>
					</form>
					<script type="text/javascript">
					function validarCampo(){
						if(document.formulario.dni.value.length==0){
							alert("Debe escribir un DNI");
							document.formulario.dni.focus();
							return;
						}else{
							document.formulario.submit();
						}
					}
					</script>
				</div>

				<div class="col-sm-2" style="display:${hide ? 'none' : 'block'}">
					<button onclick="window.location.href='./AsignarCargosServlet'" class="btn btn-default">Asignar Cargos</button>
				</div>
			</div>
		</div>
	</div>
</body>
	<%@ include file="/Bottom.html" %>
</html>

