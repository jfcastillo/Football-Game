package modelo;

public class Cronometro {

	
	private int minutos;
	private int segundos;
//	private boolean terminarTiempo;

	
	
	
	public Cronometro() {
		minutos=0;
		segundos=0;
		
		
	}
	
	public void avanzar() {
		
		if(segundos<=58) {
			segundos++;
		}
		else {
			segundos=0;
			minutos++;
		}
		
	}



	public int getMinutos() {
		return minutos;
	}



	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}



	public int getSegundos() {
		return segundos;
	}



	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}


	
	
}
