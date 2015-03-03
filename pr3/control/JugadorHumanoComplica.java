package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.Tablero;
import tp.pr3.logica.Utiles;

public class JugadorHumanoComplica implements Jugador{
   
	private Scanner sc;
	
	JugadorHumanoComplica(Scanner in){
		this.sc=in;
		
		
	}

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		FactoriaTipoJuego f= new FactoriaConecta4();
		System.out.print("Introduce la columna: "	);
		int columna = sc.nextInt();
		sc.nextLine();
		return f.creaMovimiento(columna,Utiles.fila(columna, tab),color);
	}

}
