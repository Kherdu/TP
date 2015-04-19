package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.Tablero;
import tp.pr4.logica.Utiles;

public class JugadorHumanoComplica implements Jugador{
   
	private Scanner sc;
	
	JugadorHumanoComplica(Scanner in){
		this.sc=in;
		
		
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		FactoriaTipoJuego f= new FactoriaComplica();
		System.out.print("Introduce la columna: "	);
		int columna = sc.nextInt();
		sc.nextLine();
		return f.creaMovimiento(columna,Utiles.fila(columna, tab),color);
	}

}
