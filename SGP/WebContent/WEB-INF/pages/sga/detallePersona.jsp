<!DOCTYPE html>
<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Personas</title>
    <jsp:include page="/WEB-INF/pages/commons/header.jsp" />
	<jsp:include page="/WEB-INF/pages/commons/dataTableHeader.jsp" />
	<!-- Usamos la libreria JQuery para facilitar el uso de Javascript -->
	<script>
	    $(document).ready(function() {
	    	
	    	$("#cedula").numeric();
	    	
	    	$("#form1").submit(function(){
	    		if($('#cedula').val().length > 12){
	    			alert("La Cédula no puede ser mayor a 12 Digitos");
	    			return false;
	    		}
	    		if(($('#nombre').val().length > 50)||($('#apePaterno').val().length > 50)
	    				||($('#apeMaterno').val().length > 50)){
	    			alert("Los datos de nombre y/o apellidos no pueden ser mayores a 50 Caracteres");
	    			return false;
	    		}
	    		if(($("#cedula").val().length <= 0)||($("#nombre").val().length <= 0)
	    				||($("#apePaterno").val().length <= 0)){
	    	    	alert("Los campos con * no pueden estar vacíos");
	    	    	return false;
	    	    }else if(!isValidDate($("#fechaNac").val())){
	        		alert("Formato de Fecha Invalido, debe ser de la forma aaaa-mm-dd");
	        		return false;
	        	}else{
	        		return true;
	        	}
	    	});

	        $('#cancelar').click(function(){
	        	$("#cedula").css("color", "white");
	        	$("#cedula").val("1");
				$("#nombre").css("color", "white");
				$("#nombre").val("a");
				$("#apePaterno").css("color", "white");
				$("#apePaterno").val("a");
				$("#fechaNac").css("color", "white");
				$("#fechaNac").val("1900-01-01");
	        	$("#accion").val("listarPersonas");
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
                            Persona
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-10 col-lg-offset-1">
                                    <!--Es importante definir el id del formulario, ya que se validara con JavaScript -->
                                        <form id="form1" name="form1" action="${pageContext.request.contextPath}/ServletControlador" method="post" >
                                        <div class="form-group">
                                            <!-- Esta accion se va modificar por JavaScript según la opción seleccionada -->
                                            <input type="hidden" name="accion"  id="accion" value="guardarPersona"/>
                                            <!--nos va a servir para que tome el valor dinamicamente del nombre de la aplicacion-->
                                            <input type="hidden" name="contexto"  id="contexto" value="${pageContext.request.contextPath}"/>
                                            <!--Id persona. Si estamos editando, enviamos el atributo booleano modificar Esto nos permitirá distinguir si estamos Agregando(insert) o modificando (update) -->
                                            <input type="hidden" name="modificar" value="${modificar}" />
                                            <label>Cédula *</label>
                                            <input type="number" id="cedula" name="cedula" class="form-control" value="${persona.cedula}" required/>
                                            <label>Nombre *</label>
                                            <input type="text" id="nombre" name="nombre" class="form-control" value="${persona.nombre}" required/>
                                            <label>Apellido Paterno *</label>
                                            <input type="text" id="apePaterno" name="apePaterno" class="form-control" value="${persona.apePaterno}" required/>
                                            <label>Apellido Materno</label>
                                            <input type="text" id="apeMaterno" name="apeMaterno" class="form-control" value="${persona.apeMaterno}" />
                                            <label>Género *</label>
                                            <select name="genero" class="form-control">
	                                            <c:choose>
												  <c:when test='${persona.genero.equals("F")}'>
												  	<option selected>F</option>
												  	<option>M</option>
												  </c:when>
												  <c:when test='${persona.genero.equals("M")}'>
												  	<option selected>M</option>
												  	<option>F</option>
												  </c:when>
												  <c:otherwise>
												    <option>F</option>
												    <option>M</option>
												  </c:otherwise>
												</c:choose>
                                            </select>
                                            <label>Fecha de Nacimiento *</label>
                                            <input type="date" id ="fechaNac" name="fecNacimiento" class="form-control" value="${persona.fecNacimiento}" required/>
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
