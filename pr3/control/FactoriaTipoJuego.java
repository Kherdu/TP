package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.ReglasJuego;

public interface FactoriaTipoJuego {

	
	public abstract ReglasJuego creaReglas();
	
	public abstract Movimiento creaMovimiento(int col, int fila, Ficha color);
	
	public abstract Jugador creaJugadorHumanoConsola(Scanner in);
	
	public abstract Jugador creaJugadorAleatorio();
}
