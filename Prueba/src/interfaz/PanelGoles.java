package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelGoles extends JPanel{
	



	private VentanaPrincipal vPrincipal;
	private JLabel Marcador;
	private JLabel izquierda;
	private JLabel derecha;
	private JLabel nomJug1;
	private JLabel nomJug2;
	

	
	public PanelGoles(VentanaPrincipal vPrincipal) {
		
		this.vPrincipal = vPrincipal;
		setBackground(Color.BLACK);
		setLayout(new GridLayout());
		
		Marcador = new JLabel("MARCADOR",SwingConstants.CENTER);
		izquierda = new JLabel("0",SwingConstants.CENTER);
		derecha = new JLabel("0",SwingConstants.CENTER);
		nomJug1= new JLabel("jug 1 ",SwingConstants.CENTER);
		nomJug2= new JLabel("jug 2",SwingConstants.CENTER);
		
		Marcador.setForeground(Color.WHITE);
		izquierda.setForeground(Color.WHITE);
		derecha.setForeground(Color.WHITE);
		JLabel labAux1= new JLabel();
		labAux1.setBackground(Color.BLACK);
		JLabel labAux2= new JLabel();
		labAux2.setBackground(Color.BLACK);
		nomJug1.setForeground(Color.WHITE);
		nomJug2.setForeground(Color.WHITE);
		add(labAux1);
		add(nomJug1);
		add(izquierda);
		add(Marcador);
		add(nomJug2);
		add(derecha);
		add(labAux2);
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







	public JLabel getNomJug2() {
		return nomJug2;
	}





	

}
