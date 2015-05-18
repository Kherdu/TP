package tp.pr5.control;

import tp.pr5.constants.Constants;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.Utiles;

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
