package sga.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sga.eis.dto.ProductosPuntoVenta;
import sga.eis.dto.PuntoVenta;
import sga.eis.dto.Usuario;
import sga.services.ProductoService;
import sga.services.PuntoVentaService;
import sga.services.UsuarioService;
import sga.services.impl.ProductoServiceImpl;
import sga.services.impl.PuntoVentaServiceImpl;
import sga.services.impl.UsuarioServiceImpl;
import sga.web.utilities.IGlobalConstant;

/**
 * Clase que Implementa un Controlador de Tipo PuntoVenta
 * 
 * @author Juan Carlos Fuyo
 */
public class PuntoVentaController {

    /**
     * Utilizamos el patron singleton, solo existen un objeto de tipo
     * PuntoVentaController en Memoria
     */
    private static PuntoVentaController controlPuntoVenta;

    /**
     * Constructor sin argumentos privado, para implementar el patron singleton
     */
    private PuntoVentaController() {
    }

    /**
     * Metodo que Crea una nueva y unica instancia de PuntoVentaController si es
     * que no existe
     * 
     * @return the controlPuntoVenta (Controlador de PuntoVentas)
     */
    public static PuntoVentaController getInstance() {
        if (controlPuntoVenta == null) {
            controlPuntoVenta = new PuntoVentaController();
        }
        return controlPuntoVenta;
    }

