package modelo;

import java.io.Serializable;

/**
 * Esta clase representa la información del usuario
 **/
public class Usuario implements Serializable {

	/**
	 * Atributo que representa el puntaje del usuario
	 */
	private int puntaje;
	/**
	 * Atributo que representa el nombre del usuario
	 */
	private String nombre;

	/**
	 * Metodo constructor de la clase Usuario. Se encarga de inicializar los
	 * atributos.
	 */
	public Usuario(String nombre) {
		super();
		this.puntaje = 0;
		this.nombre = nombre;
	}

	/**
	 * Nombre: sumarPuntaje. El metodo se encarga de modificar el puntaje del
	 * usuario <b>El puntaje del usuario ha sido modificado</b>
	 */
	public void sumarPuntaje(int puntaje) {
		this.puntaje += puntaje;
	}

	/**
	 * @return puntaje
	 */
	public int getPuntaje() {
		return puntaje;
	}

	/**
	 * @param puntaje
	 */
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	/**
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
