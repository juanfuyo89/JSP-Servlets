package sga.eis.dto;

import java.io.Serializable;

/**
 * Esta clase representa la primary key de la tabla Personas.
 * 
 * @author Juan Carlos Fuyo
 */
public class PersonaPk implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Este atributo mapea la primary-key de la colunma cedula en la tabla Personas.
	 */
	protected String cedula;

	/**
	 * Constructor vacio
	 */
	public PersonaPk() {
	}

	/**
	 * Constructor con parametros
	 * 
	 * @param idPersona
	 */
	public PersonaPk(final String cedula) {
		this.cedula = cedula;
	}

	/**
	 * Sets the value of idPersona
	 */
	public void setIdPersona(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * Gets the value of idPersona
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other) {
		if (_other == null) {
			return false;
		}

		if (_other == this) {
			return true;
		}

		if (!(_other instanceof PersonaPk)) {
			return false;
		}

		final PersonaPk _cast = (PersonaPk) _other;
		if (cedula == null ? _cast.cedula != cedula : !cedula.equals(_cast.cedula)) {
			return false;
		}

		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode() {
		int _hashCode = 0;
		if (cedula != null) {
			_hashCode = 29 * _hashCode + cedula.hashCode();
		}

		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("ap.eis.dto.PersonaPk: ");
		ret.append("idPersona=" + cedula);
		return ret.toString();
	}

}
