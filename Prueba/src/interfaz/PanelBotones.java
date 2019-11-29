package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelBotones extends JPanel implements ActionListener, MouseListener {

	public static final String INICIAR_JUEGO = "iniciar juego";
	public static final String SALIR = "salir";
	public static final String CREDITOS = "creditos";
	public static final String INTELIGENCIA_ARTIFICIAL = "inteligencia artificial";

	private MenuPrincipal principal;
	JButton butIniciar;
	JButton butCreditos;
	JButton butSalir;
	JButton butInteligenciaArtificial;

	public PanelBotones(MenuPrincipal menuPrincipal) {
		this.addMouseListener(this);
		this.setBackground(Color.BLACK);
		principal = menuPrincipal;
		butIniciar = new JButton(INICIAR_JUEGO);
		butIniciar.setFont(new Font("Arial", 1, Font.BOLD));
		butIniciar.setOpaque(true);
		butIniciar.setBackground(Color.BLACK);
		butIniciar.setBorder(null);
		butIniciar.setFocusable(false);
		butIniciar.setSelected(false);
		butIniciar.requestFocus(false);
		butIniciar.setFocusPainted(false);
		butIniciar.addMouseListener(this);
		butIniciar.setHorizontalAlignment(SwingConstants.CENTER);
		butIniciar.setIcon(new ImageIcon("./resources/botones/MenuPrincipal/iniciar.png"));
		butIniciar.addActionListener(this);
		butIniciar.setContentAreaFilled(false);
		butIniciar.setActionCommand(INICIAR_JUEGO);

		butInteligenciaArtificial = new JButton(this.INTELIGENCIA_ARTIFICIAL);
		butInteligenciaArtificial.setOpaque(true);
		butInteligenciaArtificial.setFocusPainted(false);
		butInteligenciaArtificial.setFocusable(false);
		butInteligenciaArtificial.setSelected(false);
		butInteligenciaArtificial.setIgnoreRepaint(true);
		butInteligenciaArtificial.setContentAreaFilled(false);
		butInteligenciaArtificial.setBackground(Color.BLACK);
		butInteligenciaArtificial.setBorder(null);
		butInteligenciaArtificial.addMouseListener(this);
		butInteligenciaArtificial.addActionListener(this);
		butInteligenciaArtificial.setFont(new Font("Arial", 1, Font.BOLD));
		butInteligenciaArtificial.setIcon(new ImageIcon("./resources/botones/MenuPrincipal/inteligencia.png"));
		butInteligenciaArtificial.setActionCommand(this.INTELIGENCIA_ARTIFICIAL);

		butCreditos = new JButton(CREDITOS);
		butCreditos.setOpaque(true);
		butCreditos.setFocusPainted(false);
		butCreditos.setFocusable(false);
		butCreditos.setSelected(false);
		butCreditos.setIgnoreRepaint(true);
		butCreditos.setContentAreaFilled(false);
		butCreditos.setBackground(Color.BLACK);
		butCreditos.setBorder(null);
		butCreditos.addMouseListener(this);
		butCreditos.addActionListener(this);
		butCreditos.setFont(new Font("Arial", 1, Font.BOLD));
		butCreditos.setIcon(new ImageIcon("./resources/botones/MenuPrincipal/creditos.png"));
		butCreditos.setActionCommand(CREDITOS);
		butSalir = new JButton(SALIR);
		butSalir.setOpaque(true);
		butSalir.setBackground(Color.BLACK);
		butSalir.setFocusable(false);
		butSalir.setFocusPainted(false);
		butSalir.setBorder(null);
		butSalir.setContentAreaFilled(false);
		butSalir.setFont(new Font("Arial", 1, Font.BOLD));
		butSalir.addMouseListener(this);
		butSalir.setIcon(new ImageIcon("./resources/botones/MenuPrincipal/salir.png"));
		butSalir.setActionCommand(SALIR);
		butSalir.addActionListener(this);
		
		this.setPreferredSize(new Dimension(600, 500));
		this.setLayout(new GridLayout(9, 1));
		this.add(new JLabel());
		this.add(butIniciar);
		this.add(new JLabel());
		this.add(butCreditos);
		this.add(new JLabel());
		this.add(butInteligenciaArtificial);
		this.add(new JLabel());
		this.add(butSalir);
		this.add(new JLabel());

	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if (comando.equals(INICIAR_JUEGO)) {
			String nombre = JOptionPane.showInputDialog("Por favor ingrese su nombre");
			principal.iniciarJuego(nombre);
		} else if (comando.equals(CREDITOS)) {
			
			principal.creditos();
		} else if(comando.equals(this.INTELIGENCIA_ARTIFICIAL)) {
			principal.inteligenciaArtificial();
			
		}
			
			else {
			System.exit(0);
		}

	}

	private void creditos() {
		// TODO Auto-generated method stub

	}

	private void iniciarJuego() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent evento) {

	}

	@Override
	public void mouseEntered(MouseEvent evento) {
		Object o = evento.getSource();
		JButton b = null;
		String buttonText = "";

		if (o instanceof JButton) {
			b = (JButton) o;
		} else {
			butIniciar.setIcon(new ImageIcon("./resources/botones/MenuPrincipal/iniciar.png"));
			butCreditos.setIcon(new ImageIcon("./resources/botones/MenuPrincipal/creditos.png"));
			butSalir.setIcon(new ImageIcon("./resources/botones/MenuPrincipal/salir.png"));
			butInteligenciaArtificial.setIcon(new ImageIcon("./resources/botones/MenuPrincipal/inteligencia.png"));
		}

		if (b != null)
			buttonText = b.getText();

		if (buttonText.equals(INICIAR_JUEGO)) {
			butIniciar.setIcon(new ImageIcon("./resources/botones/MenuPrincipal/iniciar2.png"));

		} else if (buttonText.equals(CREDITOS)) {
			butCreditos.setIcon(new ImageIcon("./resources/botones/MenuPrincipal/creditos2.png"));
		} else if (buttonText.equals(SALIR)) {
			butSalir.setIcon(new ImageIcon("./resources/botones/MenuPrincipal/salir2.png"));
		} else if (buttonText.equals(INTELIGENCIA_ARTIFICIAL)) {
		butInteligenciaArtificial.setIcon(new ImageIcon("./resources/botones/MenuPrincipal/inteligencia2.png"));
		}
		

	}

	@Override
	public void mouseExited(MouseEvent evento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent evento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent evento) {
		// TODO Auto-generated method stub

	}

}
