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
	
	private HiloPatearPelota hiloPatear;
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
	

	public PanelGoles getPanelGoles() {
		return panelGoles;
	}


	public void setPanelGoles(PanelGoles panelGoles) {
		this.panelGoles = panelGoles;
	}


	public HiloPatearPelota getHiloPatear() {
		return hiloPatear;
	}


	public void setHiloPatear(HiloPatearPelota hiloPatear) {
		this.hiloPatear = hiloPatear;
	}


	public void activarPublicidad() {
		if(cliente.getDatos()[1] !=null) {
			Publicidad p = new Publicidad(cliente.getDatos()[0], cliente.getDatos()[1], this);
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
		else if (cadena.contains("#segundoTiempo")) {
			mostrarMensajes(1);
		}
		else if (cadena.contains("#termino")) {
			String cadenas = "";
			if (getCliente().getId().equals("0")) {
				cadenas = "#termino "+getCliente().getNickName()+" "+cliente.getContadorPublicidad();
			}
			
			String[] msg = cadenas.split(" ");
			mostrarMensajes(2);
			terminarPartido(msg[1], msg[2]);
		}
		else if (cadena.contains("#nickname ")) {
			String nick = cadena.split(" ")[1];
			if (cliente.getId().equals("0")) {
				
				panelGoles.setNomJug2(nick);
				try {
					cliente.enviarDatos("#nickname "+cliente.getNickName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				panelGoles.setNomJug1(nick);
			}
		}
		else if(cadena.contains("#gol")) {
			String[] msg = cadena.split(" ");
			if (msg[1].equals("derecha")) {
				setGolesDerecha(msg[2]);
			}
			else if (msg[1].equals("izquierda")) {
				setGolesIzquierda(msg[2]);
			}
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
//		System.out.println(Integer.parseInt(cliente.getId()));
		juego.mover(direccion, Integer.parseInt(cliente.getId()));
		
		try {
//			System.out.println("ventana: "+darPersonaje().getPosicionX()+" "+darPersonaje().getPosicionY()+ " "+Integer.parseInt(cliente.getId())+ " "+darPersonaje().getRutaImagen());
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
				if (hiloPatear != null) {
					hiloPatear.setStop(true);
				}
			}
			else if(posx>=1080 && (posy>=220 && posy<=410))
			{
				setGolesIzquierda();
				if (hiloPatear != null) {
					hiloPatear.setStop(true);
				}
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
		System.out.println("goles derecha: "+g);
		panelGoles.setDerecha(g);
		reiniciarBalon();
		JOptionPane.showMessageDialog(this, "GOOOOOOOLLL");
		try {
			cliente.enviarDatos("#gol derecha "+panelGoles.getDerecha().getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reiniciarTodo();
		
	}
	public void setGolesDerecha(String g)
	{
		panelGoles.setDerecha(g);
	}
	
	public void setGolesIzquierda()
	{
		darMarcador()[1]+=1;
		String g = Integer.toString(darMarcador()[1]);
		System.out.println("goles derecha: "+g);
		panelGoles.setIzquierda(g);
		reiniciarBalon();
		JOptionPane.showMessageDialog(this, "GOOOOOOOLLL");
		try {
			cliente.enviarDatos("#gol izquierda "+panelGoles.getIzquierda().getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reiniciarTodo();
	}

	public void setGolesIzquierda(String g)
	{
		panelGoles.setIzquierda(g);
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
		try {
			cliente.enviarDatos(darPersonaje().getPosicionX()+" "+darPersonaje().getPosicionY()+ " "+Integer.parseInt(cliente.getId())+ " "+darPersonaje().getRutaImagen());
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refrescar();
		
	}


	private void reiniciarBalon() {
		Dimension size = getSize();
		darBall().setPosicionX(Ball.X_INICIAL_BALON);
		darBall().setPosicionY(Ball.Y_INICIAL_BALON);
		try {
			cliente.enviarDatos(darMapa().getPelota().getPosicionX()+" "+darMapa().getPelota().getPosicionY()+ " "+Integer.parseInt(cliente.getId())+ "p");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if (cliente.getId().equals("1")) {
			iniciarHiloCronometro();
			System.out.println("#nickname "+cliente.getNickName());
			try {
				cliente.enviarDatos("#nickname "+cliente.getNickName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		hiloPatear = new HiloPatearPelota(darPersonajes(), this, darMapa().getPelota(), Integer.parseInt(cliente.getId()));
		hiloPatear.start();
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


	public void terminarPartido(String nick2, String publicidad) {
		panelGoles.TerminarPartido();
		String mensaje = "";
		int gIzq = Integer.parseInt(panelGoles.getIzquierda().getText()+"");
		int gDer = Integer.parseInt(panelGoles.getDerecha().getText()+"");
		if (gDer > gIzq) {
			mensaje += "Ganó "+panelGoles.getNomJug2().getText()+"\n";
//			if (cliente.getId().equals("0")) {
//				mensaje += "Ganó "+panelGoles.getNomJug2()+"\n";
//			}
//			else {
//				mensaje += "Ganó "+nick2+" \n";
//			}
		}
		else if (gDer < gIzq) {
			mensaje += "Ganó "+panelGoles.getNomJug1().getText()+" \n";
//			if (cliente.getId().equals("0")) {
//				mensaje += "Ganó "+cliente.getNickName()+" \n";
//			}
//			else {
//				mensaje += "Ganó "+nick2+" \n";
//			}
		}
		else {
			mensaje += "Empate"+" \n";
		}
		mensaje += "Goles de "+panelGoles.getNomJug1().getText()+" "+ panelGoles.getIzquierda().getText()+"\n";
//		if (nick2.equals(panelGoles.getNomJug1().getText())) {
//			mensaje += "Segundos de publicidad visto " +publicidad+"\n";
//		}
//		else {
//			mensaje += "Segundos de publicidad visto " +cliente.getContadorPublicidad()+"\n";
//		}
		mensaje += "Goles de "+panelGoles.getNomJug2().getText()+" "+ panelGoles.getDerecha().getText()+"\n";
		mensaje += "Segundos de publicidad visto " +cliente.getContadorPublicidad()+"\n";
//		if (nick2.equals(panelGoles.getNomJug2().getText())) {
//			mensaje += "Segundos de publicidad visto " +publicidad;
//		}
//		else {
//			mensaje += "Segundos de publicidad visto " +cliente.getContadorPublicidad();
//		}
		
		JOptionPane.showMessageDialog(this, mensaje);
		
	}
	public void mostrarMensajes(int id) {
		if (id == 1) {
			JOptionPane.showMessageDialog(this, "Se termina el primer tiempo.");
		}
		else {
			JOptionPane.showMessageDialog(this,"Se termina el partido.");
		}
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
