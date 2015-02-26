package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoConecta4;
import tp.pr3.logica.Tablero;
import tp.pr3.logica.Utiles;

public class JugadorHumanoConecta4 implements Jugador {

	private Scanner sc;
	
	JugadorHumanoConecta4(Scanner in){
		this.sc=in;
		
		
	}
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		//System.out.println("Introduce la fila: ");   //parser
		//int fila = sc.nextInt();
		System.out.print("Introduce la columna: "	);
		int columna = sc.nextInt();
		sc.nextLine();
		return new MovimientoConecta4(columna,color);
	}
	



}
