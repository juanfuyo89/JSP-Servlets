<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Puntos de Venta</title>
    <jsp:include page="/WEB-INF/pages/commons/header.jsp" />
	<jsp:include page="/WEB-INF/pages/commons/dataTableHeader.jsp" />
	<!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
	<script>
	    $(document).ready(function() {
	    	
	    	$("#form1").submit(function(){
	    		if(($('#nombre').val().length > 50) || ($('#direccion').val().length > 50)){
	    			alert("El nombre y/o dirección del Punto de Venta no pueden ser mayor a 50 Caracteres");
	    			return false;
	    		}
	    		if($('#telefono').val().length > 12){
	    			alert("El telefono del punto de venta no puede superar 12 dígitos");
	    			return false;
	    		}
	    		if($('#idAdmin').val().length > 12){
	    			alert("El id del Administrador del punto de venta no puede superar 12 dígitos");
	    			return false;
	    		}
	    		if(($("#nombre").val().length <= 0) && ($("#dirección").val().length <= 0) 
	    				&& ($("#telefono").val().length <= 0)){
	    	    	alert("Los campos con * no pueden estar vacíos o nulos");
	    	    	return false;
	    	    }else{
	        		return true;
	        	}
	    	});
	        
	        $('#cancelar').click(function(){
	        	$("#nombre").css("color", "white");
	        	$("#nombre").val("a");
	        	$("#direccion").css("color", "white");
	        	$("#direccion").val("a");
	        	$("#telefono").css("color", "white");
	        	$("#telefono").val("1");
	        	$("#accion").val("listarPuntosVenta");
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
                            Punto de Venta
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-10 col-lg-offset-1">
                                    <!--Es importante definir el id del formulario, ya que se validara con JavaScript -->
                                        <form id="form1" name="form1" action="${pageContext.request.contextPath}/ServletControlador" method="post" >
                                        <div class="form-group">
                                            <!-- Esta accion se va modificar por JavaScript según la opción seleccionada -->
                                            <input type="hidden" name="accion"  id="accion" value="guardarPuntoVenta"/>
                                            <!--nos va a servir para que tome el valor dinamicamente del nombre de la aplicacion-->
                                            <input type="hidden" name="contexto"  id="contexto" value="${pageContext.request.contextPath}"/>
                                            <!--Id persona. Si estamos editando, reenviamos el id_persona al servidor Esto nos permitirá distinguir 
                                            si estamos Agregando(insert) o modificando (update) -->
            								<input type="hidden" name="idPuntoVenta" value="${puntoVenta.idPuntoVenta}" />
                                            <label>Nombre Punto de Venta *</label>
                                            <input  type="text" id="nombre" name="nombre" value="${puntoVenta.nombre}" class="form-control" required/>
                                            <label>Dirección *</label>
                                            <input  type="text" id="direccion" name="direccion" value="${puntoVenta.direccion}" class="form-control" required/>
                                            <label>Teléfono *</label>
                                            <input  type="number" id="telefono" name="telefono" value="${puntoVenta.telefono}" class="form-control" required/>
                                            <label>Id Administrador</label>
                                            <input  type="text" id="idAdmin" name="idAdmin" value="${puntoVenta.idAdmin}" class="form-control" />
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