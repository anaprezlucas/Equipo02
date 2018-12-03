<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Modificacion de Usuario</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script 
  src="http://1000hz.github.io/bootstrap-validator/dist/validator.min.js"></script>
<style>
.modificacion-container {
    margin-left: 5%;
	margin-bottom: 5%;
	box-shadow: 5px 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}
.modificacion-container h2{
	padding: 0% 0% 0% 5%;
}
.busqueda-container {
    margin-left: 5%;
	margin-bottom: 5%;
	box-shadow: 5px 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0
		rgba(0, 0, 0, 0.19);
}
.busqueda-container h2{
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
.botones-form .btnModUsuario {
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
      
			<div class="nav-item pull-left col-md-1 fixed-top" style="position: relative; top: 8px">
				<form action="REfichajesAdminNav" method="GET"> <button class="btn" type="submit"><strong><span class="glyphicon glyphicon-sort"></span> Fichajes</strong></button></form>
			</div>
			<div class="col-md-1 pull-right fixed-top" style="position: relative; top: 8px">
				<form action="logout" method="GET"> <button class="btn btn-danger" type="submit"><strong><span class="glyphicon glyphicon-log-out"></span>Salir</strong></button></form>
			</div>
		</div>
	</nav>


	<div class="container busqueda-container col-md-4">
		<div class="row">
			<h2>
				<span class="glyphicon glyphicon-search"></span> B&uacutesqueda de usuario
			</h2>

			<div class="gestion-form left-div">
				<form id="busquedaUsuario" action="buscarUsuarioPorDni"  data-toggle="validator" method=get >
					<div class="form-group">
							<h3>DNI del usuario</h3>
							<input name="txtDniBusqueda" type="text" class="form-control" 
							placeholder="00000000A" maxlength="9" data-error="Formato del DNI inv&aacute;lido"pattern="(([X-Z]{1})([-]?)(\d{7})([-]?)([A-Z]{1}))|((\d{8})([-]?)([A-Z]{1}))" value="" required/>
							<div class="help-block with-errors"></div>
					</div>				
					
					<div class="botones-form text-right" style="margin: auto;">
						    	<input type="submit" class="btnBusquedaUsuario input-lg"
									name="Buscar" value="B&uacute;squeda"/>			
					</div>
					
				</form>
			</div>
			
		</div>
	</div>
		<div class="container modificacion-container col-md-6">
		
			<h2>
				<span class="glyphicon glyphicon-pencil"></span> Modificaci&oacuten Usuario
			</h2>
			<form id="ModificacionUsuario" action="modificarUser"  data-toggle="validator" method=get >
			
        		 	<div class="form-group">
    				<h3>DNI del usuario</h3>
						<input id="DNIUsuario" type="text" disabled class="form-control"
						       placeholder="DNI Usuario" value="${dniUser}" />
					</div>
      
        		<div class="form-group">
  					<h3>Nombre del Usuario</h3>
						<input id="NombreUsuario" type="text" disabled class="form-control" placeholder="Nombre Usuario"
						 value="${nombreUser}" />
					</div>
	
				 	<div class="form-group">
							
							<h3>E-mail del usuario</h3>
							<input name="txtUsuarioEmail" type="email" class="form-control" 
							placeholder="usuario@ejemplo.com" data-error="Direcci&oacute;n de correo inv&aacute;lida" value="" required/>
							<div class="help-block with-errors"></div>
							<label>E-mail actual: ${EmailUsuario} </label>
				 	</div>
				 	<div class="form-group">
						    <h3>Rol del usuario</h3> 
						    <select name = "listaRoles">
						    	<option value = "Empleado"> Empleado </option>
						    	<option value = "Gestor de incidencias"> Gestor de incidencias </option>
						    </select>
						    <label>Rol de usuario actual: ${RolUsuario} </label>
						    	
					</div>
					
					<div class="botones-form text-right" style="margin: auto;">
						    	<input type="submit" class="btnModUsuario input-lg"
									name="Aceptar" value="Aceptar" />
								
						</div>
					</form>
			
			</div>

</body>
</html>
