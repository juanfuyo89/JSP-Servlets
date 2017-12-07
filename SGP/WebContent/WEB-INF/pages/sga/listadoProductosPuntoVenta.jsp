<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Productos x PDV</title>
<jsp:include page="/WEB-INF/pages/commons/header.jsp" />
<jsp:include page="/WEB-INF/pages/commons/dataTableHeader.jsp" />
<!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
<script>
    $(document).ready(function() {
    	
    	$('.idPuntoVenta').hide();
    	$('.idProducto').hide();
    	$('.editar').css('cursor','pointer');
    	$('.eliminar').css('cursor','pointer');
    	
    	//console.log("debug-ready");
        $('#dataTable').DataTable({
            responsive: true
        });
        
        $('.editar').click(function(){
        	$id = $(this).attr("id");
        	$idNum = $id.split("_")[1];
        	$("#idPuntoVenta" + $idNum).attr('checked', true);
        	$("#idProducto" + $idNum).attr('checked', true);
        	$("#accion").val("editarProductosPuntoVenta");
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
				<div class="col-lg-8 col-lg-offset-2">
					<div class="panel panel-default">
						<div class="panel-heading">Listado de Productos por Punto de Venta</div>
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
											<c:if test='${idRol=="1"}' >
											<th align="center">
												<span class="glyphicon glyphicon-edit"></span>
											</th>
											</c:if> <!-- /IF Validación usuarios editar -->
											<th align="center">Id. PDV</th>
							                <th align="center">Id. Producto</th>
							                <th align="center">Cantidad</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="productosPuntoVenta" items="${listaProductosPuntoVenta}" varStatus="row">
											<tr class="odd gradeX">
											<!-- IF para validar el usuario antes de mostrar las opción de editar Marcas -->
												<c:if test='${idRol=="1"}' >
												<td align="center">
													<span class="glyphicon glyphicon-edit editar" title="Editar Registro" id="edit_${row.count}"></span>
													<input class="idPuntoVenta" type="checkbox" name="idPuntoVenta" id="idPuntoVenta${row.count}" 
																value="${productosPuntoVenta.idPuntoVenta}" />
													<input class="idProducto" type="checkbox" name="idProducto" id="idProducto${row.count}" 
																value="${productosPuntoVenta.idProducto}" />
												</td>
												</c:if> <!-- /IF Validación usuarios editar Marcas -->
												<td align="center">
													${productosPuntoVenta.idPuntoVenta}
												</td>
												<td align="center">
													${productosPuntoVenta.idProducto}
												</td>
												<td align="center">
													${productosPuntoVenta.cantidad}
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