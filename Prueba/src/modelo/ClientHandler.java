package modelo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable  
{ 
    Scanner scn = new Scanner(System.in); 
    private String name; 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    Socket s; 
    boolean isloggedin; 
    boolean nombreEnviado;
    String positions;
    String recipient;
      
    // constructor 
    public ClientHandler(Socket s, String name, 
                            DataInputStream dis, DataOutputStream dos) { 
        this.dis = dis; 
        this.dos = dos; 
        this.name = name; 
        this.s = s; 
        this.isloggedin = true; 
        nombreEnviado = false;
        positions = "";
        recipient = "";
    } 
  
    @Override
    public void run() { 
  
        String received;
        boolean stop = true;
        while (stop)  
        { 
            try
            {         	
            	
//            	
//                // receive the string 
//                received = dis.readUTF(); 
//                  
//                System.out.println(received); 
//                  
//                if(received.equals("logout")){ 
//                    this.isloggedin=false; 
//                    this.s.close(); 
//                    break; 
//                } 
                
                if(!nombreEnviado) {
                	
                	positions = this.name;
                	recipient = this.name;
                	
                	// search for the recipient in the connected devices list. 
                    // ar is the vector storing client of active users 
                    for (ClientHandler mc : Servidor.ar)  
                    { 
                        // if the recipient is found, write on its 
                        // output stream 
                        if (mc.name.equals(recipient) && mc.isloggedin==true)  
                        { 
                            mc.dos.writeUTF(positions); 
                            nombreEnviado = true;
                            break; 
                        } 
                    }
                }
                else {
                	
                	received = dis.readUTF();
                	// break the string into message and recipient part 
                	String [] cadena = received.split("#"); 
                	positions = cadena[0]; 
                	recipient = cadena[1]; 
                	
                	// search for the recipient in the connected devices list. 
                	// ar is the vector storing client of active users 
                	for (ClientHandler mc : Servidor.ar)  
                	{ 
                		// if the recipient is found, write on its 
                		// output stream 
                		if (mc.name.equals(recipient) && mc.isloggedin==true)  
                		{ 
                			mc.dos.writeUTF(positions); 
                			break; 
                		} 
                	} 
                }
                
                  
            } catch (IOException e) { 
                  
                e.printStackTrace(); 
            } 
              
        }
        try
        { 
            // closing resources 
            this.dis.close(); 
            this.dos.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
              
       
         
           
          
       
    } 
} 