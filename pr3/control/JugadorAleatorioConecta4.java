package tp.pr3.control;

import tp.pr3.constants.Constants;
import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.Tablero;
import tp.pr3.logica.Utiles;

public class JugadorAleatorioConecta4 implements Jugador{

	
	

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		int random = (int) ((Math.random()*Constants.AnchoC4)+1);
		FactoriaTipoJuego f= new FactoriaConecta4();
		
		return f.creaMovimiento(random,Utiles.fila(random, tab),color);
	}

}
