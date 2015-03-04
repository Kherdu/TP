package tp.pr3.logica;

import java.util.Scanner;

import tp.pr3.control.Jugador;

public class Partida {

	private int height;
	private int width;

	private static int n = 10;
	private Tablero tablero;
	private Ficha turno; // jugador que tiene el turno
	private boolean terminada;
	private Ficha ganador; //
	private Movimiento[] moveStack; // array posiciones para deshacer
	private int lastPos; // puntero para array circular
	private int numJugadas;
	private ReglasJuego juego;

	public Partida(ReglasJuego reglas) {

		this.tablero = reglas.iniciaTablero();
		this.turno = reglas.jugadorInicial();
		this.terminada = false;
		if (reglas.getTipo() == Juego.CONECTA4) {
			this.moveStack = new MovimientoConecta4[n];
		} else if (reglas.getTipo() == Juego.COMPLICA) {
			this.moveStack = new MovimientoComplica[n];
		}
		this.ganador = Ficha.VACIA;
		this.lastPos = 0;
		this.numJugadas = 0;
		this.height = reglas.getAlto();
		this.width = reglas.getAncho();
		this.juego = reglas;

	}

	public Ficha getGanador() {
		/*
		 * Devuelve el color del ganador. Sólo válido si la partida ya ha
		 * terminado (isTerminada() == true). Returns: Color del ganador. Si la
		 * partida terminó en tablas, Ficha.VACIA. Si la partida no ha terminado
		 * aún, el resultado es indeterminado.
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

		this.turno = Ficha.BLANCA;
		this.terminada = false;
		if (reglas.getTipo() == Juego.CONECTA4) {
			this.moveStack = new MovimientoConecta4[n];
			this.juego = new ReglasConecta4();
		} else if (reglas.getTipo() == Juego.COMPLICA) {
			this.moveStack = new MovimientoComplica[n];
			this.juego = new ReglasComplica();
		}
		this.ganador = Ficha.VACIA;
		this.lastPos = 0;
		this.numJugadas = 0;

		this.height = reglas.getAlto();
		this.width = reglas.getAncho();
		this.tablero = new Tablero(width, height);

	}

	public void ejecutaMovimiento(Movimiento mov) throws MovimientoInvalido {

		
		if (ganador != Ficha.VACIA || mov.getJugador() != turno
				|| mov.getColumna() > tablero.getAncho()
				|| mov.getColumna() < 1) { // si hay ya ganador o el movimiento
											// no pertenece al jugador al que le
											// toca o es una casilla de fuera
											// del tablero
			
			throw new MovimientoInvalido("penee");
		} else {
			mov.ejecutaMovimiento(tablero);
			moveStack[lastPos] = mov;
			avanzaTurno();
		}
		ganador = juego.hayGanador(mov, tablero);
		if (juego.tablas(mov.getJugador(), tablero) || ganador != Ficha.VACIA) {
			terminada = true;
		}
	

	}

	public boolean undo() {
		// deshacer movimiento
		boolean ret = false;
		if (numJugadas > 0 && lastPos >= 0) {
			Movimiento deshaz;

			retrocedeTurno();
			deshaz = moveStack[lastPos];
			deshaz.undo(tablero);

			ret = true;
		}
		return ret;

	}

	public ReglasJuego getJuego() {
		return juego;
	}

	
	public void Mover( Jugador j, Scanner sc ) throws MovimientoInvalido{
			
		Movimiento m= j.getMovimiento(tablero, turno) ;
		ejecutaMovimiento(m);
	}
	
	
	// -----------------------------------------------------------------------

	
	
	private void avanzaTurno() {
		// advance Pointer
		if (lastPos == 9) {
			lastPos = 0;
		} else
			lastPos++;

		cambiaTurno();
		if (numJugadas != 10) {
			numJugadas++;
		}
	}

	private void retrocedeTurno() {
		// forward Pointer
		if (lastPos == 0) {
			lastPos = 9;
		} else
			lastPos--;
		cambiaTurno();

		numJugadas--;
	}

	private void cambiaTurno() {
		if (turno == Ficha.BLANCA)
			turno = Ficha.NEGRA;
		else if (turno == Ficha.NEGRA)
			turno = Ficha.BLANCA;

	}

	public Tablero getTablero() {
		return tablero;
	}

	public String pintaTablero() {
		return tablero.toString();
	}

}
