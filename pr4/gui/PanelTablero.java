package tp.pr4.GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.TableroInmutable;

public class PanelTablero extends JPanel implements Observer {

	private ControladorGUI c;
	private TableroInmutable t;
	private JPanel panel_Tablero;
	private JPanel panel;
	private JLabel labelTurno;

	public PanelTablero() {

	}

	public PanelTablero(ControladorGUI c) {

		this.c = c;
		c.addObserver(this);
		this.t = c.getTab();
		setLayout(new GridLayout(2, 1, 30, 30));
		
		panel_Tablero = new JPanel();
		panel_Tablero.setMinimumSize(new Dimension(500,500));
		panel= new JPanel();
		
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
		for (int i = 0; i < t.getFilas(); i++) {
			for (int j = 0; j < t.getColumnas(); j++) {
				Casilla cas = new Casilla(i, j);
				panel_Tablero.add(cas);

			}

		}
		
	
		

	}

	private void construyeOtros() {

		
		panel.setLayout(new GridLayout(2,1));
		// creo que asi cuela... siempre empieza blancas y cada vez que se
		// cambia turno te lo notifican
		labelTurno = new JLabel(Ficha.BLANCA.toString());
		JButton aleatorio = new JButton("Movimiento Aleatorio");
		aleatorio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}

		});

		panel.add(labelTurno);
		panel.add(aleatorio, BorderLayout.SOUTH);

	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno) {
		this.t = tab;
		construyeTablero();
		labelTurno = new JLabel(turno.toString());
		this.repaint();
		
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tab, Ficha ganador) {
		// poner tablero y boton bloqueados

	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {

		this.t = tab;
		construyeTablero();
		labelTurno = new JLabel(turno.toString());

	}

	@Override
	public void onUndoNotPossible(TableroInmutable tab, Ficha turno) {
		// nada

	}

	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas) {
		// quitar ultima casilla puesta y actualizar turno

	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha jugador, Ficha turno) {
		// colocar nueva casilla y actualiar turno

	}

	@Override
	public void onMovimientoIncorrecto(TableroInmutable movimientoException) {
		// aviso de movimiento incorrecto

	}

}
