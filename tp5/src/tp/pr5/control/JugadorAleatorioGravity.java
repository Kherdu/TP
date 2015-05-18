package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.TableroInmutable;

public class JugadorAleatorioGravity implements Jugador{

	

	@Override
	public Movimiento getMovimiento(TableroInmutable tab, Ficha color) {
		
		int columnaRandom=0;
		int filaRandom=0;
		FactoriaTipoJuego f= new FactoriaGravity();
		do {
			columnaRandom = (int) ((Math.random()*tab.getColumnas())+1);
			filaRandom = (int) ((Math.random()*tab.getFilas())+1);
		}while(tab.getCasilla(columnaRandom, filaRandom)!= Ficha.VACIA);
		
		return f.creaMovimiento(columnaRandom,filaRandom,color);
	}

}
