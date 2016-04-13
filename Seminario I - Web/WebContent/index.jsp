<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<%@ include file="./head.html"%>
</head>

<body>
	<div class="container">
		<form action="LoginServlet" method="get" >
			<div class="form-signin" style="
				padding-left: 40%; 
				padding-top: 70;
				padding-bottom: 90;
			    background-color: #d9edf7;
			    border-radius: 5px;
			    margin-top: 90;
			    width: initial;">
				<h2 class="form-signin-heading">Inicie Sesión</h2>
				<label for="usuario">Usuario</label> <input type="text"
					class="form-control" name="usuario" id="usuario"
					placeholder="Usuario">

				<label for="contrasenia">Contraseña</label> <input type="password"
					class="form-control" name="contrasenia" id="contrasenia"
					placeholder="Contraseña">
				<button type="submit" class="btn btn-lg btn-primary btn-block">Conectar</button>
			</div>
		</form>
	</div>
</body>
</html>

