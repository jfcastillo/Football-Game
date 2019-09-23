package modelo;

/**
 * Es una clase general que otorga atributos que permiten pintar
 **/
public abstract class Imagen {

	/**
	 * Atributo que representa la posicion x de la imagen
	 */
	protected int posicionX;
	/**
	 * Atributo que representa la posicion y de la imagen
	 */
	protected int posicionY;
	/**
	 * Atributo que representa la ruta de la iamgen
	 */
	protected String rutaImagen;

	/**
	 * Constructor de la clase Imagen. Se encarga de inicializar las relaciones y
	 * atributos.
	 * 
	 * @param posicionX
	 * @param posicionY
	 * @param rutaImagen
	 */
	public Imagen(int posicionX, int posicionY, String rutaImagen) {
		super();
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.rutaImagen = rutaImagen;
	}

	/**
	 * @return posicionX
	 */
	public int getPosicionX() {
		return posicionX;
	}

	/**
	 * @return posicionY
	 */
	public int getPosicionY() {
		return posicionY;
	}

	/**
	 * @param posicionX
	 */
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	/**
	 * @param posicionY
	 */
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

}
