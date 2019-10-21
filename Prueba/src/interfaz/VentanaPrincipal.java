package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import hilos.HiloColisionesGenerales;
import hilos.HiloPatearPelota;
import hilos.HiloPintar;
import hilos.HiloTiempo;
import hilos.HiloColisionPelota;
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
	
	private PanelGoles panelGoles;
	
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
	public VentanaPrincipal(String nombreCliente) {
		cliente = new Cliente(this, nombreCliente);
		setMinimumSize(new Dimension(ANCHO, ALTURA));
		setMaximumSize(new Dimension(ANCHO, ALTURA));
		setPreferredSize(new Dimension(ANCHO, ALTURA));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		setLocationRelativeTo(null);
		panelJuego = new PanelJuego(this);
		panelGoles = new PanelGoles(this);
		
		setLocationRelativeTo(null);
		add(panelJuego, BorderLayout.CENTER);
		add(panelGoles, BorderLayout.NORTH);
		pack();
//		setVisible(true);
		//juego = new Juego();
	}

	public void activarPublicidad() {
		if(cliente.getDatos()[1] !=null) {
			Publicidad p = new Publicidad(cliente.getDatos()[0], cliente.getDatos()[1]);
//			Thread hilito = new Thread(new Runnable(){
//				@Override
//				public void run() {
//					while(true) {
//						p.repaint();
//						
//					}
//				}
//			});
//			hilito.start();
			
		}
	}
	
	public boolean isPlaying() {
		return juego.isPlaying();
	}
	
	public void setPlaying(boolean value) {
		juego.setPlaying(true);
	}
	
	public void inicializarJuego(String nombreJugador) throws IOException {
		juego = new Mapa();
		juego.leerMapa();
		setPlaying(true);
		iniciarHilos();
//		cliente.setNombre(nombreJugador);
		if(Integer.parseInt(cliente.getId())==-1||Integer.parseInt(cliente.getId())==0) {
			panelGoles.setNomJug1(nombreJugador);
		}
		else {
			panelGoles.setNomJug2(nombreJugador);
		}
		System.out.println("--"+nombreJugador);

	
		
		
	}

	/**
	 * Método que re pinta la ventana
	 */
	public void refrescar() {

		repaint();
	//	this.asignarNombreUsuario();
		
	}
	public void refrescarReloj(int min, int seg) {
		panelGoles.refrescarReloj(min,seg);
		
		
	}
	public void recibirPosiciones(String cadena) {
		if(cadena.charAt(cadena.length()-1) == 'p') {
			String arr[] = cadena.split(" ");			
			darMapa().getPelota().setPosicionX(Integer.parseInt(arr[0]));
			darMapa().getPelota().setPosicionY(Integer.parseInt(arr[1]));
		}
		else if(cadena.contains("#tiempo")) {
			String[] msg = cadena.split(" ");
			int minutos = Integer.parseInt(msg[1]);
			int segundos = Integer.parseInt(msg[2]);
			refrescarReloj(minutos, segundos);
		}
		else {			
			String arr[] = cadena.split(" ");
//			System.out.println(arr[2]);
//			int posicion = -1;
//			if (cliente.getId().equals("0")) {
//				posicion = 1;
//			}
//
//			else if(cliente.getId().equals("1")){
//				posicion = 0;
//			}
			darPersonajes()[Integer.parseInt(arr[2])].setPosicionX(Integer.parseInt(arr[0]));
			darPersonajes()[Integer.parseInt(arr[2])].setPosicionY(Integer.parseInt(arr[1]));
			darPersonajes()[Integer.parseInt(arr[2])].setRutaImagen(arr[3]);
		}
		refrescar();
	}

	/**
	 * Se encarga de llamar las instrucciones desde el modelo del mundo.
	 * 
	 * @throws IOException
	 */
	
	/**
	 * Método que mueve el personaje
	 */
	public void moverJugador(int direccion) {//-----------------------------------------------------------
		System.out.println(Integer.parseInt(cliente.getId()));
		juego.mover(direccion, Integer.parseInt(cliente.getId()));
		
		try {
			System.out.println("ventana: "+darPersonaje().getPosicionX()+" "+darPersonaje().getPosicionY()+ " "+Integer.parseInt(cliente.getId())+ " "+darPersonaje().getRutaImagen());
//			String cadena = darPersonaje().getPosicionX()+" "+darPersonaje().getPosicionY()+" "+darPersonaje().getRutaImagen();
			cliente.enviarDatos(darPersonaje().getPosicionX()+" "+darPersonaje().getPosicionY()+ " "+Integer.parseInt(cliente.getId())+ " "+darPersonaje().getRutaImagen());
			cliente.enviarDatos(darMapa().getPelota().getPosicionX()+" "+darMapa().getPelota().getPosicionY()+ " "+Integer.parseInt(cliente.getId())+ "p");
//			cliente.enviarDatos(cadena);
//			cadena = darMapa().getPelota().getPosicionX()+" "+darMapa().getPelota().getPosicionY()+ " "+Integer.parseInt(cliente.getId())+ "p";
//			cliente.enviarDatos(cadena);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refrescar();
	}
	
	public void moverPelota(int direccion) {
		darMapa().getPelota().mover(direccion);
		try {
			cliente.enviarDatos(darMapa().getPelota().getPosicionX()+" "+darMapa().getPelota().getPosicionY()+ " "+Integer.parseInt(cliente.getId())+ "p");
			int posx = darMapa().getPelota().getPosicionX();
			int posy = darMapa().getPelota().getPosicionY();
			System.out.println("la posicion x"+darMapa().getPelota().getPosicionX()+" "+"la posicion y "+darMapa().getPelota().getPosicionY());
			if(posx==160 && (posy>=220 && posy<=416))
			{
				setGolesDerecha();
			}
			else if(posx>=1100 && (posy>=220 && posy<=410))
			{
				setGolesIzquierda();
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refrescar();
	}
	
	public void patearPelota() {
		if (darPersonaje().isTieneBalon()) {
			darPersonaje().setTieneBalon(false);
			iniciarPatearPelota();
			
		}
		try {
			cliente.enviarDatos(darMapa().getPelota().getPosicionX()+" "+darMapa().getPelota().getPosicionY()+ " "+Integer.parseInt(cliente.getId())+ "p");
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refrescar();
	}
	
	
	public void setGolesDerecha()
	{
		darMarcador()[0]+=1;
		String g = Integer.toString(darMarcador()[0]);
		panelGoles.setDerecha(g);
		reiniciarBalon();
		JOptionPane.showMessageDialog(this, "GOOOOOOOLLL");
		reiniciarTodo();
		
	}
	
	public void setGolesIzquierda()
	{
		darMarcador()[1]+=1;
		String g = Integer.toString(darMarcador()[0]);
		panelGoles.setIzquierda(g);
		reiniciarBalon();
		JOptionPane.showMessageDialog(this, "GOOOOOOOLLL");
		reiniciarTodo();
	}


	public void reiniciarTodo() {
		reiniciarBalon();
		reiniciarJugadores();
		
	}

	private void reiniciarJugadores() {
		this.juego.getPersonaje()[0].setPosicionX(Personaje.X_INICIAL_JUG1);
		this.juego.getPersonaje()[0].setTieneBalon(false);
		this.juego.getPersonaje()[1].setPosicionX(Personaje.X_INICIAL_JUG2);
		this.juego.getPersonaje()[0].setPosicionY(Personaje.Y_INICIAL);
		this.juego.getPersonaje()[1].setPosicionY(Personaje.Y_INICIAL);
		this.juego.getPersonaje()[1].setTieneBalon(false);
		refrescar();
		
	}


	private void reiniciarBalon() {
		Dimension size = getSize();
		darBall().setPosicionX(Ball.X_INICIAL_BALON);
		darBall().setPosicionY(Ball.Y_INICIAL_BALON);
		refrescar();
		
	}


	public Cliente getCliente() {
		return cliente;
	}
	
	public int[] darMarcador() {
		return darMapa().getMarcador(); 
	}

	/**
	 * 
	 * @return
	 * @throws DatoRepetidoException
	 * @throws IOException
	 */
	



	public void iniciarHilos() {
		iniciarColisionesGenerales();
		if (cliente.getId().equals("0")) {
			iniciarHiloCronometro();
		}
	
		
		
		//iniciarColisionPelota();
		
	}
	public void iniciarHiloRepintar() {
		HiloPintar pintar = new HiloPintar(this);
		pintar.start();
	}
	public void iniciarHiloCronometro() {
		HiloTiempo crono = new HiloTiempo(this);
		crono.start();
		
		
	}
	
	public void iniciarColisionesGenerales() {
		HiloColisionesGenerales hiloColisionJ = new HiloColisionesGenerales(darPersonajes(), darMapa().getPrimerObjetoMapa(),
				this, darBall());
		
		hiloColisionJ.start();
	}
	
	public void iniciarPatearPelota() {
		HiloPatearPelota hiloPa = new HiloPatearPelota(darPersonajes(), this, darMapa().getPelota(), Integer.parseInt(cliente.getId()));
		hiloPa.start();
	}
		

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
		return juego.getPersonaje()[Integer.parseInt(cliente.getId())];
	}
	
	public Personaje[] darPersonajes() {
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
	
	public void asignarNombreUsuario() {
		System.out.println(cliente.getNickName());
		System.out.println(cliente.getId());
		if(Integer.parseInt(cliente.getId())==0) {
			panelGoles.cambiarNombreJugador1(cliente.getNickName());
			
		}
		else if(Integer.parseInt(cliente.getId())==1){
			panelGoles.cambiarNombreJugador2(cliente.getNickName());
		}
		
	}


	public void segundoTiempo() {
		panelGoles.segundoTiempo();
		
	}


	public void terminarPartido() {
		panelGoles.TerminarPartido();
		
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
