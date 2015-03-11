	package tp.pr4.control;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.Tablero;

public interface Jugador {
	
	
	Movimiento getMovimiento(Tablero tab,Ficha color);
}
