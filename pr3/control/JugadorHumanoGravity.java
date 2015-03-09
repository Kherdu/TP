package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.Tablero;

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
