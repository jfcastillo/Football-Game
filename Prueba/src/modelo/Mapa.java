package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
 * Esta clase se encarga leer los objetos y guardarlos
 **/
public class Mapa{
	/**
	 * Relación que representa la lista del ObjetoMapa
	 */

	// ------------------------------
	// DECLARACIÓN DE CONSTANTES
	// ------------------------------

	/**
	 * Fondo del mapa del primer nivel
	 */
	public static final String FONDO_NIVEL_1 = "./recursos/Imagenes/bloques/FondoMapa.jpg";

	/**
	 * Ancho del mapa en pixeles
	 */
	public static final int ANCHO = 1300;

	/**
	 * Alto del mapa en pixeles
	 */
	public static final int ALTO = 700;

	public boolean isPlaying;
	
	private Cronometro crono;
	
	private ObjetoMapa primerObjetoMapa;
	/**
	 * Relación que representa la clase Personaje
	 */
	private Personaje[] personajes;
	
	private Ball pelota;
	
	/*
	 * 0 jugador 0
	 * 1 jugador 1
	 */
	private int[] marcador;
	/**
	 * Relación que representa la lista de Enemigo
	 */
//	private Enemigo primerEnemigo;

	/**
	 * Atributo que representa la cantidad de objetos
	 */
	private int cantidadObjetos;

	

	/**
	 * Atributo que representa el nivel del Mapa
	 */
	private int nivel;

	/**
	 * Constructor de la clase Mapa. Se encarga de inicializar las relaciones y
	 * atributos de la clase.
	 */
	public Mapa() {
		primerObjetoMapa = null;
	//	primerEnemigo = null;
		cantidadObjetos = 0;
		personajes = new Personaje[2];
		this.nivel = 1;
		marcador = new int [2];
		
	}

	/**
	 * @return personaje
	 */
	public Personaje[] getPersonaje() {
		return personajes;
	}
	

	public int[] getMarcador() {
		return marcador;
	}

	public void setMarcador(int[] marcador) {
		this.marcador = marcador;
	}

	public Ball getPelota() {
		return pelota;
	}
	/**
	 * @param direccion
	 */
	public void mover(int direccion, int id) {
		personajes[id].mover(direccion);
	}
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	public void setPlaying(boolean value) {
		isPlaying = value;
	}

	/**
	 * Metodo: Leer mapa Se encarga de leer un archivo de texto en el cual esta
	 * representado el mapa por medio de caracteres. Hace el llamado al metodo
	 * leerLineaDeMapa.
	 * 
	 * @throws IOException
	 */
	public void leerMapa() throws IOException {
		int contadorLineas = 0;
		BufferedReader br = new BufferedReader(new FileReader(new File("./resources/mapas/nivel1.txt")));
		String line = "";
		while ((line = br.readLine()) != null) {
//			System.out.println(line);
			leerLineaDeMapa(line, contadorLineas);
			contadorLineas++;
		}
		br.close();
	}

	/**
	 * Metodo: LeerLineaDeMapa Se encarga de leer un String para identificar cada
	 * caracter que Hace el llamado al metodo leerCaracter.
	 * 
	 * @param line
	 *            : Linea que se va a leer
	 * @param contadorLineas:
	 */
	private void leerLineaDeMapa(String line, int contadorLineas) {
		for (int i = 0; i < line.length(); i++) {
			leerCaracter(line.charAt(i), i, contadorLineas);
		}
	}

	/**
	 * Metodo: LeerCaracter Se encarga de identificar que tipo de objeto esta
	 * representado por el caracter que entra por parametro.
	 * 
	 * @param c:
	 *            Caracter para identificar
	 * @param i:
	 *            identificador de posicion
	 * @param contadorLineas
	 */
	private void leerCaracter(char c, int i, int contadorLineas) {
		Imagen o = null;
		Random r = new Random();

		switch (c) {
		case 'B':
			o = new Bloque(i * Bloque.ANCHO, contadorLineas * Bloque.ALTO, Bloque.BLOQUE, cantidadObjetos);
			cantidadObjetos++;
			break;
		case 'L':
			o = new Bloque(i * Bloque.ANCHO, contadorLineas * Bloque.ALTO, Bloque.LINEA, cantidadObjetos);
			cantidadObjetos++;
			break;
		case 'O':
			o = new Ball(Ball.X_INICIAL_BALON, Ball.Y_INICIAL_BALON, Ball.RUTA);
			break;
//		case 'C':
//			o = new Item(i * Item.ANCHO, contadorLineas * Item.ALTO, "imagen de un Item", cantidadObjetos, Item.COMIDA);
//			cantidadObjetos++;
//			break;
//		case 'E':
//			o = new Enemigo(i * 32, contadorLineas * 32, Enemigo.RUTA + (r.nextInt(1) + 1) + ".gif", cantidadEnemigos);
//			cantidadEnemigos++;
//			break;
		case '*':
			o = new Personaje(Personaje.X_INICIAL_JUG1, Personaje.Y_INICIAL, Personaje.IMAGEN3, 0);
			
			break;
		case '#':
			o = new Personaje(Personaje.X_INICIAL_JUG2, Personaje.Y_INICIAL, Personaje.IMAGEN2ROTADA, 1);
			break;
		}

		if (o != null) {
			if (o instanceof Bloque) {
//				System.out.println("BLOQUE");
				agregarObjeto(primerObjetoMapa, (Bloque) o);
			}

//			if (o instanceof Item) {
//				System.out.println("ITEM");
//
//				agregarObjeto(primerObjetoMapa, (Item) o);
//			}
			if (o instanceof Personaje && ((Personaje) o).getId() == 0) {
				personajes[0] = (Personaje) o;
			}
			if (o instanceof Personaje && ((Personaje) o).getId() == 1) {
				personajes[1] = (Personaje) o;
			}
			if (o instanceof Ball) {
				pelota = (Ball) o;
			}
		}
	}

