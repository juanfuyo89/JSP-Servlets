<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Pagina de Login del sistema SGP">
    <meta name="author" content="Juan Carlos Fuyo">
    <title>Login SGP</title>
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/dist/js/sb-admin-2.js"></script>
    
    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <!-- Estilos y funciones propias -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/estilos.css"/>
	<script type="javascript" src="${pageContext.request.contextPath}/resources/funciones.js"></script>
	<script>
	$(document).ready(function() {
    	
    	$("#form1").submit(function(){
    		if(($('#username').val().length > 55) || ($('#password').val().length > 55)){
    			alert("El username y/o password no pueden ser mayor a 55 Caracteres");
    			return false;
    		}
    		if(($('#username').val().length <= 0) || ($('#password').val().length <= 0)){
    	    	alert("Los campos no pueden estar vacíos o nulos");
    	    	return false;
    	    }else{
        		return true;
        	}
    	});

  	  	function sf(){
	  	    document.form1.username.value = "";
	  	    document.form1.password.value = "";
	  	    document.form1.username.focus();
  	  	}
  	  
    });
	</script>
  </head>
  <body onload="sf();">
    <div class="container">
        
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Ingrese su Usuario y Contraseña</h3>
                    </div>
                    <div class="panel-body">
                        <form id="form1" name="form1" action="${pageContext.request.contextPath}/ServletControlador" method="post">
                            <input type="hidden" name="accion" value="validarUsuario" />
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" id="username" placeholder="Username" name="username" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" id="password" placeholder="Password" name="password" type="password" value="">
                                </div>
                                <input type="submit" class="btn btn-lg btn-success btn-block" value="Enviar"/>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- /Mensaje -->
		<div class="row">
			<jsp:include page="/WEB-INF/pages/commons/messages.jsp"/>
		</div>
    </div>
  </body>
</html>