/**
 * 
 */
package sga.web.utilities;

/**
 * Interface que define las constantes globales que se utilizan en las capas Web
 * y Servicios
 * 
 * @author Juan Carlos Fuyo González
 */
public interface IGlobalConstant {

    /** Numero 0 en formato Int. */
    int    INT_ZERO            = 0;
    /** Numero 1 en formato Int. */
    int    INT_ONE             = 1;
    /** Numero 2 en formato Int. */
    int    INT_TWO             = 2;
    /** Numero 3 en formato Int. */
    int    INT_THREE           = 3;
    /** Numero 4 en formato Int. */
    int    INT_FOUR            = 4;
    /** Numero 5 en formato Int. */
    int    INT_FIVE            = 5;
    /** Numero 6 en formato Int. */
    int    INT_SIX             = 6;
    /** Numero 7 en formato Int. */
    int    INT_SEVEN           = 7;
    /** Numero 8 en formato Int. */
    int    INT_EIGTH           = 8;
    /** Numero 9 en formato Int. */
    int    INT_NINE            = 9;
    /** Numero 10 en formato Int. */
    int    INT_TEN             = 10;
    /** Numero 11 en formato Int. */
    int    INT_ELEVEN          = 11;
    /** Numero 12 en formato Int. */
    int    INT_TWELVE          = 12;
    /** Numero 13 en formato Int. */
    int    INT_THIRTEEN        = 13;
    /** Numero 14 en formato Int. */
    int    INT_FORTEEN         = 14;
    /** Numero 15 en formato Int. */
    int    INT_FIFTEEN         = 15;
    /** Numero 16 en formato Int. */
    int    INT_SIXTEEN         = 16;
    /** Numero 17 en formato Int. */
    int    INT_SEVENTEEN       = 17;
    /** Numero 18 en formato Int. */
    int    INT_EIGHTEEN        = 18;
    /** Numero 19 en formato Int. */
    int    INT_NINETEEN        = 19;
    /** Numero 20 en formato Int. */
    int    INT_TWENTY          = 20;

    /** Controlador de tipo Persona. */
    int    PERSONA_TYPE        = 1;
    /** Controlador de tipo Usuario. */
    int    USUARIO_TYPE        = 2;

    /** Numero 0 en formato String. */
    String STRING_ZERO         = "0";
    /** Numero 1 en formato String. */
    String STRING_ONE          = "1";
    /** Numero 00 en formato String. */
    String STRING_ZERO_ZERO    = "00";
    /** Numero 01 en formato String. */
    String STRING_ZERO_ONE     = "01";
    /** Numero 02 en formato String. */
    String STRING_ZERO_TWO     = "02";

    /** NNombre de la tabla Usuarios en la BD. */
    String TABLE_USUARIOS      = "Usuarios";

    /* Constantes para manejar las acciones del Servlet Controlador */
    /** Opción Listar **/
    String LISTAR_PERSONAS     = "listarPersonas";
    String LISTAR_USUARIOS     = "listarUsuarios";
    String LISTAR_PRODUCTOS    = "listarProductos";
    String LISTAR_TIPOS        = "listarTipos";
    String LISTAR_MARCAS       = "listarMarcas";
    String LISTAR_PDV          = "listarPuntosVenta";
    String LISTAR_PRODXPDV     = "listarProductosPuntoVenta";
    String LISTAR_VENTAS       = "listarVentas";
    /** Opción Buscar **/
    String BUSCAR_PERSONA      = "buscarPersona";
    String BUSCAR_USUARIO      = "buscarUsuario";
    String BUSCAR_PRODUCTO     = "buscarProducto";
    String BUSCAR_TIPO         = "buscarTipo";
    String BUSCAR_MARCA        = "buscarMarca";
    String BUSCAR_PDV          = "buscarPuntoVenta";
    String BUSCAR_PRODXPDV     = "buscarProductosPuntoVenta";
    String BUSCAR_VENTA        = "buscarVenta";
    /** Opción Mostrar **/
    String MOSTRAR_PERSONA     = "mostrarPersona";
    String MOSTRAR_USUARIO     = "mostrarUsuario";
    String MOSTRAR_PRODUCTO    = "mostrarProducto";
    String MOSTRAR_TIPO        = "mostrarTipo";
    String MOSTRAR_MARCA       = "mostrarMarca";
    String MOSTRAR_PDV         = "mostrarPuntoVenta";
    String MOSTRAR_PRODXPDV    = "mostrarProductosPuntoVenta";
    String MOSTRAR_VENTA       = "mostrarVenta";
    String MOSTRAR_HIST_VENTAS = "mostrarHistoricoVentas";
    /** Opción Agregar **/
    String AGREGAR_PERSONA     = "agregarPersona";
    String AGREGAR_USUARIO     = "agregarUsuario";
    String AGREGAR_PRODUCTO    = "agregarProducto";
    String AGREGAR_TIPO        = "agregarTipo";
    String AGREGAR_MARCA       = "agregarMarca";
    String AGREGAR_PDV         = "agregarPuntoVenta";
    String VER_DET_PROD        = "verDetallesProducto";
    String MODIF_PRODXPDV      = "modificarProductosPuntoVenta";
    /** Opción Editar **/
    String EDITAR_PERSONA      = "editarPersona";
    String EDITAR_USUARIO      = "editarUsuario";
    String EDITAR_PRODUCTO     = "editarProducto";
    String EDITAR_TIPO         = "editarTipo";
    String EDITAR_MARCA        = "editarMarca";
    String EDITAR_PDV          = "editarPuntoVenta";
    String EDITAR_PRODXPDV     = "editarProductosPuntoVenta";
    String EDITAR_VENTA        = "editarVenta";
    /** Opción Eliminar **/
    String ELIMINAR_PERSONA    = "eliminarPersona";
    String ELIMINAR_USUARIO    = "eliminarUsuario";
    String ELIMINAR_PRODUCTO   = "eliminarProducto";
    String ELIMINAR_TIPO       = "eliminarTipo";
    String ELIMINAR_MARCA      = "eliminarMarca";
    String ELIMINAR_PDV        = "eliminarPuntoVenta";
    String ELIMINAR_VENTA      = "eliminarVenta";
    /** Opción Guardar **/
    String GUARDAR_PERSONA      = "guardarPersona";
    String GUARDAR_USUARIO      = "guardarUsuario";
    String GUARDAR_PRODUCTO     = "guardarProducto";
    String GUARDAR_TIPO         = "guardarTipo";
    String GUARDAR_MARCA        = "guardarMarca";
    String GUARDAR_PDV          = "guardarPuntoVenta";
    String GUARDAR_PRODXPDV     = "guardarProductosPuntoVenta";
    String GUARDAR_VENTA        = "guardarVenta";
    /** Opción Guardar **/
    String SALIR                = "salir";

}
