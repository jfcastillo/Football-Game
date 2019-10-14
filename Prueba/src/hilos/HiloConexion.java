package hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import modelo.ClientHandler;
import modelo.Servidor;

public class HiloConexion extends Thread {
	
	private Servidor server;
	private ServerSocket serverSocket;
	
	
	/**
	 * Hilo atento a los clientes que se conectan
	 * @param server
	 * @param serverSocket
	 */
	public HiloConexion(Servidor server, ServerSocket serverSocket) {
		super();
		this.server = server;
		this.serverSocket = serverSocket;
	}



	@Override
	public void run() {
		DataInputStream in;
		DataOutputStream out;
		while(true) {
			try {
				Socket socket = serverSocket.accept();
				System.out.println("El cliente se ha conectado!");
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				String nameCliente = in.readUTF();
				System.out.println("Nombre del cliente "+nameCliente);
				ClientHandler clienteHandler = new ClientHandler(socket, nameCliente, in, out);
				server.addClient(nameCliente,clienteHandler);
				if (server.getMapClients().size()>1) {
					String[] juego = (in.readUTF()).split(" ");
					HiloComunicacion hiloC = new HiloComunicacion(server, cliente1, cliente2)
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	

}
