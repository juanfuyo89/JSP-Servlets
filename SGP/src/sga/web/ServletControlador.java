/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package sga.web;

import sga.eis.dto.Usuario;
import sga.services.UsuarioService;
import sga.services.impl.UsuarioServiceImpl;
import sga.web.utilities.IGlobalConstant;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Juan Carlos Fuyo
 */
@WebServlet(name = "ServletControlador", urlPatterns = { "/ServletControlador" })
public class ServletControlador extends HttpServlet {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
     * Tipo de Controlador
     */
    private int               controlType;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // Controlador de tipo Persona
        PersonaController controlPersona;
        // Controlador de tipo Usuario
        UsuarioController controlUsuario;
        // Controlador de tipo Producto
        ProductoController controlProducto;
        // Controlador de tipo Usuario
        PuntoVentaController controlPuntoVenta;
        // Controlador de tipo Usuario
        VentaController controlVenta;

        // Revisamos los casos de uso del sistema SGP
        String accion = request.getParameter("accion");
        System.out.println("ServletControlador - accion: " + accion);

        if ("validarUsuario".equals(accion)) {
            this.validarUsuario(request, response);
        }
        else if(this.confirmarUsuarioEnSession(request, response)) {

            switch (accion) {
    		case "listarPersonas":
    		    controlPersona = PersonaController.getInstance();
                controlPersona.listarPersonas(request, response);          
    			break;
    		case "listarUsuarios":
    			controlUsuario = UsuarioController.getInstance();
                controlUsuario.listarUsuarios(request, response);
    			break;
    		case "listarProductos":
    			controlProducto = ProductoController.getInstance();
                controlProducto.listarProductos(request, response);
    			break;
    		case "listarTipos":
    			controlProducto = ProductoController.getInstance();
                controlProducto.listarTipos(request, response);
    			break;
    		case "listarMarcas":
    			controlProducto = ProductoController.getInstance();
                controlProducto.listarMarcas(request, response);
    			break;
    		case "listarPuntosVenta":
    			controlPuntoVenta = PuntoVentaController.getInstance();
                controlPuntoVenta.listarPuntosVenta(request, response);
    			break;
    		case "listarProductosPuntoVenta":
    			controlPuntoVenta = PuntoVentaController.getInstance();
                controlPuntoVenta.listarProductosPuntoVenta(request, response);
    			break;
    		case "listarVentas":
    			controlVenta = VentaController.getInstance();
                controlVenta.listarVentas(request, response);
    			break;
    		case "buscarPersona":
    			controlPersona = PersonaController.getInstance();
                controlPersona.buscarPersona(request, response);
    			break;
            case "buscarUsuario":
                controlUsuario = UsuarioController.getInstance();
                controlUsuario.buscarUsuario(request, response);
                break;
    		case "buscarProducto":
    			controlProducto = ProductoController.getInstance();
                controlProducto.buscarProducto(request, response);
    			break;
    		case "buscarTipo":
    			controlProducto = ProductoController.getInstance();
                controlProducto.buscarTipo(request, response);
    			break;
    		case "buscarMarca":
    			controlProducto = ProductoController.getInstance();
                controlProducto.buscarMarca(request, response);
    			break;
    		case "buscarPuntoVenta":
    			controlPuntoVenta = PuntoVentaController.getInstance();
                controlPuntoVenta.buscarPuntoVenta(request, response);
    			break;
    		case "buscarProductosPuntoVenta":
    			controlPuntoVenta = PuntoVentaController.getInstance();
                controlPuntoVenta.buscarProductosPuntoVenta(request, response);
    			break;
    		case "buscarVenta":
    			controlVenta = VentaController.getInstance();
                controlVenta.buscarVenta(request, response);
    			break;
    		case "mostrarPersona":
    			controlPersona = PersonaController.getInstance();
                controlPersona.mostrarPersona(request, response);
    			break;
    		case "mostrarUsuario":
    			controlUsuario = UsuarioController.getInstance();
                controlUsuario.mostrarUsuario(request, response);
    			break;
    		case "mostrarProducto":
    			controlProducto = ProductoController.getInstance();
                controlProducto.mostrarProducto(request, response);
    			break;
    		case "mostrarTipo":
    			controlProducto = ProductoController.getInstance();
                controlProducto.mostrarTipo(request, response);
    			break;
    		case "mostrarMarca":
    			controlProducto = ProductoController.getInstance();
                controlProducto.mostrarMarca(request, response);
    			break;
            case "mostrarPuntoVenta":
                controlPuntoVenta = PuntoVentaController.getInstance();
                controlPuntoVenta.mostrarPuntoVenta(request, response);
                break;
    		case "mostrarProductosPuntoVenta":
    			controlPuntoVenta = PuntoVentaController.getInstance();
                controlPuntoVenta.mostrarProductosPuntoVenta(request, response);
    			break;
    		case "mostrarVenta":
    			controlVenta = VentaController.getInstance();
                controlVenta.mostrarVenta(request, response);
    			break;
    		case "mostrarHistoricoVentas":
    			controlVenta = VentaController.getInstance();
                controlVenta.mostrarHistoricoVentas(request, response);
    			break;
            case "agregarPersona":
                controlPersona = PersonaController.getInstance();
                controlPersona.agregarPersona(request, response);          
                break;
            case "agregarUsuario":
                controlUsuario = UsuarioController.getInstance();
                controlUsuario.agregarUsuario(request, response);          
                break;
            case "agregarProducto":
                controlProducto = ProductoController.getInstance();
                controlProducto.agregarProducto(request, response);         
                break;
            case "agregarTipo":
                controlProducto = ProductoController.getInstance();
                controlProducto.agregarTipo(request, response);          
                break;
            case "agregarMarca":
                controlProducto = ProductoController.getInstance();
                controlProducto.agregarMarca(request, response);          
                break;
            case "agregarPuntoVenta":
                controlPuntoVenta = PuntoVentaController.getInstance();
                controlPuntoVenta.agregarPuntoVenta(request, response);          
                break;
            case "verDetallesProducto":
                controlProducto = ProductoController.getInstance();
                controlProducto.verDetallesProducto(request, response);          
                break;
            case "modificarProductosPuntoVenta":
                controlPuntoVenta = PuntoVentaController.getInstance();
                controlPuntoVenta.modificarProductosPuntoVenta(request, response);          
                break;
            case "editarPersona":
                controlPersona = PersonaController.getInstance();
                controlPersona.editarPersona(request, response);          
                break;
            case "editarUsuario":
                controlUsuario = UsuarioController.getInstance();
                controlUsuario.editarUsuario(request, response);          
                break;
            case "editarProducto":
                controlProducto = ProductoController.getInstance();
                controlProducto.editarProducto(request, response);         
                break;
            case "editarTipo":
                controlProducto = ProductoController.getInstance();
                controlProducto.editarTipo(request, response);          
                break;
            case "editarMarca":
                controlProducto = ProductoController.getInstance();
                controlProducto.editarMarca(request, response);          
                break;
            case "editarPuntoVenta":
                controlPuntoVenta = PuntoVentaController.getInstance();
                controlPuntoVenta.editarPuntoVenta(request, response);          
                break;
            case "editarProductosPuntoVenta":
                controlPuntoVenta = PuntoVentaController.getInstance();
                controlPuntoVenta.editarProductosPuntoVenta(request, response);          
                break;
            case "editarVenta":
                controlVenta = VentaController.getInstance();
                controlVenta.editarVenta(request, response);          
                break;
            case "eliminarPersona":
                controlPersona = PersonaController.getInstance();
                controlPersona.eliminarPersona(request, response);          
                break;
            case "eliminarUsuario":
                controlUsuario = UsuarioController.getInstance();
                controlUsuario.eliminarUsuario(request, response);          
                break;
            case "eliminarProducto":
                controlProducto = ProductoController.getInstance();
                controlProducto.eliminarProducto(request, response);          
                break;
            case "eliminarTipo":
                controlProducto = ProductoController.getInstance();
                controlProducto.eliminarTipo(request, response);          
                break;
            case "eliminarMarca":
                controlProducto = ProductoController.getInstance();
                controlProducto.eliminarMarca(request, response);          
                break;
            case "eliminarPuntoVenta":
                controlPuntoVenta = PuntoVentaController.getInstance();
                controlPuntoVenta.eliminarPuntoVenta(request, response);          
                break;
            case "eliminarVenta":
                controlVenta = VentaController.getInstance();
                controlVenta.eliminarVenta(request, response);          
                break;
            case "guardarPersona":
                controlPersona = PersonaController.getInstance();
                controlPersona.guardarPersona(request, response);          
                break;
            case "guardarUsuario":
                controlUsuario = UsuarioController.getInstance();
                controlUsuario.guardarUsuario(request, response);          
                break;
            case "guardarProducto":
                controlProducto = ProductoController.getInstance();
                controlProducto.guardarProducto(request, response);          
                break;
            case "guardarTipo":
                controlProducto = ProductoController.getInstance();
                controlProducto.guardarTipo(request, response);          
                break;
            case "guardarMarca":
                controlProducto = ProductoController.getInstance();
                controlProducto.guardarMarca(request, response);          
                break;
            case "guardarPuntoVenta":
                controlPuntoVenta = PuntoVentaController.getInstance();
                controlPuntoVenta.guardarPuntoVenta(request, response);          
                break;
            case "guardarProductosPuntoVenta":
                controlPuntoVenta = PuntoVentaController.getInstance();
                controlPuntoVenta.guardarProductosPuntoVenta(request, response);          
                break;
            case "guardarVenta":
                controlVenta = VentaController.getInstance();
                controlVenta.guardarVenta(request, response);          
                break;
            case "salir":
                this.salir(request, response);          
                break;
    		default:
                this.accionPorDefault(request, response);
    			break;
    		}

        }
        else {
            request.getRequestDispatcher("/WEB-INF/pages/sga/login.jsp").forward(
                    request, response);
        }

    }

    /**
     * Metodo para validar si el usuario ya inicio sesion
     * 
     * @param request
     * @param response
     * @param accion
     * @throws ServletException
     * @throws IOException
     */
    private boolean confirmarUsuarioEnSession(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Revisamos si ya está el usuario en la sesion
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
        if (usuario != null) {
            // Si ya existe un usuario en session, respondemos verdadero
            return true;
        }
        return false;
    }

    /**
     * Metodo para validar si el usuario y password proporcinados son correctos
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void validarUsuario(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        int messageType = IGlobalConstant.INT_ZERO;
        if (!this.confirmarUsuarioEnSession(request, response)) {
            // Recuperamos los parametros del formulario
            String usuarioParam = request.getParameter("username");
            String passwordParam = request.getParameter("password");
            // Creamos el objeto DTO a enviar a la capa de servicio
            Usuario usuarioDto = new Usuario();
            usuarioDto.setUsername(usuarioParam);
            usuarioDto.setPassword(passwordParam);
            // Revisamos si existen el usuario y el password en la BD
            // Utilizamos el servicio de Usuarios
            UsuarioService usuarioService = UsuarioServiceImpl.getInstance();
            usuarioDto = usuarioService.usuarioExistente(usuarioDto);
            // Si el usuario es válido, lo redireccionamos al caso de
            // listarPersonas
            if (usuarioDto.getIdUsuario() != null) {
                // Agregamos el usuario a la session
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuarioDto.getUsername());
                session.setAttribute("idRol", usuarioDto.getIdRol());
            }
            else {
                // si el usuario no es valido, lo mandamos a la pagina de login
                // nuevamente
                request.setAttribute("mensaje", "Credenciales Incorrectas");
                request.setAttribute("type", messageType);
                request.getRequestDispatcher("/WEB-INF/pages/sga/login.jsp").forward(
                        request, response);
            }
        }
        // Redireccionamos el usuario a la pagina de inicio
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    /**
     * Metodo para Salir de la sesion
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void salir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Eliminamos la session del servidor y redireccionamos a la pagina de
        // inicio
        request.getSession().invalidate();
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * Metodo que se ejecuta cuando no llega ninguna accion al servlet
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void accionPorDefault(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        int messageType = IGlobalConstant.INT_ZERO;
        // Redireccionamos a la pagina de inicio
        String mensaje = "Acci&oacute;n no proporcionada o desconocida";
        request.setAttribute("type", messageType);
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * 
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet encaragado de controlar el flujo de inforamción entre"
                + "las capas de vista y servicios de la aplicación";
    }

    /**
     * @param controlType the controlType to set
     */
    public void setControlType(int controlType) {
        this.controlType = controlType;
    }

    /**
     * @return the controlType
     */
    public int getControlType() {
        return controlType;
    }
}
