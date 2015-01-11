package tp.pr2.logica;



public abstract class Movimiento {


	public abstract Ficha getJugador();
    public abstract boolean ejecutaMovimiento(Tablero tab);
    public abstract void undo(Tablero tab);
    
    
}
