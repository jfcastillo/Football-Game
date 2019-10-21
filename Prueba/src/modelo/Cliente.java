package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

import hilos.HiloLeerCliente;
import interfaz.VentanaPrincipal;

public class Cliente {
	
	/*
	 * 
	 * Direccion local de la maquina
	 */
	public static final String LOCAL_HOST = "localhost";
	/**
	 * Puerto por donde se establecera la conexion
	 */
	public static final int PORT = 8000; 
	/**
	 * Socket que permitira la conexion con el servidor
	 */
	private static Socket socket;
	
	private DataInputStream in;
	private DataOutputStream out;
	
	private int direccionMovimiento;
	private String nickName;
	
	private String id;
	private boolean idRecibido;
	
	private boolean isConnected;
	
	//variables para el multicast UDP
	protected MulticastSocket socketMulti = null;
    protected byte[] buf = new byte[768];
    
    protected String [] datos;
    
    private HiloLeerCliente hiloLeer;
	
	private VentanaPrincipal vPrincipal;
	
	/**
	 * Main
	 * @param args
	 */
	
	public Cliente(VentanaPrincipal vPrincipal, String nickName) {
		id = "-1";
		this.vPrincipal = vPrincipal;
		this.nickName = nickName;
		isConnected = true;
		direccionMovimiento = 0;
		idRecibido = false;
		datos = new String[2];
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader( System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			System.out.println("::Cliente disponible para ser atendido::");
			
			socket = new Socket(LOCAL_HOST, PORT);			
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(nickName);
			
			hiloLeer = new HiloLeerCliente(this, br, bw, in, out);
			hiloLeer.start();
			
	        // readMessage thread 
			//hilo para estar pendiente cuando el servidor envíe un mensaje al cliente
//	        Thread readMessage = new Thread(new Runnable()  
//	        { 
//	            @Override
//	            public void run() { 
//	  
//	                while (true) { 
//	                    try { 
//	                        // read the message sent to this client 
//	                    	
//	                    	if(!idRecibido) {
//	                    		id = in.readUTF();
//	                    		idRecibido= true;
//	                    		System.out.println(id);
//	                    
//	                    	
//	                    	}
//	                    	else {
//	                    		String msg = in.readUTF(); 
//	                    		recibirDatos(msg);
//	                    		System.out.println(msg); 
//	                    		
//	                    	}
//	                    } catch (IOException e) { 
//	  
//	                        e.printStackTrace(); 
//	                    } 
//	                } 
//	            } 
//	        });
			
//			Thread receiveMulti = new Thread(new Runnable() {
//
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//
//					try {
//						socketMulti = new MulticastSocket(4446);
//						InetAddress group = InetAddress.getByName("230.0.0.0");
//						socketMulti.joinGroup(group);
//						while (true) {
//							DatagramPacket packet = new DatagramPacket(buf, buf.length);
//							socketMulti.receive(packet);
//							String received = new String(
//									packet.getData(), 0, packet.getLength());
//							if ("PUBLICIDAD".equals(received)) {
//								System.out.println(received);
//								break;
//							}
//						}
//						socketMulti.leaveGroup(group);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			        socketMulti.close();
//				}
//
//
//	        });
	        
	        Thread receiveMulti = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					int contador = 0;
					try {
						socketMulti = new MulticastSocket(4446);
						InetAddress group = InetAddress.getByName("230.0.0.0");
						socketMulti.joinGroup(group);
						while (contador <2 ) {
							DatagramPacket packet = new DatagramPacket(buf, buf.length);
							socketMulti.receive(packet);
							String received = new String(
									packet.getData(), 0, packet.getLength());
							if (received != null) {
								datos[contador]= received;
								contador++;
							}
						}
						socketMulti.leaveGroup(group);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        socketMulti.close();
			        vPrincipal.activarPublicidad();
				}
	        	
	        	
	        });
	  
//	        sendMessage.start(); 
//	        readMessage.start(); 
	        receiveMulti.start();
		
	        /*
			bw.write("Su encriptacion fue : " + mensajeDelServidor);
			bw.flush();
			bw.close();
			br.close();
			
			in.close();
			out.close();
			
			socket.close();
	         */

		} catch (Exception e) {
		}
	}
	
	public String[] getDatos() {
		return datos;
	}
	
	public boolean isConnected() {
		return isConnected;
	}


	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}


	public void enviarDatos(String cadena) throws IOException {
//		int idC = Integer.parseInt(id);
//		if (idC % 2 == 0) {
//			idC = 0;
//		}
//		else {
//			idC = 1;
//		}
////		if(this.id.equals("0")) {
////			out.writeUTF(cadena+"#"+"1"); 
////		}
////		else if(this.id.equals("1")) {
////			out.writeUTF(cadena+"#"+"0"); 
////		}
//		if(idC == 0) {
//			out.writeUTF(cadena+"#"+"1"); 
//		}
//		else if(idC == 1) {
//			out.writeUTF(cadena+"#"+"0"); 
//		}
		out.writeUTF(cadena);
	}
	
	public void recibirDatos(String cadena) {
		
		vPrincipal.recibirPosiciones(cadena);
	}
	
	public static Socket getSocket() {
		return socket;
	}

	public static void setSocket(Socket socket) {
		Cliente.socket = socket;
	}

	public DataInputStream getIn() {
		return in;
	}

	public void setIn(DataInputStream in) {
		this.in = in;
	}

	public DataOutputStream getOut() {
		return out;
	}

	public void setOut(DataOutputStream out) {
		this.out = out;
	}

	public int getDireccionMovimiento() {
		return direccionMovimiento;
	}

	public void setDireccionMovimiento(int direccionMovimiento) {
		this.direccionMovimiento = direccionMovimiento;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNombre(String nombreJugador) {
		nickName= nombreJugador;
		
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}


	public boolean isIdRecibido() {
		return idRecibido;
	}


	public void setIdRecibido(boolean idRecibido) {
		this.idRecibido = idRecibido;
	}
	
	

	


}