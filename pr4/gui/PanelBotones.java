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
import tp.pr4.logica.InstruccionInvalida;
import tp.pr4.logica.Juego;
import tp.pr4.logica.MovimientoInvalido;
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

	public PanelBotones(ControladorGUI c, Ficha jugadorInicial) {

		this.c = c;
		c.addObserver(this);
		setLayout(new GridLayout(4, 1));

		Border borde = BorderFactory.createLineBorder(Color.black);

		

		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBorder(borde);

		JButton deshacer = new JButton("Deshacer");
		deshacer.setEnabled(false);
		deshacer.setName("Deshacer");
		deshacer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.undo();
			}

		});

		JButton reiniciar = new JButton("Reiniciar");
		reiniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.reset();
			}

		});

		panel.add(deshacer);
		panel.add(reiniciar);

		JPanel panel_1 = new JPanel();

		panel_1.setLayout(new GridLayout(3, 2, 20, 20));
		panel_1.setBorder(borde);

		juego = new JComboBox(Juego.values());
		juego.setSelectedItem(Juego.CONECTA4);
		

		JButton cambiarJuego = new JButton("cambiar Juego");

		JLabel filasLabel = new JLabel("Filas");
		fieldColumnas = new JTextField();
		fieldColumnas.setEnabled(false); //por defecto deshabilitados porque iniciamos con c4
		fieldColumnas.setVisible(false);

		JLabel columnasLabel = new JLabel("Columnas");
		fieldFilas = new JTextField();
		fieldFilas.setEnabled(false);
		fieldFilas.setVisible(false);
		
		panel_1.add(filasLabel);
		panel_1.add(fieldFilas);
		panel_1.add(columnasLabel);
		panel_1.add(fieldColumnas);
		panel_1.add(juego);
		panel_1.add(cambiarJuego);

		cambiarJuego.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				c.cambiaJuego((Juego) juego.getSelectedItem(),
						fieldFilas.getText(), fieldColumnas.getText());
			}

		});

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new FlowLayout());

		JButton salir = new JButton("Salir");
		salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}

		});

		juego.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				Juego j=(Juego) juego.getSelectedItem();
				if (j.redimensionable(j)){
					fieldFilas.setEnabled(true);
					fieldFilas.setVisible(true);
					fieldColumnas.setEnabled(true);
					fieldColumnas.setVisible(true);
					
				}else{
					fieldFilas.setEnabled(false);
					fieldFilas.setVisible(false);
					fieldColumnas.setEnabled(false);
					fieldColumnas.setVisible(false);
				}
			}
			
			
			
		});
		panel_2.add(salir);

		this.add(panel);
		
		this.add(panel_1);
		this.add(panel_2);
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Partida reiniciada", "Info",
				JOptionPane.INFORMATION_MESSAGE);
		enableDeshacer(false);
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tab, Ficha ganador) {
		
		this.repaint();
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Jugando a: "
				+ juego.getSelectedItem().toString(), "Info",
				JOptionPane.INFORMATION_MESSAGE);

	}

	@Override
	public void onUndoNotPossible(TableroInmutable tab, Ficha turno) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "No se puede deshacer", "Error",
				JOptionPane.ERROR_MESSAGE);
		enableDeshacer(false);
		

	}

	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas) {
		
		//deshabilitamos boton de deshacer, no quedan movimientos que hacer
		if (!hayMas) enableDeshacer(false);
		

	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha jugador, Ficha turno) {
		//habilitamos boton de deshacer, porque hemos hecho al menos un movimiento valido
		enableDeshacer(true);
		

	}

	@Override
	public void onMovimientoIncorrecto(TableroInmutable movimientoException) {
		// nada, lo hace el otro panel

	}

	//estos dos no pueden ocurrir en GUI
	@Override
	public void onMovimientoInvalido(MovimientoInvalido e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInstruccionInvalida(InstruccionInvalida e) {
		// TODO Auto-generated method stub
		
	}

	private void enableDeshacer(Boolean b){
		Component[] cmps=panel.getComponents();
		for (Component c: cmps){
			if (c instanceof JButton){
				if(c.getName()=="Deshacer"){
					c.setEnabled(b);
				}
			}
		}
		panel.validate();
		this.repaint();
	}

	@Override
	public void onInicio(TableroInmutable tin, Ficha turno) {
		
		
	}
}
