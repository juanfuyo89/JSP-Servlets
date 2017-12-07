/**
 * 
 */
package sga.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sga.eis.dto.Usuario;
import sga.services.PersonaService;
import sga.services.UsuarioService;
import sga.services.impl.PersonaServiceImpl;
import sga.services.impl.UsuarioServiceImpl;
import sga.web.utilities.IGlobalConstant;

/**
 * Clase que Implementa un Controlador de Tipo Usuario
 * 
 * @author Juan Carlos Fuyo
 */
public class UsuarioController {

    /**
     * Utilizamos el patron singleton, solo existen un objeto de tipo
     * UsuarioController en Memoria
     */
    private static UsuarioController controlUsuario;

    /**
     * Constructor sin argumentos privado, para implementar el patron singleton
     */
    private UsuarioController() {
    }

    /**
     * Metodo que Crea una nueva y unica instancia de UsuarioController si es
     * que no existe
     * 
     * @return the controlUsuario (Controlador de Usuarios)
     */
    public static UsuarioController getInstance() {
        if (controlUsuario == null) {
            controlUsuario = new UsuarioController();
        }
        return controlUsuario;
    }

    /**
     * Metodo para procesar el caso de uso de listarUsuarios
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void listarUsuarios(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Revisamos si ya está el usuario en la sesion
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
        // Si el usuario ya esta en session, lo redireccionamos al listado de
        // Usuarios
        if (usuario != null) {
            // Recuperamos el listado de usuarios, utilizamos el servicio de
            // Usuarios
            UsuarioService usuarioService = UsuarioServiceImpl.getInstance();
            List<Usuario> usuarios = usuarioService.getAllUsuarios();
            // Si se encontraron Usuarios, las compartimos en la pagina Web
            if (usuarios != null && usuarios.size() > 0) {
                request.setAttribute("listaUsuarios", usuarios);
            }
            request.getRequestDispatcher(
                    "/WEB-INF/pages/sga/listadoUsuarios.jsp").forward(request,
                    response);
        }
    }

    /**
     * Metodo encargado de mostrar un usuario en el listado de usuarios de
     * acuerdo a una busqueda por Id de Usuario
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void mostrarUsuario(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String idUsuario = request.getParameter("idUsuario");
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Recuperamos los Usuarios utilizando el servicio de Usuarios
        UsuarioService usuarioService = UsuarioServiceImpl.getInstance();
        Usuario usuario = usuarioService.getUsuarioById(new Integer(idUsuario));
        if (usuario != null) {
            List<Usuario> usuarios = new ArrayList<Usuario>();
            usuarios.add(usuario);
            // compartimos el objeto Usuario obtenido, para poderlo modificar
            request.setAttribute("listaUsuarios", usuarios);
            request.getRequestDispatcher(
                    "/WEB-INF/pages/sga/listadoUsuarios.jsp").forward(request,
                    response);
        }
        request.setAttribute("type", messageType);
        mensaje = "No se encuentra el usuario buscado";
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("/WEB-INF/pages/sga/buscarUsuario.jsp").forward(
                request, response);
    }

    /**
     * Metodo encarcado de redireccionar a la pagina de Busqueda de Usuarios por
     * Id
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void buscarUsuario(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Solo redireccionamos a la página de busqueda de usuarios por id
        request.getRequestDispatcher("/WEB-INF/pages/sga/buscarUsuario.jsp").forward(
                request, response);
    }

    /**
     * Metodo que agrega un usuario en el sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void agregarUsuario(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // No procesamos ningun parametro, sino que solo redireccionamos
        // a la vista para agregar un nuevo usuario
        request.getRequestDispatcher("/WEB-INF/pages/sga/detalleUsuario.jsp").forward(
                request, response);
    }

    /**
     * Metodo que edita un Usuario en el sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void editarUsuario(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Recuperamos el identificador a procesar y reenviamos a la pagina de
        // detalle
        String idUsuarioParam = request.getParameter("usuarios");
        Integer idUsuario = null;
        if (idUsuarioParam != null && !idUsuarioParam.trim().equals("")) {
            idUsuario = new Integer(idUsuarioParam);
            // Utilizamos el servicio de Usuario para recuperar el objeto de la
            // BD
            UsuarioService usuarioService = UsuarioServiceImpl.getInstance();
            Usuario usuario = usuarioService.getUsuarioById(idUsuario);
            // compartimos el objeto Usuario obtenido, para poderlo modificar
            request.setAttribute("user", usuario);
            request.getRequestDispatcher(
                    "/WEB-INF/pages/sga/detalleUsuario.jsp").forward(request,
                    response);
        }
        else {
            mensaje = "Debe seleccionar un elemento a Editar";
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            // Reutilizamos el metodo listarUsuarios
            this.listarUsuarios(request, response);
        }
    }

    /**
     * Metodo que elimina un Usuario del sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void eliminarUsuario(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // 1. Procesamos los parametros
        // Recuperamos TODOS los objetos seleccionados
        String[] idUsuarioParams = request.getParameterValues("usuarios");
        List<Integer> idUsuarios = new ArrayList<Integer>();
        // 2. Utilizamos los objetos de Modelo (Usuario)
        // Validamos los parametros a eliminar
        if (idUsuarioParams != null && idUsuarioParams.length > 0) {
            for (String usuario : idUsuarioParams) {
                idUsuarios.add(new Integer(usuario));
            }
            // 3.Utilizamos la capa de servicio para eliminar los objetos
            UsuarioService usuarioService = UsuarioServiceImpl.getInstance();
            boolean registrosEliminados = usuarioService.eliminarUsuarios(idUsuarios);
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
        this.listarUsuarios(request, response);
    }

    /**
     * Este metodo nos permite insertar o modificar un Usuario, La diferencia va
     * a estar si recibimos o no una llave primaria por parte del formulario
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void guardarUsuario(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        boolean elementoGuardado = false;
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Procesamos los parametros
        Usuario usuario = this.populateUsuario(request, response);
        // Validamos que no exista el username en sistema
        int idUsuario = (usuario.getIdUsuario() != null)
                ? usuario.getIdUsuario().intValue()
                : IGlobalConstant.INT_ZERO;
        UsuarioService usuarioService = UsuarioServiceImpl.getInstance();
        Usuario usuarioAux = usuarioService.getUsuarioByFullName(usuario.getUsername());
        if (usuarioAux == null) {
            elementoGuardado = validarPersona(usuarioService, usuario);
        }
        else if (usuarioAux.getIdUsuario() == idUsuario) {
            elementoGuardado = validarPersona(usuarioService, usuario);
        }
        else {
            mensaje = "Ya existe el nombre de Usuario en sistema, verifique los datos";
        }
        // Redireccionamos dependiendo del resultado
        if (elementoGuardado) {
            mensaje = "Se guard&oacute; el usuario correctamente";
            messageType = IGlobalConstant.INT_ONE;
            // Reutilizamos el caso de listarUsuarios
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            this.listarUsuarios(request, response);
        }
        else {
            mensaje = (mensaje != null && !mensaje.trim().equals(""))
                    ? mensaje
                    : "No existe la persona en sistema o ya se encuentra asociada " +
                    		"a otro usuario, verifique los datos";
        }
        // compartimos el objeto Usuario, para poderlo modificar
        request.setAttribute("user", usuario);
        request.setAttribute("type", messageType);
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("/WEB-INF/pages/sga/detalleUsuario.jsp").forward(
                request, response);
    }

    /**
     * Metodo que valida si existe una persona en sistema y almacena un Usuario
     * asociado a esta si existe
     * 
     * @param usuarioService
     * @param usuario
     * @return
     */
    private boolean validarPersona(UsuarioService usuarioService,
            Usuario usuario) {
        // Verificamos que exista la persona en BD
        PersonaService personaService = PersonaServiceImpl.getInstance();
        if (personaService.getPersonaById(usuario.getIdPersona()) != null) {
            // Validamos que no esté asociada a otro usuario
            Usuario usuarioAux = usuarioService.getUsuarioByPersona(usuario.getIdPersona());
            if (usuarioAux != null) {
                return (usuarioAux.getIdUsuario() == usuario.getIdUsuario())
                        ? usuarioService.guardarUsuario(usuario)
                        : false;
            }
            else {
                return usuarioService.guardarUsuario(usuario);
            }
        }
        else {
            return false;
        }
    }

    /**
     * Metodo encargado de Poblar un objeto de tipo Usuario con los valores
     * recibidos por parametros para ser manipulados por las capas de servicios,
     * datos y Web
     * 
     * @param request
     * @param response
     * @return Usuario
     * @throws ServletException
     * @throws IOException
     */
    private Usuario populateUsuario(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Procesamos los parametros
        String idUsuarioParam = request.getParameter("idUsuario");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String idPersona = request.getParameter("idPersona");
        String idRol = request.getParameter("idRol");
        // Utilizamos el objeto Modelo
        Usuario usuario = new Usuario();
        // Validamos la PK
        // si el idUsuario no venia en los parametros, se coloca null
        Integer idUsuario = null;
        if (idUsuarioParam != null && !idUsuarioParam.trim().equals("")) {
            idUsuario = new Integer(idUsuarioParam);
        }
        usuario.setIdUsuario(idUsuario);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setIdPersona(idPersona);
        usuario.setIdRol(new Integer(idRol));
        return usuario;
    }

}
