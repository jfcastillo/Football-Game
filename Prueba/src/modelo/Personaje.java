package modelo;

import java.io.Serializable;

/**
 * Esta clase representa las características del personaje
 **/
public class Personaje extends Imagen implements Movible{
	
	public final static int X_INICIAL_JUG1 = 255;
	public final static int X_INICIAL_JUG2 = 1050;
	public final static int Y_INICIAL=273;
	

	/**
	 * Constante que representa el ancho del personaje
	 */
	public final static int ANCHO = 32;

	/**
	 * Constante que representa el alto del personaje
	 */
	public final static int ALTO = 40;

	/**
	 * Constante que representa la ruta de la imagen del personaje
	 */
	public final static String IMAGEN = "./resources/jug.gif";
	
	public final static String IMAGEN2 = "./resources/Imagenes/personajes/personaje2Corre.gif";
	public final static String IMAGEN2ROTADA = "./resources/Imagenes/personajes/personaje2rotado.gif";
	public final static String IMAGEN3 = "./resources/Imagenes/personajes/personaje3Corre.gif";
	public final static String IMAGEN3ROTADA = "./resources/Imagenes/personajes/personaje3Correrotado.gif";

	/**
	 * Atributo que representa el movimiento hacia arriba del personaje
	 */
	private boolean mueveArriba;
	/**
	 * Atributo que representa el movimiento hacia abajo del personaje
	 */
	private boolean mueveAbajo;
	/**
	 * Atributo que representa el movimiento hacia la izquierda del personaje
	 */
	private boolean mueveIzquierda;
	/**
	 * Atributo que representa el movimiento hacia la derecha del personaje
	 */
	private boolean mueveDerecha;
	/**
	 * Atributo que representa la relación raizHabilidades con la clase Habilidades
	 */
	//private Habilidad raizHabilidad;

	//private boolean lanzandoHabilidad;
	//private Habilidad habilidadActual;

	private int vida;
	private int puntos;
	
	private int id;
	
	private boolean tieneBalon;
	
	private Ball balon;

	/**
	 * @param posicionX
	 * @param posicionY
	 * @param rutaImagen
	 * @param detenido
	 */
	public Personaje(int posicionX, int posicionY, String rutaImg, int id) {
		super(posicionX, posicionY, rutaImg);
		mueveArriba = false;
		mueveAbajo = false;
		mueveIzquierda = false;
		mueveDerecha = false;
		this.id = id;
		tieneBalon = false;
		balon = null;

	//	lanzandoHabilidad = false;

		// raizHabilidad = new Habilidades(5, Habilidades.BOLA_DE_PAPEL); vida = 100;
		puntos = 0;
		vida = 100;
	}
	
	
	

//	public boolean isLanzandoHabilidad() {
//		return lanzandoHabilidad;
//	}
//
//	public void setLanzandoHabilidad(boolean lanzandoHabilidad) {
//		this.lanzandoHabilidad = lanzandoHabilidad;
//	}

//	public Habilidad getHabilidadActual() {
//		return habilidadActual;
//	}
//
//	public void setHabilidadActual(Habilidad habilidadActual) {
//		this.habilidadActual = habilidadActual;
//	}
	

	public boolean isTieneBalon() {
		return tieneBalon;
	}


	public Ball getBalon() {
		return balon;
	}


	public void setBalon(Ball balon) {
		this.balon = balon;
	}


	public void setTieneBalon(boolean tieneBalon) {
		this.tieneBalon = tieneBalon;
	}


