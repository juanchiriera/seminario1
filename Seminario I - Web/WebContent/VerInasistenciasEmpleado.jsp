<%@page import="java.awt.List"%>
<%@page import="negocio.Empleado"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</head>

<body>

	<%@ include file="/nav.html"%>

	<div class="container-fluid">
		<div class="row">
			<div class="2 main">
				<div></div>
				<h1 class="page-header" style="margin-top: 15px;">
					Inasistencias del Empleado
				</h1>
			</div>
			
			
			<p> Nombre: ${inasistencia.getNombre()}</p>
			<p> Apellido: ${inasistencia.getApellido()}</p>
			<p> D.N.I.: ${inasistencia.getDni()}</p>
			
			<div class="col-xs-12">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Tipo Inasistencias</th>
								<th>Dias Restantes</th>
							</tr>
						</thead>


						<c:forEach var="licencia" items="${inasistencia.licencias}">
							<tr>
								<td>${licencia.getTipoLicencia()}</td>
								<td>${licencia.getDiasRestantes()}</td>
							</tr>
						</c:forEach>

					</table>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
