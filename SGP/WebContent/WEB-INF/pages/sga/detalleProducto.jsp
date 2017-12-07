<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Productos</title>
    <jsp:include page="/WEB-INF/pages/commons/header.jsp" />
	<jsp:include page="/WEB-INF/pages/commons/dataTableHeader.jsp" />
	<!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
	<script>
	    $(document).ready(function() {
	    	
	    	$('#divDcto').hide();
	    	$('#aplicaDcto').css('cursor','pointer');
	    	
	    	$costo = $("#costo").val().split(".");
	    	$("#costo").val($costo[0]);
	    	$precio = $("#precio").val().split(".");
	    	$("#precio").val($precio[0]);
	    	
	    	if($("#porcentaje").val() > 0){
	    		$("#aplicaDcto").attr('checked', true);
	    		$("#aplicaDcto").val("1");
	    		$('#divDcto').show();
	    	}else{
	    		$("#aplicaDcto").attr('checked', false);
	    		$("#aplicaDcto").val("");
	    		$('#divDcto').hide();
	    	}
	    	
	    	$("#aplicaDcto").click(function(){
	    		if($("#aplicaDcto").prop('checked')){
	    			$('#divDcto').show();
		    		$("#aplicaDcto").val("1");
	    		} else{
	    			$('#divDcto').hide();
		    		$("#aplicaDcto").val("");
	    		}
	    	});
	    	
	    	$("#costo").numeric();
	    	$("#precio").numeric();
	    	$("#idTipo").numeric();
	    	$("#idMarca").numeric();
	    	$("#porcentaje").numeric();
	    	
	    	$("#form1").submit(function(){
	    		if($("#aplicaDcto").prop('checked')){
	    	    	if((!isValidDate($("#fechInicial").val())) || (!isValidDate($("#fechFinal").val()))){
		        		alert("Formato de Fecha Invalido, debe ser de la forma aaaa-mm-dd");
		        		return false;	    	    		
	    	    	}
	    			if(($("#descDcto").val() <= 0)||($("#fechInicial").val() <= 0)
		    				||($("#fechFinal").val() <= 0)||($("#porcentaje").val() <= 0)){
		    	    	alert("Si Aplica Descuento, los campos no pueden estar vacíos");
		    	    	return false;
		    	    }
	        	}
	    		if($('#nombre').val().length > 50){
	    			alert("El nombre del producto no puede ser mayor a 50 Caracteres");
	    			return false;
	    		}
	    		if($('#descProducto').val().length > 300){
	    			alert("La descripción del producto no puede ser mayor a 300 Caracteres");
	    			return false;
	    		}
	    		if($("#costo").val().length > 10){
	    			alert("El costo del producto no puede superar los $999999999");
	    			return false;
	    		}
	    		if($("#precio").val().length > 10){
	    			alert("El precio del producto no puede superar los $999999999");
	    			return false;
	    		}
	    		if($("#idTipo").val().length > 9){
	    			alert("El Id del tipo de producto no puede superar 9 digitos");
	    			return false;
	    		}
	    		if($("#idMarca").val().length > 9){
	    			alert("El Id de de la marca no puede superar 9 digitos");
	    			return false;
	    		}
	    		if($('#descDcto').val().length > 200){
	    			alert("La descripción del Descuento no puede ser mayor a 200 Caracteres");
	    			return false;
	    		}
	    		if($("#procentaje").val().length > 2){
	    			alert("El porcentaje de descuento no puede superar dos dígitos");
	    			return false;
	    		}
	    		if(($("#nombre").val().length <= 0)||($("#costo").val().length <= 0)||($("#precio").val().length <= 0)
	    				||($("#idTipo").val().length <= 0)||($("#idMarca").val().length <= 0)){
	    	    	alert("Los campos con * no pueden estar vacíos");
	    	    	return false;
	    	    }else{
	        		return true;
	        	}
	    	});
	        
	        $('#cancelar').click(function(){
	    		$("#aplicaDcto").attr('checked', false);
	    		$("#aplicaDcto").val("");
	        	$("#nombre").css("color", "white");
	        	$("#nombre").val("a");
				$("#descProducto").css("color", "white");
				$("#descProducto").val("a");
				$("#costo").css("color", "white");
				$("#costo").val("1");
				$("#precio").css("color", "white");
				$("#precio").val("1");
				$("#idTipo").css("color", "white");
				$("#idTipo").val("1");
				$("#idMarca").css("color", "white");
				$("#idMarca").val("1");
	        	$("#accion").val("listarProductos");
	        	$("#form1").submit(true);
	        });

	        function isValidDate(dateString){
	            // revisar el patrón
	            if(!/^\d{4}\-\d{1,2}\-\d{1,2}$/.test(dateString))
	                return false;

	            // convertir los numeros a enteros
	            var parts = dateString.split("-");
	            var day = parseInt(parts[2], 10);
	            var month = parseInt(parts[1], 10);
	            var year = parseInt(parts[0], 10);
	            // Revisar los rangos de año y mes
	            if( (year < 1000) || (year > 3000) || (month == 0) || (month > 12) )
	                return false;

	            var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

	            // Ajustar para los años bisiestos
	            if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
	                monthLength[1] = 29;

	            // Revisar el rango del dia
	            return day > 0 && day <= monthLength[month - 1];
	        }
	        
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
                            Producto
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-10 col-lg-offset-1">
                                    <!--Es importante definir el id del formulario, ya que se validara con JavaScript -->
                                        <form id="form1" name="form1" action="${pageContext.request.contextPath}/ServletControlador" method="post" e>
                                        <div class="form-group">
                                            <!-- Esta accion se va modificar por JavaScript según la opción seleccionada -->
                                            <input type="hidden" name="accion"  id="accion" value="guardarProducto"/>
                                            <!--nos va a servir para que tome el valor dinamicamente del nombre de la aplicacion-->
                                            <input type="hidden" name="contexto"  id="contexto" value="${pageContext.request.contextPath}"/>
                                            <!--Id persona. Si estamos editando, reenviamos el id_persona al servidor Esto nos permitirá distinguir si estamos Agregando(insert) o modificando (update) -->
            								<input type="hidden" name="idProducto" value="${productoBean.producto.idProducto}" />
                                            <label>Nombre *</label>
                                            <input  type="text" id="nombre" name="nombre" value="${productoBean.producto.nombre}" class="form-control" required/>
                                            <label>Descripción *</label>
                                            <textarea id="descProducto" name="descProducto" class="form-control" rows="3" required>${productoBean.producto.descripcion}</textarea>
                                            <label>Costo de Compra *</label>
                                            <input type="number" id="costo" name="costo" value='${productoBean.producto.costo}' class="form-control" required/>
                                            <label>Precio de Venta *</label>
                                            <input type="number" id="precio" name="precio" value='${productoBean.producto.precio}' class="form-control" required/>
                                            <label>Id. Tipo *</label>
                                            <input type="number" id="idTipo" name="idTipo" value="${productoBean.producto.tipo}" class="form-control" required/>
                                            <label>Id. Marca *</label>
                                            <input type="number" id="idMarca" name="idMarca" value="${productoBean.producto.marca}" class="form-control" required/>
                                            <label>Aplica Dcto</label>
											<input type="checkbox" name="aplicaDcto" id="aplicaDcto"/>
                                            <!--Id Descuento. Si estamos editando, reenviamos el id_descuento al servidor Esto nos permitirá distinguir si estamos Agregando(insert) o modificando (update) -->
            								<input type="hidden" id="idDcto" name="idDcto" value="${productoBean.descuento.idDcto}" />
            								<div id="divDcto">
                                            <label>Descripción Descuento</label>
                                            <textarea id="descDcto" name="descDcto" class="form-control" rows="3">${productoBean.descuento.descripcion}</textarea>
                                            <label>Fecha Inicial</label>
                                            <input type="Date" id="fechInicial" name="fechInicial" value="${productoBean.descuento.fechInicial}" class="form-control" />
                                            <label>Fecha Final</label>
                                            <input type="Date" id="fechFinal" name="fechFinal" value="${productoBean.descuento.fechFinal}" class="form-control" />
                                            <label>Porcentaje Dcto</label>
                                            <input type="number" id="porcentaje" name="porcentaje" value="${productoBean.descuento.porcentaje}" class="form-control" />
                                            </div>
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