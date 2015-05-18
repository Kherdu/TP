package tp.pr5.logica;

public class Utiles {

	
	public static int fila (int w, TableroInmutable tab) {
		// devuelve la primera fila vacia de la columna que le pasas, si esta
		// llena devuelve 0

		int fila = tab.getFilas();

		while (tab.getCasilla(w, fila) != Ficha.VACIA && fila >= 1) {
			fila--;
		}

		return fila;

	}
	
}
