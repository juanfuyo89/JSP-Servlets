<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles Producto</title>
    <jsp:include page="/WEB-INF/pages/commons/header.jsp" />
	<jsp:include page="/WEB-INF/pages/commons/dataTableHeader.jsp" />
	<!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
	<script>
	    $(document).ready(function() {
	    	
	    	if($("#percent").text() > 0){
	    		$("#precioVenta").val($("#price").text() - $("#price").text() * ($("#percent").text()/100));
	    	}
	    	else{
	    		$("#precioVenta").val($("#price").text());
	    	}
	    	
	    	//$("#precioVenta").prop('disabled', true);
	    	
	    	$precio = $("#precioVenta").val().split(".");
	    	$("#precioVenta").val($precio[0]);
	    	
	    	$("#idPersona").numeric();
	    	$("#idPuntoVenta").numeric();
	    	$("#precioVenta").numeric();
	    	$("#cantidad").numeric();
	    	
	    	$("#form1").submit(function(){
	    		if($("#idPersona").val().length > 12){
	    			alert("La Cédula de la Persona no puede superar 12 digitos");
	    			return false;
	    		}
	    		if($("#idPuntoVenta").val().length > 10){
	    			alert("El Id del Punto de Venta no puede superar 10 digitos");
	    			return false;
	    		}
	    		if($("#precioVenta").val().length > 10){
	    			alert("El precio del producto no puede superar los $999999999");
	    			return false;
	    		}
	    		if($('#cantidad').val().length > 6){
	    			alert("La cantidad de Productos vendidos no puede ser mayor a 999999");
	    			return false;
	    		}
	    		if(($("#idPersona").val() <= 0)||($("#idPuntoVenta").val() <= 0)||
	    				($("#precioVenta").val() <= 0)||($("#cantidad").val() <= 0)){
	    	    	alert("Los campos con * no pueden estar vacíos");
	    	    	return false;
	    	    }else{
	        		return true;
	        	}
	    	});
	        
	        $('#cancelar').click(function(){
	    		$("#idPersona").attr('checked', false);
	    		$("#idPersona").val("1");
	        	$("#idPuntoVenta").css("color", "white");
	        	$("#idPuntoVenta").val("1");
				$("#precioVenta").css("color", "white");
				$("#precioVenta").val("1");
				$("#cantidad").css("color", "white");
				$("#cantidad").val("1");
	        	$("#accion").val("listarProductos");
	        	$("#form1").submit(true);
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
            	<!-- /.col-lg-6 -->
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i>
                            <b>Detalles Producto</b>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <!-- Nav tabs -->
                            <ul class="nav nav-pills">
                                <li class="active"><a href="#productoPanel" data-toggle="tab">Producto</a>
                                </li>
                                <li><a href="#descuentoPanel" data-toggle="tab">Descuento</a>
                                </li>
                            </ul>

                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div class="tab-pane fade in active" id="productoPanel">
                                    <h4>${productoBean.producto.idProducto} - ${productoBean.producto.nombre}</h4>
                                    <p>${productoBean.producto.descripcion}</p>
                                    <h5>Precio:</h5>
                                    <p>$ <span id="price">${productoBean.producto.precio}</span></p>
                                    <h6>Tipo de Producto</h6>
                                    <p>${productoBean.tipo.idTipo} - ${productoBean.tipo.nombre}</p>
                                    <h6>Marca</h6>
                                    <p>${productoBean.marca.idMarca} - ${productoBean.marca.nombre}</p>
                                </div>
                                <div class="tab-pane fade" id="descuentoPanel">
                                    <h4>Descuento - ${productoBean.descuento.idDcto}</h4>
                                    <p>Descuento - ${productoBean.descuento.descripcion}</p>
                                    <h6>Desde: ${productoBean.descuento.fechInicial} - Hasta: ${productoBean.descuento.fechFinal}</h6>
                                    <h5>Porcentaje:</h5>
                                    <p><span id="percent">${productoBean.descuento.porcentaje}</span>%</p>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->
                <!-- /.col-lg-6 -->
                <div class="col-lg-6">
					<div class="panel panel-default">
                        <div class="panel-heading">
                        	<i class="fa fa-bar-chart-o fa-fw"></i>
                            <b>Vender Producto</b>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-10 col-lg-offset-1">
                                    <!--Es importante definir el id del formulario, ya que se validara con JavaScript -->
                                        <form id="form1" name="form1" action="${pageContext.request.contextPath}/ServletControlador" method="post" >
                                        <div class="form-group">
                                            <!--nos va a servir para que tome el valor dinamicamente del nombre de la aplicacion-->
                                            <input type="hidden" name="contexto"  id="contexto" value="${pageContext.request.contextPath}"/>
											<!-- Esta accion se va modificar por JavaScript según la opción seleccionada -->
											<input type="hidden" name="accion" id="accion" value="guardarVenta"/>
                                            <!--nos va a servir para que haga un insert en lugar de un update de la venta -->
                                            <input type="hidden" name="idProducto"  id="idProducto" value="${productoBean.producto.idProducto}"/>
                                            <label>Cédula Vendedor *</label>
                                            <input  type="number" id="idPersona" name="idPersona" class="form-control" required/>
                                            <label>Id. Punto de Venta *</label>
                                            <input type="number" id="idPuntoVenta" name="idPuntoVenta" class="form-control" required/>
                                            <label>Precio de Venta *</label>
                                            <input type="number" id="precioVenta" name="precioVenta" class="form-control" readonly required/>
                                            <label>Cantidad *</label>
                                            <input type="number" id="cantidad" name="cantidad" class="form-control" required/>
                                            <br/>
                                            <input type="submit" id="submit" class="btn btn-primary" value="Vender" />
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
                <!-- /.col-lg-6 -->
            </div>
            <!-- /.row -->
      
      	</div>
      
    </div>
    
</body>
</html>