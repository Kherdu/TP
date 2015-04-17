package tp.pr4.GUI;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.border.Border;

import tp.pr4.logica.Juego;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class PanelBotones extends JPanel {

	/**
	 * Create the panel.
	 */
	private ControladorGUI c;
	private JTextField fieldFilas;
	private JTextField fieldColumnas;
	
	public PanelBotones(ControladorGUI c) {
		
		this.c=c;
		setLayout(new GridLayout(4, 1));
		
		Border borde= BorderFactory.createLineBorder(Color.black);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBorder(borde);
		
		
			JButton deshacer = new JButton("Deshacer");
			deshacer.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0){
					c.movAleatorio();
				}
				
			});
			
			
			JButton reiniciar = new JButton("Reiniciar");
			reiniciar.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0){
					c.reset();
				}
				
			});
			
			panel.add(deshacer);
			panel.add(reiniciar);
			
			
		Juego[] NombreJuegos= {Juego.COMPLICA,Juego.CONECTA4 , Juego.GRAVITY};
		JPanel panel_1 = new JPanel();
		
		panel_1.setLayout(new GridLayout(3,2, 20, 20));
		panel_1.setBorder(borde);
	
		
		
			JComboBox juego = new JComboBox(NombreJuegos);
			
		
			JButton cambiarJuego = new JButton("cambiar Juego");
			
			JLabel filasLabel = new JLabel("Filas");
			fieldColumnas = new JTextField();
			
			
			JLabel columnasLabel = new JLabel("Columnas");
			
			
			fieldFilas = new JTextField();
			
		
			panel_1.add(filasLabel);
			panel_1.add(fieldFilas);
			panel_1.add(columnasLabel);
			panel_1.add(fieldColumnas);
			panel_1.add(juego);
			panel_1.add(cambiarJuego);
			
			
			cambiarJuego.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0){
					
					c.cambiaJuego((Juego) juego.getSelectedItem(), fieldFilas.getText(), fieldColumnas.getText());
				}
				
			});
			
			
			JPanel panel_2= new JPanel();
			panel_2.setLayout(new FlowLayout());
			
				JButton salir = new JButton("Salir");
				salir.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0){
						System.exit(0);
					}
					
				});
			
			panel_2.add(salir);
			
			
			this.add(panel);
			this.add(panel_1);
			this.add(panel_2);
	}

}
