package tp.pr4.logica;

public interface ReglasJuego {

	

	Ficha hayGanador(Movimiento ultimoMovimiento, TableroInmutable t);

	/*
	 * Permite averiguar si en la partida ya tenemos un ganador o no. Devuelve
	 * el color del ganador (si lo hay). Parameters: ultimoMovimiento - Ultimo
	 * movimiento realizado. Las distintas implementaciones pueden utilizar este
	 * parámetro para optimizar la b�squeda del ganador. t - Estado del tablero.
	 * Returns: color del ganador, si lo hay. Si no lo hay, devuelve Ficha.VACIA
	 * (eso NO significa necesariamente que la partida haya terminado en
	 * tablas).
	 */
	Tablero iniciaTablero();

	Ficha jugadorInicial();

	Ficha siguienteTurno(Ficha ultimoEnPoner, TableroInmutable t);

	boolean tablas(Ficha ultimoEnPoner, TableroInmutable t);

	int getAlto();

	int getAncho();

	Juego getTipo();
	
	int getNumJugadores();
	
	

}
