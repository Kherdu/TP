package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.Utiles;

public class JugadorHumanoComplica implements Jugador{
   
	private Scanner sc;
	
	JugadorHumanoComplica(Scanner in){
		this.sc=in;
		
		
	}

	@Override
	public Movimiento getMovimiento(TableroInmutable tab, Ficha color) {
		FactoriaTipoJuego f= new FactoriaComplica();
		System.out.print("Introduce la columna: "	);
		int columna = sc.nextInt();
		sc.nextLine();
		return f.creaMovimiento(columna,Utiles.fila(columna, tab),color);
	}

}
