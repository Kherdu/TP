package tp.pr4.control;

import tp.pr4.constants.Constants;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.Tablero;
import tp.pr4.logica.TableroInmutable;
import tp.pr4.logica.Utiles;

public class JugadorAleatorioConecta4 implements Jugador{

	
	

	@Override
	public Movimiento getMovimiento(TableroInmutable tab, Ficha color) {
		
		int random=0;
		
		do{
			random = (int) ((Math.random()*Constants.AnchoC4)+1);
		}while (Utiles.fila(random, tab)==0);
		
		FactoriaTipoJuego f= new FactoriaConecta4();
		
		return f.creaMovimiento(random,Utiles.fila(random, tab),color);
	}

}
