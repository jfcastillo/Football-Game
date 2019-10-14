package hilos;

import modelo.ClientHandler;
import modelo.Servidor;

public class HiloComunicacion extends Thread {
	
	private Servidor server;
	private ClientHandler cliente1;
	private ClientHandler cliente2;
	public HiloComunicacion(Servidor server, ClientHandler cliente1, ClientHandler cliente2) {
		super();
		this.server = server;
		this.cliente1 = cliente1;
		this.cliente2 = cliente2;
	}
	
	

	

}
