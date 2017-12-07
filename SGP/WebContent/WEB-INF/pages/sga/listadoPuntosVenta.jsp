<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Puntos de Venta</title>
<jsp:include page="/WEB-INF/pages/commons/header.jsp" />
<jsp:include page="/WEB-INF/pages/commons/dataTableHeader.jsp" />
<!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
<script>
    $(document).ready(function() {
    	
    	$('.puntosVenta').hide();
    	$('.editar').css('cursor','pointer');
    	$('.eliminar').css('cursor','pointer');
    	
    	//console.log("debug-ready");
        $('#dataTable').DataTable({
            responsive: true
        });
        
        $('.editar').click(function(){
        	$id = $(this).attr("id");
        	$idNum = $id.split("_")[1];
        	$("#puntosVenta" + $idNum).attr('checked', true);
        	$("#accion").val("editarPuntoVenta");
        	$("#form1").submit();
        });
        
        $('.eliminar').click(function(){
        	$id = $(this).attr("id");
        	$idNum = $id.split("_")[1];
        	$("#puntosVenta" + $idNum).attr('checked', true);
        	$("#accion").val("eliminarPuntoVenta");
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
						<div class="panel-heading">Listado de Puntos de Venta</div>
						<!-- /.panel-heading -->
						<div class="panel-body">

							<!--Es importante definir el id del formulario, ya que se validara con JavaScript -->
							<form id="form1" name="form1"
								action="${pageContext.request.contextPath}/ServletControlador"
								method="post">
								<!-- Esta accion se va modificar por JavaScript seg�n la opci�n seleccionada -->
								<input type="hidden" name="accion" id="accion" />
								<table class="table table-striped table-bordered table-hover" id="dataTable">
									<thead>
										<tr>
											<!-- IF para validar el usuario antes de mostrar las opci�n de editar -->
											<c:if test='${idRol=="1"}' >
											<th align="center">
												<span class="glyphicon glyphicon-edit"></span>
											</th>
											</c:if> <!-- /IF Validaci�n usuarios editar -->
											<th align="center">Id. PDV</th>
							                <th align="center">Nombre</th>
							                <th align="center">Direcci�n</th>
											<th align="center">Telefono</th>
							                <th align="center">Id Admin</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="puntoVenta" items="${listaPuntosVenta}" varStatus="row">
											<tr class="odd gradeX">
												<!-- IF para validar el usuario antes de mostrar las opci�n de editar -->
												<c:if test='${idRol=="1"}' >
												<td align="center">
													<span class="glyphicon glyphicon-edit editar" title="Editar Registro" id="edit_${row.count}"></span>
													<span class="glyphicon glyphicon-trash eliminar" title="Eliminar Registro" id="delete_${row.count}"></span>
													<input class="puntosVenta" type="checkbox" name="puntosVenta" id="puntosVenta${row.count}" value="${puntoVenta.idPuntoVenta}" />
												</td>
												</c:if> <!-- /IF Validaci�n usuarios editar -->
												<td align="center">
													${puntoVenta.idPuntoVenta}
												</td>
												<td align="center">
													${puntoVenta.nombre}
												</td>
												<td align="center">
													${puntoVenta.direccion}
												</td>
												<td align="center">
													${puntoVenta.telefono}
												</td>
												<td align="center">
													${puntoVenta.idAdmin}
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