	/**
	 * Metodo: agregarObjeto Se encarga de agregar un objeto tipo ObjetoMapa a la
	 * lista enlazada primerObjeo.
	 * 
	 * @param o:
	 *            ObjetoMapa que se va a agregar.
	 */
	public void agregarObjeto(ObjetoMapa primer, ObjetoMapa nuevo) {
		if (primerObjetoMapa == null) {
			primerObjetoMapa = nuevo;
		} else {
			if (primer.getSiguiente() == null) {
				primer.setSiguiente(nuevo);
			} else {
				agregarObjeto(primer.getSiguiente(), nuevo);
			}
		}
	}

	/**
	 * Nombre: buscarObjetoMapa. El método se encarga de buscar en la lista enlazada
	 * un objeto del mapa
	 * 
	 * @param aux
	 *            - primero objeto de la lista enlazada
	 * @param identificador
	 *            - codigo que identifica al objeto a buscar
	 * 
	 *            <b>pos</b> retorna null si el objeto no existe en la lista
	 *            <b>pos</b> retorna el objetoMapa que coincide con el identificador
	 * 
	 * @return objetoMapa con el mismo identificador de la lista enlazada
	 */
	public ObjetoMapa buscarObjetoMapa(ObjetoMapa aux, int identificador) {
		if (aux == null) {
			return null;
		} else {
			if (aux.getSiguiente() == null) {
				if (aux.getIdentificador() == identificador) {
					return aux;
				} else {
					return null;
				}
			} else {
				if (aux.getIdentificador() == identificador) {
					return aux;
				} else {
					return buscarObjetoMapa(aux.getSiguiente(), identificador);
				}
			}
		}
	}

	/**
	 * Nombre: eliminarObjeto. El método se encarga de eliminar de la lista enlazada
	 * un objeto del mapa
	 * 
	 * @param identificador
	 *            - codigo que identifica al objeto a buscar
	 * 
	 *            <b>pos</b> se busca al elemento a eliminar <b>pos</b> se elimina
	 *            el elemento encontrado
	 */
	public void eliminarObjeto(int identificador) {
		ObjetoMapa o = buscarObjetoMapa(primerObjetoMapa, identificador);
		if (o == primerObjetoMapa) {
			primerObjetoMapa = o.getSiguiente();
			o.setSiguiente(null);
		} else {
			ObjetoMapa anterior = objetoAnterior(primerObjetoMapa, null, identificador);
			anterior.setSiguiente(o.getSiguiente());
			o.setSiguiente(null);
		}
	}

	/**
	 * Nombre: objetoAnterior. El método se encarga obtener el objeto anterior al
	 * elemento que coincida con el identificador
	 * 
	 * @param aux
	 *            - primero objeto de la lista enlazada
	 * @param anterior
	 *            - objeto anterior
	 * @param identificador
	 *            - codigo que identifica al objeto a buscar
	 * 
	 *            <b>pos</b> se encuentra al objeto con el identificador y retorna
	 *            el anterior a este <b>pos</b> se llama al método objetoAnterior si
	 *            el identificador no coincide
	 * 
	 * @return ante - elemento anterior al elemento que coincide con el
	 *         identificador
	 */
	public ObjetoMapa objetoAnterior(ObjetoMapa aux, ObjetoMapa anterior, int identificador) {
		ObjetoMapa ante = anterior;
		ObjetoMapa actual = aux;
		if (actual.getIdentificador() == identificador) {
			return ante;
		} else {
			ante = actual;
			return objetoAnterior(actual.getSiguiente(), ante, identificador);
		}
	}



	/**
	 * @return primerObjetoMapa
	 */
	public ObjetoMapa getPrimerObjetoMapa() {
		return primerObjetoMapa;
	}

	/**
	 * @param primerObjetoMapa
	 */
	public void setPrimerObjetoMapa(ObjetoMapa primerObjetoMapa) {
		this.primerObjetoMapa = primerObjetoMapa;
	}



}
