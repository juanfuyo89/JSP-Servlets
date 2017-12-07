/**
 * 
 */
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
import sga.eis.dto.Persona;
import sga.services.PersonaService;
import sga.services.impl.PersonaServiceImpl;
import sga.web.utilities.IGlobalConstant;

/**
 * Clase que Implementa un Controlador de Tipo Persona
 * 
 * @author Juan Carlos Fuyo
 */
public class PersonaController {

    /**
     * Utilizamos el patron singleton, solo existen un objeto de tipo
     * PersonaController en Memoria
     */
    private static PersonaController controlPersona;

    /**
     * Constructor sin argumentos privado, para implementar el patron singleton
     */
    private PersonaController() {
    }

    /**
     * Metodo que Crea una nueva y unica instancia de PersonaController si es
     * que no existe
     * 
     * @return the controlPersona (Controlador de Personas)
     */
    public static PersonaController getInstance() {
        if (controlPersona == null) {
            controlPersona = new PersonaController();
        }
        return controlPersona;
    }

    /**
     * Metodo para procesar el caso de uso de listarPersonas
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void listarPersonas(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Recuperamos las personas utilizando el servicio de Personas
        PersonaService personaService = PersonaServiceImpl.getInstance();
        List<Persona> personas = personaService.getAllPersonas();
        // Si se encontraron personas, las compartimos en la pagina Web
        if (personas != null && personas.size() > 0) {
            request.setAttribute("listaPersonas", personas);
        }
        request.getRequestDispatcher("/WEB-INF/pages/sga/listadoPersonas.jsp").forward(
                request, response);
    }

    /**
     * Metodo encargado de mostrar una persona en el listado de personas de
     * acuerdo a una busqueda por Cedula
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void mostrarPersona(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String cedula = request.getParameter("cedula");
        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;
        // Recuperamos las personas utilizando el servicio de Personas
        PersonaService personaService = PersonaServiceImpl.getInstance();
        Persona persona = personaService.getPersonaById(cedula);
        // Si no se encuentra la persona retornamos
        // con un mensaje indicando lo sucedido
        if (persona != null) {
            // Enviamos el valor modificar en 1 para que la capa de servicios
            // modifique y no
            // inserte
            request.setAttribute("modificar", IGlobalConstant.INT_ONE);
            List<Persona> personas = new ArrayList<Persona>();
            personas.add(persona);
            // compartimos el objeto persona obtenido, para poderlo modificar
            request.setAttribute("listaPersonas", personas);
            request.getRequestDispatcher(
                    "/WEB-INF/pages/sga/listadoPersonas.jsp").forward(request,
                    response);
        }
        request.setAttribute("type", messageType);
        mensaje = "No se encuentra la persona buscada";
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("/WEB-INF/pages/sga/buscarPersona.jsp").forward(
                request, response);
    }

    /**
     * Metodo encarcado de redireccionar a la pagina de Busqueda de Personas por
     * Cedula
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void buscarPersona(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Solo redireccionamos a la página de busqueda de personas por cédula
        request.getRequestDispatcher("/WEB-INF/pages/sga/buscarPersona.jsp").forward(
                request, response);
    }

    /**
     * Metodo que agrega una persona en el sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void agregarPersona(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Asignamos 0 (Falso) a la variable modificar, ya que en la capa de
        // servicios se usará
        // para determinar si está insertando o actualizando
        int modificar = IGlobalConstant.INT_ZERO;
        request.setAttribute("modificar", modificar);
        request.getRequestDispatcher("/WEB-INF/pages/sga/detallePersona.jsp").forward(
                request, response);
    }

    /**
     * Metodo que edita una persona en el sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void editarPersona(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;

        // Recuperamos el identificador a procesar y reenviamos a la pagina de
        // detalle
        String cedula = request.getParameter("personas");
        if (cedula != null && !cedula.trim().equals("")) {
            // Utilizamos el servicio de persona para recuperar el objeto de la
            // BD
            PersonaService personaService = PersonaServiceImpl.getInstance();
            Persona persona = personaService.getPersonaById(cedula);

            // compartimos el objeto persona obtenido, para poderlo modificar
            request.setAttribute("persona", persona);
            // Asignamos 1 (Verdadero) a la variable modificar, ya que en la
            // capa de servicios se usará
            // para determinar si está insertando o actualizando
            int modificar = IGlobalConstant.INT_ONE;
            request.setAttribute("modificar", modificar);
            request.getRequestDispatcher(
                    "/WEB-INF/pages/sga/detallePersona.jsp").forward(request,
                    response);
        }
        else {
            mensaje = "Debe seleccionar un elemento a Editar";
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            // Reutilizamos el caso de listarPersonas
            this.listarPersonas(request, response);
        }

    }

    /**
     * Metodo que elimina una persona del sistema
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void eliminarPersona(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;

        // 1. Procesamos los parametros
        // Recuperamos TODOS los objetos seleccionados
        String[] idPersonaParams = request.getParameterValues("personas");
        System.out.println("ID Persona: " + idPersonaParams[0]);
        List<String> idPersonas = new ArrayList<String>();
        // 2. Utilizamos los objetos de Modelo (Persona)
        // Validamos los parametros a eliminar
        if (idPersonaParams != null && idPersonaParams.length > 0) {
            for (String persona : idPersonaParams) {
                idPersonas.add(persona);
            }
            // 3.Utilizamos la capa de servicio para eliminar los objetos
            PersonaService personaService = PersonaServiceImpl.getInstance();
            boolean registrosEliminados = personaService.eliminarPersonas(idPersonas);

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
        this.listarPersonas(request, response);
    }

    /**
     * Este metodo nos permite insertar o modificar una persona La diferencia va
     * a estar si recibimos o no una llave primaria por parte del formulario
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void guardarPersona(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String mensaje = null;
        int messageType = IGlobalConstant.INT_ZERO;

        // 1. Procesamos los parametros
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String apePaterno = request.getParameter("apePaterno");
        String apeMaterno = request.getParameter("apeMaterno");
        String genero = request.getParameter("genero");
        String fecNacimiento = request.getParameter("fecNacimiento");
        int modificar = Integer.parseInt(request.getParameter("modificar"));
        // 2. Utilizamos el objeto Modelo
        Persona persona = new Persona();
        persona.setCedula(cedula);
        persona.setNombre(nombre);
        persona.setApePaterno(apePaterno);
        persona.setApeMaterno(apeMaterno);
        persona.setGenero(validarGenero(genero));
        // 3. Formateamos la fecha que recibimos como un String
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            persona.setFecNacimiento(formatter.parse(fecNacimiento));

            // 4. Utilizamos la capa de servicio
            PersonaService personaService = PersonaServiceImpl.getInstance();
            boolean elementoGuardado = personaService.guardarPersona(persona,
                    modificar);

            // 5. Redireccionamos dependiendo del resultado
            if (elementoGuardado) {
                messageType = IGlobalConstant.INT_ONE;
                mensaje = "Se guard&oacute; el elemento correctamente";
                request.setAttribute("mensaje", mensaje);

            }
            else {
                mensaje = "No se guardo correctamente el elemento";
                request.setAttribute("mensaje", mensaje);
            }

        }
        catch (ParseException e) {
            mensaje = "Formato de fecha no valido, debe tener el formato (yyyy-mm-dd)";
            request.setAttribute("type", messageType);
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher(
            "/WEB-INF/pages/sga/detallePersona.jsp").forward(request,
            response);
        }

        // 6. Reutilizamos el caso de listarPersonas
        request.setAttribute("type", messageType);
        request.setAttribute("mensaje", mensaje);
        this.listarPersonas(request, response);

    }

    /**
     * Metodo encargado de validar el parametro Genero
     * 
     * @param genero
     * @return
     */
    private String validarGenero(String genero) {
        if (genero.length() > 1) {
            genero = genero.substring(IGlobalConstant.INT_ZERO,
                    IGlobalConstant.INT_ONE);
        }
        return genero;
    }

}
