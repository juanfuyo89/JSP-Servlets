package sga.eis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import sga.eis.dao.PuntoVentaDao;
import sga.eis.dto.PuntoVenta;
import sga.eis.dto.PuntoVentaPk;
import sga.eis.exceptions.PuntoVentaDaoException;
import sga.eis.jdbc.connect.ResourceManager;
import sga.eis.jdbc.utilities.IDaoConstants;

/**
 * Clase que implementa los metodos de consulta y modificación de la tabla
 * Puntos_venta de la BD
 * 
 * @author Juan Carlos Fuyo
 */
public class PuntoVentaDaoImpl implements PuntoVentaDao {

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
    protected final String     SQL_SELECT        = "SELECT id_pdv, nombre_pdv, direccion, telefono, id_admin FROM "
                                                         + getTableName() + "";

    /**
     * Sentencia SQL INSERT para esta tabla
     */
    protected final String     SQL_INSERT        = "INSERT INTO "
                                                         + getTableName()
                                                         + " ( nombre_pdv, direccion, telefono, id_admin ) VALUES ( ?, ?, ?, ? )";

    /**
     * Sentencia SQL UPDATE para esta tabla
     */
    protected final String     SQL_UPDATE        = "UPDATE "
                                                         + getTableName()
                                                         + " SET  nombre_pdv=?, direccion=?, telefono=?, id_admin=? WHERE id_pdv=?";

    /**
     * Sentencia SQL DELETE para esta tabla
     */
    protected final String     SQL_DELETE        = "DELETE FROM "
                                                         + getTableName()
                                                         + " WHERE id_pdv = ?";

    /**
     * Indice de la columna id_pdv
     */
    protected static final int COLUMN_ID_PDV     = 1;

    /**
     * Indice de la columna nombre_pdv
     */
    protected static final int COLUMN_NOMBRE_PDV = 2;

    /**
     * Indice de la columna direccion
     */
    protected static final int COLUMN_DIRECCION  = 3;

    /**
     * Indice de la columna telefono
     */
    protected static final int COLUMN_TELEFONO   = 4;

    /**
     * Indice de la columna id_admin
     */
    protected static final int COLUMN_ID_ADMIN   = 5;

    /**
     * Numero de columnas
     */
    protected static final int NUMBER_OF_COLUMNS = 5;

    /**
     * Indice de la primary-key id_pdv
     */
    protected static final int PK_COLUMN_ID_PDV  = 1;

    /**
     * Constructor vacio de la clase
     */
    public PuntoVentaDaoImpl() {
    }

    /**
     * Constructor conparametros de la clase
     * 
     * @param userConn
     */
    public PuntoVentaDaoImpl(final java.sql.Connection userConn) {
        this.userConn = userConn;
    }

