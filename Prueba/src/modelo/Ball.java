package modelo;

public class Ball extends Personaje implements Movible {
	
	public final static int  ANCHO = 25;
	public final static int ALTO = 25;
	public final static String RUTA = "./resources/Imagenes/balls/ball.png";

	public Ball(int posicionX, int posicionY, String DESTROYER) {
		super(posicionX, posicionY, DESTROYER);
		// TODO Auto-generated constructor stub
	}



}
