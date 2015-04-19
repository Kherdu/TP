package tp.pr3.logica.test;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoComplica;
import tp.pr3.logica.ReglasComplica;
import tp.pr3.logica.ReglasJuego;

public class UndoComplicaTest extends UndoConecta4Test {
	
	@Override
	protected Movimiento getMovimiento(int donde, Ficha color) {
		return new MovimientoComplica(donde, color);
	}
	
	@Override
	protected ReglasJuego getReglas() {
		return new ReglasComplica();
	}
}
