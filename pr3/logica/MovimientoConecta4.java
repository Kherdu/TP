package tp.pr3.logica;

public class MovimientoConecta4 extends Movimiento {
	private Ficha ficha;
	private int columna;

	@Override
	public Ficha getJugador() {

		return ficha;
	}

	public int getColumna() {
		return columna;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public MovimientoConecta4(int col, Ficha color) {
		this.columna = col;
		this.ficha = color;
	}

	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {

		

		if (columna < 1 || columna > tab.getAncho()) { 
			// si se intenta meter fuera del tablero
			throw new MovimientoInvalido("Posicion incorrecta. Debe estar entre 1 y "+ tab.getAncho());
			
		} else if (Utiles.fila(columna, tab)==0) {
			// si columna completa
			throw new MovimientoInvalido("Columna llena.");

		}
		tab.setCasilla(columna, Utiles.fila(columna, tab), ficha);

		
	}

	@Override
	public void undo(Tablero tab) {

		tab.setCasilla(columna, (Utiles.fila(columna, tab) + 1), Ficha.VACIA);

	}

	
}
