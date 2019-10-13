package hilos;

import java.net.ServerSocket;

import modelo.Servidor;

public class HiloConexion extends Thread {
	
	private Servidor server;
	private ServerSocket serverSocket;
	

	
	public HiloConexion(Servidor server, ServerSocket serverSocket) {
		super();
		this.server = server;
		this.serverSocket = serverSocket;
	}



	@Override
	public void run() {
		while(true) {
			
		}
	}

	

}