    /**
     * Inserta un nuevo registro en la tabla Puntos_venta.
     */
    @Override
    public PuntoVentaPk insert(PuntoVenta dto) throws PuntoVentaDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            stmt = userConn.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            stmt.setString(index++, dto.getNombre());
            stmt.setString(index++, dto.getDireccion());
            stmt.setString(index++, dto.getTelefono());
            if (dto.getIdAdmin() != null) {
                stmt.setString(index, dto.getIdAdmin());
            }
            else {
                stmt.setNull(index, java.sql.Types.VARCHAR);
            }
            System.out.println("Executing " + SQL_INSERT + " with DTO: " + dto);
            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
            // retorna valores de las columnas auto-increment
            rs = stmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                dto.setIdPuntoVenta(new Integer(rs.getInt(1)));
            }
            reset(dto);
            return dto.createPk();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new PuntoVentaDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Actualiza un unico registro en la tabla Puntos_venta.
     */
    @Override
    public void update(PuntoVentaPk pk, PuntoVenta dto)
            throws PuntoVentaDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_UPDATE + " with DTO: " + dto);
            stmt = userConn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, dto.getNombre());
            stmt.setString(index++, dto.getDireccion());
            stmt.setString(index++, dto.getTelefono());
            if (dto.getIdAdmin() != null) {
                stmt.setString(index++, dto.getIdAdmin());
            }
            else {
                stmt.setNull(index++, java.sql.Types.VARCHAR);
            }
            stmt.setInt(index, pk.getIdPuntoVenta().intValue());
            int rows = stmt.executeUpdate();
            reset(dto);
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new PuntoVentaDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Elimina un unico registro en la tabla Puntos_venta.
     */
    @Override
    public void delete(PuntoVentaPk pk) throws PuntoVentaDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_DELETE + " with PK: " + pk);
            stmt = userConn.prepareStatement(SQL_DELETE);
            if (pk.getIdPuntoVenta() != null) {
                stmt.setInt(IDaoConstants.INT_ONE,
                        pk.getIdPuntoVenta().intValue());
            }
            else {
                stmt.setNull(IDaoConstants.INT_ONE, java.sql.Types.INTEGER);
            }
            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new PuntoVentaDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Retorna un unico registro en la tabla Puntos_venta que conicida con la
     * primary-key especificada.
     */
    @Override
    public PuntoVenta findByPrimaryKey(PuntoVentaPk pk)
            throws PuntoVentaDaoException {
        return findByPrimaryKey(pk.getIdPuntoVenta());
    }

    /**
     * Retorna un registro de la tabla Puntos_venta que coincida con el criterio
     * 'id_PuntoVenta = :idPuntoVenta'.
     */
    @Override
    public PuntoVenta findByPrimaryKey(Integer idPuntoVenta)
            throws PuntoVentaDaoException {
        PuntoVenta[] ret = findByDynamicSelect(
                SQL_SELECT + " WHERE id_pdv = ?", new Object[] { idPuntoVenta });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna los registros de la tabla Puntos_venta que coincida con el
     * criterio 'nombre_pdv = :nombre'.
     */
    @Override
    public PuntoVenta[] findByName(String nombre) throws PuntoVentaDaoException {
        return findByDynamicSelect(SQL_SELECT + " WHERE nombre_pdv LIKE ?",
                new Object[] { "%" + nombre + "%" });
    }

    /**
     * Retorna todas las filas de la tabla Puntos_venta.
     */
    @Override
    public PuntoVenta[] findAll() throws PuntoVentaDaoException {
        return findByDynamicSelect(SQL_SELECT + " ORDER BY id_pdv", null);
    }

    /**
     * Retorna todos los registros de la tabla Puntos_venta que coincidan con el
     * criterio 'id_admin = :idPersona'.
     */
    @Override
    public PuntoVenta findByAdmin(String idPersona)
            throws PuntoVentaDaoException {
        PuntoVenta[] ret = findByDynamicSelect(SQL_SELECT
                + " WHERE id_admin = ?", new Object[] { idPersona });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna todos los registros de la tabla Puntos_venta que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    @Override
    public PuntoVenta[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws PuntoVentaDaoException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + sql);
            // prepare statement
            stmt = userConn.prepareStatement(sql);
            stmt.setMaxRows(maxRows);
            // se setean los parametros de la consulta
            for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
                stmt.setObject(i + 1, sqlParams[i]);
            }
            System.out.println(sql);
            rs = stmt.executeQuery();
            // recuperamos los resultados
            return fetchMultiResults(rs);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new PuntoVentaDaoException("Exception: " + e.getMessage(), e);
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
     * Retorna todos los registros de la tabla Puntos_venta que coincidan con el
     * WHERE SQL especificado arbitrariamente
     */
    @Override
    public PuntoVenta[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws PuntoVentaDaoException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            // construct the SQL statement
            final String SQL = SQL_SELECT + " WHERE " + sql;
            System.out.println("Executing " + SQL);
            // prepare statement
            stmt = userConn.prepareStatement(SQL);
            stmt.setMaxRows(maxRows);
            // se setean los parametros de la consulta
            for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
                stmt.setObject(i + 1, sqlParams[i]);
            }
            rs = stmt.executeQuery();
            // recuperamos los resultados
            return fetchMultiResults(rs);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new PuntoVentaDaoException("Exception: " + e.getMessage(), e);
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
    protected PuntoVenta fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            PuntoVenta dto = new PuntoVenta();
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
    protected PuntoVenta[] fetchMultiResults(ResultSet rs) throws SQLException {
        Collection<PuntoVenta> resultList = new ArrayList<PuntoVenta>();
        while (rs.next()) {
            PuntoVenta dto = new PuntoVenta();
            populateDto(dto, rs);
            resultList.add(dto);
        }
        PuntoVenta ret[] = new PuntoVenta[resultList.size()];
        resultList.toArray(ret);
        return ret;
    }

    /**
     * Se encarga de poblar un DTO con datos de un ResultSet especificado
     */
    protected void populateDto(PuntoVenta dto, ResultSet rs)
            throws SQLException {
        dto.setIdPuntoVenta(new Integer(rs.getInt(COLUMN_ID_PDV)));
        dto.setNombre(rs.getString(COLUMN_NOMBRE_PDV));
        dto.setDireccion(rs.getString(COLUMN_DIRECCION));
        dto.setTelefono(rs.getString(COLUMN_TELEFONO));
        dto.setIdAdmin(rs.getString(COLUMN_ID_ADMIN));
    }

    /**
     * Restablece los atributos modificados en el DTO
     */
    protected void reset(PuntoVenta dto) {
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

    /**
     * @return the userConn
     */
    @Override
    public Connection getUserConn() {
        return userConn;
    }

    /**
     * @param userConn the userConn to set
     */
    @Override
    public void setUserConn(Connection userConn) {
        this.userConn = userConn;
    }

    /**
     * @return the maxRows
     */
    @Override
    public int getMaxRows() {
        return maxRows;
    }

    /**
     * @param maxRows the maxRows to set
     */
    @Override
    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    /**
     * Metodo que retorna el nombre de la tabla de BD
     * 
     * @return String
     */
    public String getTableName() {
        return IDaoConstants.TABLE_PUNTOS_VENTA;
    }

}
