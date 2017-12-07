package sga.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sga.eis.dto.Persona;
import sga.eis.dto.Producto;
import sga.eis.dto.ProductosPuntoVenta;
import sga.eis.dto.Venta;
import sga.services.PersonaService;
import sga.services.ProductoService;
import sga.services.PuntoVentaService;
import sga.services.UsuarioService;
import sga.services.VentaService;
import sga.services.impl.PersonaServiceImpl;
import sga.services.impl.ProductoServiceImpl;
import sga.services.impl.PuntoVentaServiceImpl;
import sga.services.impl.UsuarioServiceImpl;
import sga.services.impl.VentaServiceImpl;
import sga.web.model.ProductoBean;
import sga.web.utilities.IGlobalConstant;

/**
 * Clase que Implementa un Controlador de Tipo Venta
 * 
 * @author Juan Carlos Fuyo
 */
public class VentaController {

    /**
     * Utilizamos el patron singleton, solo existen un objeto de tipo
     * VentaController en Memoria
     */
    private static VentaController controlVenta;

    /**
     * Constructor sin argumentos privado, para implementar el patron singleton
     */
    private VentaController() {
    }

    /**
     * Metodo que Crea una nueva y unica instancia de VentaController si es que
     * no existe
     * 
     * @return the controlVenta (Controlador de Ventas)
     */
    public static VentaController getInstance() {
        if (controlVenta == null) {
            controlVenta = new VentaController();
        }
        return controlVenta;
    }

