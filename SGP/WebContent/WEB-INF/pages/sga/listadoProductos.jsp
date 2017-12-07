<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Productos</title>
<jsp:include page="/WEB-INF/pages/commons/header.jsp" />
<jsp:include page="/WEB-INF/pages/commons/dataTableHeader.jsp" />
<!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
<script>
    $(document).ready(function() {
    	
    	$('.productos').hide();
    	$('.editar').css('cursor','pointer');
    	$('.eliminar').css('cursor','pointer');
    	$('.detalles').css('cursor','pointer');

        $('#dataTable').DataTable({
            responsive: true
        });
        
        $('.detalles').click(function(){
        	$id = $(this).attr("id");
        	$idNum = $id.split("_")[1];
        	$("#productos" + $idNum).attr('checked', true);
        	$("#accion").val("verDetallesProducto");
        	$("#form1").submit();
        });
        
        $('.editar').click(function(){
        	$id = $(this).attr("id");
        	$idNum = $id.split("_")[1];
        	$("#productos" + $idNum).attr('checked', true);
        	$("#accion").val("editarProducto");
        	$("#form1").submit();
        });
        
        $('.eliminar').click(function(){
        	$id = $(this).attr("id");
        	$idNum = $id.split("_")[1];
        	$("#productos" + $idNum).attr('checked', true);
        	$("#accion").val("eliminarProducto");
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
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Listado de Productos</div>
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
											<!-- IF para validar el usuario antes de mostrar las opción de editar Productos -->
											<c:if test='${idRol=="1" || idRol=="2"}' >
											<th align="center">
												<span class="glyphicon glyphicon-edit"></span>
											</th>
											</c:if> <!-- /IF Validación usuarios editar Productos -->
											<th align="center">Id.</th>
											<th align="center">Nombre</th>
											<th align="center">Costo</th>
											<th align="center">Precio</th>
											<th align="center">Tipo</th>
											<th align="center">Marca</th>
											<th align="center">Descuento</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="productoBean" items="${listaProductos}" varStatus="row">
											<tr class="odd gradeX">
												<td align="center">
													<!-- IF para validar el usuario antes de mostrar las opción de editar Productos -->
													<c:if test='${idRol=="1" || idRol=="2"}' >
														<span class="glyphicon glyphicon-edit editar" title="Editar Registro" id="edit_${row.count}"></span>
														<span class="glyphicon glyphicon-trash eliminar" title="Eliminar Registro" id="delete_${row.count}"></span>
													</c:if> <!-- /IF Validación usuarios editar Productos -->	
													<span class="glyphicon glyphicon-eye-open detalles" title="Ver Detalles del Producto" id="detalles_${row.count}"></span>
													<input class="productos" type="checkbox" name="productos" id="productos${row.count}" value="${productoBean.producto.idProducto}" />
												</td>
												<td align="center">
													${productoBean.producto.idProducto}
												</td>
												<td align="center">
													${productoBean.producto.nombre}
												</td>
												<!-- IF para validar el rol antes de mostrar el costo del Producto -->
												<c:if test='${idRol=="1" || idRol=="2"}' >
													<td align="center">
														$${productoBean.producto.costo}
													</td>
												</c:if>
												<td align="center">
													$${productoBean.producto.precio}
												</td>
												<td align="center">
													${productoBean.tipo.nombre}
												</td>
												<td align="center">
													${productoBean.marca.nombre}
												</td>
												<td align="center">
													<c:if test='${productoBean.descuento.porcentaje == null}' >
														0
													</c:if>
													${productoBean.descuento.porcentaje}%
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