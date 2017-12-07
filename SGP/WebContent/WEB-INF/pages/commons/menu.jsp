<!-- Usamos el API de jstl para reducir el uso de scriptlets -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="${pageContext.request.contextPath}">${usuario}</a>
	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">
		<!-- /.dropdown -->
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<!-- <li><a href="#"><i class="fa fa-user fa-fw"></i>Perfil</a></li>
				<li class="divider"></li> -->
				<li> <!-- LogOff -->
					<a href="${pageContext.request.contextPath}/ServletControlador?accion=salir">
						<i class="fa fa-sign-out fa-fw"></i> 
						Salir
					</a>
				</li> <!-- /LogOff -->
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->
	
	<!-- IF para validar el usuario antes de mostrar las opciones de edicion y consulta -->
	<c:if test='${idRol=="1" || idRol=="2" || idRol=="3"}' > 

	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<li>
					<a href="${pageContext.request.contextPath}">
						<i class="fa fa-dashboard fa-fw"></i> Inicio
					</a>
				</li>
				<li> <!-- Personas -->
					<a href="#">
						<i class="fa fa-bar-chart-o fa-fw"></i>
						Personas
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a href="${pageContext.request.contextPath}/ServletControlador?accion=listarPersonas">
								Listar Personas
							</a>
						</li>
						<li><a href="${pageContext.request.contextPath}/ServletControlador?accion=buscarPersona">
								Buscar Persona
							</a>
						</li>
						<c:if test='${idRol=="1"}' >
							<li><a href="${pageContext.request.contextPath}/ServletControlador?accion=agregarPersona">
									Agregar Persona
								</a>
							</li>
						</c:if>
						</ul> <!-- /.nav-second-level -->
				</li> <!-- /Personas -->
				<c:if test='${idRol=="1" || idRol=="1"}' >
					<li> <!-- Usuarios -->
						<a href="tables.html">
							<i class="fa fa-table fa-fw"></i>
							Usuarios
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level">
							<li>
								<a href="${pageContext.request.contextPath}/ServletControlador?accion=listarUsuarios">
									Listar Usuarios
								</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/ServletControlador?accion=buscarUsuario">
									Buscar Usuario
								</a>
							</li>
						<c:if test='${idRol=="1"}' >
							<li>
								<a href="${pageContext.request.contextPath}/ServletControlador?accion=agregarUsuario">
									Agregar Usuario
								</a>
							</li>
						</c:if>
						</ul> <!-- /.nav-second-level -->
					</li> <!-- /Usuarios -->
				</c:if>
				<li> <!-- Productos General -->
					<a href="forms.html">
						<i class="fa fa-edit fa-fw"></i>
						Productos
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li> <!-- Productos -->
							<a href="${pageContext.request.contextPath}/ServletControlador?accion=listarProductos">
								Listar Productos
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/ServletControlador?accion=buscarProducto">
								Buscar Productos
							</a>
						</li>
						<!-- IF para validar el usuario antes de mostrar las opción de agregar Productos -->
						<c:if test='${idRol=="1" || idRol=="2"}' >
							<li>
								<a href="${pageContext.request.contextPath}/ServletControlador?accion=agregarProducto">
									Agregar Producto
								</a>
							</li> <!-- /Productos -->
						</c:if> <!-- /IF Validación usuarios agregar Productos -->
						<li> <!-- Tipos -->
							<a href="#">Tipos<span class="fa arrow"></span></a>
							<ul class="nav nav-third-level">
								<li>
									<a href="${pageContext.request.contextPath}/ServletControlador?accion=listarTipos">
										Listar Tipos
									</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/ServletControlador?accion=buscarTipo">
										Buscar Tipos
									</a>
								</li>
								<!-- IF para validar el usuario antes de mostrar las opción de agregar Tipos -->
								<c:if test='${idRol=="1" || idRol=="2"}' >
									<li>
										<a href="${pageContext.request.contextPath}/ServletControlador?accion=agregarTipo">
											Agregar Tipo
										</a>
									</li>
								</c:if> <!-- /IF Validación usuarios agregar Tipos -->
							</ul>
						</li> <!-- /Tipos -->
						<li> <!-- Marcas -->
							<a href="#">Marcas<span class="fa arrow"></span></a>
							<ul class="nav nav-third-level">
								<li>
									<a href="${pageContext.request.contextPath}/ServletControlador?accion=listarMarcas">
										Listar Marcas
									</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/ServletControlador?accion=buscarMarca">
										Buscar Marcas
									</a>
								</li>
								<!-- IF para validar el usuario antes de mostrar las opción de agregar Marcas -->
								<c:if test='${idRol=="1" || idRol=="2"}' >
								<li>
									<a href="${pageContext.request.contextPath}/ServletControlador?accion=agregarMarca">
										Agregar Marca
									</a>
								</li>
								</c:if> <!-- /IF Validación usuarios agregar Marcas -->
							</ul>
						</li> <!-- /Marcas -->
					</ul> 
				</li> <!-- /Productos General-->
				<li> <!-- Puntos de Venta General-->
					<a href="#">
						<i class="fa fa-wrench fa-fw"></i>
						Puntos de Venta
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<!-- Puntos de Venta -->
						<li> 
							<a href="${pageContext.request.contextPath}/ServletControlador?accion=listarPuntosVenta">
								Listar Puntos de Venta
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/ServletControlador?accion=buscarPuntoVenta">
								Buscar Puntos de Venta
							</a>
						</li>
						<c:if test='${idRol=="1"}'> <!-- Validación de Usuario Admin para Registrar Puntos de Venta -->
							<li>
								<a href="${pageContext.request.contextPath}/ServletControlador?accion=agregarPuntoVenta">
									Agregar Punto de Venta
								</a>
							</li> 
						</c:if> <!-- /Validación de Usuario Admin para Registrar Puntos de Venta -->
						<!-- /Puntos de Venta -->
						<!-- Productos por Punto de Venta -->
						<li> 
							<a href="#">Productos X PDV<span class="fa arrow"></span></a>
							<ul class="nav nav-third-level">
								<li>
									<a href="${pageContext.request.contextPath}/ServletControlador?accion=buscarProductosPuntoVenta">
										Buscar Productos X PDV
									</a>
								</li>	
								<!-- IF para validar el usuario antes de mostrar las opción de Editar -->
								<c:if test='${idRol=="2"}' >
									<li>
										<a href="${pageContext.request.contextPath}/ServletControlador?accion=modificarProductosPuntoVenta">
											Editar Productos X PDV
										</a>
									</li>
								</c:if>
							</ul>
						</li> 
						<!-- /Productos por Punto de Venta -->
					</ul> <!-- /.nav-second-level -->
				</li> <!-- /Puntos de Venta General-->
				<li> <!-- Ventas -->
					<a href="#">
						<i class="fa fa-files-o fa-fw"></i>
						Ventas
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a href="${pageContext.request.contextPath}/ServletControlador?accion=buscarVenta">
								Buscar Ventas
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/ServletControlador?accion=mostrarHistoricoVentas">
								Ver mi Histórico de Ventas
							</a>
						</li>
					</ul> <!-- /.nav-second-level -->
				</li> <!-- /Ventas -->
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	
	</c:if> <!-- /IF Validación usuarios Menu -->
	
	<!-- /.navbar-static-side -->
</nav>