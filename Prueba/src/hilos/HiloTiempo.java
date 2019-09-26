package hilos;

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
			crono.avanzar();
			principal.refrescarReloj(crono.getMinutos(),crono.getSegundos());
			try {
				this.sleep(DORMIR);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(crono.getMinutos()==1&&crono.getSegundos()==0) {
			principal.reiniciarTodo();
			JOptionPane.showMessageDialog(principal, "Se termina el primer tiempo.");
			principal.segundoTiempo();
			}
			if(crono.getMinutos()==2) {
				JOptionPane.showMessageDialog(principal,"Se termina el partido.");
				principal.terminarPartido();
				terminar=true;
			}

		}
		
		
		
		
		
	}

	
	
	
	
	
	
	
	
}
