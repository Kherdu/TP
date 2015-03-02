package tp.pr3.logica;

public class MovimientoGravity extends Movimiento{
	private Ficha ficha;
	private int columna;
	private int fila;
	
	public MovimientoGravity(int col, int fil, Ficha f){
		this.ficha=f;
		this.columna=col;
		this.fila=fil;
	
	}

	@Override
	public Ficha getJugador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ejecutaMovimiento(Tablero tab) {
		// TODO Auto-generated method stub
		return false;
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
