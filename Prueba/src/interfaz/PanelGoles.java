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
	
	private int golesI;
	private int golesD;
	
	public PanelGoles(VentanaPrincipal vPrincipal) {
		
		this.vPrincipal = vPrincipal;
		setBackground(Color.BLACK);
		setLayout(new GridLayout());
		
		golesI=0;
		golesD=0;
		
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
	
	public void setGolesDerecha()
	{
		golesD+=1;
		String g = Integer.toString(golesD);
		derecha.setText(g);
	}
	
	public void setGolesIzquierda()
	{
		golesI+=1;
		String g = Integer.toString(golesI);
		izquierda.setText(g);
	}
	

}
