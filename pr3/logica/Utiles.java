package tp.pr3.logica;

public class Utiles {

	
	public static int fila (int w, Tablero tablero) {
		// devuelve la primera fila vacia de la columna que le pasas, si esta
		// llena devuelve 0

		int fila = tablero.getAlto();

		while (tablero.getCasilla(w, fila) != Ficha.VACIA && fila >= 1) {
			fila--;
		}

		return fila;

	}
	
}
