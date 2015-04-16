package tp.pr4.GUI;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private ControladorGUI c;
	
	


	public MainWindow(ControladorGUI c) {
		this.c=c;
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,2,20,20));
		
		//cambiar esto, pasarlo con el tablero inmutable
		JPanel panelIz= new PanelTablero(c.getReglas().getAlto(),c.getReglas().getAncho());
		
		
		JPanel panelDr= new PanelBotones();
		
		panelIz.setVisible(true);
		panelDr.setVisible(true);
		
		
		
		this.add(panelIz);
		this.add(panelDr);
		this.setVisible(true);
		
	}

	
	
}
