package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.TableroInmutable;

public class JugadorHumanoGravity implements Jugador {

private Scanner sc;
	
	JugadorHumanoGravity(Scanner in){
		this.sc=in;
		
		
	}


	@Override
	public Movimiento getMovimiento(TableroInmutable tab, Ficha color) {
		
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
