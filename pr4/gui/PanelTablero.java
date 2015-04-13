package tp.pr4.GUI;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;

public class PanelTablero extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelTablero() {
		setLayout(new GridLayout(2, 1, 30,30));
		
		JPanel panel_Tablero = new JPanel();
		
		
		JPanel panel = new JPanel();
	
		
		
			JButton Aleatorio = new JButton("Movimiento Aleatorio");
			panel.add(Aleatorio, BorderLayout.SOUTH);
			
		
		
		
		add(panel_Tablero);
		add(panel);
		
		
	}

}
