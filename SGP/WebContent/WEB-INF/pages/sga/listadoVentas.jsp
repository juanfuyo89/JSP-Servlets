<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Ventas</title>
<jsp:include page="/WEB-INF/pages/commons/header.jsp" />
<jsp:include page="/WEB-INF/pages/commons/dataTableHeader.jsp" />
<!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
<script>
    $(document).ready(function() {
    	
    	$('.ventas').hide();
    	$('.editar').css('cursor','pointer');
    	$('.eliminar').css('cursor','pointer');
    	
    	//console.log("debug-ready");
        $('#dataTable').DataTable({
            responsive: true
        });
        
        $('.editar').click(function(){
        	$id = $(this).attr("id");
        	$idNum = $id.split("_")[1];
        	$("#ventas" + $idNum).attr('checked', true);
        	$("#accion").val("editarVenta");
        	$("#form1").submit();
        });
        
        $('.eliminar').click(function(){
        	$id = $(this).attr("id");
        	$idNum = $id.split("_")[1];
        	$("#ventas" + $idNum).attr('checked', true);
        	$("#accion").val("eliminarVenta");
        	$("#form1").submit();
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
				<div class="col-lg-10 col-lg-offset-1">
					<div class="panel panel-default">
						<div class="panel-heading">Listado de Ventas</div>
						<!-- /.panel-heading -->
						<div class="panel-body">

							<!--Es importante definir el id del formulario, ya que se validara con JavaScript -->
							<form id="form1" name="form1"
								action="${pageContext.request.contextPath}/ServletControlador"
								method="post">
								<!-- Esta accion se va modificar por JavaScript según la opción seleccionada -->
								<input type="hidden" name="accion" id="accion" />
								<table class="table table-striped table-bordered table-hover" id="dataTable">
									<thead>
										<tr>
											<!-- IF para validar el usuario antes de mostrar las opción de editar -->
											<c:if test='${idRol=="1" || idRol=="2"}' >
											<th align="center">
												<span class="glyphicon glyphicon-edit"></span>
											</th>
											</c:if> <!-- /IF Validación usuarios editar -->
											<th align="center">Id. Venta</th>
											<th align="center">PDV</th>
							                <th align="center">Vendedor</th>
							                <th align="center">Producto</th>
							                <th align="center">Fecha</th>
							                <th align="center">Precio</th>
							                <th align="center">Cantidad</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="venta" items="${listaVentas}" varStatus="row">
											<tr class="odd gradeX">
											<!-- IF para validar el usuario antes de mostrar las opción de editar -->
												<c:if test='${idRol=="1" || idRol=="2"}' >
												<td align="center">
													<span class="glyphicon glyphicon-edit editar" title="Editar Cantidad" id="edit_${row.count}"></span>
													<span class="glyphicon glyphicon-trash eliminar" title="Eliminar Venta" id="delete_${row.count}"></span>
													<input class="ventas" type="checkbox" name="ventas" id="ventas${row.count}" value="${venta.idVenta}" />
												</td>
												</c:if> <!-- /IF Validación usuarios editar -->
												<td align="center">
													${venta.idVenta}
												</td>
												<td align="center">
													${venta.idPuntoVenta}
												</td>
												<td align="center">
													${venta.idPersona}
												</td>
												<td align="center">
													${venta.idProducto}
												</td>
												<td align="center">
													${venta.fecVenta}
												</td>
												<td align="center">
													${venta.precioVenta}
												</td>
												<td align="center">
													${venta.cantidad}
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</form>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				
			</div>

		</div>
	</div>
</body>
</html>