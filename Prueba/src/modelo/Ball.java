package modelo;

public class Ball extends Personaje implements Movible {
	
	public final static int  ANCHO = 25;
	public final static int ALTO = 25;
	public final static String RUTA = "./resources/Imagenes/balls/balon.png";

	public Ball(int posicionX, int posicionY, String rutaImg) {
		super(posicionX, posicionY, rutaImg, 3);
		// TODO Auto-generated constructor stub
	}



}
