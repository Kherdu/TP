package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.ReglasJuego;

public interface FactoriaTipoJuego {

	
	public abstract ReglasJuego creaReglas();
	
	public abstract Movimiento creaMovimiento(int col, int fila, Ficha color);
	
	public abstract Jugador creaJugadorHumanoConsola(Scanner in);
	
	public abstract Jugador creaJugadorAleatorio();
}
