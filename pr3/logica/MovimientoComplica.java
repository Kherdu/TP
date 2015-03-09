package tp.pr3.logica;

public class MovimientoComplica extends Movimiento {
//Herencia de movimiento para el juego complica.
	private Ficha ficha;
	private int columna;
	private MovimientoComplica[] borrados;
	private int ultimaBorrada;

	public MovimientoComplica(int donde, Ficha color) {
		this.ficha = color;
		this.columna = donde;
		this.ultimaBorrada = 0;
		this.borrados = new MovimientoComplica[10];
	}

	@Override
	public Ficha getJugador() {

		return ficha;
	}

	@Override
	public int getColumna() {

		return columna;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		//Funcion que se encarga de hacer el movimiento
		
		MovimientoComplica mov = new MovimientoComplica(columna, Ficha.VACIA);

		if (columna < 1 || columna > tab.getAncho()) { // si se intenta meter
														// fuera del tablero
			
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y "+ tab.getAncho()+ ".");
		} else {
			if (Utiles.fila(columna, tab) == 0) { 
				// si columna llena
				mov.setFicha(tab.getCasilla(columna, tab.getAlto()));
				borrados[ultimaBorrada] = mov; /* se encarga de las fichas que se pierden 
				por la parte de abajo del tablero*/
				advPila();
				bajaColumna(tab, columna);

			}

			tab.setCasilla(columna, Utiles.fila(columna, tab), ficha); 
			// si no esta llena movimiento normal y guardar vacio en el array

			borrados[ultimaBorrada] = mov;
			advPila();   
		}
		
	}

	@Override
	public void undo(Tablero tab) {
		// Funcion que se encarga del borrado
		if (Utiles.fila(columna, tab) > 0) { // columna no llena
			tab.setCasilla(columna, (Utiles.fila(columna, tab) + 1), Ficha.VACIA);
		}

		else { // columna llena
			if (borrados[ultimaBorrada - 1].getJugador() != Ficha.VACIA) {
				subeColumna(tab, columna);
				tab.setCasilla(columna, tab.getAlto(),
						borrados[ultimaBorrada - 1].getJugador());
			} else
				tab.setCasilla(columna, (Utiles.fila(columna, tab) + 1), Ficha.VACIA);
		}
		fwdPila();

	}

	

	public void bajaColumna(Tablero t, int w) {

		Ficha aux;
		for (int i = (t.getAlto()); i > 0; i--) { // bajamos todas las fichas
													// una casilla
			aux = t.getCasilla(w, i);
			t.setCasilla(w, i + 1, aux);
		}
		t.setCasilla(w, 1, Ficha.VACIA);// vaciamos la de arriba para luego
										// colocar
	}

	public void subeColumna(Tablero t, int w) {
		//
		Ficha aux;
		for (int i = 2; i <= t.getAlto(); i++) { // subimos todas las fichas una
													// casilla, deshechando la
													// ficha de arriba de todo
													// (w,0)
			aux = t.getCasilla(w, i);
			t.setCasilla(w, i - 1, aux);
		}

	}

	private void advPila() { // "puntero" del array circular de la memoria al guardar
		ultimaBorrada++;
		if (ultimaBorrada == 10)
			ultimaBorrada = 0;

	}

	private void fwdPila() {//"puntero" del array de memoria al borrar
		ultimaBorrada--;
		if (ultimaBorrada == 0)
			ultimaBorrada = 9;
	}

}
