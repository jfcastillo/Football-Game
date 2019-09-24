package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;


import hilos.HiloColisionJugador;
import modelo.Ball;
import modelo.Cliente;
import modelo.Mapa;
//import modelo.Partida;
import modelo.Personaje;

/**
 * Esta clase representa la clase ventanaPrincipal
 */
public class VentanaPrincipal extends JFrame {
	
	private Mapa juego;

	private PanelJuego panelJuego;
	
	
	private Cliente cliente;
	
	/**
	 * Constante que representa el nombre del juego
	 */

	public static final String NOMBRE = "Football Game";
	/**
	 * Constante que representa la altura de la ventana
	 */
	public static final int ALTURA = 700;
	/**
	 * Constante que representa la anchura de la ventana
	 */
	public static final int ANCHO = 1300;
	/**
	 * Constante que representa la escala de la ventana
	 */
	public static final int ESCALA = 4;


	/**
	 * Metodo constructor de la clase ventanaPrincipal. Se encarga de inicializar
	 * los atributos y relaciones.
	 */
	public VentanaPrincipal() {
		cliente = new Cliente(this);
		setMinimumSize(new Dimension(ANCHO, ALTURA));
		setMaximumSize(new Dimension(ANCHO, ALTURA));
		setPreferredSize(new Dimension(ANCHO, ALTURA));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(true);
		setLocationRelativeTo(null);
		panelJuego = new PanelJuego(this);
		
		setLocationRelativeTo(null);
		add(panelJuego, BorderLayout.CENTER);
		pack();
		setVisible(true);
		//juego = new Juego();
	}
	
	public static void main(String[] args) {
		VentanaPrincipal vent = new VentanaPrincipal();
		
		try {
			vent.inicializarJuego();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean isPlaying() {
		return juego.isPlaying();
	}
	
	public void setPlaying(boolean value) {
		juego.setPlaying(true);
	}
	
	public void inicializarJuego() throws IOException {
		juego = new Mapa();
		juego.leerMapa();
		setPlaying(true);
		iniciarHilos();
		
		
	}

	/**
	 * Método que re pinta la ventana
	 */
	public void refrescar() {
		repaint();
	}

	/**
	 * Se encarga de llamar las instrucciones desde el modelo del mundo.
	 * 
	 * @throws IOException
	 */
	
	/**
	 * Método que mueve el personaje
	 */
	public void moverJugador(int direccion) {
		juego.mover(direccion);
		try {
			cliente.enviarDatos(darPersonaje().getPosicionX()+" "+darPersonaje().getPosicionY());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refrescar();
	}

	/**
	 * 
	 * @return
	 * @throws DatoRepetidoException
	 * @throws IOException
	 */
	

	public void recibirPosicones(String cadena) {
		String arr[] = cadena.split(" ");
		darPersonaje().setPosicionX(Integer.parseInt(arr[0]));
		darPersonaje().setPosicionY(Integer.parseInt(arr[1]));
		refrescar();
	}

	public void iniciarHilos() {
		iniciarColisionJugador();
		
		
	}



	public void iniciarColisionJugador() {
		HiloColisionJugador hiloColisionJ = new HiloColisionJugador(darPersonaje(), darMapa().getPrimerObjetoMapa(),
				this);
		
		hiloColisionJ.start();
	}

	
	

	

//	public void iniciarHiloColisionPersonajeEnemigo() {
//		Enemigo aux = darEnemigo();
//		while (aux != null) {
//			HiloColisionEnemigoDestroyer hilo = new HiloColisionEnemigoDestroyer(aux, darPersonaje(), this);
//			hilo.start();
//			aux = aux.getSiguiente();
//		}
//
//	}

	

	

//	/**
//	 * Método que se encarga abrir la ventana puntajes
//	 */
//	public void abrirPuntaje() {
//		dialogoPuntajes = new DialogoPuntajes(this);
//		actualizarPartidas();
//		dialogoPuntajes.setVisible(true);
//		dialogoPuntajes.setLocationRelativeTo(this);
//	}

	/**
	 * @return the dialogoJuego
	 */
//	public DialogoJuego getDialogoJuego() {
//		return dialogoJuego;
//	}
//
//	/**
//	 * @return the dialogoAyuda
//	 */
//	public DialogoAyudas getDialogoAyuda() {
//		return dialogoAyuda;
//	}
//
//	/**
//	 * @return the dialogoPuntajes
//	 */
//	public DialogoPuntajes getDialogoPuntajes() {
//		return dialogoPuntajes;
//	}

	/**
	 * @return juego
	 */
	public Mapa getJuego() {
		return juego;
	}

	/**
	 * Método que se encarga de cargar el mapa de la partida actual
	 */
	
	/**
	 * @return personaje del mapa
	 */
	public Personaje darPersonaje() {
		return juego.getPersonaje();
	}
	
	public Ball darBall() {
		return juego.getPelota();
	}

	

	/**
	 * @return mapa de la partida actual
	 */
	public Mapa darMapa() {
		return juego;
	}

	

	/**
	 * Elimina y actualiza una partida seleccionada del PanelPuntajes
	 * 
	 * @param nombre
	 */


	/**
	 * Ordena los puntajes descendentemente de las partidas del PanelPuntajes
	 */
	
	

//	public boolean isJugando() {
//		return darPartidaActual().isJugando();
//	}
//
//	public void terminarJuego() {
//		darPartidaActual().setJugando(false);
//
//	}

}
