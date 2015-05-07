	package tp.pr4.control;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.Tablero;
import tp.pr4.logica.TableroInmutable;

public interface Jugador {
	
	
	Movimiento getMovimiento(TableroInmutable tab,Ficha color);
}
