package hilos;

import java.io.IOException;

import modelo.ClientHandler;
import modelo.Servidor;

public class HiloComunicacionServidor extends Thread {
	
	private Servidor server;
	private ClientHandler cliente1;
	private String idCliente2;
	private HiloPublicidad hiloPu;

	
	


	public HiloComunicacionServidor(Servidor server, ClientHandler cliente1, String idCliente2) {
		super();
		this.server = server;
		this.cliente1 = cliente1;
		this.idCliente2 = idCliente2;
		hiloPu = new HiloPublicidad(0, 0);
		
	}
	@Override
	public void run() {
		while(cliente1.isIsloggedin()) {
			try {
				
				String msgCliente1 = cliente1.getDis().readUTF();
				if (msgCliente1.length() > 0) {
//					String [] cadena = msgCliente1.split("#"); 
////				String positions = cadena[0]; 
////            	String recipient = cadena[1]; 
					server.getMapClients().get(idCliente2).getDos().writeUTF(msgCliente1);
					if (msgCliente1.contains("#tiempo")) {
						String[] cadena = msgCliente1.split(" ");
						int min = Integer.parseInt(cadena[1]);
						int seg = Integer.parseInt(cadena[2]);
						hiloPu.setMin(min);
						hiloPu.setSeg(seg);
						

					}
					if((hiloPu.getMin() == 1 || (hiloPu.getMin() == 2)) && hiloPu.getSeg() == 0) {
						System.out.println("tiempoo");
						hiloPu.multicastPublisher("./resources/ads/coke.gif", "./resources/ads/coke.mp3");
					}
					if((hiloPu.getMin() == 0) && hiloPu.getSeg() == 0 && cliente1.getId() == 0) {
						System.out.println("tiempoo");
						hiloPu.multicastPublisher("./resources/ads/coke.gif", "./resources/ads/coke.mp3");
					}
				}
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				cliente1.setIsloggedin(false);
				try {
					cliente1.getDis().close();
					cliente1.getDos().close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
//				e.printStackTrace();
				
			}
		}
	}

}
