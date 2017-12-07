<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="UTF-8">
    <title>Buscar Producto</title>
    <jsp:include page="/WEB-INF/pages/commons/header.jsp" />
    <!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
    <script>
	    $(document).ready(function() {
	    	
	    	$("#idProducto").numeric();
	    	$("#idTipo").numeric();
	    	$("#idMarca").numeric();
	    	
	    	$("#form1").submit(function(){
	    		if(($("#idProducto").val().length > 12) || ($("#idTipo").val().length > 12) 
	    				|| ($("#idMarca").val().length > 12)){
	    	    	alert("Los Ids no deben superar una logitud de 12 caracteres");
	    	    	return false;
	    	    }
	    		if($("#nombre").val().length > 50){
	    	    	alert("El nombre del Producto no debe ser mayor 50 caracteres");
	    	    	return false;
	    	    }
	    		if(($("#idProducto").val().length <= 0) && ($("#nombre").val().length <= 0) 
	    				&& ($("#idTipo").val().length <= 0) && ($("#idMarca").val().length <= 0)){
	    			$("#accion").val("listarProductos");
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
                            <b>(puede dejar los espacios en blanco para consultar todas los Productos)</b>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-10 col-lg-offset-1">
                                    <!--Es importante definir el id del formulario, ya que se validara con JavaScript -->
                                    <form id="form1" name="form1" action="${pageContext.request.contextPath}/ServletControlador" method="post" >
                                        <div class="form-group">
                                            <!-- Esta accion se va modificar por JavaScript según la opción seleccionada -->
                                            <input type="hidden" name="accion"  id="accion" value="mostrarProducto"/>
                                            <!--nos va a servir para que javascript tome el valor dinamicamente
                                            del nombre de la aplicacion-->
                                            <input type="hidden" name="contexto"  id="contexto" value="${pageContext.request.contextPath}"/>
                                            <label>Buscar por Id Producto</label>
                                            <input type="number" id="idProducto" class="form-control" placeholder="Id Producto" name="idProducto">
                                            <p class="help-block">Ingrese aquí el Id del Producto a Buscar.</p>
                                            <label>Buscar por Nombre Producto</label>
                                            <input type="text" id="nombre" class="form-control" placeholder="Nombre Producto" name="nombre">
                                            <p class="help-block">Ingrese aquí el Nombre del Producto a Buscar.</p>
                                            <label>Buscar por Id. Marca</label>
                                            <input type="number" id="idMarca" class="form-control" placeholder="Id Marca" name="idMarca">
                                            <p class="help-block">Ingrese aquí el Id de la Marca de Productos a Buscar.</p>
                                            <label>Buscar por Id. Tipo</label>
                                            <input type="number" id="idTipo" class="form-control" placeholder="Id Tipo" name="idTipo">
                                            <p class="help-block">Ingrese aquí el Id del Tipo de Productos a Buscar.</p>
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