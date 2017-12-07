<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuarios</title>
    <jsp:include page="/WEB-INF/pages/commons/header.jsp" />
	<jsp:include page="/WEB-INF/pages/commons/dataTableHeader.jsp" />
	<!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
	<script>
	    $(document).ready(function() {
	    	
	    	$("#idPersona").numeric();
	    	
	    	$("#form1").submit(function(){
	    		if($('#idPersona').val().length > 12){
	    			alert("La Cédula de la Persona no puede ser mayor a 12 Digitos");
	    			return false;
	    		}
	    		if(($('#username').val().length > 50)||($('#password').val().length > 50)
	    				||($('#apeMaterno').val().length > 12)){
	    			alert("Los datos de username y/o password no pueden ser mayores a 50 Caracteres");
	    			return false;
	    		}
	    		if(($("#username").val().length <= 0)||($("#password").val().length <= 0)
	    				||($("#idPersona").val().length <= 0)){
	    	    	alert("Los campos con * no pueden estar vacíos");
	    	    	return false;
	    	    }else{
	        		return true;
	        	}
	    	});
	        
	        $('#cancelar').click(function(){
	        	$("#username").css("color", "white");
	        	$("#username").val("a");
				$("#password").css("color", "white");
				$("#password").val("a");
				$("#idPersona").css("color", "white");
				$("#idPersona").val("1");
	        	$("#accion").val("listarUsuarios");
	        	$("#form1").submit(true);
	        });
	        
	    });
	</script>
	<c:if test='${idRol!="1"}' >
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
                            Usuario
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-10 col-lg-offset-1">
                                    <!--Es importante definir el id del formulario, ya que se validara con JavaScript -->
                                        <form id="form1" name="form1" action="${pageContext.request.contextPath}/ServletControlador" method="post" >
                                        <div class="form-group">
                                            <!-- Esta accion se va modificar por JavaScript según la opción seleccionada -->
                                            <input type="hidden" name="accion"  id="accion" value="guardarUsuario"/>
                                            <!--nos va a servir para que tome el valor dinamicamente del nombre de la aplicacion-->
                                            <input type="hidden" name="contexto"  id="contexto" value="${pageContext.request.contextPath}"/>
                                            <!--Id persona. Si estamos editando, reenviamos el id_persona al servidor Esto nos permitirá distinguir si estamos Agregando(insert) o modificando (update) -->
            								<input type="hidden" name="idUsuario" value="${user.idUsuario}" />
                                            <label>Username *</label>
                                            <input  type="text" id="username" name="username" value="${user.username}" class="form-control" required/>
                                            <label>Password *</label>
                                            <input type="password" id="password" name="password" class="form-control" required/>
                                            <label>Id. Persona *</label>
                                            <input type="number" id="idPersona" name="idPersona" value="${user.idPersona}" class="form-control" required/>
                                            <label>Rol *</label>
                                            <select name="idRol" class="form-control">
	                                            <c:choose>
												  <c:when test='${user.idRol=="1"}'>
												  	<option value="1" selected>Administrador</option>
												  	<option value="2">Admin PDV</option>
												  	<option value="3">Vendedor</option>
												  </c:when>
												  <c:when test='${user.idRol=="2"}'>
												  	<option value="1">Administrador</option>
												  	<option value="2" selected>Admin PDV</option>
												  	<option value="3">Vendedor</option>
												  </c:when>
												  <c:when test='${user.idRol=="3"}'>
												  	<option value="1">Administrador</option>
												  	<option value="2">Admin PDV</option>
												  	<option value="3" selected>Vendedor</option>
												  </c:when>
												  <c:otherwise>
												    <option value="1">Administrador</option>
												  	<option value="2">Admin PDV</option>
												  	<option value="3" selected>Vendedor</option>
												  </c:otherwise>
												</c:choose>
                                            </select>
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