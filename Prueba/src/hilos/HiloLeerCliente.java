package hilos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import modelo.Cliente;

/**
 * Hilo para estar pendiente de cuando el servidor envia un mensaje al cliente
 */
public class HiloLeerCliente extends Thread {
	
	private Cliente cliente;
	private BufferedReader br;
	private BufferedWriter bw;
	private DataInputStream in;
	private DataOutputStream out;
	
	
	

	public HiloLeerCliente(Cliente cliente, BufferedReader br, BufferedWriter bw, DataInputStream in,
			DataOutputStream out) {
		
		this.cliente = cliente;
		this.br = br;
		this.bw = bw;
		this.in = in;
		this.out = out;
	}


	@Override
	public void run() {
		while(cliente.isConnected()) {
			try { 
                // read the message sent to this client 
				String msg = in.readUTF(); 
				if (msg.contains("#idCliente")) {
					cliente.setId(msg.split(" ")[1]);
					cliente.setIdRecibido(true);
//					System.out.println("recibi el id "+cliente.getId());
					
				}            	
            	else {            	
//            		System.out.println("Al cliente "+cliente.getNickName()+" le llego el mensaje "+msg);
            		cliente.recibirDatos(msg);            		
            		
            	}
            } catch (IOException e) { 

                e.printStackTrace(); 
            }
//			try {
//				in.close();
//				out.close();
//				br.close();
//				bw.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}
	}
	


	public Cliente getCliente() {
		return cliente;
	}




	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}




	public BufferedReader getBr() {
		return br;
	}




	public void setBr(BufferedReader br) {
		this.br = br;
	}




	public BufferedWriter getBw() {
		return bw;
	}




	public void setBw(BufferedWriter bw) {
		this.bw = bw;
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




	


}
