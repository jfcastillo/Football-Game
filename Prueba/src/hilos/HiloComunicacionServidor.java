package hilos;

import java.io.IOException;

import modelo.ClientHandler;
import modelo.Servidor;

public class HiloComunicacionServidor extends Thread {
	
	private Servidor server;
	private ClientHandler cliente1;
	private ClientHandler cliente2;
	public HiloComunicacionServidor(Servidor server, ClientHandler cliente1, ClientHandler cliente2) {
		super();
		this.server = server;
		this.cliente1 = cliente1;
		this.cliente2 = cliente2;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				String msgCliente1 = cliente1.getDis().readUTF();
				System.out.println("msg: "+msgCliente1);
				if (msgCliente1.length() > 0) {
					String [] cadena = msgCliente1.split("#"); 
					String positions = cadena[0]; 
                	String recipient = cadena[1]; 
                	cliente2.getDos().writeUTF(positions);
				}
//				System.out.println("msg: "+msgCliente1);
				
				String msgCliente2 = cliente2.getDis().readUTF();
				System.out.println("msg: "+msgCliente2);
				if (msgCliente2.length() > 0) {
					String [] cadena = msgCliente2.split("#"); 
					String positions = cadena[0]; 
                	String recipient = cadena[1]; 
                	cliente1.getDos().writeUTF(positions);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
