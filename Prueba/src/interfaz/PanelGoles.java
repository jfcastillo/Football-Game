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
	

	
	public PanelGoles(VentanaPrincipal vPrincipal) {
		
		this.vPrincipal = vPrincipal;
		setBackground(Color.BLACK);
		setLayout(new GridLayout());
		
		Marcador = new JLabel("MARCADOR",SwingConstants.CENTER);
		izquierda = new JLabel("0",SwingConstants.CENTER);
		derecha = new JLabel("0",SwingConstants.CENTER);
		
		Marcador.setForeground(Color.WHITE);
		izquierda.setForeground(Color.WHITE);
		derecha.setForeground(Color.WHITE);

		
		add(izquierda);
		add(Marcador);		
		add(derecha);
	}
	
	
	
	public JLabel getIzquierda() {
		return izquierda;
	}



	public void setIzquierda(String izquierda) {
		this.izquierda.setText(izquierda);
	}



	public JLabel getDerecha() {
		return derecha;
	}



	public void setDerecha(String derecha) {
		this.derecha.setText(derecha);

	}


	

}
