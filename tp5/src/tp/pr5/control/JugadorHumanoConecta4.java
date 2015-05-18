package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.Utiles;

public class JugadorHumanoConecta4 implements Jugador {

	private Scanner sc;
	
	JugadorHumanoConecta4(Scanner in){
		this.sc=in;
		
	}
	
	@Override
	public Movimiento getMovimiento(TableroInmutable tab, Ficha color) {
		
		FactoriaTipoJuego f= new FactoriaConecta4();
		System.out.print("Introduce la columna: "	);
		int columna = sc.nextInt();
		sc.nextLine();
		return f.creaMovimiento(columna,Utiles.fila(columna, tab),color);
	}
	



}
