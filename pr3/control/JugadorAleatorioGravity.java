package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.Tablero;

public class JugadorAleatorioGravity implements Jugador{

	

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		
		int columnaRandom=0;
		int filaRandom=0;
		FactoriaTipoJuego f= new FactoriaConecta4();
		do {
			columnaRandom = (int) ((Math.random()*tab.getAncho())+1);
			filaRandom = (int) ((Math.random()*tab.getAlto())+1);
		}while(tab.getCasilla(columnaRandom, filaRandom)!= Ficha.VACIA);
		
		return f.creaMovimiento(columnaRandom,filaRandom,color);
	}

}
