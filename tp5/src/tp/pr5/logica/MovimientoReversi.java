package tp.pr5.logica;

public class MovimientoReversi extends Movimiento{
	
	private int columna;
	private int fila;
	private Ficha color;

	public MovimientoReversi(int col, int fila, Ficha color) {
		this.columna = col;
		this.fila = fila;
		this.color = color;
	}

	@Override
	public Ficha getJugador() {
		return color;
	}

	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undo(Tablero tab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getColumna() {
		// TODO Auto-generated method stub
		return 0;
	}

}
