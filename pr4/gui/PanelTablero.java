package tp.pr4.GUI;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import tp.pr4.control.Jugador;
import tp.pr4.control.JugadorAleatorioComplica;
import tp.pr4.control.JugadorAleatorioConecta4;
import tp.pr4.control.JugadorAleatorioGravity;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.Juego;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.TableroInmutable;

public class PanelTablero extends JPanel implements Observer {

	private ControladorGUI c;
	private TableroInmutable t;
	private JPanel panel_Tablero;
	private JPanel panel;
	private JLabel labelTurno;
	private Ficha turnoActual;


	public PanelTablero(ControladorGUI c, Ficha jugadorInicial) {

		this.c = c;
		c.addObserver(this);
		this.t = c.getTab();
		turnoActual=jugadorInicial;
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
		for (int i = 1; i < (t.getFilas()+1); i++) {
			for (int j = 1; j < (t.getColumnas()+1); j++) {
				
				Casilla cas = new Casilla(i, j, t.getCasilla(j, i));
				if (cas.getColor()!=Ficha.VACIA){
					cas.setEnabled(false);
					if (cas.getColor()==Ficha.BLANCA){
						cas.setBackground(Color.WHITE);
					}else
						cas.setBackground(Color.black);
				}
				panel_Tablero.add(cas);
				cas.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						c.Mover(cas.getcoordX(), cas.getcoordY(), turnoActual);
						
					}
					});
			}

		}
		
		this.repaint();
		

	}

	private void construyeOtros() {

		
		panel.setLayout(new GridLayout(2,1));
		// creo que asi cuela... siempre empieza blancas y cada vez que se
		// cambia turno te lo notifican
		labelTurno = new JLabel( "Turno de: "+ turnoActual.toString());
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
		labelTurno = new JLabel( "Turno de: "+ turnoActual.toString());
		this.repaint();
		
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tab, Ficha ganador) {
		// poner tablero y boton bloqueados
		panel.setEnabled(false);
		panel_Tablero.setEnabled(false);
		this.turnoActual=ganador;
		JFrame frame= new JFrame();
		JOptionPane.showMessageDialog(frame,
			    "Ganan las: "+ ganador.toString(),
			    "Ganador",
			    JOptionPane.INFORMATION_MESSAGE);
		labelTurno = new JLabel( "Turno de: "+ turnoActual.toString());
		this.repaint();
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {

		this.t = tab;
		construyeTablero();
		this.turnoActual=turno;
		labelTurno = new JLabel( "Turno de: "+ turnoActual.toString());
		this.repaint();
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tab, Ficha turno) {
		// nada

	}

	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas) {
		// quitar ultima casilla puesta y actualizar turno
		this.t=tab;
		construyeTablero();
		this.turnoActual=turno;
		labelTurno=new JLabel( "Turno de: "+ turnoActual.toString());
		if(!hayMas){
			panel.setEnabled(false);
		}
		this.repaint();
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha jugador, Ficha turno) {
		this.t=tab;
		construyeTablero();
		this.turnoActual=turno;
		labelTurno=new JLabel( "Turno de: "+ turnoActual.toString());
		this.repaint();
		

	}

	@Override
	public void onMovimientoIncorrecto(TableroInmutable movimientoException) {
		JFrame frame= new JFrame();
		JOptionPane.showMessageDialog(frame,
			    "MovimientoInvalido",
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}

}
