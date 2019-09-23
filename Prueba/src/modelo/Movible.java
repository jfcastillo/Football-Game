package modelo;

public interface Movible {
	public final static int DISTANCIA_QUE_SE_MUEVE_1 = 5;
	public final static int DISTANCIA_QUE_SE_MUEVE_2 = 1;
	public final static int DISTANCIA_QUE_SE_MUEVE_3 = 15;

	public final static int DIRECCION_ARRIBA = 3;
	public final static int DIRECCION_ABAJO = 4;
	public final static int DIRECCION_IZQUIERDA = 1;
	public final static int DIRECCION_DERECHA = 2;

	public void moverDerecha(int valor);

	public void moverIzquierda(int valor);

	public void moverArriba(int valor);

	public void moverAbajo(int valor);

	public void mover(int direccion);

	public void empujar();

	public void direccionActual(int direccion);

	public void mover();
}
