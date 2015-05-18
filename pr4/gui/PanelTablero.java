package tp.pr4.GUI;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.MultipleGradientPaint.ColorSpaceType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import tp.pr4.control.Jugador;
import tp.pr4.control.JugadorAleatorioComplica;
import tp.pr4.control.JugadorAleatorioConecta4;
import tp.pr4.control.JugadorAleatorioGravity;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.InstruccionInvalida;
import tp.pr4.logica.Juego;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.TableroInmutable;

public class PanelTablero extends JPanel implements Observer {

	private ControladorGUI c;
	private TableroInmutable t;
	private JPanel panel_Tablero;
	private JPanel panel;
	private JLabel labelTurno;
	private Ficha turnoActual;
	private Casilla[][] matriz;
	

	public PanelTablero(ControladorGUI c, Ficha jugadorInicial) {

		this.c = c;
		c.addObserver(this);
		c.inicio();
		turnoActual = jugadorInicial;
		setLayout(new GridLayout(2, 1, 30, 30));
		matriz= new Casilla[t.getColumnas()][t.getFilas()];
		
		panel_Tablero = new JPanel();
		panel_Tablero.setMinimumSize(new Dimension(500, 500));
		panel = new JPanel();

		construyeTablero();
		construyeOtros();

		this.add(panel_Tablero);
		this.add(panel);

	}

	private void construyeTablero() {

		panel_Tablero.removeAll();
		// for que añade botones al tablero, con gridLayout de anchura ancho y
		// filas alto

		panel_Tablero.setLayout(new GridLayout(t.getFilas(), t.getColumnas()));
		matriz= new Casilla[t.getColumnas()][t.getFilas()];
		for (int y = 1; y < (t.getFilas() + 1); y++) {
			for (int x = 1; x < (t.getColumnas() + 1); x++) {
				
				Casilla cas = new Casilla(x, y, t.getCasilla(x, y));
				
				if (cas.getColor() != Ficha.VACIA) {
					
					if (cas.getColor() == Ficha.BLANCA) {
						cas.setBackground(Color.WHITE);
					} else
						cas.setBackground(Color.BLACK);
				}else cas.setBackground(Color.BLUE);
				matriz[x-1][y-1]=cas;
				panel_Tablero.add(cas);
				cas.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						c.Mover(cas.getColumna(), cas.getFila(), turnoActual);

					}
				});
			}

		}

		this.repaint();

	}

	private void modificaTablero() {
		//se puede cambiar por una matriz de casillas auxiliar para tener acceso directo
//		Component[] components = panel_Tablero.getComponents();
//		Component cas = null;
//		
//		for (int i = 0; i < components.length; i++) {
//			cas = components[i];
//			if (cas instanceof Casilla) {
//
//				if (t.getCasilla((((Casilla) cas).getColumna()),
//						((Casilla) cas).getFila()) == Ficha.BLANCA) {
//					cas.setBackground(Color.WHITE);
//				} else if (t.getCasilla((((Casilla) cas).getColumna()),
//						((Casilla) cas).getFila()) == Ficha.NEGRA)
//					cas.setBackground(Color.BLACK);
//			}
//		}
		
		for (int y = 1; y < t.getFilas()+1; y++) {
			for (int x = 1; x < t.getColumnas()+1; x++) {
				if (t.getCasilla(x, y)== Ficha.BLANCA){
					matriz[x-1][y-1].setBackground(Color.WHITE);
				} else if (t.getCasilla(x, y)== Ficha.NEGRA){
					matriz[x-1][y-1].setBackground(Color.BLACK);
				} 
				
			}
		}
		
		

	}

	private void construyeOtros() {

		panel.setLayout(new FlowLayout());
		labelTurno = new JLabel("Turno de: " + turnoActual.toString());
		labelTurno.setBorder(BorderFactory.createLineBorder(Color.black));

		JButton aleatorio = new JButton("Movimiento Aleatorio");
		aleatorio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				c.movAleatorio(turnoActual);

			}

		});

		panel.add(labelTurno);
		panel.add(aleatorio, BorderLayout.SOUTH);

	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno) {
		this.t = tab;
		construyeTablero();
		this.turnoActual = turno;
		labelTurno.setText("Turno de: " + turnoActual.toString());
		
		panel_Tablero.validate();
		this.repaint();

	}

	@Override
	public void onPartidaTerminada(TableroInmutable tab, Ficha ganador) {

		Component[] components = panel.getComponents();

		// deshabilitamos el boton
		for (Component c : components) {
			if (c instanceof JButton) {
				c.setEnabled(false);
			}
		}

		components = null;
		components = panel_Tablero.getComponents();
		// deshabilitamos el tablero
		for (Component c : components) {
			c.setEnabled(false);
		}

		this.turnoActual = ganador;
		JFrame frame = new JFrame();
		if (ganador != Ficha.VACIA) {
			JOptionPane.showMessageDialog(frame,
					"Ganan las: " + ganador.toString(), "Ganador",
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(frame,
					"TABLAS","Terminada",
					JOptionPane.INFORMATION_MESSAGE);
		}
		panel_Tablero.validate();
		this.repaint();
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {

		this.t = tab;
		construyeTablero();
		this.turnoActual = turno;
		
		panel_Tablero.validate();
		this.repaint();
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tab, Ficha turno) {
		// nada

	}

	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas) {
		// quitar ultima casilla puesta y actualizar turno
		this.t = tab;
		construyeTablero();
		this.turnoActual = turno;
		labelTurno.setText("Turno de: " + turnoActual.toString());
		if (!hayMas) {
			panel.setEnabled(false);
		}
		panel_Tablero.validate();
		this.repaint();
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha jugador, Ficha turno) {
		this.t = tab;
		modificaTablero();
		this.turnoActual = turno;
		labelTurno.setText("Turno de: " + turnoActual.toString());
		labelTurno.validate();
		panel_Tablero.validate();
		this.repaint();

	}

	@Override
	public void onMovimientoIncorrecto(TableroInmutable movimientoException) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "MovimientoInvalido", "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	// no pueden ocurrir en GUI
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

		t=tin;
		turnoActual=turno;
		
	}

}
