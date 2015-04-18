package tp.pr4.GUI;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tp.pr4.logica.ReglasJuego;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private ControladorGUI c;
	
	private ReglasJuego r;
	

	public MainWindow(ControladorGUI c) {
		this.c=c;
		this.r=c.getReglas();
		this.setBounds(100, 100, 700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,2,20,20));
		
		//cambiar esto, pasarlo con el tablero inmutable
		JPanel panelIz= new PanelTablero(c,r.getAlto(),r.getAncho());
		
		
		JPanel panelDr= new PanelBotones(c);
		
		panelIz.setVisible(true);
		panelDr.setVisible(true);
		
		
		
		this.add(panelIz);
		this.add(panelDr);
		this.setVisible(true);
		
	}

	
	
}
