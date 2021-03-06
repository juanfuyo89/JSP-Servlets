package sga.eis.jdbc.connect;

import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

/**
 * Clase encargada de administrar los recursos de conexi�n a la BD
 * 
 * @author Juan Carlos Fuyo
 */
public class ResourceManager {

    private static String JDBC_DRIVER;                    
    private static String JDBC_URL;                       
    private static String JDBC_USER;                      
    private static String JDBC_PASS;                      
    private static int    MAX_POOL_SIZE;
    private static int    IDLE_TIME_POOL;
    private static String JDBC_FILE_NAME = "ConexionJDBC";
    
    /**
     * Metodo que retorna una conexi�n del Pool
     * 
     * @return Connection
     * @throws SQLException
     */
    public static synchronized Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    /** 
     * M�todo que retorna un DataSource considerando valores de un pool de
     * conexiones
     * 
     * @return DataSource
     */
    private static DataSource getDataSource() {
        // Cargamos los valores del archivo de propiedades
        loadProperties(JDBC_FILE_NAME);
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(JDBC_DRIVER);
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASS);
        // Definimos el tamaño del pool de conexiones
        ds.setMaxActive(MAX_POOL_SIZE);// en este caso son 20
        // conexiones abiertas
        ds.setMaxIdle(IDLE_TIME_POOL);// definimos el tiempo de espera
        // antes de cerrar la conexion
        return ds;
    }

    /**
     * M�todo para carga los valores de la conexion a BD desde un archivo de
     * propidades
     * 
     * @param file
     * @return Properties
     */
    private static Properties loadProperties(String file) {
        Properties prop = new Properties();
        ResourceBundle bundle = ResourceBundle.getBundle(file);
        Enumeration<?> e = bundle.getKeys();
        String key = null;
        while (e.hasMoreElements()) {
            key = (String) e.nextElement();
            prop.put(key, bundle.getObject(key));
        }
        // Asignamos los valores del archivo de propiedades
        // a las variables de la clase
        JDBC_DRIVER = prop.getProperty("driver");
        JDBC_URL = prop.getProperty("url");
        JDBC_USER = prop.getProperty("user");
        JDBC_PASS = prop.getProperty("pass");
        MAX_POOL_SIZE = Integer.parseInt(prop.getProperty("max_pool_size"));
        IDLE_TIME_POOL = Integer.parseInt(prop.getProperty("idle_time"));
        // Regresamos el objeto properties con los valores
        // de la conexion a la BD
        return prop;
    }

    /**
     * Cierra una conexi�n al Pool
     * 
     * @param conn
     */
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * Cierra una PreparedStatement
     * 
     * @param stmt
     */
    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * Cierra un ResulSet
     * 
     * @param rs
     */
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }
}
