package sga.eis.jdbc;

import sga.eis.dao.*;
import sga.eis.dto.*;
import sga.eis.exceptions.*;
import sga.eis.jdbc.connect.ResourceManager;
import sga.eis.jdbc.utilities.IDaoConstants;
import java.sql.Connection;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que implementa los metodos de consulta y modificación de la tabla
 * Personas de la BD
 * 
 * @author Juan Carlos Fuyo
 */
public class PersonaDaoImpl extends AbstractDAO implements PersonaDao {

    /**
     * Atributo de Conexión a la BD.
     */
    private Connection         userConn;

    /**
     * los metodos de consulta pasaran este valor al metodo setMaxRows de JDBC
     */
    protected int              maxRows;

    /**
     * Todos los metodos de consulta en esta clase usan esta constante SELECT
     * para construir sus queries
     */
    protected final String     SQL_SELECT        = "SELECT * FROM "
                                                         + getTableName() + "";

    /**
     * Sentencia SQL INSERT para esta tabla
     */
    protected final String     SQL_INSERT        = "INSERT INTO "
                                                         + getTableName()
                                                         + " (cedula,nombre,apellido_paterno,apellido_materno,genero,fecha_nacimiento) VALUES ( ?, ?, ?, ?, ?, ? )";

    /**
     * Sentencia SQL UPDATE para esta tabla
     */
    protected final String     SQL_UPDATE        = "UPDATE "
                                                         + getTableName()
                                                         + " SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, genero = ?, fecha_nacimiento = ? WHERE cedula = ?";

    /**
     * Sentencia SQL DELETE para esta tabla
     */
    protected final String     SQL_DELETE        = "DELETE FROM "
                                                         + getTableName()
                                                         + " WHERE cedula = ?";

    /**
     * Indice de la columna cedula
     */
    protected static final int COLUMN_CEDULA     = 1;

    /**
     * Indice de la columna nombre
     */
    protected static final int COLUMN_NOMBRE     = 2;

    /**
     * Indice de la columna apellido_paterno
     */
    protected static final int COLUMN_APEPATERNO = 3;

    /**
     * Indice de la columna apellido_materno
     */
    protected static final int COLUMN_APEMATERNO = 4;

    /**
     * Indice de la columna apellido_paterno
     */
    protected static final int COLUMN_GENERO     = 5;

    /**
     * Indice de la columna apellido_paterno
     */
    protected static final int COLUMN_FECHANAC   = 6;

    /**
     * Numero de columnas
     */
    protected static final int NUMBER_OF_COLUMNS = 6;

    /**
     * Indice de la primary-key cedula
     */
    protected static final int PK_COLUMN_CEDULA  = 1;

    /**
     * Constructor vacio
     */
    public PersonaDaoImpl() {
    }

    /**
     * Constructor con parametros
     * 
     * @param userConn
     */
    public PersonaDaoImpl(final java.sql.Connection userConn) {
        this.userConn = userConn;
    }

    /**
     * Inserta un nuevo registro en la tabla Personas.
     */
    public void insert(Persona dto) throws PersonaDaoException {

        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;

        try {
            // Validamos la conexion
            this.validateConnection();
            stmt = userConn.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            stmt.setString(index++, dto.getCedula());
            stmt.setString(index++, dto.getNombre());
            stmt.setString(index++, dto.getApePaterno());
            stmt.setString(index++, dto.getApeMaterno());
            stmt.setString(index++, dto.getGenero());
            stmt.setDate(index, new java.sql.Date(
                    dto.getFecNacimiento().getTime()));
            System.out.println("Executing " + SQL_INSERT + " with DTO: " + dto);
            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
            dto.createPk();
        }
        catch (Exception _e) {
            _e.printStackTrace();
            throw new PersonaDaoException("Exception: " + _e.getMessage(), _e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Actualiza un unico registro en la tabla Personas.
     */
    public void update(PersonaPk pk, Persona dto) throws PersonaDaoException {

        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;

        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_UPDATE + " with DTO: " + dto);
            stmt = userConn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, dto.getNombre());
            stmt.setString(index++, dto.getApePaterno());
            stmt.setString(index++, dto.getApeMaterno());
            stmt.setString(index++, dto.getGenero());
            stmt.setDate(index++, new java.sql.Date(
                    dto.getFecNacimiento().getTime()));
            if (pk.getCedula() != null) {
                stmt.setString(index, dto.getCedula());
            }
            else {
                stmt.setNull(index, java.sql.Types.VARCHAR);
            }

            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
        }
        catch (Exception _e) {
            _e.printStackTrace();
            throw new PersonaDaoException("Exception: " + _e.getMessage(), _e);
        }
        finally {
            ResourceManager.close(stmt);
        }

    }

