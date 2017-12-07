package sga.eis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import sga.eis.dao.DescuentoDao;
import sga.eis.dto.Descuento;
import sga.eis.dto.DescuentoPk;
import sga.eis.exceptions.DescuentoDaoException;
import sga.eis.jdbc.connect.ResourceManager;
import sga.eis.jdbc.utilities.IDaoConstants;

/**
 * Clase que implementa los metodos de consulta y modificación de la tabla
 * Descuentos de la BD
 * 
 * @author Juan Carlos Fuyo
 */
public class DescuentoDaoImpl implements DescuentoDao {

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
    protected final String     SQL_SELECT           = "SELECT id_dcto, descripcion, fecha_inicial, fecha_final, porcentaje FROM "
                                                            + getTableName()
                                                            + "";

    /**
     * Sentencia SQL INSERT para esta tabla
     */
    protected final String     SQL_INSERT           = "INSERT INTO "
                                                            + getTableName()
                                                            + " ( descripcion, fecha_inicial, fecha_final, porcentaje ) VALUES ( ?, ?, ?, ? )";

    /**
     * Sentencia SQL UPDATE para esta tabla
     */
    protected final String     SQL_UPDATE           = "UPDATE "
                                                            + getTableName()
                                                            + " SET  descripcion=?, fecha_inicial=?, fecha_final=?, porcentaje=? WHERE id_dcto=?";

    /**
     * Sentencia SQL DELETE para esta tabla
     */
    protected final String     SQL_DELETE           = "DELETE FROM "
                                                            + getTableName()
                                                            + " WHERE id_dcto = ?";

    /**
     * Indice de la columna id_dcto
     */
    protected static final int COLUMN_ID_DCTO       = 1;

    /**
     * Indice de la columna descripcion
     */
    protected static final int COLUMN_DESCRIPCION   = 2;

    /**
     * Indice de la columna fecha_inicial
     */
    protected static final int COLUMN_FECHA_INICIAL = 3;

    /**
     * Indice de la columna fecha_final
     */
    protected static final int COLUMN_FECHA_FINAL   = 4;

    /**
     * Indice de la columna porcentaje
     */
    protected static final int COLUMN_PORCENTAJE    = 5;

    /**
     * Numero de columnas
     */
    protected static final int NUMBER_OF_COLUMNS    = 5;

    /**
     * Indice de la primary-key id_dcto
     */
    protected static final int PK_COLUMN_ID_DCTO    = 1;

    /**
     * Constructor vacio
     */
    public DescuentoDaoImpl() {
    }

    /**
     * Constructor con parametros
     * 
     * @param userConn
     */
    public DescuentoDaoImpl(final java.sql.Connection userConn) {
        this.userConn = userConn;
    }

