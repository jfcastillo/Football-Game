package modelo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
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
	
	public static void main(String[] args) {
		
		DataInputStream in;
		DataOutputStream out;
		
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("::Servidor escuchando a los posibles clientes::");
			int clave = (int) (Math.random() * 20) + 1;

			
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
				//out.writeUTF(clave+"");
				
			//	socket.close();
			//	System.out.println("::El cliente fue desconectado del server::");
			}
	
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	  public static String convertStringToHex(String str){
		  char[] chars = str.toCharArray();  
		  StringBuffer hex = new StringBuffer();
		  for(int i = 0; i < chars.length; i++){
		    hex.append(Integer.toHexString((int)chars[i]));
		  } 
		  return hex.toString();
	  }

	
	/**
	 * Metodo encargado de realizar la encriptacion de Cesar, sumando 2 posiciones al ASCII
	 * de cada caracter
	 * @param mensajeObtenidoCliente != Null && != ""
	 * @return
	 */
	private static String metodoServicioServer(String mensajeObtenidoCliente) {
		
		String respuesta = "";
		char caracter ;
		
		if(mensajeObtenidoCliente != "") {
			for (int i = 0; i < mensajeObtenidoCliente.length(); i++) {
				caracter = mensajeObtenidoCliente.charAt(i);
				caracter = (char) (caracter +3);
				respuesta += Character.toString((caracter)) + "";
			}
		}
		else {
			respuesta = "::El cliente no envio texto para encriptar::";
		}
		return respuesta;
		
	}

}