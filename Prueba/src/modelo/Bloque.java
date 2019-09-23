package modelo;

/**
 * Esta clase se encarga cargar los datos de los bloques del mapa.
 **/
public class Bloque extends ObjetoMapa {

	/**
	 * Constante que representa el ancho del personaje
	 */
	public final static int ANCHO = 32;
	/**
	 * Constante que representa el largo del personaje
	 */
	public final static int ALTO = 32;
	
	/**
	 * Constante que representa la ruta de la imagen del bloque
	 */

	
	public final static String BLOQUE = "./resources/Imagenes/bloques/bloqueblanco.png";
	public final static String LINEA = "./resources/Imagenes/bloques/whiteline.png";

	/**
	 * Atributo que representa el ancho del bloque
	 */
	private int ancho;
	
	/**
	 * Atributo que representa el largo del bloque
	 */
	private int largo;

	/*
	 * Constructor de la clase Bloque. Se encarga de inicializar las relaciones y atributos.
	 *
	 * @param posicionX
	 * @param posicionY
	 * @param rutaImagen
	 * @param identificador
	 */
	public Bloque(int posicionX, int posicionY, String rutaImagen, int identificador) {
		super(posicionX, posicionY, rutaImagen, true, identificador);
		ancho = ANCHO;
		largo = ALTO;
	}

	/**
	 * @return ancho
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * @return largo
	 */
	public int getLargo() {
		return largo;
	}

}
