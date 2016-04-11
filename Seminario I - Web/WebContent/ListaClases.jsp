<%@page import="java.awt.List"%>
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
					Clases
				</h1>
			</div>

			<div class="col-xs-12">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Clase</th>
								<th>Materia</th>
								<th>Curso</th>
								<th>Division</th>
								<th>Horas Semanales</th>
								<th>Estado</th>
							</tr>
						</thead>


						<c:forEach var="clase" items="${clases}">
							<tr>
								<td>${clase.getNroClase()}</td>
								<td>${clase.getNombre()}</td>
								<td>${clase.getCurso()}</td>
								<td>${clase.getDivision()}</td>
								<td>${clase.getHsSemanales()}</td>
								<td>${clase.getEstado()}</td>
							</tr>
						</c:forEach>

					</table>
				</div>
			</div>
		</div>
	</div>
</body>
	<%@ include file="/Bottom.html" %>
</html>

