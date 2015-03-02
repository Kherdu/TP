package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.Tablero;

public class JugadorHumanoComplica implements Jugador{
   
	private Scanner sc;
	
	JugadorHumanoComplica(Scanner in){
		this.sc=in;
		
		
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		// TODO Auto-generated method stub
		return null;
	}

}
