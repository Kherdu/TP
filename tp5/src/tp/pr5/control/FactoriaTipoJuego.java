package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.ReglasJuego;

public interface FactoriaTipoJuego {

	
	public abstract ReglasJuego creaReglas();
	
	public abstract Movimiento creaMovimiento(int col, int fila, Ficha color);
	
	public abstract Jugador creaJugadorHumanoConsola(Scanner in);
	
	public abstract Jugador creaJugadorAleatorio();
}
