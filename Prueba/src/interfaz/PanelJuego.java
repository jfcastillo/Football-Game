package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import modelo.Bloque;

import modelo.Mapa;
import modelo.ObjetoMapa;
import modelo.Personaje;

/**
 * Esta clase representa la clase PanelJuego
 */
public class PanelJuego extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;

	/**
	 * Relacion con la clase VentanaPrincipal
	 **/
	private VentanaPrincipal vPrincipal;


	/**
	 * Metodo constructor de la clase PanelJuego. Se encarga de inicializar los
	 * atributos y relaciones.
	 */
	public PanelJuego(VentanaPrincipal vPrincipal) {
		
		this.vPrincipal = vPrincipal;
		setBackground(Color.GRAY);
		addKeyListener(this);
		setFocusable(true);
	}

	/**
	 * Nombre: paintComponent El metodo se encarga de pintar la imagen del fondo del
	 * panel y los respectivos objetos. <b>pos</b>Se pinto el fondo y la imagen de
	 * los objetos.
	 */
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		
		Dimension height = getSize();
		ImageIcon Img = new ImageIcon("./resources/soccerField.png");// Pintar cancha.
		g.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
		
		ImageIcon ball = new ImageIcon(vPrincipal.darBall().getRutaImagen());
		g.drawImage(ball.getImage(), vPrincipal.darBall().getPosicionX(), vPrincipal.darBall().getPosicionY(), 30, 50, null);
		
		//g.drawImage(new ImageIcon(Mapa.FONDO_NIVEL_1).getImage(), 0, 0, Mapa.ANCHO, Mapa.ALTO, this); // Carga el fondo
																										// del nivel 1
		Personaje auxi = null;
		for (int i = 0; i < vPrincipal.darPersonajes().length; i++) {
			auxi = vPrincipal.darPersonajes()[i];
			ImageIcon jug = new ImageIcon(auxi.getRutaImagen());
			g.drawImage(jug.getImage(), auxi.getPosicionX(), auxi.getPosicionY(), 30, 50, null);
		}
		

		ObjetoMapa aux = vPrincipal.darMapa().getPrimerObjetoMapa();
		while (aux != null) {
			if (aux instanceof Bloque) {

				//if (aux.getRutaImagen().equals(Bloque.BLOQUE)) {
					g.drawImage(new ImageIcon(aux.getRutaImagen()).getImage(), aux.getPosicionX(), aux.getPosicionY(),
							Bloque.ALTO, Bloque.ANCHO, this);
					aux = aux.getSiguiente();
				//}
//				 else if (aux.getRutaImagen().equals(Bloque.MESA)) {
//					g.drawImage(new ImageIcon(Bloque.MESA).getImage(), aux.getPosicionX(), aux.getPosicionY(),
//							Bloque.ALTO, Bloque.ANCHO, this);
//					aux = aux.getSiguiente();
//				}

			}
//			if (aux instanceof Ball) {
//			//	if (((Ball) aux).getTipo() == Item.MALETIN) {
//					g.drawImage(new ImageIcon(aux.getRutaImagen()).getImage(), aux.getPosicionX(), aux.getPosicionY(),
//							aux.ALTO, aux.ANCHO, this);
//				//}
//				aux = aux.getSiguiente();
//			}

		}

//		Enemigo aux2 = vPrincipal.darEnemigo();
//
//		while (aux2 != null) {
//			g.drawImage(new ImageIcon(aux2.getRutaImagen()).getImage(), aux2.getPosicionX(), aux2.getPosicionY(),
//					Enemigo.ALTO, Enemigo.ANCHO, this);
//			aux2 = aux2.getSiguiente();
//		}
//
//		if (vPrincipal.darPersonaje().isLanzandoHabilidad()) {
//			g.drawImage(new ImageIcon(vPrincipal.darHabilidadActual().getNombre()).getImage(),
//					vPrincipal.darHabilidadActual().getPosicionX(), vPrincipal.darHabilidadActual().getPosicionY(),
//					Habilidad.ALTO, Habilidad.ANCHO, this);
//		}

	}

	/**
	 * Nombre: keyPressed El metodo se encarga de obtener la direcci�n las teclas
	 * <b>pos</b>Se cambia la posciones del personaje
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) { // left
			vPrincipal.moverJugador(1);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // Right
			vPrincipal.moverJugador(2);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {// up
			vPrincipal.moverJugador(3);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) { // down
			vPrincipal.moverJugador(4);
		}
//		if(vPrincipal.getCliente().getId().equals("0")) {
//			if (e.getKeyCode() == KeyEvent.VK_LEFT) { // left
//				vPrincipal.moverJugador1(1);
//			}
//			if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // Right
//				vPrincipal.moverJugador1(2);
//			}
//			if (e.getKeyCode() == KeyEvent.VK_UP) {// up
//				vPrincipal.moverJugador1(3);
//			}
//			if (e.getKeyCode() == KeyEvent.VK_DOWN) { // down
//				vPrincipal.moverJugador1(4);
//			}
//		}else {
//			if (e.getKeyCode() == KeyEvent.VK_A) { // left
//				vPrincipal.moverJugador2(1);
//			}
//			if (e.getKeyCode() == KeyEvent.VK_D) { // Right
//				vPrincipal.moverJugador2(2);
//			}
//			if (e.getKeyCode() == KeyEvent.VK_W) {// up
//				vPrincipal.moverJugador2(3);
//			}
//			if (e.getKeyCode() == KeyEvent.VK_S) { // down
//				vPrincipal.moverJugador2(4);
//			}
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
