	package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.TableroInmutable;

public interface Jugador {
	
	
	Movimiento getMovimiento(TableroInmutable tab,Ficha color);
}
