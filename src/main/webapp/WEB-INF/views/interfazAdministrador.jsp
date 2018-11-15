<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>Fichajes</title>
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
	margin-right: 5%;
	margin-left: 5%;
}
.historial-container {
	margin-top: 5%;
}
.gestion-form {
	padding: 5%;
	box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}
.gestion-form h3 {
	text-align: center;
	color: #333;
}
.historial-form {
	padding: 5%;
	background: #0062cc;
	box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}
.historial-form h3 {
	text-align: center;
	color: #fff;
}
.historial-form h4 {
	color: #fff;
}
.btnfichaje {
	width: 50%;
	border-radius: 2rem;
	padding: 0%;
	border: solid;
	cursor: pointer;
	margin-bottom: 15px;
}
.btnUsuario {
	width: 50%;
	border-radius: 2rem;
	padding: 0%;
	border: solid;
	cursor: pointer;
	margin-bottom: 5px;
}
.gestion-form .btnfichaje {
	font-weight: 600;
	color: #fff;
	background-color: #0062cc;
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
			<div class="nav-item pull-left fixed-top"
				style="position: relative; top: 8px">
				<form action="inicio" method="GET">
					<button class="btn btn-space" type="submit">
						<strong><span class="glyphicon glyphicon-sort"></span>
							Fichajes</strong>
					</button>
				</form>
			</div>
			<div class="nav-item pull-left  fixed-top"
				style="position: relative; top: 8px">
				<form action="fichajes" method="GET">
					<button class="btn btn-space " type="submit">
						<strong><span class="glyphicon glyphicon-copy"></span>
							Gestionar Incidencias</strong>
					</button>
				</form>
			</div>
			<div class="nav-item pull-left fixed-top"
				style="position: relative; top: 8px">
				<form action="Incidencias" method="GET">
					<button class="btn btn-space" type="submit">
						<strong><span class="glyphicon glyphicon-cog"></span>
							Gestionar Contraseña</strong>
					</button>
				</form>
			</div>
			<div class="nav-item pull-right fixed-top"
				style="position: relative; top: 8px">
				<form action="logout" method="GET">
					<button class="btn btn-danger" type="submit">
						<strong><span class="glyphicon glyphicon-log-out"></span>Salir</strong>
					</button>
				</form>
			</div>
		</div>
	</nav>

	<center>
		<br /> <img src="https://i.imgur.com/bwlSMSI.png" style="width: %;">
		<br />
	</center>

	<div class="container fichaje-container col-md-4">
		<div class="row">
			<h2>
				<span class=" glyphicon glyphicon-sort"></span> Fichajes
			</h2>



			<div class="gestion-form">
				<form>

					<div class="form-inline text-center" style="margin: auto;">

						<input type="submit" class="btnfichaje input-lg"
							name="abrirFichaje" value="Abrir Fichaje" formaction="abrirFichaje" />
					
						<input type="submit" class="btnfichaje input-lg"
						name="cerrarFichaje" value="Cerrar Fichaje" formaction="cerrarFichaje" />
					

					</div>
				</form>
			</div>

		</div>

	</div>
	
	<div class="container fichaje-container col-md-4">
		<div class="row">
			<h2>
				<span class=" glyphicon glyphicon-asterisk"></span> Gestión de usuarios
			</h2>


			<div class="gestion-form center-div">
				<form method = "get">

					<div class="form-inline text-center" style="margin: auto;">

							
							<input type="submit" class="btnUsuario input-lg"
								name="crearUsuario" value="Crear usuario" formaction = "interfazCrearUsuario"/>
							
							<input type="submit" class="btnUsuario input-lg"
								name="modificarUsuario" value="Modificar usuario" formaction = ""/>
						
							<input type="submit" class="btnUsuario input-lg"
								name="eliminarUsuario" value="Eliminar usuario" formaction = ""/>
					
					</div>
				</form>
			</div>

		</div>

	</div>
</body>
</html>