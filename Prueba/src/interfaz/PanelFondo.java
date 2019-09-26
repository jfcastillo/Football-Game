package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import interfaz.VentanaPrincipal;
import modelo.Bloque;
import modelo.ObjetoMapa;

/**
 * Esta clase representa la clase PanelFondoVentanaPrincipal
 */
public class PanelFondo extends JPanel implements KeyListener{

	/**
	 * Relacion con la clase PanelBotonesVentanaPrincipal
	 **/
	private JPanel panelBotones;
	private VentanaPrincipal vPrincipal;
	//private JButton butSalir;
	;

	/**
	 * Constructor de la clase PanelBotonesVentanaPrincipal.
	 * @param vPrincipal
	 */
	public PanelFondo(VentanaPrincipal vPrincipal) {

		this.vPrincipal = vPrincipal;
		panelBotones = new JPanel();
		panelBotones.setBorder(new EmptyBorder(10, 175, 20, 175));
		setLayout(new BorderLayout());
		addKeyListener(this);
		
//		butSalir = new JButton("SALIR");
//		panelBotones.add(butSalir , BorderLayout.WEST);
//		
//		add(panelBotones, BorderLayout.SOUTH);

	}

	/**
	 * Metodo : paintComponent
	 * 
	 * @param grafico
	 *            : Graphics
	 */
	public void paintComponent(Graphics grafico) {

		Dimension height = getSize();
		super.paintComponent(grafico);
		ImageIcon Img = new ImageIcon("./resources/soccerField.png");// Pintar cancha.
		grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
		
		ImageIcon jug = new ImageIcon("./resources/jug.gif"); //vPrincipal.darPersonaje().IMAGEN);
		grafico.drawImage(jug.getImage(), vPrincipal.darPersonaje().getPosicionX(), vPrincipal.darPersonaje().getPosicionY(), 30, 50, null);
		System.out.println("hola");
		ObjetoMapa aux = vPrincipal.darMapa().getPrimerObjetoMapa();
//		while (aux != null) {
//			if (aux instanceof Bloque) {
//
//				if (aux.getRutaImagen().equals(Bloque.BLOQUE)) {
//					grafico.drawImage(new ImageIcon(Bloque.BLOQUE).getImage(), aux.getPosicionX(), aux.getPosicionY(),
//							Bloque.ALTO, Bloque.ANCHO, this);
//					aux = aux.getSiguiente();
//				}
////				 else if (aux.getRutaImagen().equals(Bloque.MESA)) {
////					g.drawImage(new ImageIcon(Bloque.MESA).getImage(), aux.getPosicionX(), aux.getPosicionY(),
////							Bloque.ALTO, Bloque.ANCHO, this);
////					aux = aux.getSiguiente();
////				}
//
//			}
////			if (aux instanceof Item) {
////				if (((Item) aux).getTipo() == Item.MALETIN) {
////					g.drawImage(new ImageIcon(aux.getRutaImagen()).getImage(), aux.getPosicionX(), aux.getPosicionY(),
////							Item.ALTO, Item.ANCHO, this);
////				}
////				aux = aux.getSiguiente();
////			}
//
//		}
 
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		// TODO Auto-generated method stub
//		if (e.getKeyCode() == KeyEvent.VK_LEFT) { // left
//			vPrincipal.moverJugador(1);
//		}
//		if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // Right
//			vPrincipal.moverJugador(2);
//		}
//		if (e.getKeyCode() == KeyEvent.VK_UP) {// up
//			vPrincipal.moverJugador(3);
//		}
//		if (e.getKeyCode() == KeyEvent.VK_DOWN) { // down
//			vPrincipal.moverJugador(4);
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
