<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>Administrador</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  
<style>

.fichaje-container{
    margin-top: 5%;
    margin-bottom: 5%;
}

.historial-container{
  margin-top: 10%;
}

.gestion-form{
    padding: 5%;
    box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0 rgba(0, 0, 0, 0.19);
    
}

.gestion-form h3{
    text-align: center;
    color: #333;
}

.historial-form{
    padding: 5%;
    background: #0062cc;
    box-shadow: 0 5px 8px 0 rgba(0, 0, 0, 0.2), 0 9px 26px 0 rgba(0, 0, 0, 0.19);
}
.historial-form h3{
    text-align: center;
    color: #fff;
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
  			<li style="color:white; font-size:15px;"><a href="#">Inicio</a></li>
				<li class="active"style="color:red; font-size:15px;"><a href="#">Fichajes</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Cerrar Sesión</a></li>
			</ul>
		</div>
	</nav>

  <center>
   <br/>
    <img src="http://i65.tinypic.com/s5ybty.png" style="width:%;">
   <br/>
   </center>
  


<div class="container fichaje-container ">
  <div class="row">
    <h2><span class=" glyphicon glyphicon-sort"></span> Fichajes </h2>
   
      
  
      <div class="gestion-form center-div col-md-10">
        <h3>Gestión y Validación de Fichajes</h3>
        <form>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Tu Usuario *" value="" />
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="Tu contraseña *" value="" />
            </div>
          <div class="form-inline text-center" style="margin:auto;">
            <div class="form-group">
                <input type="submit" class="btnSubmit" value="Fichar" />
            </div>
            <div class="form-group">
                <input type="submit" class="btnSubmit" value="Cerrar Fichaje" />
            </div>
            </div>
            <div class="form-group">
                <a href="#" class="ForgetPwd">¿Has olvidado tu contraseña?</a>
            </div>
        </form>
      </div>
      
</div>


<div class= "container historial-container">
    <div class="historial-form col-md-10">
    
            <h3>Historial de Fichajes</h3>
            <div class="panel-body">Usuario: </div>
            <div class="panel-body">Hora actual:  </div>
    </div>
      
</div>
 
  <div class="col-md-12" style="position: fixed; bottom: 0;">
  <div class="panel-footer">
   &copy; Copyright 2018 InTime - Equipo02. Todos los derechos reservados.
  </div>
 </div>

  </div>
</div>