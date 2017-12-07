package sga.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sga.eis.dto.Descuento;
import sga.eis.dto.Marca;
import sga.eis.dto.Producto;
import sga.eis.dto.Tipo;
import sga.services.ProductoService;
import sga.services.impl.ProductoServiceImpl;
import sga.web.model.ProductoBean;
import sga.web.utilities.IGlobalConstant;

/**
 * Clase que Implementa un Controlador de Tipo Producto
 * 
 * @author Juan Carlos Fuyo
 */
public class ProductoController {

    /**
     * Utilizamos el patron singleton, solo existen un objeto de tipo
     * ProductoController en Memoria
     */
    private static ProductoController controlProducto;

    /**
     * Constructor sin argumentos privado, para implementar el patron singleton
     */
    private ProductoController() {
    }

    /**
     * Metodo que Crea una nueva y unica instancia de ProductoController si es
     * que no existe
     * 
     * @return the controlProducto (Controlador de Productos)
     */
    public static ProductoController getInstance() {
        if (controlProducto == null) {
            controlProducto = new ProductoController();
        }
        return controlProducto;
    }

    /**
     * Metodo para procesar el caso de uso de listarProductos
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void listarProductos(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Recuperamos el listado de productos, utilizamos el servicio de
        // Productos
        ProductoService productoService = ProductoServiceImpl.getInstance();
        List<ProductoBean> productos = productoService.getAllProductos();
        // Si se encontraron Productos, las compartimos en la pagina Web
        if (!productos.isEmpty()) {
            request.setAttribute("listaProductos", productos);
        }
        request.getRequestDispatcher("/WEB-INF/pages/sga/listadoProductos.jsp").forward(
                request, response);
    }

    /**
     * Metodo encargado de mostrar un producto en el listado de productos de
     * acuerdo a una busqueda con base en alguna de sus propiedades
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void mostrarProducto(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Utilizamos el objeto modelo para de Productos para la respuesta
        List<ProductoBean> productosBean = new ArrayList<ProductoBean>();
        // Procesamos los parametros de busqueda
        String idProductoParam = request.getParameter("idProducto");
        String nombreParam = request.getParameter("nombre");
        String tipoParam = request.getParameter("idTipo");
        String marcaParam = request.getParameter("idMarca");
        // Recuperamos los Productos utilizando el servicio de Productos
        ProductoService productoService = ProductoServiceImpl.getInstance();
        if (idProductoParam != null && !idProductoParam.trim().equals("")) {
            Integer idProducto = new Integer(idProductoParam);
            Producto producto = productoService.getProductoById(idProducto);
            if (producto != null) {
                productosBean.add(new ProductoBean(
                        producto,
                        productoService.getTipoById(producto.getTipo()),
                        productoService.getMarcaById(producto.getMarca()),
                        productoService.getDescuentoById(producto.getDescuento())));
            }
        }
        else if (nombreParam != null && !nombreParam.trim().equals("")) {
            productosBean = productoService.getProductosByName(nombreParam);
        }
        else if (marcaParam != null && !marcaParam.trim().equals("")) {
            Integer idMarca = new Integer(marcaParam);
            productosBean = productoService.getProductosByMarca(idMarca);
        }
        else if (tipoParam != null && !tipoParam.trim().equals("")) {
            Integer idTipo = new Integer(tipoParam);
            productosBean = productoService.getProductosByTipo(idTipo);
        }
        if (!productosBean.isEmpty()) {
            // compartimos el objeto obtenido, para poderlo mostrar
            request.setAttribute("listaProductos", productosBean);
            request.getRequestDispatcher(
                    "/WEB-INF/pages/sga/listadoProductos.jsp").forward(request,
                    response);
        }
        request.setAttribute("type", messageType);
        mensaje = "No se encuentra el producto buscado";
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("/WEB-INF/pages/sga/buscarProducto.jsp").forward(
                request, response);
    }

    /**
     * Metodo encarcado de redireccionar a la pagina de Busqueda de Productos
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void buscarProducto(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Solo redireccionamos a la página de busqueda de productos
        request.getRequestDispatcher("/WEB-INF/pages/sga/buscarProducto.jsp").forward(
                request, response);
    }

    /**
     * Metodo que agrega un producto en el sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void agregarProducto(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // No procesamos ningun parametro, sino que solo redireccionamos
        // a la vista para agregar un nuevo producto
        request.getRequestDispatcher("/WEB-INF/pages/sga/detalleProducto.jsp").forward(
                request, response);
    }

    /**
     * Metodo que edita un Producto en el sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void editarProducto(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Recuperamos el identificador a procesar y reenviamos a la pagina de
        // detalle
        String idProductoParam = request.getParameter("productos");
        Integer idProducto = null;
        if (idProductoParam != null && !idProductoParam.trim().equals("")) {
            idProducto = new Integer(idProductoParam);
            // Utilizamos el servicio de Producto para recuperar el objeto de la
            // BD
            ProductoService productoService = ProductoServiceImpl.getInstance();
            Producto producto = productoService.getProductoById(idProducto);
            // compartimos el objeto Producto obtenido, para poderlo modificar
            request.setAttribute(
                    "productoBean",
                    new ProductoBean(
                            producto,
                            null,
                            null,
                            productoService.getDescuentoById(producto.getDescuento())));
            request.getRequestDispatcher(
                    "/WEB-INF/pages/sga/detalleProducto.jsp").forward(request,
                    response);
        }
        else {
            mensaje = "Debe seleccionar un elemento a Editar";
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            // Reutilizamos el metodo listarProductos
            this.listarProductos(request, response);
        }
    }

    /**
     * Metodo que elimina un Producto del sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void eliminarProducto(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // 1. Procesamos los parametros
        // Recuperamos TODOS los objetos seleccionados
        String[] idProductoParams = request.getParameterValues("productos");
        List<Integer> idProductos = new ArrayList<Integer>();
        // 2. Utilizamos los objetos de Modelo (Producto)
        // Validamos los parametros a eliminar
        if (idProductoParams != null && idProductoParams.length > 0) {
            for (String producto : idProductoParams) {
                idProductos.add(new Integer(producto));
            }
            // 3.Utilizamos la capa de servicio para eliminar los objetos
            ProductoService productoService = ProductoServiceImpl.getInstance();
            boolean registrosEliminados = productoService.eliminarProductos(idProductos);
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
        this.listarProductos(request, response);
    }

    /**
     * Este metodo nos permite insertar o modificar un Producto, La diferencia
     * va a estar si recibimos o no una llave primaria por parte del formulario
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void guardarProducto(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        ProductoService productoService = ProductoServiceImpl.getInstance();
        // Procesamos los parametros
        String idProductoParam = request.getParameter("idProducto");
        int idProducto = (idProductoParam != null && !idProductoParam.trim().equals(
                ""))
                ? new Integer(idProductoParam).intValue()
                : IGlobalConstant.INT_ZERO;
        // Validamos que no exista el nombre del Producto en Sistema
        Producto producto = productoService.getProductoByFullName(request.getParameter("nombre"));
        if (producto == null) {
            // Verificamos Tipo y Marca del Producto en BD
            this.validarMarcaTipo(request, response);
        }
        else if (producto.getIdProducto().intValue() == idProducto) {
            // Verificamos Tipo y Marca del Producto en BD
            this.validarMarcaTipo(request, response);
        }
        else {
            mensaje = "El nombre del Producto ya existe, verifique los datos";
        }
        producto = this.populateProducto(request, response);
        String idDctoParam = request.getParameter("idDcto");
        Descuento descuento = (idDctoParam != null && !idDctoParam.trim().equals(
                ""))
                ? productoService.getDescuentoById(new Integer(idDctoParam))
                : this.validarDcto(request, response);
        // compartimos el objeto ProductoBean, para poderlo modificar
        request.setAttribute("productoBean", new ProductoBean(producto, null,
                null, descuento));
        request.setAttribute("type", messageType);
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("/WEB-INF/pages/sga/detalleProducto.jsp").forward(
                request, response);
    }

    public void verDetallesProducto(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Recuperamos el identificador a procesar y reenviamos a la pagina de
        // detalle
        String idProductoParam = request.getParameter("productos");
        Integer idProducto = null;
        if (idProductoParam != null && !idProductoParam.trim().equals("")) {
            idProducto = new Integer(idProductoParam);
            // Utilizamos el servicio de Producto para recuperar el objeto de la
            // BD
            ProductoService productoService = ProductoServiceImpl.getInstance();
            Producto producto = productoService.getProductoById(idProducto);
            // compartimos el objeto Producto obtenido, para poderlo modificar
            request.setAttribute("productoBean", new ProductoBean(producto,
                    productoService.getTipoById(producto.getTipo()),
                    productoService.getMarcaById(producto.getMarca()),
                    productoService.getDescuentoById(producto.getDescuento())));
            request.getRequestDispatcher(
                    "/WEB-INF/pages/sga/verDetallesProducto.jsp").forward(
                    request, response);
        }
        else {
            mensaje = "Debe seleccionar un elemento";
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            // Reutilizamos el metodo listarProductos
            this.listarProductos(request, response);
        }
    }

    /**
     * Metodo para procesar el caso de uso de listarTipos
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void listarTipos(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Recuperamos el listado de tipos, utilizamos el servicio de
        // Productos
        ProductoService productoService = ProductoServiceImpl.getInstance();
        List<Tipo> tipos = productoService.getAllTipos();
        // Si se encontraron Tipos, las compartimos en la pagina Web
        if (tipos != null && tipos.size() > 0) {
            request.setAttribute("listaTipos", tipos);
        }
        request.getRequestDispatcher("/WEB-INF/pages/sga/listadoTipos.jsp").forward(
                request, response);
    }

    /**
     * Metodo encargado de mostrar un listado de tipos de acuerdo a una busqueda
     * por nombre o Id de Tipo
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void mostrarTipo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        List<Tipo> tipos = new ArrayList<Tipo>();
        String idTipoParam = request.getParameter("idTipo");
        String nombreParam = request.getParameter("nombre");
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Recuperamos los Tipos utilizando el servicio de Tipos
        ProductoService productoService = ProductoServiceImpl.getInstance();
        if (idTipoParam != null && !idTipoParam.trim().equals("")) {
            Integer idTipo = new Integer(idTipoParam);
            Tipo tipo = productoService.getTipoById(idTipo);
            if (tipo != null) {
                tipos.add(tipo);
            }
        }
        else if (nombreParam != null && !nombreParam.trim().equals("")) {
            tipos = productoService.getTiposByName(nombreParam);
        }
        if (!tipos.isEmpty()) {
            // compartimos el objeto obtenido, para poderlo mostrar
            request.setAttribute("listaTipos", tipos);
            request.getRequestDispatcher("/WEB-INF/pages/sga/listadoTipos.jsp").forward(
                    request, response);
        }
        request.setAttribute("type", messageType);
        mensaje = "No se encuentra el tipo buscado";
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("/WEB-INF/pages/sga/buscarTipo.jsp").forward(
                request, response);
    }

    /**
     * Metodo encarcado de redireccionar a la pagina de Busqueda de Tipos
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void buscarTipo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Solo redireccionamos a la página de busqueda de productos por id
        request.getRequestDispatcher("/WEB-INF/pages/sga/buscarTipo.jsp").forward(
                request, response);
    }

    /**
     * Metodo que agrega un Tipo en el sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void agregarTipo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // No procesamos ningun parametro, sino que solo redireccionamos
        // a la vista para agregar un nuevo Tipo
        request.getRequestDispatcher("/WEB-INF/pages/sga/detalleTipo.jsp").forward(
                request, response);
    }

    /**
     * Metodo que edita un Tipo en el sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void editarTipo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Recuperamos el identificador a procesar y reenviamos a la pagina de
        // detalle
        String idTipoParam = request.getParameter("tipos");
        Integer idTipo = null;
        if (idTipoParam != null && !idTipoParam.trim().equals("")) {
            idTipo = new Integer(idTipoParam);
            // Utilizamos el servicio de Tipo para recuperar el objeto de la
            // BD
            ProductoService productoService = ProductoServiceImpl.getInstance();
            Tipo tipo = productoService.getTipoById(idTipo);
            // compartimos el objeto Tipo obtenido, para poderlo modificar
            request.setAttribute("tipo", tipo);
            request.getRequestDispatcher("/WEB-INF/pages/sga/detalleTipo.jsp").forward(
                    request, response);
        }
        else {
            mensaje = "Debe seleccionar un elemento a Editar";
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            // Reutilizamos el metodo listarTipos
            this.listarTipos(request, response);
        }
    }

    /**
     * Metodo que elimina un Tipo de Producto del sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void eliminarTipo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // 1. Procesamos los parametros
        // Recuperamos TODOS los objetos seleccionados
        String[] idTipoParam = request.getParameterValues("tipos");
        List<Integer> idTipos = new ArrayList<Integer>();
        // 2. Utilizamos los objetos de Modelo (Producto)
        // Validamos los parametros a eliminar
        if (idTipoParam != null && idTipoParam.length > 0) {
            for (String tipo : idTipoParam) {
                idTipos.add(new Integer(tipo));
            }
            // 3.Utilizamos la capa de servicio para eliminar los objetos
            ProductoService productoService = ProductoServiceImpl.getInstance();
            boolean registrosEliminados = productoService.eliminarTipos(idTipos);
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
        this.listarTipos(request, response);
    }

    /**
     * Este metodo nos permite insertar o modificar un Tipo, La diferencia va a
     * estar si recibimos o no una llave primaria por parte del formulario
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void guardarTipo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        boolean elementoGuardado = false;
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Procesamos los parametros
        Tipo tipo = this.populateTipo(request, response);
        // Validamos que no exista el nombre en sistema
        int idTipo = (tipo.getIdTipo() != null)
                ? tipo.getIdTipo().intValue()
                : IGlobalConstant.INT_ZERO;
        ProductoService tipoService = ProductoServiceImpl.getInstance();
        Tipo tipoAux = tipoService.getTipoByFullName(tipo.getNombre());
        if (tipoAux == null) {
            elementoGuardado = tipoService.guardarTipo(tipo);
        }
        else if (tipoAux.getIdTipo() == idTipo) {
            elementoGuardado = tipoService.guardarTipo(tipo);
        }
        else {
            mensaje = "Ya existe el nombre del Tipo de Producto en sistema, verifique los datos";
        }
        // Redireccionamos dependiendo del resultado
        if (elementoGuardado) {
            mensaje = "Se guard&oacute; el elemento correctamente";
            messageType = IGlobalConstant.INT_ONE;
            // Reutilizamos el caso de listarTipos
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            this.listarTipos(request, response);
        }
        else {
            mensaje = (mensaje != null && !mensaje.trim().equals(""))
                    ? mensaje
                    : "No se guardo correctamente el elemento";
        }
        // compartimos el objeto Tipo, para poderlo modificar
        request.setAttribute("tipo", tipo);
        request.setAttribute("type", messageType);
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("/WEB-INF/pages/sga/detalleTipo.jsp").forward(
                request, response);
    }

    /**
     * Metodo para procesar el caso de uso de listarMarcas
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void listarMarcas(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Recuperamos el listado de marcas, utilizamos el servicio de
        // Productos
        ProductoService productoService = ProductoServiceImpl.getInstance();
        List<Marca> marcas = productoService.getAllMarcas();
        // Si se encontraron Marcas, las compartimos en la pagina Web
        if (marcas != null && marcas.size() > 0) {
            request.setAttribute("listaMarcas", marcas);
        }
        request.getRequestDispatcher("/WEB-INF/pages/sga/listadoMarcas.jsp").forward(
                request, response);
    }

    /**
     * Metodo encargado de mostrar un listado de marcas de acuerdo a una
     * busqueda por nombre o Id de Marca
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void mostrarMarca(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        List<Marca> marcas = new ArrayList<Marca>();
        String idMarcaParam = request.getParameter("idMarca");
        String nombreParam = request.getParameter("nombre");
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Recuperamos las Marcas utilizando el servicio de Productos
        ProductoService productoService = ProductoServiceImpl.getInstance();
        if (idMarcaParam != null && !idMarcaParam.trim().equals("")) {
            Integer idMarca = new Integer(idMarcaParam);
            Marca marca = productoService.getMarcaById(idMarca);
            if (marca != null) {
                marcas.add(marca);
            }
        }
        else if (nombreParam != null && !nombreParam.trim().equals("")) {
            marcas = productoService.getMarcasByName(nombreParam);
        }
        if (!marcas.isEmpty()) {
            // compartimos el objeto obtenido, para poderlo mostrar
            request.setAttribute("listaMarcas", marcas);
            request.getRequestDispatcher("/WEB-INF/pages/sga/listadoMarcas.jsp").forward(
                    request, response);
        }
        request.setAttribute("type", messageType);
        mensaje = "No se encuentra la marca buscada";
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("/WEB-INF/pages/sga/buscarMarca.jsp").forward(
                request, response);
    }

    /**
     * Metodo encarcado de redireccionar a la pagina de Busqueda de Marcas
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void buscarMarca(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Solo redireccionamos a la página de busqueda de Marcas por id
        request.getRequestDispatcher("/WEB-INF/pages/sga/buscarMarca.jsp").forward(
                request, response);
    }

    /**
     * Metodo que agrega una Marca en el sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void agregarMarca(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // No procesamos ningun parametro, sino que solo redireccionamos
        // a la vista para agregar una nueva Marca
        request.getRequestDispatcher("/WEB-INF/pages/sga/detalleMarca.jsp").forward(
                request, response);
    }

    /**
     * Metodo que edita una Marca en el sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void editarMarca(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Recuperamos el identificador a procesar y reenviamos a la pagina de
        // detalle
        String idMarcaParam = request.getParameter("marcas");
        Integer idMarca = null;
        if (idMarcaParam != null && !idMarcaParam.trim().equals("")) {
            idMarca = new Integer(idMarcaParam);
            // Utilizamos el servicio de Productos para recuperar el objeto de
            // la BD
            ProductoService productoService = ProductoServiceImpl.getInstance();
            Marca marca = productoService.getMarcaById(idMarca);
            // compartimos el objeto Marca obtenido, para poderlo modificar
            request.setAttribute("marca", marca);
            request.getRequestDispatcher("/WEB-INF/pages/sga/detalleMarca.jsp").forward(
                    request, response);
        }
        else {
            mensaje = "Debe seleccionar un elemento a Editar";
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            // Reutilizamos el metodo listarMarcas
            this.listarMarcas(request, response);
        }
    }

    /**
     * Metodo que elimina una Marca de Productos del sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void eliminarMarca(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // 1. Procesamos los parametros
        // Recuperamos TODOS los objetos seleccionados
        String[] idMarcaParam = request.getParameterValues("marcas");
        List<Integer> idMarcas = new ArrayList<Integer>();
        // 2. Utilizamos los objetos de Modelo (Marca)
        // Validamos los parametros a eliminar
        if (idMarcaParam != null && idMarcaParam.length > 0) {
            for (String marca : idMarcaParam) {
                idMarcas.add(new Integer(marca));
            }
            // 3.Utilizamos la capa de servicio para eliminar los objetos
            ProductoService productoService = ProductoServiceImpl.getInstance();
            boolean registrosEliminados = productoService.eliminarMarcas(idMarcas);
            if (registrosEliminados) {
                messageType = IGlobalConstant.INT_ONE;
                mensaje = "Se eliminaron correctamente los registros";
            }
        }
        else {
            mensaje = "Debe seleccionar uno o varios elementos a Eliminar";
        }
        // 4. Redireccionamos al listado de marcas (ya no debe de mostrar los
        // registros eliminados)
        request.setAttribute("type", messageType);
        request.setAttribute("mensaje", mensaje);
        this.listarMarcas(request, response);
    }

    /**
     * Este metodo nos permite insertar o modificar un Marca, La diferencia va a
     * estar si recibimos o no una llave primaria por parte del formulario
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void guardarMarca(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        boolean elementoGuardado = false;
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Procesamos los parametros
        Marca marca = this.populateMarca(request, response);
        // Validamos que no exista el nombre en sistema
        int idMarca = (marca.getIdMarca() != null)
                ? marca.getIdMarca().intValue()
                : IGlobalConstant.INT_ZERO;
        ProductoService marcaService = ProductoServiceImpl.getInstance();
        Marca marcaAux = marcaService.getMarcaByFullName(marca.getNombre());
        if (marcaAux == null) {
            elementoGuardado = marcaService.guardarMarca(marca);
        }
        else if (marcaAux.getIdMarca() == idMarca) {
            elementoGuardado = marcaService.guardarMarca(marca);
        }
        else {
            mensaje = "Ya existe el nombre de la Marca en sistema, verifique los datos";
        }
        // Redireccionamos dependiendo del resultado
        if (elementoGuardado) {
            mensaje = "Se guard&oacute; el elemento correctamente";
            messageType = IGlobalConstant.INT_ONE;
            // Reutilizamos el caso de listarMarcas
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            this.listarMarcas(request, response);
        }
        else {
            mensaje = (mensaje != null && !mensaje.trim().equals(""))
                    ? mensaje
                    : "No se guardo correctamente el elemento";
        }
        // compartimos el objeto Marca, para poderlo modificar
        request.setAttribute("marca", marca);
        request.setAttribute("type", messageType);
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("/WEB-INF/pages/sga/detalleMarca.jsp").forward(
                request, response);
    }

    /**
     * Metodo encargado de validar que existan la marca y el tipo de Producto en
     * sistema antes de almacenarlo en la BD
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void validarMarcaTipo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        Producto producto = this.populateProducto(request, response);
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Verificamos que exista el tipo de Producto en BD
        ProductoService productoService = ProductoServiceImpl.getInstance();
        Tipo tipo = productoService.getTipoById(new Integer(
                request.getParameter("idTipo")));
        if (tipo != null) {
            producto.setTipo(tipo.getIdTipo());
            // Verificamos que exista la marca de Producto en BD
            Marca marca = productoService.getMarcaById(new Integer(
                    request.getParameter("idMarca")));
            if (marca != null) {
                producto.setMarca(marca.getIdMarca());
                // Validamos que tenga descuento
                Descuento descuento = this.validarDcto(request, response);
                if (descuento != null) {
                    productoService.guardarDescuento(descuento);
                    producto.setDescuento(descuento.getIdDcto());
                }
                else {
                    producto.setDescuento(IGlobalConstant.INT_ZERO);
                }
                // Utilizamos la capa de servicio
                boolean elementoGuardado = productoService.guardarProducto(producto);
                // Redireccionamos dependiendo del resultado
                if (elementoGuardado) {
                    mensaje = "Se guard&oacute; el elemento correctamente";
                    messageType = IGlobalConstant.INT_ONE;
                    // Reutilizamos el caso de listarProductos
                    request.setAttribute("type", messageType);
                    request.setAttribute("mensaje", mensaje);
                    this.listarProductos(request, response);
                }
                else {
                    mensaje = "No se guardo correctamente el elemento";
                }
            }
            else {
                mensaje = "No existe la Marca en sistema, por favor verifique los datos";
            }
        }
        else {
            mensaje = "No existe el Tipo de Producto en sistema, por favor verifique los datos";
        }
        String idDctoParam = request.getParameter("idDcto");
        Descuento descuento = (idDctoParam != null && !idDctoParam.trim().equals(
                ""))
                ? productoService.getDescuentoById(new Integer(idDctoParam))
                : this.validarDcto(request, response);
        // compartimos el objeto ProductoBean, para poderlo modificar
        request.setAttribute("productoBean", new ProductoBean(producto, null,
                null, descuento));
        request.setAttribute("type", messageType);
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("/WEB-INF/pages/sga/detalleProducto.jsp").forward(
                request, response);
    }

    /**
     * Metodo encargado de validar que exista el Descuento y de Poblar el objeto
     * en caso de que sea verdadero
     * 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    private Descuento validarDcto(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        Descuento descuento = null;
        // Recuperamos el parametro que valida si aplica descuento
        String aplicaDcto = request.getParameter("aplicaDcto");
        if (aplicaDcto != null && !aplicaDcto.trim().equals("")) {
            descuento = new Descuento();
            String idDctoParam = request.getParameter("idDcto");
            Integer idDcto = null;
            if (idDctoParam != null && !idDctoParam.trim().equals("")) {
                idDcto = new Integer(idDctoParam);
            }
            descuento.setIdDcto(idDcto);
            descuento.setDescripcion(request.getParameter("descDcto"));
            try {
                // Formateamos la fecha que recibimos como un String
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                descuento.setFechInicial(formatter.parse(request.getParameter("fechInicial")));
                descuento.setFechFinal(formatter.parse(request.getParameter("fechFinal")));
            }
            catch (ParseException e) {
                Producto producto = this.populateProducto(request, response);
                // compartimos el objeto ProductoBean, para poderlo modificar
                request.setAttribute("productoBean", new ProductoBean(producto,
                        null, null, descuento));
                mensaje = "Formato de fecha no valido, debe tener el formato (yyyy-mm-dd)";
                request.setAttribute("mensaje", mensaje);
                request.setAttribute("type", messageType);
                request.getRequestDispatcher(
                        "/WEB-INF/pages/sga/detalleProducto.jsp").forward(
                        request, response);
            }
            descuento.setPorcentaje(new Integer(
                    request.getParameter("porcentaje")));
        }
        return descuento;
    }

    /**
     * Metodo encargado de Poblar un objeto de tipo Producto con los valores
     * recibidos por parametros para ser manipulados por las capas de servicios,
     * datos y Web
     * 
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    private Producto populateProducto(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Procesamos los parametros
        String idProductoParam = request.getParameter("idProducto");
        String nombre = request.getParameter("nombre");
        String descProducto = request.getParameter("descProducto");
        String costo = request.getParameter("costo");
        String precio = request.getParameter("precio");
        String idTipoParam = request.getParameter("idTipo");
        String idMarcaParam = request.getParameter("idMarca");
        int idTipo = new Integer(idTipoParam);
        int idMarca = new Integer(idMarcaParam);
        // Utilizamos el objeto Modelo
        Producto producto = new Producto();
        // Validamos la PK
        // si el idProducto no venia en los parametros, se coloca null
        Integer idProducto = null;
        if (idProductoParam != null && !idProductoParam.trim().equals("")) {
            idProducto = new Integer(idProductoParam);
        }
        producto.setIdProducto(idProducto);
        producto.setNombre(nombre);
        producto.setDescripcion(descProducto);
        producto.setCosto(new Integer(costo.replace(".0", "")));
        producto.setPrecio(new Integer(precio.replace(".0", "")));
        producto.setMarca(idMarca);
        producto.setTipo(idTipo);
        return producto;
    }

    /**
     * Metodo encargado de Poblar un objeto de tipo Tipo con los valores
     * recibidos por parametros para ser manipulados por las capas de servicios,
     * datos y Web
     * 
     * @param request
     * @param response
     * @return Tipo
     * @throws ServletException
     * @throws IOException
     */
    private Tipo populateTipo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Procesamos los parametros
        String idTipoParam = request.getParameter("idTipo");
        String nombre = request.getParameter("nombre");
        // Utilizamos el objeto Modelo
        Tipo tipo = new Tipo();
        // Validamos la PK
        // si el idTipo no venia en los parametros, se coloca null
        Integer idTipo = null;
        if (idTipoParam != null && !idTipoParam.trim().equals("")) {
            idTipo = new Integer(idTipoParam);
        }
        tipo.setIdTipo(idTipo);
        tipo.setNombre(nombre);
        return tipo;
    }

    /**
     * Metodo encargado de Poblar un objeto de marca Marca con los valores
     * recibidos por parametros para ser manipulados por las capas de servicios,
     * datos y Web
     * 
     * @param request
     * @param response
     * @return Marca
     * @throws ServletException
     * @throws IOException
     */
    private Marca populateMarca(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Procesamos los parametros
        String idMarcaParam = request.getParameter("idMarca");
        String nombre = request.getParameter("nombre");
        String esNacional = request.getParameter("esNacional");
        // Utilizamos el objeto Modelo
        Marca marca = new Marca();
        // Validamos la PK
        // si el idMarca no venia en los parametros, se coloca null
        Integer idMarca = null;
        if (idMarcaParam != null && !idMarcaParam.trim().equals("")) {
            idMarca = new Integer(idMarcaParam);
        }
        marca.setIdMarca(idMarca);
        marca.setNombre(nombre);
        marca.setEsNacional(new Integer(esNacional));
        return marca;
    }

}