    /**
     * Metodo para procesar el caso de uso de listarPuntoVentas
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void listarPuntosVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Recuperamos el listado de puntosVenta, utilizamos el servicio de
        // PuntoVenta
        PuntoVentaService puntoVentaService = PuntoVentaServiceImpl.getInstance();
        List<PuntoVenta> puntosVenta = puntoVentaService.getAllPuntosVenta();
        // Si se encontraron PuntoVentas, las compartimos en la pagina Web
        if (!puntosVenta.isEmpty()) {
            request.setAttribute("listaPuntosVenta", puntosVenta);
        }
        request.getRequestDispatcher(
                "/WEB-INF/pages/sga/listadoPuntosVenta.jsp").forward(request,
                response);
    }

    /**
     * Metodo encargado de mostrar un listado de puntos de Venta de acuerdo a
     * una busqueda por nombre o Id
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void mostrarPuntoVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        List<PuntoVenta> puntosVenta = new ArrayList<PuntoVenta>();
        String idPuntoVentaParam = request.getParameter("idPuntoVenta");
        String nombreParam = request.getParameter("nombre");
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Recuperamos los PuntoVentas utilizando el servicio de PuntoVentas
        PuntoVentaService puntoVentaService = PuntoVentaServiceImpl.getInstance();
        if (idPuntoVentaParam != null && !idPuntoVentaParam.trim().equals("")) {
            Integer idPuntoVenta = new Integer(idPuntoVentaParam);
            PuntoVenta puntoVenta = puntoVentaService.getPuntoVentaById(idPuntoVenta);
            if (puntoVenta != null) {
                puntosVenta.add(puntoVenta);
            }
        }
        else if (nombreParam != null && !nombreParam.trim().equals("")) {
            puntosVenta = puntoVentaService.getPuntoVentaByName(nombreParam);
        }
        if (!puntosVenta.isEmpty()) {
            // compartimos el objeto obtenido, para poderlo mostrar
            request.setAttribute("listaPuntosVenta", puntosVenta);
            request.getRequestDispatcher(
                    "/WEB-INF/pages/sga/listadoPuntosVenta.jsp").forward(
                    request, response);
        }
        request.setAttribute("type", messageType);
        mensaje = "No se encuentra el punto de Venta buscado";
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("/WEB-INF/pages/sga/buscarPuntoVenta.jsp").forward(
                request, response);
    }

    /**
     * Metodo encarcado de redireccionar a la pagina de Busqueda de PuntoVentas
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void buscarPuntoVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Solo redireccionamos a la página de busqueda de puntos de Venta
        request.getRequestDispatcher("/WEB-INF/pages/sga/buscarPuntoVenta.jsp").forward(
                request, response);
    }

    /**
     * Metodo que agrega un Punto de Venta en el sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void agregarPuntoVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // No procesamos ningun parametro, sino que solo redireccionamos
        // a la vista para agregar un nuevo Punto de Venta
        request.getRequestDispatcher("/WEB-INF/pages/sga/detallePuntoVenta.jsp").forward(
                request, response);
    }

    /**
     * Metodo que edita un Punto de Venta en el sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void editarPuntoVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Recuperamos el identificador a procesar y reenviamos a la pagina de
        // detalle
        String idPuntoVentaParam = request.getParameter("puntosVenta");
        Integer idPuntoVenta = null;
        if (idPuntoVentaParam != null && !idPuntoVentaParam.trim().equals("")) {
            idPuntoVenta = new Integer(idPuntoVentaParam);
            // Utilizamos el servicio de PuntoVenta para recuperar el objeto de
            // la
            // BD
            PuntoVentaService puntoVentaService = PuntoVentaServiceImpl.getInstance();
            PuntoVenta puntoVenta = puntoVentaService.getPuntoVentaById(idPuntoVenta);
            // compartimos el objeto PuntoVenta obtenido, para poderlo modificar
            request.setAttribute("puntoVenta", puntoVenta);
            request.getRequestDispatcher(
                    "/WEB-INF/pages/sga/detallePuntoVenta.jsp").forward(
                    request, response);
        }
        else {
            mensaje = "Debe seleccionar un elemento a Editar";
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            // Reutilizamos el metodo listarPuntoVentas
            this.listarPuntosVenta(request, response);
        }
    }

    /**
     * Metodo que elimina un PuntoVenta de PuntoVenta del sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void eliminarPuntoVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // 1. Procesamos los parametros
        // Recuperamos TODOS los objetos seleccionados
        String[] idPuntoVentaParam = request.getParameterValues("puntosVenta");
        List<Integer> idPuntoVentas = new ArrayList<Integer>();
        // 2. Utilizamos los objetos de Modelo (PuntoVenta)
        // Validamos los parametros a eliminar
        if (idPuntoVentaParam != null && idPuntoVentaParam.length > 0) {
            for (String puntoVenta : idPuntoVentaParam) {
                idPuntoVentas.add(new Integer(puntoVenta));
            }
            // 3.Utilizamos la capa de servicio para eliminar los objetos
            PuntoVentaService puntoVentaService = PuntoVentaServiceImpl.getInstance();
            boolean registrosEliminados = puntoVentaService.eliminarPuntosVenta(idPuntoVentas);
            if (registrosEliminados) {
                messageType = IGlobalConstant.INT_ONE;
                mensaje = "Se eliminaron correctamente los registros";
            }
        }
        else {
            mensaje = "Debe seleccionar uno o varios elementos a Eliminar";
        }
        // 4. Redireccionamos al listado de personas (ya no debe de mostrar los
        // registros eliminados)
        request.setAttribute("type", messageType);
        request.setAttribute("mensaje", mensaje);
        this.listarPuntosVenta(request, response);
    }

    /**
     * Este metodo nos permite insertar o modificar un Punto de Venta, La
     * diferencia va a estar si recibimos o no una llave primaria por parte del
     * formulario
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void guardarPuntoVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        boolean elementoGuardado = false;
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Procesamos los parametros
        PuntoVenta puntoVenta = this.populatePuntoVenta(request, response);
        // Hacemos uso de la capa de Servicios para guardar el punto de venta
        PuntoVentaService puntoVentaService = PuntoVentaServiceImpl.getInstance();
        elementoGuardado = validarPersona(puntoVentaService, puntoVenta);
        // Redireccionamos dependiendo del resultado
        if (elementoGuardado) {
            mensaje = "Se guard&oacute; el elemento correctamente";
            messageType = IGlobalConstant.INT_ONE;
            // Reutilizamos el caso de listarPuntoVentas
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            this.listarPuntosVenta(request, response);
        }
        else {
            mensaje = "No existe la persona en sistema o el usuario no tiene el rol de Administrador de PDV";
        }
        // compartimos el objeto PuntoVenta, para poderlo modificar
        request.setAttribute("puntoVenta", puntoVenta);
        request.setAttribute("type", messageType);
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("/WEB-INF/pages/sga/detallePuntoVenta.jsp").forward(
                request, response);
    }

    /**
     * Metodo para procesar el caso de uso de listarPuntoVentas
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void listarProductosPuntoVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Recuperamos el listado de Productos por Punto de Venta, utilizamos el
        // servicio de PuntoVenta
        PuntoVentaService puntoVentaService = PuntoVentaServiceImpl.getInstance();
        List<ProductosPuntoVenta> productosPuntoVenta = puntoVentaService.getAllProductosPuntoVenta();
        // Si se encontraron Productos por Punto de Venta, los compartimos en la
        // vista
        if (!productosPuntoVenta.isEmpty()) {
            request.setAttribute("listaProductosPuntoVenta",
                    productosPuntoVenta);
        }
        request.getRequestDispatcher(
                "/WEB-INF/pages/sga/listadoProductosPuntoVenta.jsp").forward(
                request, response);
    }

    /**
     * Metodo encarcado de redireccionar a la pagina de Busqueda de N° de
     * Productos por Punto de Venta
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void buscarProductosPuntoVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Solo redireccionamos a la página de busqueda de puntos de Venta
        request.getRequestDispatcher(
                "/WEB-INF/pages/sga/buscarProductosPuntoVenta.jsp").forward(
                request, response);
    }

    /**
     * Metodo encargado de mostrar un listado de Productos por Punto de Venta de
     * acuerdo a una busqueda por Id de PDV, idProducto o ambos
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void mostrarProductosPuntoVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        List<ProductosPuntoVenta> produtosPuntoVenta = new ArrayList<ProductosPuntoVenta>();
        String idPuntoVentaParam = request.getParameter("idPuntoVenta");
        String idProductoParam = request.getParameter("idProducto");
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Recuperamos los ProductosPuntoVentas utilizando el servicio de
        // ProductosPuntoVentas
        PuntoVentaService puntoVentaService = PuntoVentaServiceImpl.getInstance();
        if ((idPuntoVentaParam != null && !idPuntoVentaParam.trim().equals(""))
                && (idProductoParam != null && !idProductoParam.trim().equals(
                        ""))) {
            Integer idPuntoVenta = new Integer(idPuntoVentaParam);
            Integer idProducto = new Integer(idProductoParam);
            ProductosPuntoVenta productoPuntoVenta = puntoVentaService.getProductoPuntoVentaById(
                    idPuntoVenta, idProducto);
            if (productoPuntoVenta != null) {
                produtosPuntoVenta.add(productoPuntoVenta);
            }
        }
        else if (idPuntoVentaParam != null
                && !idPuntoVentaParam.trim().equals("")) {
            Integer idPuntoVenta = new Integer(idPuntoVentaParam);
            produtosPuntoVenta = puntoVentaService.getProductoPuntoVentaByPuntoVenta(idPuntoVenta);
        }
        else if (idProductoParam != null && !idProductoParam.trim().equals("")) {
            Integer idProducto = new Integer(idProductoParam);
            produtosPuntoVenta = puntoVentaService.getProductoPuntoVentaByProducto(idProducto);
        }
        if (!produtosPuntoVenta.isEmpty()) {
            // compartimos el objeto obtenido, para poderlo mostrar
            request.setAttribute("listaProductosPuntoVenta",
                    produtosPuntoVenta);
            request.getRequestDispatcher(
                    "/WEB-INF/pages/sga/listadoProductosPuntoVenta.jsp").forward(
                    request, response);
        }
        request.setAttribute("type", messageType);
        mensaje = "No se encuentran los Registros asociados a la busqueda";
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher(
                "/WEB-INF/pages/sga/buscarProductosPuntoVenta.jsp").forward(
                request, response);
    }

    /**
     * Metodo que edita el N° de Productos por Punto de Venta por el
     * Administrador del Sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void editarProductosPuntoVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Recuperamos el identificador a procesar y reenviamos a la pagina de
        // detalle
        String idPuntoVentaParam = request.getParameter("idPuntoVenta");
        String idProductoParam = request.getParameter("idProducto");
        Integer idPuntoVenta = null;
        Integer idProducto = null;
        if ((idPuntoVentaParam != null && !idPuntoVentaParam.trim().equals(""))
                && (idProductoParam != null && !idProductoParam.trim().equals(
                        ""))) {
            idPuntoVenta = new Integer(idPuntoVentaParam);
            idProducto = new Integer(idProductoParam);
            // Utilizamos el servicio de PuntoVenta para recuperar el objeto de
            // la
            // BD
            PuntoVentaService puntoVentaService = PuntoVentaServiceImpl.getInstance();
            ProductosPuntoVenta productosPuntoVenta = puntoVentaService.getProductoPuntoVentaById(
                    idPuntoVenta, idProducto);
            // compartimos el objeto ProductosPuntoVenta obtenido, para poderlo
            // modificar
            request.setAttribute("productosPuntoVenta", productosPuntoVenta);
            request.getRequestDispatcher(
                    "/WEB-INF/pages/sga/detalleProductosPuntoVenta.jsp").forward(
                    request, response);
        }
        else {
            mensaje = "Debe seleccionar un elemento a Editar";
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            // Reutilizamos el metodo listarPuntoVentas
            this.listarProductosPuntoVenta(request, response);
        }
    }

    /**
     * Metodo que edita el N° de Productos por Punto de Venta por el
     * Administrador del PDV
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void modificarProductosPuntoVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
        PuntoVentaService puntoVentaService = PuntoVentaServiceImpl.getInstance();
        PuntoVenta puntoVenta = puntoVentaService.getPuntoVentaByUser(usuario);
        ProductosPuntoVenta productosPuntoVenta = new ProductosPuntoVenta();
        productosPuntoVenta.setIdPuntoVenta(puntoVenta.getIdPuntoVenta());
        request.setAttribute("productosPuntoVenta", productosPuntoVenta);
        request.getRequestDispatcher(
                "/WEB-INF/pages/sga/detalleProductosPuntoVenta.jsp").forward(
                request, response);
    }

    /**
     * Este metodo nos permite insertar o modificar el N° de Productos por Punto
     * de Venta
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void guardarProductosPuntoVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        boolean elementoGuardado = false;
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Procesamos los parametros
        ProductosPuntoVenta productosPuntoVenta = this.populateProductosPuntoVenta(
                request, response);
        // Hacemos uso de la capa de Servicios para guardar el punto de venta
        PuntoVentaService puntoVentaService = PuntoVentaServiceImpl.getInstance();
        elementoGuardado = validarProductosPuntoVenta(puntoVentaService,
                productosPuntoVenta);
        // Redireccionamos dependiendo del resultado
        if (elementoGuardado) {
            mensaje = "Se guard&oacute; el elemento correctamente";
            messageType = IGlobalConstant.INT_ONE;
            // Reutilizamos el caso de listarPuntoVentas
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            this.listarProductosPuntoVenta(request, response);
        }
        else {
            mensaje = "No existe el Almacen o el Producto en sistema, verifique los datos";
            HttpSession session = request.getSession();
            String usuario = (String) session.getAttribute("usuario");
            UsuarioService usuarioService = UsuarioServiceImpl.getInstance();
            Usuario user = usuarioService.getUsuarioByFullName(usuario);
            if (user.getIdRol() == IGlobalConstant.INT_TWO) {
                productosPuntoVenta.setIdProducto(null);
            }
        }
        // compartimos el objeto PuntoVenta, para poderlo modificar
        request.setAttribute("productosPuntoVenta", productosPuntoVenta);
        request.setAttribute("type", messageType);
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher(
                "/WEB-INF/pages/sga/detalleProductosPuntoVenta.jsp").forward(
                request, response);
    }

    /**
     * Metodo que valida si existe una persona en sistema y almacena un
     * PuntoVenta asociado a esta si existe
     * 
     * @param puntoVentaService
     * @param puntoVenta
     * @return
     */
    private boolean validarPersona(PuntoVentaService puntoVentaService,
            PuntoVenta puntoVenta) {
        // Verificamos que exista la persona en BD
        UsuarioService usuarioService = UsuarioServiceImpl.getInstance();
        Usuario usuario = usuarioService.getUsuarioByPersona(puntoVenta.getIdAdmin());
        if (usuario != null) {
            if (usuario.getIdRol() == IGlobalConstant.INT_TWO) {
                puntoVenta.setIdAdmin(puntoVenta.getIdAdmin());
                return puntoVentaService.guardarPuntoVenta(puntoVenta);
            }
            return false;
        }
        else {
            return false;
        }
    }

