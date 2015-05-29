package tp.pr5.logica;

import java.util.ArrayList;
import java.util.Scanner;

import tp.pr5.GUI.Observer;
import tp.pr5.control.Jugador;

public class Partida {

	private static int TAM_PILA = 10;
	private Tablero tablero;
	private Ficha turno; // jugador que tiene el turno
	private boolean terminada;
	private Ficha ganador; //
	private Movimiento[] moveStack; // array posiciones para deshacer
	private int lastPos; // puntero para array circular
	private int numJugadas;
	private ReglasJuego juego;
	private boolean isTablas;
	private ArrayList<Observer> observers;
	private TableroInmutable tin;

	public Partida(ReglasJuego reglas) {

		this.tablero = reglas.iniciaTablero();
		this.tin = reglas.iniciaTablero();
		this.turno = reglas.jugadorInicial();
		this.terminada = false;
		if (reglas.getTipo() == Juego.CONECTA4) {
			this.moveStack = new MovimientoConecta4[TAM_PILA];
		} else if (reglas.getTipo() == Juego.COMPLICA) {
			this.moveStack = new MovimientoComplica[TAM_PILA];
		} else if (reglas.getTipo() == Juego.GRAVITY) {
			this.moveStack = new MovimientoGravity[TAM_PILA];
		} else if (reglas.getTipo() == Juego.REVERSI) {
			this.moveStack = new MovimientoReversi[TAM_PILA];
		}
		this.ganador = Ficha.VACIA;
		this.lastPos = 0;
		this.numJugadas = 0;

		this.juego = reglas;
		this.isTablas = false;
		observers = new ArrayList<Observer>();
	}

	public Ficha getGanador() {
		/*
		 * Devuelve el color del ganador. Sólo válido si la partida ya ha
		 * terminado (isTerminada() == true). Returns: Color del ganador. Si la
		 * partida terminó en tablas, Ficha.VACIA. Si la partida no ha terminado
		 * aun, el resultado es indeterminado.
		 */
		return ganador;
	}

	public Ficha getTurno() {
		// saca el turno actual
		Ficha ret = this.turno;
		if (terminada) {
			ret = Ficha.VACIA;
		}

		return ret;

	}

	public boolean isTerminada() {

		// probar si la partida ha terminado, usar tras cada movimiento

		return terminada;

	}

	public void reset(ReglasJuego reglas) {

		if (reglas.getTipo() == Juego.CONECTA4) {
			this.moveStack = new MovimientoConecta4[TAM_PILA];
			this.juego = new ReglasConecta4();
		} else if (reglas.getTipo() == Juego.COMPLICA) {
			this.moveStack = new MovimientoComplica[TAM_PILA];
			this.juego = new ReglasComplica();
		} else if (reglas.getTipo() == Juego.GRAVITY) {
			this.moveStack = new MovimientoGravity[TAM_PILA];
			this.juego = new ReglasGravity();
		}else if(reglas.getTipo() == Juego.REVERSI) {
			this.moveStack = new MovimientoReversi[TAM_PILA];
			this.juego = new ReglasReversi();			
		}

		this.turno = Ficha.BLANCA;
		this.terminada = false;
		this.ganador = Ficha.VACIA;
		this.lastPos = 0;
		this.numJugadas = 0;

		this.tablero = reglas.iniciaTablero();
		this.tin = reglas.iniciaTablero();

		for (Observer o : observers) {
			o.onReset(tin, turno);
			o.onCambioJuego(tin, turno);
		}
	}

	public void ejecutaMovimiento(Movimiento mov) throws MovimientoInvalido {

		if (ganador != Ficha.VACIA || mov.getJugador() != turno) {
			// si hay ya ganador o el movimiento
			// no pertenece al jugador al que le
			// toca o es una casilla de fuera
			// del tablero
			// este throw NUNCA es posible en el modo GUI
			for (Observer o : observers) {
				o.onMovimientoInvalido(new MovimientoInvalido(
						"error gordo y peludo"));
			}

		} else {
			mov.ejecutaMovimiento(tablero);
			tin = tablero;
			moveStack[lastPos] = mov;
			avanzaTurno();
		}
		
		this.tin = tablero;
		for (Observer o : observers) {
			// observadores movimiento correcto
			o.onMovimientoEnd(tin, mov.getJugador(), turno);

		}
		ganador = juego.hayGanador(mov, tablero);
		if (ganador==Ficha.VACIA && turno==Ficha.VACIA){
			terminada=true;
		}
		

		if (ganador != Ficha.VACIA) {
			terminada = true;
			// observadores partidaTerminada con ganador
			for (Observer o : observers) {

				o.onPartidaTerminada(tin, ganador);
			}
		}

		isTablas = juego.tablas(mov.getJugador(), tablero);

		if (isTablas) {
			terminada = true;
			for (Observer o : observers) {

				o.onPartidaTerminada(tin, ganador);

			}
		}

		
	}

	public boolean undo() {
		// deshacer movimiento
		boolean ret = false;
		boolean mas = false;
		if (numJugadas > 0 && lastPos >= 0) {
			Movimiento deshaz;

			retrocedeTurno();
			deshaz = moveStack[lastPos];
			deshaz.undo(tablero);
			mas = true;
			ret = true;
		} else {
			for (Observer o : observers) {

				o.onUndoNotPossible(tin, ganador);

			}
		}

		this.tin = tablero;

		if (numJugadas == 0)
			mas = false;
		for (Observer o : observers) {
			o.onUndo(tin, turno, mas);
		}

		return ret;
	}

	public ReglasJuego getJuego() {
		return juego;
	}

	public void Mover(Jugador j) {

		Movimiento m = j.getMovimiento(tin, turno);
		try {
			ejecutaMovimiento(m);
		} catch (MovimientoInvalido e) {
			for (Observer o : observers) {
				o.onMovimientoInvalido(e);
			}
		}
	}

	// -----------------------------------------------------------------------

	private void avanzaTurno() {

		// cambiamos turno
		turno = juego.siguienteTurno(moveStack[lastPos].getJugador(), tin);

		// avanzamos puntero
		if (lastPos == TAM_PILA - 1) {
			lastPos = 0;
		} else
			lastPos++;

		// sumamos jugada
		if (numJugadas != TAM_PILA) {
			numJugadas++;
		}
	}

	private void retrocedeTurno() {
		// retrocedemos puntero
		if (lastPos == 0) {
			lastPos = TAM_PILA - 1;
		} else
			lastPos--;
		// cambiamos turno al anterior
		turno = moveStack[lastPos].getJugador();
		// reducimos numero de jugadas (ya sabemos arriba si se puede deshacer o
		// no)
		numJugadas--;
	}

	public boolean isTablas() {
		return isTablas;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public String pintaTablero() {
		return tablero.toString();
	}

	public void addObserver(Observer o) {
		observers.add(o);

	}

	public void instruccionInvalida(String s) {
		for (Observer o : observers) {

			o.onInstruccionInvalida(new InstruccionInvalida("no he entendido "
					+ s));
		}

	}
	
	public void inicio() {
		for (Observer o : observers) {
			o.onInicio(tin, turno);
		}
	}
}
