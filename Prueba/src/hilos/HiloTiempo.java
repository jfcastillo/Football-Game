package hilos;

import java.io.IOException;

import javax.swing.JOptionPane;

import interfaz.VentanaPrincipal;
import modelo.Cronometro;

public class HiloTiempo extends Thread {
	public static final long DORMIR = 1000;
	
	Cronometro crono;
	VentanaPrincipal principal;
	private boolean terminar;
	
	public HiloTiempo(VentanaPrincipal ppal) {
		crono= new Cronometro();
		terminar= false;
		principal=ppal;
	}
	
	
	@Override
	public void run() {
		while(!terminar) {
			if(crono.getMinutos()==0&&crono.getSegundos()==0) {
				JOptionPane.showMessageDialog(principal, "El partido va a iniciar, presione ok cuando esté listo.");
				
			}
			crono.avanzar();
			if (principal.getCliente().getId().equals("1")) {
				try {
					principal.getCliente().enviarDatos("#tiempo "+crono.getMinutos()+" "+crono.getSegundos());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			principal.refrescarReloj(crono.getMinutos(),crono.getSegundos());
			try {
				this.sleep(DORMIR);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(crono.getMinutos()==1&&crono.getSegundos()==0) {
			principal.reiniciarTodo();
			principal.mostrarMensajes(1);
			try {
				principal.getCliente().enviarDatos("#segundoTiempo");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			principal.segundoTiempo();
			}
			if(crono.getMinutos()==2) {
				try {
					String cadena = "";
					if (principal.getCliente().getId().equals("1")) {
//						cadena = "#termino "+principal.getCliente().getNickName()+" "+principal.getPanelGoles().getDerecha();
						cadena = "#termino "+principal.getCliente().getNickName()+" "+principal.getCliente().getContadorPublicidad();
						principal.getCliente().enviarDatos(cadena);
					}
					else {
//						cadena = "#termino "+principal.getCliente().getNickName()+" "+principal.getPanelGoles().getIzquierda();
					}
					principal.getCliente().enviarDatos(cadena);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				principal.mostrarMensajes(2);
//				principal.terminarPartido();
				terminar=true;
			}

		}
		
		
		
		
		
	}

	
	
	
	
	
	
	
	
}
