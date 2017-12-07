<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Marcas</title>
<jsp:include page="/WEB-INF/pages/commons/header.jsp" />
<jsp:include page="/WEB-INF/pages/commons/dataTableHeader.jsp" />
<!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
<script>
    $(document).ready(function() {
    	
    	$('.marcas').hide();
    	$('.editar').css('cursor','pointer');
    	$('.eliminar').css('cursor','pointer');
    	
    	//console.log("debug-ready");
        $('#dataTable').DataTable({
            responsive: true
        });
        
        $('.editar').click(function(){
        	$id = $(this).attr("id");
        	$idNum = $id.split("_")[1];
        	$("#marcas" + $idNum).attr('checked', true);
        	$("#accion").val("editarMarca");
        	$("#form1").submit();
        });
        
        $('.eliminar').click(function(){
        	$id = $(this).attr("id");
        	$idNum = $id.split("_")[1];
        	$("#marcas" + $idNum).attr('checked', true);
        	$("#accion").val("eliminarMarca");
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
						<div class="panel-heading">Listado de Marcas</div>
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
											<!-- IF para validar el usuario antes de mostrar las opción de editar Marcas -->
											<c:if test='${idRol=="1" || idRol=="2"}' >
											<th align="center">
												<span class="glyphicon glyphicon-edit"></span>
											</th>
											</c:if> <!-- /IF Validación usuarios editar Marcas -->
											<th align="center">Id. Marca</th>
							                <th align="center">Nombre</th>
							                <th align="center">Es Nacional</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="marca" items="${listaMarcas}" varStatus="row">
											<tr class="odd gradeX">
											<!-- IF para validar el usuario antes de mostrar las opción de editar Marcas -->
												<c:if test='${idRol=="1" || idRol=="2"}' >
												<td align="center">
													<span class="glyphicon glyphicon-edit editar" title="Editar Registro" id="edit_${row.count}"></span>
													<span class="glyphicon glyphicon-trash eliminar" title="Eliminar Registro" id="delete_${row.count}"></span>
													<input class="marcas" type="checkbox" name="marcas" id="marcas${row.count}" value="${marca.idMarca}" />
												</td>
												</c:if> <!-- /IF Validación usuarios editar Marcas -->
												<td align="center">
													${marca.idMarca}
												</td>
												<td align="center">
													${marca.nombre}
												</td>
												<td align="center">
													<c:choose>
													  <c:when test='${marca.esNacional=="1"}'>
													  	SI
													  </c:when>
													  <c:when test='${marca.esNacional=="0"}'>
													  	NO
													  </c:when>
													  <c:otherwise>
													    NO
													  </c:otherwise>
													</c:choose>
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