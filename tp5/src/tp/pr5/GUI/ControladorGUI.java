package tp.pr5.GUI;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr5.control.FactoriaComplica;
import tp.pr5.control.FactoriaConecta4;
import tp.pr5.control.FactoriaGravity;
import tp.pr5.control.FactoriaReversi;
import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.control.Jugador;
import tp.pr5.control.JugadorAleatorioComplica;
import tp.pr5.control.JugadorAleatorioConecta4;
import tp.pr5.control.JugadorAleatorioGravity;
import tp.pr5.control.JugadorAleatorioReversi;
import tp.pr5.control.JugadorHumanoComplica;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Juego;
import tp.pr5.logica.ModoAuto;
import tp.pr5.logica.ModoJuego;
import tp.pr5.logica.ModoMan;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoComplica;
import tp.pr5.logica.MovimientoConecta4;
import tp.pr5.logica.MovimientoGravity;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.MovimientoReversi;
import tp.pr5.logica.Partida;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJugador;

public class ControladorGUI {

	private FactoriaTipoJuego f;
	private Partida p;
	private ReglasJuego r;
	private Juego j;
	private ModoJuego modoBlancas;
	private ModoJuego modoNegras;

	public ControladorGUI(FactoriaTipoJuego factoria, Partida partida) {

		f = factoria;
		p = partida;
		r = f.creaReglas();
		j = r.getTipo();
		modoBlancas = new ModoMan();
		modoNegras = new ModoMan();

	}

	public void Mover(int columna, int fila, Ficha turno) {

		if ((modoBlancas.getJ() == TipoJugador.MANUAL && turno == Ficha.BLANCA)
				|| (modoNegras.getJ() == TipoJugador.MANUAL && turno == Ficha.NEGRA)) {

			try {
				if (j == Juego.COMPLICA) {

					Movimiento m = new MovimientoComplica(columna, turno);
					p.ejecutaMovimiento(m);
				} else if (j == Juego.CONECTA4) {

					Movimiento m = new MovimientoConecta4(columna, turno);
					p.ejecutaMovimiento(m);
				} else if (j == Juego.GRAVITY) {

					Movimiento m = new MovimientoGravity(columna, fila, turno);
					p.ejecutaMovimiento(m);
				} else {

					Movimiento m = new MovimientoReversi(columna, fila, turno);
					p.ejecutaMovimiento(m);
				}

			} catch (MovimientoInvalido e) {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	public void movAleatorio(Ficha turno) {

		// si el turno es de blancas y el aleatorio lo mandan las propias
		// blancas no se permite hacer el movimiento, asi como si ambos
		// jugadores son automaticos
		if ((turno == Ficha.BLANCA && modoBlancas.getJ() == TipoJugador.MANUAL)
				|| (turno == Ficha.NEGRA && modoNegras.getJ() == TipoJugador.MANUAL)) {

			movMaquina();
		}
	}

	public void movMaquina() {

		if (llamador ==p.getTurno()) {
			if (j == Juego.COMPLICA) {
				Jugador jug = new JugadorAleatorioComplica();
				p.Mover(jug);
			} else if (j == Juego.CONECTA4) {
				Jugador jug = new JugadorAleatorioConecta4();
				p.Mover(jug);
			} else if (j == Juego.GRAVITY) {
				Jugador jug = new JugadorAleatorioGravity();
				p.Mover(jug);
			} else if (j == Juego.REVERSI) {
				Jugador jug = new JugadorAleatorioReversi();
				p.Mover(jug);
			}
		}
	}

	public Juego getJ() {
		return j;
	}

	public FactoriaTipoJuego getFactoria() {
		return f;
	}

	public Partida getPartida() {
		return p;
	}

	public ReglasJuego getReglas() {
		return r;
	}

	public void addObserver(Observer o) {
		p.addObserver(o);
	}

	public void reset(FactoriaTipoJuego f) {
		r = f.creaReglas();
		p.reset(r);
		j = r.getTipo();

	}

	public void undo(Ficha turno) {
		if ((turno == Ficha.BLANCA && modoBlancas.getJ() == TipoJugador.MANUAL)
				|| (turno == Ficha.NEGRA && modoNegras.getJ() == TipoJugador.MANUAL)){

				p.undo();
		}
		
	}

	public void deshacermas(Ficha turno){
		if (turno==Ficha.BLANCA){
			modoBlancas.deshacer();
		}else if (turno==Ficha.NEGRA) 
			modoNegras.deshacer();
	}
	public void undoMaquina() {
		p.undo();
		
	}

	public void reset() {
		reset(f);

	}

	public void cambiaJuego(Juego seleccionado, String filas, String columnas) {

		if (seleccionado == Juego.GRAVITY) {
			int h;
			int w;
			if (filas.isEmpty() && columnas.isEmpty()) {
				f = new FactoriaGravity();

			} else {
				try {
					h = Integer.parseInt(filas);
					w = Integer.parseInt(columnas);
					if (h <= 0) {
						h = 1;

					}
					if (w <= 0) {
						w = 1;
					}
					f = new FactoriaGravity(h, w);
				} catch (NumberFormatException e) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Numero incorrecto",
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}

		} else if (seleccionado == Juego.CONECTA4) {
			f = new FactoriaConecta4();

		} else if (seleccionado == Juego.COMPLICA) {
			f = new FactoriaComplica();

		} else if (seleccionado == Juego.REVERSI) {
			f = new FactoriaReversi();

		}

		reset();

	}

	public void inicio() {
		p.inicio();

	}

	public void tipoJugador(TipoJugador tipo, Ficha f) {

		if (tipo == TipoJugador.MANUAL) {

			if (f == Ficha.BLANCA) {
				modoBlancas.terminar();
				modoBlancas = new ModoMan();
				modoBlancas.comenzar();
			} else if (f == Ficha.NEGRA) {
				modoNegras.terminar();
				modoNegras = new ModoMan();
				modoNegras.comenzar();
			}

		} else if (tipo == TipoJugador.AUTOMATICO) {

			if (f == Ficha.BLANCA) {
				modoBlancas.terminar();
				modoBlancas = new ModoAuto(this);
				modoBlancas.comenzar();
			} else if (f == Ficha.NEGRA) {
				modoNegras.terminar();
				modoNegras = new ModoAuto(this);
				modoNegras.comenzar();
			}
		}

	}

	public void reiniciaHilo(Ficha turno) {
		if (turno == Ficha.BLANCA) {
			modoBlancas.terminar();
			modoBlancas.comenzar();
		} else if (turno == Ficha.NEGRA) {
			modoNegras.terminar();
			modoNegras.comenzar();
		}

	}

	public void matahilos() {
		modoBlancas.terminar();
		modoNegras.terminar();
	}

}
