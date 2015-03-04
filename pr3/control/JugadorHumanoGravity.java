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
		
		FactoriaTipoJuego cosa= new FactoriaGravity();
		System.out.print("Introduce la fila: ");
		int fila = sc.nextInt();
		System.out.print("Introduce la columna: ");
		int columna = sc.nextInt();
		sc.nextLine();
		return cosa.creaMovimiento(columna, fila, color);
		
	}

}
