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

	}

	public PanelTablero(int alto, int ancho) {
		
		setLayout(new GridLayout(2, 1, 30, 30));

		JPanel panel_Tablero = new JPanel();
		//for que añade botones al tablero, con gridLayout de anchura ancho y filas alto
		panel_Tablero.setLayout(new GridLayout(alto,ancho));
		
		
		for (int i=0;i<alto;i++){
			//añadir botones casilla que hereden de observador
			for (int j=0;j<ancho;j++){
				Casilla c= new Casilla(i,j);
				panel_Tablero.add(c);
				
			}
			
		}
		
		
		JPanel panel = new JPanel();

		JButton Aleatorio = new JButton("Movimiento Aleatorio");
		panel.add(Aleatorio, BorderLayout.SOUTH);

		add(panel_Tablero);
		add(panel);
	}

}
