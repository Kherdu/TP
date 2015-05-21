package tp.pr5.GUI;

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

import tp.pr5.logica.Ficha;
import tp.pr5.logica.InstruccionInvalida;
import tp.pr5.logica.Juego;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJugador;

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
	private JComboBox jug1;
	private JComboBox jug2;
	private Ficha turno;

	public PanelBotones(ControladorGUI c, Ficha jugadorInicial) {

		this.c = c;
		c.addObserver(this);
		setLayout(new GridLayout(4, 1));
		this.turno=jugadorInicial;
		Border borde = BorderFactory.createTitledBorder("Partida");
		
		

		panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		
		panel.setBorder(borde);
		
		JButton deshacer = new JButton("Deshacer");
		deshacer.setEnabled(false);
		deshacer.setName("Deshacer");
		deshacer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.undo(turno);
			}

		});

		JButton reiniciar = new JButton("Reiniciar");
		reiniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.reset();
			}

		});
		
		JLabel j1= new JLabel("BLANCAS");
		jug1 = new JComboBox(TipoJugador.values());
		jug1.setSelectedItem(TipoJugador.MANUAL);
		
		JLabel j2= new JLabel("NEGRAS");
		jug2 = new JComboBox(TipoJugador.values());
		jug2.setSelectedItem(TipoJugador.MANUAL);
		
		
		panel.add(deshacer);
		panel.add(reiniciar);
		panel.add(j1);
		panel.add(jug1);
		panel.add(j2);
		panel.add(jug2);
		
		jug1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				c.tipoJugador((TipoJugador)jug1.getSelectedItem(),Ficha.BLANCA);
			}

		});
		
		jug2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				c.tipoJugador((TipoJugador)jug2.getSelectedItem(),Ficha.NEGRA);
			}

		});

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
		
		c.matahilos();
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Partida reiniciada", "Info",
				JOptionPane.INFORMATION_MESSAGE);
		enableDeshacer(false);
		this.turno=turno;
		
		
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
		this.turno=turno;
		c.reiniciaHilo(turno);
		
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tab, Ficha turno) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "No se puede deshacer", "Error",
				JOptionPane.ERROR_MESSAGE);
		enableDeshacer(false);
		this.turno=turno;
		

	}

	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas) {
		//deshabilitamos boton de deshacer, no quedan movimientos que hacer
		if (!hayMas) enableDeshacer(false);
		this.turno=turno;
		if (turno==Ficha.BLANCA && jug1.getSelectedItem()==TipoJugador.AUTOMATICO){
			c.deshacermas(turno);
		}
		if (turno==Ficha.NEGRA && jug2.getSelectedItem()==TipoJugador.AUTOMATICO){
			c.deshacermas(turno);
		}
		
		

	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha jugador, Ficha turno) {
		// nada, lo hace el otro panel
		enableDeshacer(true);
		this.turno=turno;

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

	
	@Override
	public void onInicio(TableroInmutable tin, Ficha turno) {
		this.turno=turno;
		
	}
	
	private void enableDeshacer(Boolean b){
		Component[] cmps=panel.getComponents();
		for (Component c: cmps){
			
				if(c.getName()=="Deshacer"){
					c.setEnabled(b);
				}
			
		}
		panel.validate();
		this.repaint();
	}
}
