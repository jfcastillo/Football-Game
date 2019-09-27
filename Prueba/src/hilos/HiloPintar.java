package hilos;

import interfaz.VentanaPrincipal;

public class HiloPintar extends Thread {
	
	private VentanaPrincipal vPrincipal;
	
	public HiloPintar(VentanaPrincipal vPrincipal) {
		this.vPrincipal = vPrincipal;
	}
	@Override
	public void run() {
		while(true) {
			vPrincipal.refrescar();
		}
	}
	

}
