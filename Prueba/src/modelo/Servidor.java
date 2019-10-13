package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;

public class Servidor {
	
	// Vector to store active clients 
    static Vector<ClientHandler> ar = new Vector<>(); 
      
    // counter for clients 
    static int i = 0; 
  
	/**
	 * Puerto por donde el servidor atendera a los clientes
	 */
	public static final int PORT = 8000;
	/**
	 * El servidor dispone de un serversocket, para permitir la conexion a los clientes
	 */
	private static  ServerSocket serverSocket;
	/**
	 * El servidor dispone de un socket para atender a cada cliente por individual
	 */
	private static Socket socket;
	
	private static DatagramSocket socketMulti;
    private static InetAddress group;
    private static byte[] buf;
	
    /**
     * Mapa de socket de los clientes conectados
     */
    private HashMap<Socket, String> mapClients;
	public static void main(String[] args) {
		
		DataInputStream in;
		DataOutputStream out;
		
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("Servidor escuchando");
			long tiempoIni = System.currentTimeMillis();
			while(true) {
				
				socket = serverSocket.accept();
				System.out.println("El cliente se ha conectado!");
				
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			
				ClientHandler clienteHandler = new ClientHandler(socket, i+"", in, out);
				Thread t = new Thread(clienteHandler);
				
				i++;
				ar.add(clienteHandler);
				t.start();
			
				Thread publicidad = new Thread(new Runnable() {
					boolean stop = false;

					@Override
					public void run() {
						
						while(!stop) {
						// TODO Auto-generated method stub
							long tiempoFini = System.currentTimeMillis();
							if(tiempoFini- tiempoIni >= 60000 && !stop) {
								System.out.println("entreeeeeeeeeeee");
								try {
									multicastPublisher("PUBLICIDAD");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								stop = true;
							}
						}
					}
					
				});
				publicidad.start();
			}
	
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	 public static void multicastPublisher(String multicastMessage) throws IOException {
		        socketMulti = new DatagramSocket();
		        group = InetAddress.getByName("230.0.0.0");
		        buf = multicastMessage.getBytes();
		 
		        DatagramPacket packet  = new DatagramPacket(buf, buf.length, group, 4446);
		        socketMulti.send(packet);
		        socketMulti.close();
		    }


	

}
