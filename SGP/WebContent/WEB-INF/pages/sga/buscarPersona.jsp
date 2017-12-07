<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="UTF-8">
    <title>Buscar Persona</title>
    <jsp:include page="/WEB-INF/pages/commons/header.jsp" />
	<!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
    <script>
	    $(document).ready(function() {
	    	
	    	$("#cedula").numeric();
	    	
	    	$("#form1").submit(function(){
	    		if($('#cedula').val().length > 12){
	    			alert("La cédula no puede ser mayor a 12 Digitos");
	    			return false;
	    		}
	    		if($("#cedula").val().length <= 0){
	    			$("#accion").val("listarPersonas");
	    			return true;
	    	    }else{
	        		return true;
	        	}
	    	});
	        
	    });
	</script>
</head>
<body>

	<div id="wrapper">
	
      	<jsp:include page="/WEB-INF/pages/commons/menu.jsp" />
      	
      	<!-- Hacemos uso del framework Bootstrap para manejar las vistas -->
		<div id="page-wrapper"> 
		
			<br></br>
	      	<!-- /Mensaje -->
			<div class="row">
				<jsp:include page="/WEB-INF/pages/commons/messages.jsp"/>
			</div>

	        <!-- /.row -->
            <div class="row">
                <div class="col-lg-6 col-lg-offset-3">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Buscar Persona por N° de Cédula
                            <b>(puede dejar los espacios en blanco para consultar todas las Personas)</b>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-10 col-lg-offset-1">
                                    <!--Es importante definir el id del formulario, ya que se validara con JavaScript -->
                                    <form id="form1" name="form1" action="${pageContext.request.contextPath}/ServletControlador" method="post" >
                                        <div class="form-group">
                                            <!-- Esta accion se va modificar por JavaScript según la opción seleccionada -->
                                            <input type="hidden" name="accion"  id="accion" value="mostrarPersona"/>
                                            <!--nos va a servir para que javascript tome el valor dinamicamente
                                            del nombre de la aplicacion-->
                                            <input type="hidden" name="contexto"  id="contexto" value="${pageContext.request.contextPath}"/>
                                            <label>Cédula</label>
                                            <input type="number" id="cedula" class="form-control" placeholder="Cédula" name="cedula">
                                            <p class="help-block">Ingrese aquí el N° de Cédula de la persona a Buscar.</p>
                                            <input type="submit" class="btn btn-primary" value="Buscar" />
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
      
      </div>
      
    </div>
    
</body>
</html>