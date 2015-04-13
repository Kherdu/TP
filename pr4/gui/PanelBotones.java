package tp.pr4.GUI;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.border.Border;

public class PanelBotones extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelBotones() {
		setLayout(new GridLayout(4, 1, 20,20));
		
		Border borde= BorderFactory.createLineBorder(Color.black);
		
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBorder(borde);
		
		
			JButton deshacer = new JButton("Deshacer");
		
			panel.add(deshacer);
			
			JButton Reiniciar = new JButton("Reiniciar");
			panel.add(Reiniciar);
		
		
		String[] NombreJuegos= {"Conecta4", "Complica", "Gravity"};
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new FlowLayout());
		panel_1.setBorder(borde);
	
		
		
			JComboBox Juego = new JComboBox(NombreJuegos);
			panel_1.add(Juego);
			
			JButton cambiarJuego = new JButton("cambiar Juego");
			panel_1.add(cambiarJuego);
		
		JPanel panel_2= new JPanel();
		panel_2.setLayout(new FlowLayout());
		
			JButton Salir = new JButton("Salir");
			panel_2.add(Salir);

			
			add(panel);
			add(panel_1);
			add(panel_2);
	}

}
