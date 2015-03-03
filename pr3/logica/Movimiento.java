package tp.pr3.logica;

public abstract class Movimiento {

	public abstract Ficha getJugador();

	public abstract void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido;

	public abstract void undo(Tablero tab);

	public abstract int getColumna();

}
