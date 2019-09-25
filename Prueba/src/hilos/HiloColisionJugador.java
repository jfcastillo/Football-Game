package hilos;

import java.awt.Rectangle;

import interfaz.VentanaPrincipal;
import modelo.Ball;
import modelo.Bloque;
import modelo.ObjetoMapa;
import modelo.Personaje;
import hilos.HiloColisionPelota;

public class HiloColisionJugador extends Thread {

	private Personaje[] personajes;
	private ObjetoMapa objeto;
	private VentanaPrincipal vPrincipal;
	private Ball balon;

	public HiloColisionJugador(Personaje []personajes, ObjetoMapa objeto, VentanaPrincipal vPrincipal, Ball bal) {
		super();
		this.personajes = personajes;
		this.objeto = objeto;
		this.vPrincipal = vPrincipal;
		balon = bal;

	}

	@Override
	public void run() {

		while (vPrincipal.isPlaying()) {
			Rectangle areaPel = new Rectangle(balon.getPosicionX(), balon.getPosicionY(),
					Personaje.ANCHO, Personaje.ALTO);
			
			for (int i = 0; i < personajes.length; i++) {
				
				Rectangle areaJugador = new Rectangle(personajes[i].getPosicionX(), personajes[i].getPosicionY(),
						Personaje.ANCHO, Personaje.ALTO);
				
				ObjetoMapa aux = objeto;
				while (aux != null) {
					
					if (aux instanceof Bloque) {
						Rectangle rectanguloBloque = new Rectangle(aux.getPosicionX(), aux.getPosicionY(), Bloque.ANCHO,
								Bloque.ANCHO);
						if (areaJugador.intersects(rectanguloBloque)) {
							personajes[i].empujar();
						}
						if (areaPel.intersects(rectanguloBloque)) {
							balon.empujar();
						}
						if(areaJugador.intersects(areaPel)) {
							System.out.println("entre");
//							balon.empujar();
							vPrincipal.moverPelota(personajes[i].darDireccionActual());
//							balon.mover(personajes[i].darDireccionActual());
//							HiloColisionPelota hiloColisionP = new HiloColisionPelota(personajes, objeto, vPrincipal, balon, i);
//					
//							hiloColisionP.start();
						}
					}
					
//				if (aux instanceof Item) {
//
//					if (((Item) aux).getTipo() == Item.COMIDA || ((Item) aux).getTipo() == Item.MALETIN) {
//						Rectangle rectanguloItem = new Rectangle(aux.getPosicionX(), aux.getPosicionY(), Item.ANCHO,
//								Item.ANCHO);
//						if (rectanguloDestroyer.intersects(rectanguloItem)) {
//							personaje.sumarPuntos();
//							vPrincipal.aumentarPuntaje();
//							vPrincipal.eliminarObjetoMapa(aux.getIdentificador());
//							vPrincipal.cambiarImagenHabilidad();
//						}
//					}
//				}
					
					aux = aux.getSiguiente();
				}
			}


		}
	}

}
