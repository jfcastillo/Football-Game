package hilos;

import interfaz.VentanaPrincipal;
import modelo.Ball;
import modelo.Personaje;

public class HiloPatearPelota extends Thread {
	
	private Personaje[] personajes;
	private VentanaPrincipal vPrincipal;
	private Ball pelota;
	private int id;

	public HiloPatearPelota(Personaje []personajes, VentanaPrincipal vPrincipal, Ball pelota, int i) {
		this.personajes = personajes;
		this.vPrincipal = vPrincipal;
		this.pelota = pelota;
		this.id = id;
	}
	@Override
	public void run() {
		boolean si = true;
		int c = 0;
		while(si) {

			
				
				vPrincipal.patearPelota(personajes[id].darDireccionActual());
				//pelota.mover(personajes[id].darDireccionActual());
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
				c++;
				if (c==10) {
					si = false;
				}
			
			
		}
	}

	

}