    /**
     * Elimina un unico registro en la tabla Personas.
     */
    public void delete(PersonaPk pk) throws PersonaDaoException {

        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;

        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_DELETE + " with PK: " + pk);
            stmt = userConn.prepareStatement(SQL_DELETE);
            if (pk.getCedula() != null) {
                stmt.setString(1, pk.getCedula());
            }
            else {
                stmt.setNull(1, java.sql.Types.VARCHAR);
            }

            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
        }
        // Agregamos esta validacion para poder recueparar la excepcion si es
        // que ocurrio un error por integridad referencial
        catch (Exception _e) {
            _e.printStackTrace();
            throw new PersonaDaoException("Exception: " + _e.getMessage(), _e);
        }
        finally {
            ResourceManager.close(stmt);
        }

    }

    /**
     * Retorna un unico registro en la tabla Personas que conicida con la
     * primary-key especificada.
     */
    public Persona findByPrimaryKey(PersonaPk pk) throws PersonaDaoException {
        return findByPrimaryKey(pk.getCedula());
    }

    /**
     * Retorna un registro de la tabla Personas que coincida con el criterio
     * 'cedula = :cedula'.
     */
    public Persona findByPrimaryKey(String cedula) throws PersonaDaoException {
        Persona ret[] = findByDynamicSelect(SQL_SELECT + " WHERE cedula = ?",
                new Object[] { cedula });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna todas las filas de la tabla Personas.
     */
    public Persona[] findAll() throws PersonaDaoException {
        return findByDynamicSelect(SQL_SELECT + " ORDER BY cedula", null);
    }

    /**
     * Retorna todos los registros de la tabla Usuarios que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    public Persona[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws PersonaDaoException {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + sql);
            // prepare statement
            stmt = userConn.prepareStatement(sql);
            stmt.setMaxRows(maxRows);

            // seteamos parametros
            for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
                stmt.setObject(i + 1, sqlParams[i]);
            }

            rs = stmt.executeQuery();

            // Recuperamos el resultado
            return fetchMultiResults(rs);
        }
        catch (Exception _e) {
            _e.printStackTrace();
            throw new PersonaDaoException("Exception: " + _e.getMessage(), _e);
        }
        finally {
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            if (userConn != null) {
                ResourceManager.close(userConn);
            }

        }

    }

    /**
     * Retorna todos los registros de la tabla Usuarios que coincidan con el
     * WHERE SQL especificado arbitrariamente
     */
    public Persona[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws PersonaDaoException {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Validamos la conexion
            this.validateConnection();
            // construimos la consulta SQL completa
            final String SQL = SQL_SELECT + " WHERE " + sql;

            System.out.println("Executing " + SQL);
            // prepare statement
            stmt = userConn.prepareStatement(SQL);
            stmt.setMaxRows(maxRows);

            // seteamos parametros
            for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
                stmt.setObject(i + 1, sqlParams[i]);
            }

            rs = stmt.executeQuery();

            // recuperamos resultados
            return fetchMultiResults(rs);
        }
        catch (Exception _e) {
            _e.printStackTrace();
            throw new PersonaDaoException("Exception: " + _e.getMessage(), _e);
        }
        finally {
            ResourceManager.close(rs);
            ResourceManager.close(stmt);
            if (userConn != null) {
                ResourceManager.close(userConn);
            }

        }

    }

    /**
     * Recupera una unica fila desde el result set especificado
     */
    protected Persona fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Persona dto = new Persona();
            populateDto(dto, rs);
            return dto;
        }
        else {
            return null;
        }

    }

    /**
     * Recupera multiples filas desde el result set especificado
     */
    protected Persona[] fetchMultiResults(ResultSet rs) throws SQLException {
        Collection<Persona> resultList = new ArrayList<Persona>();
        while (rs.next()) {
            Persona dto = new Persona();
            populateDto(dto, rs);
            resultList.add(dto);
        }

        Persona ret[] = new Persona[resultList.size()];
        resultList.toArray(ret);
        return ret;
    }

    /**
     * Se encarga de poblar un DTO con datos de un ResultSet especificado
     */
    protected void populateDto(Persona dto, ResultSet rs) throws SQLException {
        dto.setCedula(rs.getString(COLUMN_CEDULA));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setApePaterno(rs.getString(COLUMN_APEPATERNO));
        dto.setApeMaterno(rs.getString(COLUMN_APEMATERNO));
        dto.setGenero(rs.getString(COLUMN_GENERO));
        dto.setFecNacimiento(rs.getDate(COLUMN_FECHANAC));
    }

    /**
     * Metodo encargado de validar que la conexión sea valida al momento de
     * usarla
     */
    public void validateConnection() {
        // Si la conexion es aún valida la usamos, de los contrario la traemos
        // de ResourceManager
        try {
            userConn = (userConn != null)
                    ? userConn
                    : ResourceManager.getConnection();
            userConn = (!userConn.isClosed())
                    ? userConn
                    : ResourceManager.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Agregamos estos metodos para poder recuperar o setear una conexion
    // externa
    /**
     * @return the userConn
     */
    public java.sql.Connection getUserConn() {
        return userConn;
    }

    /**
     * @param userConn the userConn to set
     */
    public void setUserConn(java.sql.Connection userConn) {
        this.userConn = userConn;
    }

    /**
     * Sets the value of maxRows
     */
    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    /**
     * Gets the value of maxRows
     */
    public int getMaxRows() {
        return maxRows;
    }

    /**
     * Metodo que retorna el nombre de la tabla de BD
     * 
     * @return String
     */
    public String getTableName() {
        return IDaoConstants.TABLE_PERSONAS;
    }

}
