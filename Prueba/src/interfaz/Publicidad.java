package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javazoom.jl.decoder.JavaLayerException;

public class Publicidad extends JFrame{

		private PanelPublicidad panelPublicidad;
		private PanelBotones but;
		

		
		public Publicidad(String gif, String audio) {
			this.setBackground(Color.BLACK);
			this.setLayout(new BorderLayout());
			try {
				panelPublicidad= new PanelPublicidad(gif, audio, this);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//but = new PanelBotones(null);
			this.setResizable(true);
			//setLocation(500,500);
			this.setSize(new Dimension(500,500));
			setPreferredSize(new Dimension(500,500));
			
			//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.add(panelPublicidad,BorderLayout.CENTER);
			this.setTitle("PUBLICIDAD NO-PREPAGADA");
			setVisible(true);
			panelPublicidad.setVisible(true);
			pack();
			try {
				panelPublicidad.reproducirMusica();
			} catch (FileNotFoundException | JavaLayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		
//		public static void main(String[] args) {
//			Publicidad p = new Publicidad("./resources/ads/coke.gif", "./resources/ads/coke.mp3");
//				while(true) {
//					p.repaint();
//				}
//		}
			
		
}
