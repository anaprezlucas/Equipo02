<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
<title>Login InTime</title>

<style>
.login-container{
    margin-bottom: 10%;
}
</style>

</head>
<body>

<center>
   <br/>
    <img src="http://i65.tinypic.com/s5ybty.png" style="width:%;" align=right> 
   <br/>
</center>
   
<div class="container login-container ">
  <div class="row">
    <h2><span class=" glyphicon glyphicon-pencil"></span> Login </h2>

      <div class="gestion-form center-div col-md-10">
        <form>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Tu Usuario *" value="" />
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="Tu contraseña *" value="" />
            </div>
          <div class="form-inline text-center" style="margin:auto;">
            <div class="form-group">
                <input type="submit" class="btnSubmit" value="Acceso" />
            </div>
            </div>
            <div class="checkbox">
   				<label><input type="checkbox">Recordar usuario</label>
 			</div>
            <div class="form-group">
                <a href="#" class="ForgetPwd">Recuperar contraseña</a>
            </div>
        </form>
      </div>
</div>
      
</div>
	
</body>
</html>