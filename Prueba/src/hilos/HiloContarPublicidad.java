package hilos;

import interfaz.VentanaPrincipal;

public class HiloContarPublicidad  extends Thread implements Runnable{
	private VentanaPrincipal vPincipal;
	private boolean stop;

	public HiloContarPublicidad(VentanaPrincipal vPincipal) {
		super();
		this.vPincipal = vPincipal;
		stop = false;
	}
	
	@Override
	public void run() {
		while(!stop) {
			vPincipal.getCliente().setContadorPublicidad(1);
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	

}
