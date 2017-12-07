package sga.eis.dto;

import java.io.Serializable;

/**
 * clase representa la tabla Usuarios.
 * 
 * @author Juan Carlos Fuyo Esta
 */
public class Usuario implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea la colunma id_usuario en la tabla Usuarios.
     */
    protected Integer         idUsuario;

    /**
     * Este atributo mapea la colunma username en la tabla Usuarios.
     */
    protected String          username;

    /**
     * Este atributo mapea la colunma passwd en la tabla Usuarios.
     */
    protected String          password;

    /**
     * Este atributo mapea la colunma id_persona en la tabla Usuarios.
     */
    protected String          idPersona;

    /**
     * Este atributo mapea la colunma id_rol en la tabla Usuarios.
     */
    protected int             idRol;

    /**
     * Constructor vacio
     */
    public Usuario() {
    }

    /**
     * Metodo para crear la primary-key de la tabla Usuarios
     * 
     * @return UsuarioPk
     */
    public UsuarioPk createPk() {
        return new UsuarioPk(idUsuario);
    }

    /**
     * @return the idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the idPersona
     */
    public String getIdPersona() {
        return idPersona;
    }

    /**
     * @param idPersona the idPersona to set
     */
    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * @return the idRol
     */
    public int getIdRol() {
        return idRol;
    }

    /**
     * @param idRol the idRol to set
     */
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    /** 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((idPersona == null) ? 0 : idPersona.hashCode());
        result = prime * result + idRol;
        result = prime * result
                + ((idUsuario == null) ? 0 : idUsuario.hashCode());
        result = prime * result
                + ((password == null) ? 0 : password.hashCode());
        result = prime * result
                + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) obj;
        if (idPersona == null) {
            if (other.idPersona != null) {
                return false;
            }
        }
        else if (!idPersona.equals(other.idPersona)) {
            return false;
        }
        if (idRol != other.idRol) {
            return false;
        }
        if (idUsuario == null) {
            if (other.idUsuario != null) {
                return false;
            }
        }
        else if (!idUsuario.equals(other.idUsuario)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        }
        else if (!password.equals(other.password)) {
            return false;
        }
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        }
        else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", username=" + username
                + ", password=" + password + ", idPersona=" + idPersona
                + ", idRol=" + idRol + "]";
    }

}
