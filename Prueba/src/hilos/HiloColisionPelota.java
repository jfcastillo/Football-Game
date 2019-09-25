package hilos;

import java.awt.Rectangle;

import interfaz.VentanaPrincipal;
import modelo.Ball;
import modelo.Bloque;
import modelo.ObjetoMapa;
import modelo.Personaje;

public class HiloColisionPelota extends Thread {

	
	private Personaje[] personajes;
	private ObjetoMapa objeto;
	private VentanaPrincipal vPrincipal;
	private Ball pelota;
	private int id;

	public HiloColisionPelota(Personaje []personajes, ObjetoMapa objeto, VentanaPrincipal vPrincipal, Ball pelota, int id) {
		super();
		this.personajes = personajes;
		this.objeto = objeto;
		this.vPrincipal = vPrincipal;
		this.pelota = pelota;
		this.id = id;

	}

	@Override
	public void run() {
		boolean si = true;
		while(si) {

			for (int i = 0; i < 10; i++) {
				
				vPrincipal.moverPelota(personajes[id].darDireccionActual());
				//pelota.mover(personajes[id].darDireccionActual());
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
			}
			si = false;
		}
	}
}
