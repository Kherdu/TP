package tp.pr4.GUI;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.TableroInmutable;

public interface Observer {
	
	
	//gestion de partida
	
	/*
	 * Método invocado por la clase Partida que permite notificar a sus observadores(
	 * las vistas) que se ha reiniciado la partida. Proporciona información del
	 * estado inicial del tablero y el turno (que será una ficha blanca o negra).	
	 */
	abstract void onReset(TableroInmutable tab, Ficha turno);
	
	
	/*
	 * La partida notifica a los observadores que ha terminado la partida llamando a este método. 
	 * Además proporciona al observador una vista del tablero de sólo
	 *	lectura y el ganador.
	 */
	abstract void onPartidaTerminada(TableroInmutable tab, Ficha ganador);
	
	
	/*La partida notifica a los observadores que se ha cambiado el juego. Se proporciona
	 *el estado inicial del tablero y el turno
	 */
	abstract void onCambioJuego(TableroInmutable tab, Ficha turno);
	
	
	// Gestion tablero
	
	/* La partida notifica a los observadores que una operación deshacer no ha tenido
	 * éxito porque no se puede deshacer.
	 */
	abstract void onUndoNotPossible(TableroInmutable tab, Ficha turno);
	
	
	/* La partida notifica a los observadores que se ha deshecho un movimiento. Además,
     * proporciona el estado final del tablero, el turno del siguiente jugador y si
	 * hay más movimientos a deshacer o no.
	 */
	abstract void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas);
	
	/* La partida notifica a los observadores que se ha terminado de realizar un movimiento.
	 * Se proporciona además una vista del tablero de sólo lectura, el jugador
     * que ha jugado, y el turno del siguiente jugador.
	 */
	abstract void onMovimientoEnd(TableroInmutable tab, Ficha jugador, Ficha turno);
	
	/* La partida notifica que se ha producido un movimiento incorrecto proporcionando
	 * el objeto MovimientoInvalido con una explicación del problema que se
	 * ha producido.
	 */
	abstract void onMovimientoIncorrecto(TableroInmutable movimientoException);

}
