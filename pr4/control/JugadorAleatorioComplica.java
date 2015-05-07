package tp.pr4.control;

import tp.pr4.constants.Constants;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.Tablero;
import tp.pr4.logica.TableroInmutable;
import tp.pr4.logica.Utiles;

public class JugadorAleatorioComplica implements Jugador{

	
	
	@Override
	public Movimiento getMovimiento(TableroInmutable tab, Ficha color) {
		int random = (int) ((Math.random()*Constants.AnchoCo)+1);
		FactoriaTipoJuego f= new FactoriaComplica();
		
		return f.creaMovimiento(random,Utiles.fila(random, tab),color);
	}

}
