package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoConecta4;
import tp.pr3.logica.ReglasConecta4;
import tp.pr3.logica.ReglasJuego;

public class FactoriaConecta4 implements FactoriaTipoJuego {

	@Override
	public ReglasJuego creaReglas() {
		
		return new ReglasConecta4();
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		
		return new MovimientoConecta4(col,color);
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
