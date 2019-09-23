package modelo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

class ClientHandler implements Runnable  
{ 
    Scanner scn = new Scanner(System.in); 
    private String name; 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    Socket s; 
    boolean isloggedin; 
    boolean nombreEnviado;
      
    // constructor 
    public ClientHandler(Socket s, String name, 
                            DataInputStream dis, DataOutputStream dos) { 
        this.dis = dis; 
        this.dos = dos; 
        this.name = name; 
        this.s = s; 
        this.isloggedin=true; 
        nombreEnviado = false;
    } 
  
    @Override
    public void run() { 
  
        String received; 
        while (true)  
        { 
            try
            { 
                // receive the string 
                received = dis.readUTF(); 
                  
                System.out.println(received); 
                  
                if(received.equals("logout")){ 
                    this.isloggedin=false; 
                    this.s.close(); 
                    break; 
                } 
                
                if (!nombreEnviado) {
					
                	
                	
                    for (ClientHandler mc : Servidor.ar)  
                    { 
                        // if the recipient is found, write on its 
                        // output stream 
                        if (mc.name.equals(name) && mc.isloggedin==true)  
                        { 
                            mc.dos.writeUTF(name); 
                            break; 
                        } 
                    }
                    nombreEnviado = true;
				}
                else {
                	
                	// break the string into message and recipient part 
                	StringTokenizer st = new StringTokenizer(received, "#"); 
                	String MsgToSend = st.nextToken(); 
                	String recipient = st.nextToken(); 
                	
                	// search for the recipient in the connected devices list. 
                	// ar is the vector storing client of active users 
                	for (ClientHandler mc : Servidor.ar)  
                	{ 
                		// if the recipient is found, write on its 
                		// output stream 
                		if (mc.name.equals(recipient) && mc.isloggedin==true)  
                		{ 
                			mc.dos.writeUTF(MsgToSend); 
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