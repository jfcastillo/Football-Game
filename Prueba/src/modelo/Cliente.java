package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

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
	
	/**
	 * Main
	 * @param args
	 */
	
	public Cliente() {
		
		direccionMovimiento = 0;
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
	        Thread sendMessage = new Thread(new Runnable()  
	        { 
	            @Override
	            public void run() { 
	                while (true) { 
	                    try { 
		                    String msg = br.readLine();
	                        out.writeUTF(msg); 
	                    } catch (IOException e) { 
	                        e.printStackTrace(); 
	                    } 
	                } 
	            } 
	        }); 
	          
	        // readMessage thread 
	        Thread readMessage = new Thread(new Runnable()  
	        { 
	            @Override
	            public void run() { 
	  
	                while (true) { 
	                    try { 
	                        // read the message sent to this client 
	                        String msg = in.readUTF(); 
	                        recibirDatos(msg);
	                        System.out.println(msg); 
	                    } catch (IOException e) { 
	  
	                        e.printStackTrace(); 
	                    } 
	                } 
	            } 
	        }); 
	  
	        sendMessage.start(); 
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
		out.writeUTF(cadena+"#"+"1"); 
	}
	
	public void recibirDatos(String cadena) {
		direccionMovimiento = Integer.parseInt(cadena);
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



}