package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;



public class PanelPublicidad extends JPanel {
	private ImageIcon img;
	private FileInputStream fil;
	
	public PanelPublicidad(String gif, String audio, Publicidad pu) throws FileNotFoundException {
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(400,400));
		
		setFocusable(true);
		setVisible(true);
		img = new ImageIcon(gif);
		fil = new FileInputStream(audio);
			
			
		

	}
	
	@Override
	public void paintComponent(Graphics g) {

		Dimension height = getSize();
		
		g.drawImage(img.getImage(), 0, 0, height.width, height.height, null);
	
	}
	
	public void reproducirMusica() throws FileNotFoundException, JavaLayerException {

		Player apl = new Player(fil);

		apl.play();
	}
	
	

}
