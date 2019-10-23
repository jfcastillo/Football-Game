package hilos;

import interfaz.VentanaPrincipal;
import modelo.Ball;
import modelo.Personaje;

public class HiloPatearPelota extends Thread {
	
	private Personaje[] personajes;
	private VentanaPrincipal vPrincipal;
	private Ball pelota;
	private int id;
	private boolean stop;

	public HiloPatearPelota(Personaje []personajes, VentanaPrincipal vPrincipal, Ball pelota, int id) {
		this.personajes = personajes;
		this.vPrincipal = vPrincipal;
		this.pelota = pelota;
		this.id = id;
		stop = false;
	}
	@Override
	public void run() {
//		boolean si = true;
		int c = 0;
		while(!stop) {
			
				
//				vPrincipal.patearPelota();
				pelota.mover(personajes[id].darDireccionActual(),8);
				vPrincipal.moverPelota(personajes[id].darDireccionActual());
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();				
				}
				c++;
				if (c==20) {
					stop = true;
				}
			
			
		}
		personajes[id].setTieneBalon(false);
		
	}
	public boolean isStop() {
		return stop;
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}

	
	

}
