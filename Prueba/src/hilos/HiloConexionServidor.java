package hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import modelo.ClientHandler;
import modelo.Servidor;

public class HiloConexionServidor extends Thread implements Runnable{
	
	private Servidor server;
	private ServerSocket serverSocket;
	private int cantidadClientes;
	
	
	/**
	 * Hilo atento a los clientes que se conectan
	 * @param server
	 * @param serverSocket
	 */
	public HiloConexionServidor(Servidor server, ServerSocket serverSocket) {
		super();
		this.server = server;
		this.serverSocket = serverSocket;
		cantidadClientes = 0;
	}



	@Override
	public void run() {
		DataInputStream in;
		DataOutputStream out;
		while(true) {
			try {
				Socket socketClient = serverSocket.accept();
				System.out.println("El cliente se ha conectado!");
				in = new DataInputStream(socketClient.getInputStream());
				out = new DataOutputStream(socketClient.getOutputStream());
				String nameCliente = in.readUTF();
				System.out.println("Nombre del cliente "+nameCliente);
				int idCliente = 0;
				if (cantidadClientes % 2 != 0) {
					idCliente = 1;
				}
				out.writeUTF("#idCliente "+idCliente);
				
				
								
				ClientHandler clienteHandler = new ClientHandler(socketClient, nameCliente, in, out);
				
//				Thread t = new Thread(clienteHandler);
//				server.getAr().add(clienteHandler);
//				t.start();
				
				server.addClient(nameCliente,clienteHandler);
				
				cantidadClientes++;
				if (cantidadClientes % 2 == 0) {
					System.out.println(server.getIdUltimoCliente());
//					HiloComunicacionServidor hiloCS = new HiloComunicacionServidor(server, server.getMapClients().get(server.getIdUltimoCliente()), clienteHandler)	;
//					hiloCS.start();
					
					HiloComunicacionServidor hiloCS = new HiloComunicacionServidor(server, server.getMapClients().get(server.getIdUltimoCliente()), clienteHandler.getName());
					hiloCS.start();
					HiloComunicacionServidor hiloCS1 = new HiloComunicacionServidor(server, clienteHandler, server.getIdUltimoCliente() );
					hiloCS1.start();
				}
				
				else {
					server.setIdUltimoCliente(nameCliente);
				}

				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	

}
