<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Productos en PDV</title>
    <jsp:include page="/WEB-INF/pages/commons/header.jsp" />
	<jsp:include page="/WEB-INF/pages/commons/dataTableHeader.jsp" />
	<!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
	<script>
	    $(document).ready(function() {
	    	
	    	$("#idPuntoVenta").numeric();
	    	$("#idProducto").numeric();
	    	$("#cantidad").numeric();
	    	
	    	$("#form1").submit(function(){
	    		if(($('#idPuntoVenta').val().length > 12) || ($('#idProducto').val().length > 12) 
	    				|| ($('#cantidad').val().length > 12)){
	    			alert("los valores no pueden ser superiores a 12 dígitos");
	    			return false;
	    		}
	    		if(($('#idPuntoVenta').val().length <= 0) || ($('#idProducto').val().length <= 0)){
	    	    	alert("Los campos con * no pueden estar vacíos o nulos");
	    	    	return false;
	    	    }else{
	        		return true;
	        	}
	    	});
	        
	        $('#cancelar').click(function(){
	        	$("#idPuntoVenta").css("color", "white");
	        	$("#idPuntoVenta").val("1");
	        	$("#idProducto").css("color", "white");
	        	$("#idProducto").val("1");
	        	$("#cantidad").css("color", "white");
	        	$("#cantidad").val("1");
	        	$("#accion").val("listarProductosPuntoVenta");
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
                            Ingresar o Modificar Productos en el punto de venta
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-10 col-lg-offset-1">
                                    <!--Es importante definir el id del formulario, ya que se validara con JavaScript -->
                                        <form id="form1" name="form1" action="${pageContext.request.contextPath}/ServletControlador" method="post" >
                                        <div class="form-group">
                                            <!-- Esta accion se va modificar por JavaScript según la opción seleccionada -->
                                            <input type="hidden" name="accion"  id="accion" value="guardarProductosPuntoVenta"/>
                                            <!--nos va a servir para que tome el valor dinamicamente del nombre de la aplicacion-->
                                            <input type="hidden" name="contexto"  id="contexto" value="${pageContext.request.contextPath}"/>
                                            <!-- Validamos que sea el Administrador del sistema para permitir la opción -->
                                            <c:choose>
												<c:when test='${productosPuntoVenta.idProducto != null}'>
													<label>Id Punto de Venta *</label>
	                                            	<input  type="number" id="idPuntoVenta" name="idPuntoVenta" value="${productosPuntoVenta.idPuntoVenta}" 
	                                            			class="form-control" required/>
	                                            </c:when>
												<c:otherwise>
													<input type="hidden" name="idPuntoVenta"  id="idPuntoVenta" value="${productosPuntoVenta.idPuntoVenta}"/>
												</c:otherwise>
											</c:choose>
                                            <label>Id Producto *</label>
                                            <input  type="number" id="idProducto" name="idProducto" value="${productosPuntoVenta.idProducto}" class="form-control" required/>
                                            <label>Cantidad</label>
                                            <input  type="number" id="cantidad" name="cantidad" value="${productosPuntoVenta.cantidad}" class="form-control" required/>
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