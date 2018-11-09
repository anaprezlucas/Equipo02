<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>Creación de Usuario</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<style>
.fichaje-container {
    margin-left: 5%;
	margin-bottom: 5%;
	box-shadow: 5px 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}

.fichaje-container h2{
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
	padding: 5% 0% 0% 0%;
}

.gestion-form .btnUsuario {
	font-weight: 600;
	color: #fff;
	background-color: #0062cc;
}
</style>


</head>

<body>

	<nav class="navbar navbar-inverse" style="background-color: #337ab7;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"></a>
			</div>
			<ul class="nav navbar-nav">
				<li style="color: white; font-size: 15px;"><a href="#">Inicio</a></li>
				<li class="active" style="color: red; font-size: 15px;"><a
					href="#">Fichajes</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
						Cerrar Sesión</a></li>
			</ul>
		</div>
	</nav>


	<div class="container fichaje-container col-md-8">
		<div class="row">
			<h2>
				<span class=" glyphicon glyphicon-plus"></span> Crear usuario
			</h2>

			<div class="gestion-form left-div col-md-6">
				<form>

							<h3>E-mail del usuario</h3>
							<input name="txtUsuarioEmail" type="text" class="form-control" 
							placeholder="usuario@ejemplo.com" value="" />	
											
							<h3>Nombre del usuario</h3>
							<input name="txtUsuarioNombre" type="text" class="form-control" 
							placeholder="Nombre y apellidos" value="" />
						  
						    <h3>Rol del usuario</h3>
						    <select name = "listaRoles">
						    	<option value = "empleado"> Empleado </option>
						    	<option value = "gestor"> Gestor de incidencias </option>
						    </select>
						    
						    <div class="botones-form text-left" style="margin: auto;">
						    	<input type="submit" class="btnUsuario input-lg"
									name="Aceptar" value="Aceptar" />
								
						    	<input type="submit" class="btnUsuario input-lg"
									name="Cancelar" value="Cancelar" />
							</div>
					
				</form>
			</div>

		</div>

	</div>

</body>
</html>