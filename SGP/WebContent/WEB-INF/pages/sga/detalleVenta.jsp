<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Venta</title>
    <jsp:include page="/WEB-INF/pages/commons/header.jsp" />
	<jsp:include page="/WEB-INF/pages/commons/dataTableHeader.jsp" />
	<!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
	<script>
	    $(document).ready(function() {
	    	
	    	$("#cantidad").numeric();
	    	
	    	$("#form1").submit(function(){
	    		if($('#cantidad').val().length > 7){
	    			alert("La cantidad de Productos no puede ser mayor a 9999999");
	    			return false;
	    		}
	    		if($("#cantidad").val().length <= 0){
	    	    	alert("Los campos con * no pueden estar vacíos");
	    	    	return false;
	    	    }else{
	        		return true;
	        	}
	    	});
	        
	        $('#cancelar').click(function(){
				$("#cantidad").css("color", "white");
				$("#cantidad").val("1");
	        	$("#accion").val("listarVentas");
	        	$("#form1").submit(true);
	        });
	        
	    });
	</script>
	<c:if test='${idRol!="1" && idRol!="2"}' >
		<META HTTP-EQUIV="REFRESH" CONTENT="0;URL=${pageContext.request.contextPath}/">
	</c:if>
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
                        	<i class="fa fa-bar-chart-o fa-fw"></i>
                            <b>Modificar Cantidad de Productos vendidos</b>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-10 col-lg-offset-1">
                                    <!--Es importante definir el id del formulario, ya que se validara con JavaScript -->
                                        <form id="form1" name="form1" action="${pageContext.request.contextPath}/ServletControlador" method="post" >
                                        <div class="form-group">
                                            <!-- Esta accion se va modificar por JavaScript según la opción seleccionada -->
                                            <input type="hidden" name="accion"  id="accion" value="guardarVenta"/>
                                            <!--nos va a servir para que tome el valor dinamicamente del nombre de la aplicacion-->
                                            <input type="hidden" name="contexto"  id="contexto" value="${pageContext.request.contextPath}"/>
                                            <!--Id persona. Si estamos editando, reenviamos el id_venta al servidor Esto nos permitirá distinguir 
                                            si estamos Agregando(insert) o modificando (update) -->
            								<input type="hidden" name="idVenta" value="${venta.idVenta}" />
            								<input type="hidden" name="idPersona" value="${venta.idPersona}" />
            								<input type="hidden" name="idProducto" value="${venta.idProducto}" />
            								<input type="hidden" name="idPuntoVenta" value="${venta.idPuntoVenta}" />
            								<input type="hidden" name="precioVenta" value="${venta.precioVenta}" />
            								<input type="hidden" name="fecVenta" value="${venta.fecVenta}" />
                                            <label>Cantidad *</label>
                                            <input type="number" id="cantidad" name="cantidad" value="${venta.cantidad}" class="form-control" required/>
                                            <br/>
                                            <input type="submit" id="submit" class="btn btn-primary" value="Guardar" />
                                            <button id="cancelar" class="btn btn-danger">Cancelar</button>
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