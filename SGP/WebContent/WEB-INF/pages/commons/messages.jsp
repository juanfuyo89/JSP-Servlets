<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.msg{
		text-align:center;
	}
	.center{
		text-align:center;
	}
</style>
<c:choose>
	<c:when test='${type == "1"}'>
		<div class="col-md-4 col-md-offset-4">
			<div class="form-group has-success">
				<label class="control-label msg" for="inputSuccess"><span class="center">${mensaje}</span></label>
		        <input type="hidden" class="form-control" id="inputSuccess">
			</div>
		</div>
	</c:when>
	<c:when test='${type == "0"}'>
		<div class="col-md-4 col-md-offset-4">
			<div class="form-group has-error">
				<label class="control-label msg" for="inputError"><span class="center">${mensaje}</span></label>
		        <input type="hidden" class="form-control" id="inputError">
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="col-md-4 col-md-offset-4">
			<div class="form-group has-success">
				<label class="control-label msg" for="inputWarning"><span class="center">${mensaje}</span></label>
		        <input type="hidden" class="form-control" id="inputWarning">
			</div>
		</div>
	</c:otherwise>
</c:choose>
