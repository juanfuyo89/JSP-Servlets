<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="UTF-8">
    <title>Buscar Tipos de Productos</title>
    <jsp:include page="/WEB-INF/pages/commons/header.jsp" />
	<!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
    <script>
	    $(document).ready(function() {
	    	
	    	$("#idTipo").numeric();
	    	
	    	$("#form1").submit(function(){
	    		if($('#idTipo').val().length > 10){
	    			alert("El Id del tipo de Producto no puede ser mayor a 10 Digitos");
	    			return false;
	    		}
	    		if(($("#idTipo").val().length <= 0) && ($("#nombre").val().length <= 0)){
	    			$("#accion").val("listarTipos");
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
                            Ingrese Uno de los parametros con el cual desea hacer la busqueda
                            <b>(puede dejar los espacios en blanco para consultar todos los Tipos)</b>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-10 col-lg-offset-1">
                                    <!--Es importante definir el id del formulario, ya que se validara con JavaScript -->
                                    <form id="form1" name="form1" action="${pageContext.request.contextPath}/ServletControlador" method="post" >
                                        <div class="form-group">
                                            <!-- Esta accion se va modificar por JavaScript seg�n la opci�n seleccionada -->
                                            <input type="hidden" name="accion"  id="accion" value="mostrarTipo"/>
                                            <!--nos va a servir para que javascript tome el valor dinamicamente
                                            del nombre de la aplicacion-->
                                            <input type="hidden" name="contexto"  id="contexto" value="${pageContext.request.contextPath}"/>
                                            <label>Buscar por Id Tipo</label>
                                            <input type="number" id="idTipo" class="form-control" placeholder="Id Tipo" name="idTipo">
                                            <p class="help-block">Ingrese aqu� el Id del Tipo de Producto a Buscar.</p>
                                            <label>Buscar por Nombre Tipo</label>
                                            <input type="text" id="nombre" class="form-control" placeholder="Nombre Tipo" name="nombre">
                                            <p class="help-block">Ingrese aqu� el Nombre del Tipo de Producto a Buscar.</p>
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