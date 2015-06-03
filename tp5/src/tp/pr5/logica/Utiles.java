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

	
	public static boolean sePuede(TableroInmutable tablero, int x, int y, Ficha ficha, int accionX, int accionY){

		boolean salir = false;
		boolean sePuede = false;
		if (sePuede == false && tablero.getCasilla(x + accionX, y + accionY) != ficha && tablero.getCasilla(x + accionX, y + accionY) != Ficha.VACIA 
				&& x > 0 && y > 0 && x <= tablero.getColumnas() && y <= tablero.getFilas()){
			
			x+=accionX;
			y+=accionY;
			while(!salir && x > 0 && y > 0 && x <= tablero.getColumnas() && y <= tablero.getFilas()){
				
				if (tablero.getCasilla(x, y) == Ficha.VACIA){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == ficha){
					
					salir = true;
					sePuede = true;
					
				}else{	
					x+=accionX; y+=accionY;
				}
				
			}
			
		}
	
		return sePuede;
	}
	
	
}
