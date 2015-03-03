package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoComplica;	
import tp.pr3.logica.ReglasComplica;
import tp.pr3.logica.ReglasJuego;

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