	public void sumarPuntos() {
		puntos = puntos + 3;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	/**
	 * @return raizHabilidad
	 */
//	public Habilidad getRaizHabilidad() {
//		return raizHabilidad;
//	}
//
//	/**
//	 * @param raizHabilidad
//	 */
//	public void setRaizHabilidad(Habilidad raizHabilidad) {
//		this.raizHabilidad = raizHabilidad;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return mueveArriba
	 */
	public boolean isMueveArriba() {
		return mueveArriba;
	}

	/**
	 * @param mueveArriba
	 */
	public void setMueveArriba(boolean mueveArriba) {
		this.mueveArriba = mueveArriba;
	}

	/**
	 * @return muevaAbajo
	 */
	public boolean isMueveAbajo() {
		return mueveAbajo;
	}

	/**
	 * @param mueveAbajo
	 */
	public void setMueveAbajo(boolean mueveAbajo) {
		this.mueveAbajo = mueveAbajo;
	}

	/**
	 * @return mueveIzquierda
	 */
	public boolean isMueveIzquierda() {
		return mueveIzquierda;
	}

	/**
	 * @param mueveIzquierda
	 */
	public void setMueveIzquierda(boolean mueveIzquierda) {
		this.mueveIzquierda = mueveIzquierda;
	}

	/**
	 * @return mueveDerecha
	 */
	public boolean isMueveDerecha() {
		return mueveDerecha;
	}

	/**
	 * @param mueveDerecha
	 */
	public void setMueveDerecha(boolean mueveDerecha) {
		this.mueveDerecha = mueveDerecha;
	}

	public int getVida() {
		return vida;
	}

	public void restarVida() {
		vida -= 5;
	}

	/**
	 * Nombre: moverDerecha. El metodo se encarga de aumentar la posicion del eje x
	 * del personaje
	 * 
	 * @param valor
	 */
	@Override
	public void moverDerecha(int valor) {
		posicionX += valor;

	}

	/**
	 * Nombre: moverIzquierda. El metodo se encarga de disminuir la posicion del eje
	 * x del personaje
	 * 
	 * @param valor
	 */
	@Override
	public void moverIzquierda(int valor) {
		posicionX -= valor;
	}

	/**
	 * Nombre: moverArriba. El metodo se encarga de disminuir la posicion del eje y
	 * del personaje
	 * 
	 * @param valor
	 */
	@Override
	public void moverArriba(int valor) {
		posicionY -= valor;
	}

	/**
	 * Nombre: moverAbajo. El metodo se encarga de aumentar la posicion del eje y
	 * del personaje
	 * 
	 * @param valor
	 */
	@Override
	public void moverAbajo(int valor) {
		posicionY += valor;
	}

	/**
	 * Nombre: mover. El metodo se encarga de modificar la posicion del personaje
	 * dado numero que entra por parámetro
	 * 
	 * @param dirección
	 *            - valor que indica que posición del personaje se quiere mover.
	 * 
	 *            <b>pre</b> El método ha modificado la posición del personaje.
	 *            <b>pos</b> El personaje ha cambiado su movimiento actual.
	 */
	@Override
	public void mover(int direccion) {

		
		
		if (direccion == 1) {
			moverIzquierda(DISTANCIA_QUE_SE_MUEVE_1);
			if (getRutaImagen().equals(IMAGEN3)) {
				setRutaImagen(IMAGEN3ROTADA);
			}
			else if (getRutaImagen().equals(IMAGEN2)) {
				setRutaImagen(IMAGEN2ROTADA);
			}
			
			
			direccionActual(1);
		}
		if (direccion == 2) {
			moverDerecha(DISTANCIA_QUE_SE_MUEVE_1);
			if (getRutaImagen().equals(IMAGEN3ROTADA)) {
				setRutaImagen(IMAGEN3);
			}
			else if (getRutaImagen().equals(IMAGEN2ROTADA)) {
				setRutaImagen(IMAGEN2);
			}
			
			direccionActual(2);
		}
		if (direccion == 3) {
			moverArriba(DISTANCIA_QUE_SE_MUEVE_1);
			direccionActual(3);
		}
		if (direccion == 4) {
			moverAbajo(DISTANCIA_QUE_SE_MUEVE_1);
			direccionActual(4);
		}
		if (tieneBalon) {
			balon.mover(direccion);
		}
	}

	/**
	 * Nombre: empujar. El método se encarga de modificar la posicion del personaje
	 * 
	 * <b>pre</b> El método ha modificado la posición del personaje.
	 * 
	 * <b>pos</b> El personaje ha cambiado su movimiento actual.
	 */
	@Override
	public void empujar() {
		if (mueveArriba) {
			moverAbajo(1);
		}
		if (mueveAbajo) {
			moverArriba(1);
		}
		if (mueveDerecha) {
			moverIzquierda(1);
		}
		if (mueveIzquierda) {
			moverDerecha(1);
		}
	}

	/**
	 * Nombre: direccionActual. El método se encarga de modificar las posiciones
	 * donde el personaje pueda moverse cuando lo obstruye algún elemento del mapa.
	 * 
	 * @param dire
	 *            - valor que indica que posición del personaje se quiere modificar.
	 *
	 *            <b>pos</b> El método ha modificado las posiciones del personaje.
	 */
	@Override
	public void direccionActual(int dire) {
		switch (dire) {
		case 1:
			setMueveIzquierda(true);
			setMueveDerecha(false);
			setMueveArriba(false);
			setMueveAbajo(false);
			break;
		case 2:
			setMueveDerecha(true);
			setMueveIzquierda(false);
			setMueveAbajo(false);
			setMueveArriba(false);
			break;
		case 3:
			setMueveArriba(true);
			setMueveDerecha(false);
			setMueveIzquierda(false);
			setMueveAbajo(false);
			break;
		case 4:
			setMueveAbajo(true);
			setMueveArriba(false);
			setMueveDerecha(false);
			setMueveIzquierda(false);
			break;
		}
	}

	public int darDireccionActual() {
		if (mueveArriba) {
			return DIRECCION_ARRIBA;
		} else if (mueveAbajo) {
			return DIRECCION_ABAJO;
		} else if (mueveDerecha) {
			return DIRECCION_DERECHA;
		} else if (mueveIzquierda) {
			return DIRECCION_IZQUIERDA;
		} else {
			return 0;
		}

	}

//	public void agregarHabilidad(Habilidad h) {
//		if (raizHabilidad == null) {
//			raizHabilidad = h;
//
//		} else {
//			raizHabilidad.agregar(h);
//		}
//
//		habilidadActual = h;
//	}

//	/**
//	 * Nombre: buscarHabilidad. El método se encarga de recorrer el arbol de
//	 * habilidades para buscar una en especifico.
//	 * 
//	 * @param nombre
//	 *            - String que representa al nombre de la habilidad a buscar
//	 *
//	 *            <b>pos</b> Se ha encontrado la habilidad con el nombre requerido
//	 * 
//	 * @return Habilidades
//	 */
//	public Habilidad buscarHabilidad(String nombre) {
//		return (raizHabilidad == null) ? null : raizHabilidad.buscar(nombre);
//	}
//
//	/**
//	 * Nombre: eliminarHabilidad. El método se encarga de recorrer el arbol de
//	 * habilidades para eliminar una en especifico.
//	 * 
//	 * @param nombre
//	 *            - String que representa al nombre de la habilidad a eliminar
//	 *
//	 *            <b>pos</b> Se ha eliminado la habilidad con el nombre requerido
//	 */
//	public void eliminarHabilidad(String nombre) {
//		Habilidad habil = buscarHabilidad(nombre);
//		Habilidad papa = habil.getPadre();
//		if (habil.esHoja()) {
//			if (habil.esHijoIzquierdo()) {
//				papa.setRamaIzquierda(null);
//			} else {
//				papa.setRamaDerecha(null);
//			}
//		} else if (habil.esHijoIzquierdo()) {
//			if (habil.tieneHijoIzquierdo() && !habil.tieneHijoDerecho()) {
//				papa.setRamaIzquierda(habil.getRamaIzquierda());
//			} else if (!habil.tieneHijoIzquierdo() && habil.tieneHijoDerecho()) {
//				papa.setRamaIzquierda(habil.getRamaDerecha());
//			} else if (habil.tieneHijoIzquierdo() && habil.tieneHijoDerecho()) {
//				papa.setRamaIzquierda(habil.sucesor());
//			}
//		} else if (habil.esHijoDerecho()) {
//			if (habil.tieneHijoIzquierdo() && !habil.tieneHijoDerecho()) {
//				papa.setRamaDerecha(habil.getRamaIzquierda());
//			} else if (!habil.tieneHijoIzquierdo() && habil.tieneHijoDerecho()) {
//				papa.setRamaDerecha(habil.getRamaDerecha());
//			} else if (habil.tieneHijoIzquierdo() && habil.tieneHijoDerecho()) {
//				papa.setRamaDerecha(habil.sucesor());
//			}
//		}
//	}
//
//	public void recorridoPreOrden(Partida aux) {
//		if (aux != null) {
//			System.out.print("" + aux.getUsuario().getNombre());
//			recorridoPreOrden(aux.getRamaIzquierda());
//			recorridoPreOrden(aux.getRamaDerecha());
//		}
//	}
//
//	public void lanzarHabilidad(int actual) {
//		if (actual == DIRECCION_ARRIBA) {
//			habilidadActual.mover(DIRECCION_ARRIBA);
//		} else if (actual == DIRECCION_ABAJO) {
//			habilidadActual.mover(DIRECCION_ABAJO);
//		} else if (actual == DIRECCION_DERECHA) {
//			habilidadActual.mover(DIRECCION_DERECHA);
//
//		} else if (actual == DIRECCION_IZQUIERDA) {
//			habilidadActual.mover(DIRECCION_IZQUIERDA);
//		}
//	}
//
//	public void actualizarHabilidadActual() {
//		if (puntos < 11) {
//			agregarHabilidad(new Habilidad(Habilidad.DANIO_REALENTIZAR, Habilidad.BOLA_DE_PAPEL, posicionX, posicionY));
//		} else if (puntos >= 11 && puntos < 26) {
//			agregarHabilidad(new Habilidad(Habilidad.DANIO_CONGELAR, Habilidad.PARCIAL, posicionX, posicionY));
//		} else if (puntos >= 26) {
//			agregarHabilidad(new Habilidad(Habilidad.DANIO_REAPARECER, Habilidad.CACA, posicionX, posicionY));
//		}
//	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub

	}

}
