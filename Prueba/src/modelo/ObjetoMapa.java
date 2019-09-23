package modelo;



/**
 * Esta clase representa los Objetos del Mapa
 **/
public class ObjetoMapa extends Imagen{

	/**
	 * Relación que representa el siguiente objetoMapa
	 */
	private ObjetoMapa siguiente;

	/**
	 * Atributo que representa el identificador del objeto
	 */
	private int identificador;
	private String ruta;

	/**
	 * @param posicionX
	 * @param posicionY
	 * @param rutaImagen
	 * @param detenido
	 * @param siguiente
	 */
	public ObjetoMapa(int posicionX, int posicionY, String rutaImagen, boolean detenido, int identificador) {
		super(posicionX, posicionY, rutaImagen);
		ruta = rutaImagen;
		this.siguiente = null;
		this.identificador = identificador;
	}

	/**
	 * @return siguiente
	 */
	public ObjetoMapa getSiguiente() {
		return siguiente;
	}

	/**
	 * @param siguiente
	 */
	public void setSiguiente(ObjetoMapa siguiente) {
		this.siguiente = siguiente;
	}

	/**
	 * @return identificador
	 */
	public int getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador
	 */
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getRutaImagen() {
		return ruta;
	}

}
