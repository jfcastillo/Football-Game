package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MenuPrincipal extends JFrame {

	private PanelBanner panelBanner;
	private PanelBotones panelBotones;
	private VentanaPrincipal principal;

	
	public MenuPrincipal() {
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		panelBanner= new PanelBanner(this);
		panelBotones=new PanelBotones(this);
		this.setResizable(false);
		this.setSize(new Dimension(750,650));
		this.setTitle("Fifa 2000");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(panelBanner,BorderLayout.NORTH);
		this.add(panelBotones,BorderLayout.CENTER);
		
		
		
	}
	
	
	
	
	
	public static void main(String[] miString) {
		MenuPrincipal a = new MenuPrincipal();
		a.setVisible(true);
	}


	


	public void iniciarJuego(String nombreJugador) {
		principal= new VentanaPrincipal(nombreJugador);
		try {
			principal.inicializarJuego(nombreJugador);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		principal.setVisible(true);
		this.setVisible(false);
		
	



		
	}





	public void creditos() {
		JOptionPane.showMessageDialog(this, "Alexis Bonilla"+"\n"+ "Camilo Olano" + "\n"+"Daniel Guzm�n"+"\n"+"Felipe Castillo");
		
	}





	public void inteligenciaArtificial() {
		// TODO Auto-generated method stub
		
	}

}
