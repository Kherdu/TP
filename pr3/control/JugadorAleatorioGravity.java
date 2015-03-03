package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.Tablero;

public class JugadorAleatorioGravity implements Jugador{

	

	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		FactoriaTipoJuego f= new FactoriaConecta4();
		int columnaRandom = (int) ((Math.random()*tab.getAncho())+1);
		int filaRandom = (int) ((Math.random()*tab.getAlto())+1);
		
		
		return f.creaMovimiento(columnaRandom,filaRandom,color);
	}

}
