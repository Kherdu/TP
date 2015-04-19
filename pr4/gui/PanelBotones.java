package tp.pr4.GUI;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.border.Border;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Juego;
import tp.pr4.logica.TableroInmutable;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class PanelBotones extends JPanel implements Observer {

	/**
	 * Create the panel.
	 */
	private ControladorGUI c;
	private JTextField fieldFilas;
	private JTextField fieldColumnas;
	private JComboBox juego;
	private JPanel panel;
	private JPanel panelundo;
	
	public PanelBotones(ControladorGUI c, Ficha jugadorInicial) {
		
		this.c=c;
		c.addObserver(this);
		setLayout(new GridLayout(4, 1));
		
		Border borde= BorderFactory.createLineBorder(Color.black);
		
		
		panelundo = new JPanel();
		panelundo.setLayout(new FlowLayout());
		panelundo.setBorder(borde);
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBorder(borde);
		
			JButton deshacer = new JButton("Deshacer");
			deshacer.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0){
					c.undo();
				}
				
				
			});
			
			
			JButton reiniciar = new JButton("Reiniciar");
			reiniciar.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0){
					c.reset();
				}
				
			});
			
			panelundo.add(deshacer);
			panel.add(reiniciar);
			
			
			
		Juego[] NombreJuegos= {Juego.COMPLICA,Juego.CONECTA4 , Juego.GRAVITY};
		JPanel panel_1 = new JPanel();
		
		panel_1.setLayout(new GridLayout(3,2, 20, 20));
		panel_1.setBorder(borde);
	
		
		
			 juego = new JComboBox(NombreJuegos);
			
		
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
			this.add(panelundo);
			this.add(panel_1);
			this.add(panel_2);
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno) {
		JFrame frame= new JFrame();
		JOptionPane.showMessageDialog(frame,
			   "Partida reiniciada",
			    "Info",
			    JOptionPane.INFORMATION_MESSAGE);
		
	}

	
	@Override
	public void onPartidaTerminada(TableroInmutable tab, Ficha ganador) {
		panelundo.setEnabled(false);
		this.repaint();
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {
		JFrame frame= new JFrame();
		JOptionPane.showMessageDialog(frame,
			   "Juego cambiado" + juego.getSelectedItem().toString(),
			    "Info",
			    JOptionPane.INFORMATION_MESSAGE);
		
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tab, Ficha turno) {
		JFrame frame= new JFrame();
		JOptionPane.showMessageDialog(frame,
			   "No se puede deshacer",
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
		panelundo.setEnabled(false);
		
	}

	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas) {
		// nada, lo hace el otro panel
		
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha jugador, Ficha turno) {
		// nada, lo hace el otro panel
		
	}

	@Override
	public void onMovimientoIncorrecto(TableroInmutable movimientoException) {
		// nada, lo hace el otro panel
		
	}

}