    /**
     * Metodo que valida si existe una persona en sistema y almacena un
     * PuntoVenta asociado a esta si existe
     * 
     * @param puntoVentaService
     * @param puntoVenta
     * @return
     */
    private boolean validarProductosPuntoVenta(
            PuntoVentaService puntoVentaService,
            ProductosPuntoVenta productosPuntoVenta) {
        // Verificamos que exista la persona en BD
        ProductoService productoService = ProductoServiceImpl.getInstance();
        if (productoService.getProductoById(productosPuntoVenta.getIdProducto()) != null
                && puntoVentaService.getPuntoVentaById(productosPuntoVenta.getIdPuntoVenta()) != null) {
            productosPuntoVenta.setIdPuntoVenta(productosPuntoVenta.getIdPuntoVenta());
            return puntoVentaService.guardarProductoPuntoVenta(productosPuntoVenta);
        }
        else {
            return false;
        }
    }

    /**
     * Metodo encargado de Poblar un objeto de tipo PuntoVenta con los valores
     * recibidos por parametros para ser manipulados por las capas de servicios,
     * datos y Web
     * 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    private PuntoVenta populatePuntoVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Procesamos los parametros
        String idPuntoVentaParam = request.getParameter("idPuntoVenta");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String idAdmin = request.getParameter("idAdmin");
        // Utilizamos el objeto Modelo
        PuntoVenta puntoVenta = new PuntoVenta();
        // Validamos la PK
        // si el idPuntoVenta no venia en los parametros, se coloca null
        Integer idPuntoVenta = null;
        if (idPuntoVentaParam != null && !idPuntoVentaParam.trim().equals("")) {
            idPuntoVenta = new Integer(idPuntoVentaParam);
        }
        puntoVenta.setIdPuntoVenta(idPuntoVenta);
        puntoVenta.setNombre(nombre);
        puntoVenta.setDireccion(direccion);
        puntoVenta.setTelefono(telefono);
        puntoVenta.setIdAdmin(idAdmin);
        return puntoVenta;
    }

    private ProductosPuntoVenta populateProductosPuntoVenta(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Procesamos los parametros
        String idPuntoVenta = request.getParameter("idPuntoVenta");
        String idProducto = request.getParameter("idProducto");
        String cantidad = request.getParameter("cantidad");
        // Utilizamos el objeto Modelo
        ProductosPuntoVenta productosPuntoVenta = new ProductosPuntoVenta();
        productosPuntoVenta.setIdPuntoVenta(new Integer(idPuntoVenta));
        productosPuntoVenta.setIdProducto(new Integer(idProducto));
        productosPuntoVenta.setCantidad(new Integer(cantidad));
        return productosPuntoVenta;
    }
}