    /**
     * Inserta un nuevo registro en la tabla Descuentos.
     */
    @Override
    public DescuentoPk insert(Descuento dto) throws DescuentoDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            stmt = userConn.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            stmt.setString(index++, dto.getDescripcion());
            stmt.setDate(index++, new java.sql.Date(
                    dto.getFechInicial().getTime()));
            stmt.setDate(index++, new java.sql.Date(
                    dto.getFechFinal().getTime()));
            stmt.setInt(index, dto.getPorcentaje());
            System.out.println("Executing " + SQL_INSERT + " with DTO: " + dto);
            int rows = stmt.executeUpdate();
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
            // retorna valores de las columnas auto-increment
            rs = stmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                dto.setIdDcto(new Integer(rs.getInt(1)));
            }
            reset(dto);
            return dto.createPk();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DescuentoDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Actualiza un unico registro en la tabla Descuentos.
     */
    @Override
    public void update(DescuentoPk pk, Descuento dto)
            throws DescuentoDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_UPDATE + " with DTO: " + dto);
            stmt = userConn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, dto.getDescripcion());
            stmt.setDate(index++, new java.sql.Date(
                    dto.getFechInicial().getTime()));
            stmt.setDate(index++, new java.sql.Date(
                    dto.getFechFinal().getTime()));
            stmt.setInt(index++, dto.getPorcentaje());
            stmt.setInt(index, pk.getIdDcto().intValue());
            int rows = stmt.executeUpdate();
            reset(dto);
            long t2 = System.currentTimeMillis();
            System.out.println(rows + " rows affected (" + (t2 - t1) + " ms)");
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DescuentoDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Elimina un unico registro en la tabla Descuentos.
     */
    @Override
    public void delete(DescuentoPk pk) throws DescuentoDaoException {
        long t1 = System.currentTimeMillis();
        PreparedStatement stmt = null;
        try {
            // Validamos la conexion
            this.validateConnection();
            System.out.println("Executing " + SQL_DELETE + " with PK: " + pk);
            stmt = userConn.prepareStatement(SQL_DELETE);
            if (pk.getIdDcto() != null) {
                stmt.setInt(IDaoConstants.INT_ONE, pk.getIdDcto().intValue());
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
            throw new DescuentoDaoException("Exception: " + e.getMessage(), e);
        }
        finally {
            ResourceManager.close(stmt);
        }
    }

    /**
     * Retorna un unico registro en la tabla Descuentos que conicida con la
     * primary-key especificada.
     */
    @Override
    public Descuento findByPrimaryKey(DescuentoPk pk)
            throws DescuentoDaoException {
        return findByPrimaryKey(pk.getIdDcto());
    }

    /**
     * Retorna un registro de la tabla Descuentos que coincida con el criterio
     * 'id_descuento = :idDescuento'.
     */
    @Override
    public Descuento findByPrimaryKey(Integer idDescuento)
            throws DescuentoDaoException {
        Descuento[] ret = findByDynamicSelect(
                SQL_SELECT + " WHERE id_dcto = ?", new Object[] { idDescuento });
        return ret.length == 0 ? null : ret[0];
    }

    /**
     * Retorna todas las filas de la tabla Descuentos.
     */
    @Override
    public Descuento[] findAll() throws DescuentoDaoException {
        return findByDynamicSelect(SQL_SELECT + " ORDER BY id_dcto", null);
    }

    /**
     * Retorna todos los registros de la tabla Descuentos que coincidan con la
     * sentencia SQL especificada arbitrariamente
     */
    @Override
    public Descuento[] findByDynamicSelect(String sql, Object[] sqlParams)
            throws DescuentoDaoException {
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
            throw new DescuentoDaoException("Exception: " + e.getMessage(), e);
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
     * Retorna todos los registros de la tabla Descuentos que coincidan con el
     * WHERE SQL especificado arbitrariamente
     */
    @Override
    public Descuento[] findByDynamicWhere(String sql, Object[] sqlParams)
            throws DescuentoDaoException {
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
            throw new DescuentoDaoException("Exception: " + e.getMessage(), e);
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
    protected Descuento fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Descuento dto = new Descuento();
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
    protected Descuento[] fetchMultiResults(ResultSet rs) throws SQLException {
        Collection<Descuento> resultList = new ArrayList<Descuento>();
        while (rs.next()) {
            Descuento dto = new Descuento();
            populateDto(dto, rs);
            resultList.add(dto);
        }
        Descuento ret[] = new Descuento[resultList.size()];
        resultList.toArray(ret);
        return ret;
    }

    /**
     * Se encarga de poblar un DTO con datos de un ResultSet especificado
     */
    protected void populateDto(Descuento dto, ResultSet rs) throws SQLException {
        dto.setIdDcto(new Integer(rs.getInt(COLUMN_ID_DCTO)));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setFechInicial(rs.getDate(COLUMN_FECHA_INICIAL));
        dto.setFechFinal(rs.getDate(COLUMN_FECHA_FINAL));
        dto.setPorcentaje(rs.getInt(COLUMN_PORCENTAJE));
    }

    /**
     * Restablece los atributos modificados en el DTO
     */
    protected void reset(Descuento dto) {
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
        return IDaoConstants.TABLE_DESCUENTOS;
    }

}
