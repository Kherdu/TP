package tp.pr5.GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.logica.TableroInmutable;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private ControladorGUI c;
	
	
	public MainWindow(ControladorGUI c, Ficha jugadorInicial) {
		this.c=c;
	
		this.setMinimumSize(new Dimension(800,600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,2,20,20));
		
	
		JPanel panelIz= new PanelTablero(c, jugadorInicial);
		
		
		JPanel panelDr= new PanelBotones(c, jugadorInicial);
		
		panelIz.setVisible(true);
		panelDr.setVisible(true);
		
		
		
		this.add(panelIz);
		this.add(panelDr);
		this.setVisible(true);
		
	}

	
	
}