    /**
     * Metodo para procesar el caso de uso de listarVentas
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void listarVentas(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Recuperamos el listado de ventas, utilizamos el servicio de
        // Ventas
        VentaService ventaService = VentaServiceImpl.getInstance();
        List<Venta> ventas = ventaService.getAllVentas();
        // Si se encontraron Ventas, las compartimos en la pagina Web
        if (!ventas.isEmpty()) {
            request.setAttribute("listaVentas", ventas);
        }
        request.getRequestDispatcher("/WEB-INF/pages/sga/listadoVentas.jsp").forward(
                request, response);
    }

    /**
     * Metodo encarcado de redireccionar a la pagina de Busqueda de Ventas
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void buscarVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Solo redireccionamos a la página de busqueda de ventas
        request.getRequestDispatcher("/WEB-INF/pages/sga/buscarVenta.jsp").forward(
                request, response);
    }

    /**
     * Metodo encargado de mostrar un venta en el listado de ventas de acuerdo a
     * una busqueda con base en alguna de sus propiedades
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void mostrarVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Utilizamos el objeto modelo para de Ventas para la respuesta
        List<Venta> ventas = new ArrayList<Venta>();
        // Procesamos los parametros de busqueda
        String idVentaParam = request.getParameter("idVenta");
        String idProductoParam = request.getParameter("idProducto");
        String idPersona = request.getParameter("idPersona");
        String cedulaUser = (String) request.getAttribute("cedulaUser");
        String idPuntoVentaParam = request.getParameter("idPuntoVenta");
        // Recuperamos los Ventas utilizando el servicio de Ventas
        VentaService ventaService = VentaServiceImpl.getInstance();
        if (idVentaParam != null && !idVentaParam.trim().equals("")) {
            Integer idVenta = new Integer(idVentaParam);
            Venta venta = ventaService.getVentaById(idVenta);
            if (venta != null) {
                ventas.add(venta);
            }
        }
        else if (idPuntoVentaParam != null
                && !idPuntoVentaParam.trim().equals("")) {
            Integer idPuntoVenta = new Integer(idPuntoVentaParam);
            ventas = ventaService.getVentaByPuntoVenta(idPuntoVenta);
        }
        else if (idProductoParam != null && !idProductoParam.trim().equals("")) {
            Integer idProducto = new Integer(idProductoParam);
            ventas = ventaService.getVentaByProducto(idProducto);
        }
        else if (idPersona != null && !idPersona.trim().equals("")
                || cedulaUser != null && !cedulaUser.trim().equals("")) {
            ventas = ventaService.getVentaByPersona((idPersona != null && !idPersona.trim().equals(
                    "")) ? idPersona : cedulaUser);
        }
        if (!ventas.isEmpty()) {
            // compartimos el objeto obtenido, para poderlo mostrar
            request.setAttribute("listaVentas", ventas);
            request.getRequestDispatcher("/WEB-INF/pages/sga/listadoVentas.jsp").forward(
                    request, response);
        }
        request.setAttribute("type", messageType);
        mensaje = "No se encuentran ventas asociadas a la busqueda";
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("/WEB-INF/pages/sga/buscarVenta.jsp").forward(
                request, response);
    }

    /**
     * Metodo encargado de mostrar Histórico de ventas en el listado de ventas
     * de acuerdo a una busqueda con base en el usuario en sesion
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void mostrarHistoricoVentas(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Revisamos si ya está el usuario en la sesion
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("usuario");
        // Validamos el username del usuario en sesion
        UsuarioService usuarioService = UsuarioServiceImpl.getInstance();
        String cedula = usuarioService.getUsuarioByFullName(username).getIdPersona();
        request.setAttribute("cedulaUser", cedula);
        this.mostrarVenta(request, response);
    }

    /**
     * Metodo que edita un Venta en el sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void editarVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Recuperamos el identificador a procesar y reenviamos a la pagina de
        // detalle
        String idVentaParam = request.getParameter("ventas");
        Integer idVenta = null;
        if (idVentaParam != null && !idVentaParam.trim().equals("")) {
            idVenta = new Integer(idVentaParam);
            // Utilizamos el servicio de Venta para recuperar el objeto de la
            // BD
            VentaService ventaService = VentaServiceImpl.getInstance();
            Venta venta = ventaService.getVentaById(idVenta);
            // compartimos el objeto Venta obtenido, para poderlo modificar
            request.setAttribute("venta", venta);
            request.getRequestDispatcher("/WEB-INF/pages/sga/detalleVenta.jsp").forward(
                    request, response);
        }
        else {
            mensaje = "Debe seleccionar un elemento a Editar";
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            // Reutilizamos el metodo listarVentas
            this.listarVentas(request, response);
        }
    }

    /**
     * Metodo que elimina una Venta del sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void eliminarVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // 1. Procesamos los parametros
        // Recuperamos TODOS los objetos seleccionados
        String idVentaParam = request.getParameter("ventas");
        Integer idVenta;
        // 2. Utilizamos los objetos de Modelo (Venta)
        // Validamos los parametros a eliminar
        if (idVentaParam != null && !idVentaParam.trim().equals("")) {
            idVenta = new Integer(idVentaParam);
            // 3.Utilizamos la capa de servicio para eliminar los objetos
            VentaService ventaService = VentaServiceImpl.getInstance();
            boolean registrosEliminados = ventaService.eliminarVenta(idVenta);
            if (registrosEliminados) {
                messageType = IGlobalConstant.INT_ONE;
                mensaje = "La venta se eliminó exitosamente";
            }
        }
        else {
            mensaje = "Debe seleccionar uno o varios elementos a Eliminar";
        }
        // 4. Redireccionamos al listado de personas (ya no debe de mostrar los
        // registros eliminados)
        request.setAttribute("type", messageType);
        request.setAttribute("mensaje", mensaje);
        this.listarVentas(request, response);
    }

    /**
     * Este metodo nos permite insertar o modificar una Venta, La diferencia va
     * a estar en el Id del Punto de Venta
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void guardarVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        boolean elementoGuardado = false;
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Procesamos los parametros
        Venta venta = this.populateVenta(request, response);
        boolean insertar = (venta.getIdVenta() == null);
        // Hacemos uso de la capa de Servicios para guardar el punto de venta
        VentaService ventaService = VentaServiceImpl.getInstance();
        elementoGuardado = validarPersonaPDV(ventaService, venta);
        // Redireccionamos dependiendo del resultado
        if (elementoGuardado) {
            messageType = IGlobalConstant.INT_ONE;
            request.setAttribute("type", messageType);
            // Si es una nueva Venta redireccionamos a la vista de Productos
            if (insertar) {
                mensaje = "Producto vendido exitosamente con Id Venta N°: "
                        + venta.getIdVenta();
                request.setAttribute("mensaje", mensaje);
                // Controlador de tipo Producto
                ProductoController controlProducto;
                controlProducto = ProductoController.getInstance();
                controlProducto.listarProductos(request, response);
            }
            // Si es una modificación utilizamos el caso de listarVentas
            mensaje = "Venta con Id: " + venta.getIdVenta()
                    + " Modificada exitosamente";
            request.setAttribute("mensaje", mensaje);
            this.listarVentas(request, response);
        }
        else {
            mensaje = "No existe la persona en sistema o la cantidad de Productos no "
                    + "se encuentran en el Almacén, verifique los datos";
            // compartimos el objeto Venta, para poderlo modificar
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            if (insertar) {
                // Utilizamos el servicio de Producto para recuperar el objeto
                // de la BD
                ProductoService productoService = ProductoServiceImpl.getInstance();
                Producto producto = productoService.getProductoById(venta.getIdProducto());
                // compartimos el objeto Producto obtenido, para poderlo
                // modificar
                request.setAttribute(
                        "productoBean",
                        new ProductoBean(
                                producto,
                                productoService.getTipoById(producto.getTipo()),
                                productoService.getMarcaById(producto.getMarca()),
                                productoService.getDescuentoById(producto.getDescuento())));
                request.getRequestDispatcher(
                        "/WEB-INF/pages/sga/verDetallesProducto.jsp").forward(
                        request, response);
            }
            request.setAttribute("venta", venta);
            request.getRequestDispatcher("/WEB-INF/pages/sga/detalleVenta.jsp").forward(
                    request, response);
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
    private boolean validarPersonaPDV(VentaService ventaService, Venta venta) {
        // Verificamos que exista la persona en BD
        PersonaService personaService = PersonaServiceImpl.getInstance();
        Persona persona = personaService.getPersonaById(venta.getIdPersona());
        if (persona != null) {
            // Validamos la existencia del punto de Venta
            PuntoVentaService puntoVentaService = PuntoVentaServiceImpl.getInstance();
            ProductosPuntoVenta productosPuntoVenta = puntoVentaService.getProductoPuntoVentaById(
                    venta.getIdPuntoVenta(), venta.getIdProducto());
            if (productosPuntoVenta != null) {
                // Validamos que exista la cantidad de Productos a vender en el
                // almacen
                return (productosPuntoVenta.getCantidad() >= venta.getCantidad())
                        ? ventaService.guardarVenta(venta,
                                productosPuntoVenta.getCantidad())
                        : false;
            }
            return false;
        }
        else {
            return false;
        }
    }

    /**
     * Metodo encargado de Poblar un objeto de tipo Venta con los valores
     * recibidos por parametros para ser manipulados por las capas de servicios,
     * datos y Web
     * 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    private Venta populateVenta(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Procesamos los parametros
        String idVentaParam = request.getParameter("idVenta");
        String idPersona = request.getParameter("idPersona");
        String idProducto = request.getParameter("idProducto");
        String idPuntoVenta = request.getParameter("idPuntoVenta");
        String precioVenta = request.getParameter("precioVenta");
        String cantidad = request.getParameter("cantidad");
        // Utilizamos el objeto Modelo
        Venta venta = new Venta();
        // Validamos la PK
        // si el idVenta no venia en los parametros, se coloca null
        Integer idVenta = null;
        if (idVentaParam != null && !idVentaParam.trim().equals("")) {
            idVenta = new Integer(idVentaParam);
        }
        venta.setIdVenta(idVenta);
        venta.setIdPersona(idPersona);
        venta.setIdProducto(new Integer(idProducto));
        venta.setIdPuntoVenta(new Integer(idPuntoVenta));
        venta.setFecVenta(new Date());
        venta.setPrecioVenta(new Integer(precioVenta.replace(".0", "")));
        venta.setCantidad(new Integer(cantidad));
        return venta;
    }

}
