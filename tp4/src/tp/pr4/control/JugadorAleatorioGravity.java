package tp.pr4.control;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.Tablero;

public class JugadorAleatorioGravity implements Jugador{

	

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		
		int columnaRandom=0;
		int filaRandom=0;
		FactoriaTipoJuego f= new FactoriaGravity();
		do {
			columnaRandom = (int) ((Math.random()*tab.getAncho())+1);
			filaRandom = (int) ((Math.random()*tab.getAlto())+1);
		}while(tab.getCasilla(columnaRandom, filaRandom)!= Ficha.VACIA);
		
		return f.creaMovimiento(columnaRandom,filaRandom,color);
	}

}
