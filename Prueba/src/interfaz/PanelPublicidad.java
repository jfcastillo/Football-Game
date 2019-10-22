package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import hilos.HiloContarPublicidad;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;



public class PanelPublicidad extends JPanel implements ActionListener{
	private ImageIcon img;
	private FileInputStream fil;
	private HiloContarPublicidad hiloContar;
	private Publicidad pu;
	private Player apl;
	
	public PanelPublicidad(String gif, String audio, Publicidad pu) throws FileNotFoundException {
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension(400,400));
		
		setFocusable(true);
		setVisible(true);
		img = new ImageIcon(gif);
		fil = new FileInputStream(audio);
		Timer temporizador  = new Timer(1, this);	
		temporizador.start();
		this.pu = pu;
		hiloContar = new HiloContarPublicidad(pu.getvPrincipal());
		hiloContar.start();
		
			
			
		

	}
	
	@Override
	public void paintComponent(Graphics g) {

		Dimension height = getSize();
		
		g.drawImage(img.getImage(), 0, 0, height.width, height.height, null);
	
	}
	
	public void reproducirMusica() throws FileNotFoundException, JavaLayerException {

		apl = new Player(fil);

		apl.play();
	}
	public void detenerMusica() {
		hiloContar.setStop(true);
		apl.close();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		
	}
	
	

}
