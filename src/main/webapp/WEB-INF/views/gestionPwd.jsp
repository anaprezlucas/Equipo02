<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" http-equiv="Content-Type"
	content="text/html,width=device-width, initial-scale=1, shrink-to-fit=no ">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script 
	src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>
	
	<title>Cambio de Contrase&ntilde;a</title>
	
<style>
.creacion-container {
	margin-left: 5%;
	margin-bottom: 5%;
	box-shadow: 5px 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}
.creacion-container h2 {
	padding: 0% 0% 0% 5%;
}
.gestion-form {
	padding: 0% 5% 0% 5%;
}
.gestion-form h3 {
	text-align: left;
	color: #333;
	text-align: left;
	font-size: 17px;
}
.botones-form {
	padding: 5% 0% 5% 0%;
}
.botones-form .btnUsuario {
	font-weight: 600;
	color: #fff;
	background-color: #0062cc;
}
</style>


<meta charset="UTF-8">
<title>Modificaci&oacute;n Contrase&ntilde;a</title>
</head>

<body>

	<nav class="navbar navbar-inverse" style="background-color: #337ab7;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"></a>
			</div>

			<div class="nav-item pull-left col-md-1 fixed-top"
				style="position: relative; top: 8px">
				<form action="REfichajesUser" method="GET">
					<button class="btn" type="submit">
						<strong><span class="glyphicon glyphicon-sort"></span>
							Fichajes</strong>
					</button>
				</form>
			</div>
			<div class="col-md-1 pull-right fixed-top"
				style="position: relative; top: 8px">
				<form action="logout" method="GET">
					<button class="btn btn-danger" type="submit">
						<strong><span class="glyphicon glyphicon-log-out"></span>Salir</strong>
					</button>
				</form>
			</div>
		</div>
	</nav>


	<div class="container creacion-container col-md-6">
		<div class="row">
			<h2>
				<span class="   glyphicon glyphicon-edit"></span> Perfil de Usuario
			</h2>

			<div class="gestion-form left-div">
				<form action="modificarPwd" method="post" id="formCrearUsuario" data-toggle="validator">
					<div class="form-group">
						<h3>Nombre</h3>
						<input id="nombreUser" type="text" disabled class="form-control"
						 value=<%=request.getAttribute("nombreUser")%> />
					</div>
          			<div class="form-group">
	  					<h3>E-mail</h3>
							<input id="mailUser" type="text" disabled class="form-control"
							 value=<%=request.getAttribute("mailUser")%>/>
					</div>
					
					<div class="form-group">
						<h3>Contrase&ntilde;a Actual</h3>
						<input name="contrasenaActual" type="password" class="form-control"
							placeholder="Contrase&ntilde;a Actual*" value=""  data-match-error="Las contrase&ntilde;as no coinciden" data-error="Campo necesario" required/>
							<div class="help-block with-errors"></div>
					</div>
					
					<div class="form-group">
						<h3>Contrase&ntilde;a Nueva</h3>
						<input id="contrasenaNueva" name="contrasenaNueva" type="password" class="form-control"
							placeholder="Contrase&ntilde;a Nueva*" data-match-error="Las contrase&ntilde;as no coinciden" data-error="Campo necesario" required/>
							<div class="help-block with-errors"></div>
					</div>
					
					<div class="form-group">
						<h3>Confirmar Contrase&ntilde;a</h3>
						<input id="contrasenaNueva2" name="contrasenaNueva2" type="password" class="form-control" data-match="#contrasenaNueva" 
						placeholder="Contrase&ntilde;a Nueva*"  data-match-error="Las contrase&ntilde;as no coinciden" required/>
							<div class="help-block with-errors"></div>
					</div>
					
					<div class="botones-form text-center" style="margin: auto;">
						<input type="submit" class="btnUsuario input-lg" name="Aceptar"
							value="Aceptar" /> <input type="submit"
							class="btnUsuario input-lg" name="Cancelar" value="Cancelar" />
					 <span style="color: red"><em>${alerta}</em></span>
					 <span style="color:red"><em>${alertaPWDinsegura}</em></span>
					 <span style="color:red"><em>${alertaPWDRepe}</em></span>
					 
					 <span style="color:green"><em>${alertaCambio}</em></span>
					</div>

				</form>
			</div>

		</div>

	</div>

</body>
</html>
