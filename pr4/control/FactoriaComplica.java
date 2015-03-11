package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoComplica;
import tp.pr4.logica.ReglasComplica;
import tp.pr4.logica.ReglasJuego;

public class FactoriaComplica implements FactoriaTipoJuego {

	public FactoriaComplica(){
		
	}
	@Override
	public ReglasJuego creaReglas() {
		return new ReglasComplica();	
		}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoComplica(col,color);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica();
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoComplica(in);
	}

	
}
