<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Usuarios</title>
<jsp:include page="/WEB-INF/pages/commons/header.jsp" />
<jsp:include page="/WEB-INF/pages/commons/dataTableHeader.jsp" />
<!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
<script>
    $(document).ready(function() {
    	
    	$('.usuarios').hide();
    	$('.editar').css('cursor','pointer');
    	$('.eliminar').css('cursor','pointer');
    	
    	//console.log("debug-ready");
        $('#dataTable').DataTable({
            responsive: true
        });
        
        $('.editar').click(function(){
        	$id = $(this).attr("id");
        	$idNum = $id.split("_")[1];
        	$("#usuarios" + $idNum).attr('checked', true);
        	$("#accion").val("editarUsuario");
        	$("#form1").submit();
        });
        
        $('.eliminar').click(function(){
        	$id = $(this).attr("id");
        	$idNum = $id.split("_")[1];
        	$("#usuarios" + $idNum).attr('checked', true);
        	$("#accion").val("eliminarUsuario");
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
						<div class="panel-heading">Listado de Usuarios</div>
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
											<th align="center">
												<span class="glyphicon glyphicon-edit"></span>
											</th>
											<th align="center">Id. Usuario</th>
							                <th align="center">Username</th>
							                <th align="center">Id. Persona</th>
							                <th>Rol</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="usuario" items="${listaUsuarios}" varStatus="row">
											<tr class="odd gradeX">
												<td align="center">
													<span class="glyphicon glyphicon-edit editar" title="Editar Registro" id="edit_${row.count}"></span>
													<span class="glyphicon glyphicon-trash eliminar" title="Eliminar Registro" id="delete_${row.count}"></span>
													<input class="usuarios" type="checkbox" name="usuarios" id="usuarios${row.count}" value="${usuario.idUsuario}" />
												</td>
												<td align="center">
													${usuario.idUsuario}
												</td>
												<td align="center">
													${usuario.username}
												</td>
												<td align="center">
													${usuario.idPersona}
												</td>
												<td align="center">
													<c:choose>
													  <c:when test='${usuario.idRol=="1"}'>
													  	Administrador
													  </c:when>
													  <c:when test='${usuario.idRol=="2"}'>
													  	Admin PDV
													  </c:when>
													  <c:when test='${usuario.idRol=="3"}'>
													  	Vendedor
													  </c:when>
													  <c:otherwise>
													    ${usuario.idRol}
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