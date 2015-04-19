package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.Tablero;

public class JugadorHumanoGravity implements Jugador {

private Scanner sc;
	
	JugadorHumanoGravity(Scanner in){
		this.sc=in;
		
		
	}


	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		
		FactoriaTipoJuego f= new FactoriaGravity();
		
		System.out.print("Introduce la columna: ");
		int columna = sc.nextInt();
		sc.nextLine();
		System.out.print("Introduce la fila: ");
		int fila = sc.nextInt();
		sc.nextLine();
		return f.creaMovimiento(columna, fila, color);
		
	}

}
