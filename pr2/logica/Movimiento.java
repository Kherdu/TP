package tp.pr2.logica;



public class Movimiento {

    public Ficha getJugador(){
    	
        
        return Ficha.NEGRA;
    }
    
    
    public abstract boolean ejecutaMovimiento(Tablero tab);
    public abstract void undo(Tablero tab);
    
    
}
