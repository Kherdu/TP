package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoComplica;	
import tp.pr3.logica.ReglasComplica;
import tp.pr3.logica.ReglasJuego;

public class FactoriaComplica implements FactoriaTipoJuego {

	@Override
	public ReglasJuego creaReglas() {
		return new ReglasComplica();	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoComplica(col,color);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoConecta4(in);
	}

	
}
