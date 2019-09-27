package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelGoles extends JPanel{
	



	private VentanaPrincipal vPrincipal;
	private JLabel Marcador;
	private JLabel izquierda;
	private JLabel derecha;
	private JLabel nomJug1;
	private JLabel nomJug2;
	private JLabel labTiempo;
	private JLabel labCronometro;
	
	

	
	public PanelGoles(VentanaPrincipal vPrincipal) {
		
		this.vPrincipal = vPrincipal;
		setBackground(Color.BLACK);
		setLayout(new GridLayout());
		
		labTiempo= new JLabel("Primer tiempo",SwingConstants.CENTER);
		labTiempo.setForeground(Color.WHITE);
		labCronometro = new JLabel("00:00",SwingConstants.CENTER);
		labCronometro.setForeground(Color.WHITE);
		Marcador = new JLabel("MARCADOR",SwingConstants.CENTER);
		izquierda = new JLabel("0",SwingConstants.CENTER);
		derecha = new JLabel("0",SwingConstants.CENTER);
		nomJug1= new JLabel("",SwingConstants.CENTER);
		nomJug2= new JLabel("",SwingConstants.CENTER);
		
		Marcador.setForeground(Color.WHITE);
		izquierda.setForeground(Color.WHITE);
		derecha.setForeground(Color.WHITE);
		JLabel labAux1= new JLabel();
		labAux1.setBackground(Color.BLACK);
		JLabel labAux2= new JLabel();
		labAux2.setBackground(Color.BLACK);
		nomJug1.setForeground(Color.WHITE);
		nomJug2.setForeground(Color.WHITE);
		add(labTiempo);
		add(nomJug1);
		add(izquierda);
		add(Marcador);
		add(nomJug2);
		add(derecha);
		add(labCronometro);
	}
	
	
	
	public JLabel getIzquierda() {
		return izquierda;
	}



	public void setIzquierda(String izquierda) {
		this.izquierda.setText(izquierda);
	}
	public void cambiarNombreJugador1(String nombre) {
		nomJug1.setText(nombre);
	}
	
	public void cambiarNombreJugador2(String nombre) {
		nomJug2.setText(nombre);
	}



	public JLabel getDerecha() {
		return derecha;
	}



	public void setDerecha(String derecha) {
		this.derecha.setText(derecha);

	}

	public JLabel getNomJug1() {
		return nomJug1;
	}
	
	public void setNomJug1(String nomJugador) {
		nomJug1.setText(nomJugador);
		
	}
	
	public void setNomJug2(String nomJugador) {
		nomJug2.setText(nomJugador);
		
	}







	public JLabel getNomJug2() {
		return nomJug2;
	}



	public void refrescarReloj(int min, int seg) {

		int minutos = min;
		int segundos = seg;
		if(segundos <=8) {
			labCronometro.setText("0"+minutos+":"+"0"+segundos);
		}
		else {
			labCronometro.setText("0"+minutos+":"+segundos);
		}


		
	}



	public void segundoTiempo() {
		labTiempo.setText("Segundo Tiempo");
		
	}



	public void TerminarPartido() {
		labTiempo.setText("Partido Finalizado");
		
	}





	

}
