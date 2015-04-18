package tp.pr4.GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tp.pr4.logica.ReglasJuego;
import tp.pr4.logica.TableroInmutable;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private ControladorGUI c;
	
	private ReglasJuego r;
	

	public MainWindow(ControladorGUI c) {
		this.c=c;
		
		this.r=c.getReglas();
		this.setMinimumSize(new Dimension(800,600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,2,20,20));
		
	
		JPanel panelIz= new PanelTablero(c);
		
		
		JPanel panelDr= new PanelBotones(c);
		
		panelIz.setVisible(true);
		panelDr.setVisible(true);
		
		
		
		this.add(panelIz);
		this.add(panelDr);
		this.setVisible(true);
		
	}

	
	
}
