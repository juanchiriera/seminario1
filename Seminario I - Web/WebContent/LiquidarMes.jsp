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
	<%@ include file="/head.html"%>
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
					Liquidar Mes
				</h1>

				<!-- Formularios-->

				<div class="col-xs-12">
					<p> Complete los campos para liquidar sueldos</p>
					<form class="form-horizontal" action="LiquidarSueldosServlet" id="LiquidarSueldosForm" style="
						background-color: #d9edf7;
					    border-radius: 5px;
					    display: block;
					    padding: 30px;
					    padding-left: 45px;
					    width: 50%;
					    height: 30%;">
	 					
	 					<div class="form-group">
							<label for="escuela" class="col-sm-2 control-label">Escuela: </label>
							<select id="escuela" name="escuela" class="form-control">
								<c:forEach var="escuela" items="${escuelas}">
									<option value="${escuela.nro}">${escuela.nro} - ${escuela.nombre}</option>	
								</c:forEach>
							</select>
						</div>
	  					<br>
<!-- 	  					Lo ideal sería que traiga los últimos 12 meses desde el día actual, pero no me sale-->
  						<div class="form-group">
							<label for="estado" class="col-sm-2 control-label">Periodo/mes: </label>
							<select name="mes" class="form-control" style="
/* 							    margin-top: 9; */
    							">
					            <option value="0">Enero</option>
					            <option value="1">Febrero</option>
					            <option value="2">Marzo</option>
					            <option value="3">Abril</option>
					            <option value="4">Mayo</option>
					            <option value="5">Junio</option>
					            <option value="6">Julio</option>
					            <option value="7">Agosto</option>
					            <option value="8">Septiembre</option>
					            <option value="9">Octubre</option>
					            <option value="10">Noviembre</option>
					            <option value="11">Diciembre</option>
					        </select>
						</div>
	  					<br>
	  					<div class="col-sm-offset-2 col-sm-10">
							<button  type="submit" class="btn btn-default" style="
							    margin-left: 300;">
							Generar TXT</button>
					</form>
				</div>
			</div>
		</div>
		<div class="col-sm-2">
			<button onclick="window.location.href='./home.jsp'" class="btn btn-default" style="margin-left: 40;">
			Regresar</button>
		</div>
	</div>
</body>
 	<%@ include file="/Bottom.html" %>
</html>

