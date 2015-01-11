package tp.pr2.logica;

public interface ReglasJuego {

    Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t);

    	/*Permite averiguar si en la partida ya tenemos un ganador o no. Devuelve el color del ganador (si lo hay).
				Parameters:
				ultimoMovimiento - Ultimo movimiento realizado. Las distintas implementaciones pueden utilizar este parámetro para optimizar la búsqueda del ganador.
				t				 - Estado del tablero.
			Returns:
			color del ganador, si lo hay. Si no lo hay, devuelve Ficha.VACIA (eso NO significa necesariamente que la partida haya terminado en tablas).
    	 */
    Tablero iniciaTablero();

    Ficha jugadorInicial();

    Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t);

    boolean tablas(Ficha ultimoEnPoner, Tablero t);
    
    int getAlto();
    
    int getAncho();
    
 
}
