package tp.pr5.control;

import tp.pr5.constants.Constants;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.Utiles;

public class JugadorAleatorioComplica implements Jugador{

	
	
	@Override
	public Movimiento getMovimiento(TableroInmutable tab, Ficha color) {
		int random = (int) ((Math.random()*Constants.AnchoCo)+1);
		FactoriaTipoJuego f= new FactoriaComplica();
		
		return f.creaMovimiento(random,Utiles.fila(random, tab),color);
	}

}
