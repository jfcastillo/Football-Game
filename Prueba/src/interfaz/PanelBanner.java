package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelBanner extends JPanel {

	
	private MenuPrincipal principal;
	JLabel labBanner;
	
	public PanelBanner(MenuPrincipal menuPrincipal) {
		this.setPreferredSize(new Dimension(600,150));
		this.setBackground(Color.BLACK);
		labBanner = new JLabel("",SwingConstants.CENTER);
		principal = menuPrincipal;
		this.setLayout(new GridLayout(1,1));
		labBanner.setIcon(new ImageIcon("./resources/banner.jpg"));
		labBanner.setAlignmentX(CENTER_ALIGNMENT);
		labBanner.setBackground(Color.GRAY);
		this.add(labBanner);
		
	}

}
