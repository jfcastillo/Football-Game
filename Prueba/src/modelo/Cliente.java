package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

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
	
	private VentanaPrincipal vPrincipal;
	
	/**
	 * Main
	 * @param args
	 */
	
	public Cliente(VentanaPrincipal vPrincipal) {
		id = "-1";
		this.vPrincipal = vPrincipal;
		
		direccionMovimiento = 0;
		idRecibido = false;
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader( System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			System.out.println("::Cliente disponible para ser atendido:: \nIngrese "
					+ "el mensaje para encriptar!!::");
			
			socket = new Socket(LOCAL_HOST, PORT);
		//	String mensaje = br.readLine();			
			
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
									
			// sendMessage thread 
//	        Thread sendMessage = new Thread(new Runnable()  
//	        { 
//	            @Override
//	            public void run() { 
//	                while (true) { 
//	                    try { 
//		                    String msg = br.readLine();
//	                        out.writeUTF(msg); 
//	                    } catch (IOException e) { 
//	                        e.printStackTrace(); 
//	                    } 
//	                } 
//	            } 
//	        }); 
	          
	        // readMessage thread 
			//hilo para estar pendiente cuando el servidor envíe un mensaje al cliente
	        Thread readMessage = new Thread(new Runnable()  
	        { 
	            @Override
	            public void run() { 
	  
	                while (true) { 
	                    try { 
	                        // read the message sent to this client 
	                    	
	                    	if(!idRecibido) {
	                    		id = in.readUTF();
	                    		idRecibido= true;
	                    		System.out.println(id);
	                    
	                    	
	                    	}
	                    	else {
	                    		String msg = in.readUTF(); 
	                    		recibirDatos(msg);
	                    		//System.out.println(msg); 
	                    		
	                    	}
	                    } catch (IOException e) { 
	  
	                        e.printStackTrace(); 
	                    } 
	                } 
	            } 
	        }); 
	  
//	        sendMessage.start(); 
	        readMessage.start(); 
		
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
	
	public void enviarDatos(String cadena) throws IOException {
		if(this.id.equals("0")) {
			out.writeUTF(cadena+"#"+"1"); 
		}
		else if(this.id.equals("1")) {
			out.writeUTF(cadena+"#"+"0"); 
		}
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
	
	

	


}