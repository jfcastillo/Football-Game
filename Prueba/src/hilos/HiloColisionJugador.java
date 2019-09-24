package hilos;

import java.awt.Rectangle;

import interfaz.VentanaPrincipal;
import modelo.Bloque;
import modelo.ObjetoMapa;
import modelo.Personaje;

public class HiloColisionJugador extends Thread {

	private Personaje[] personajes;
	private ObjetoMapa objeto;
	private VentanaPrincipal vPrincipal;

	public HiloColisionJugador(Personaje []personajes, ObjetoMapa objeto, VentanaPrincipal vPrincipal) {
		super();
		this.personajes = personajes;
		this.objeto = objeto;
		this.vPrincipal = vPrincipal;

	}

	@Override
	public void run() {

		while (vPrincipal.isPlaying()) {
			
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
