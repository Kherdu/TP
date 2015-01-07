package tp.pr2.logica;

public class MovimientoConecta4 extends Movimiento {
    private Ficha ficha;
    private int columna;
    
    public MovimientoConecta4(int col, Ficha color) {
		
    }
    
    @Override
    public boolean ejecutaMovimiento(Tablero tab){
    
        boolean ret = true; // salida

	if (columna < 1 || columna > tab.getAncho()) { // si se intenta meter fuera del											// tablero
            ret = false;
            System.err.println("Movimiento incorrecto");
	} else if (tab.getCasilla(columna, fila(columna)) != Ficha.VACIA) {
            // si columna completa
            ret = false;
            System.err.println("Movimiento incorrecto");
	} else if (terminada) {
            // si terminamos partida
            ret = false;
            System.err.println("Movimiento incorrecto");
	} else if (ficha != turno) {
            // el turno no pertenece al jugador
            ret = false;
            System.err.println("Movimiento incorrecto");
        } else {
            tab.setCasilla(columna, fila(columna), ficha);
            moveStack[lastPos] = columna;
            
            if (getGanador() != Ficha.VACIA) {                
		System.out.println(tab.toString());
		System.out.print("Ganan las ");
                
		if (turno == Ficha.BLANCA)
                    System.out.println("blancas");
		else if (turno == Ficha.NEGRA)
                    System.out.println("negras");
            } else {
                advPointer();
                    
                if (numJugadas != 10) {
                    numJugadas++;
                }
                    
            cambiaTurno();                
            }
	}

	return ret;
    }
    
    
    @Override
    public void undo(Tablero tab){
        
    
    }

}
