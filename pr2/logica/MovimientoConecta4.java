package tp.pr2.logica;

public class MovimientoConecta4 extends Movimiento {
    private Ficha ficha;
    private int columna;
    
  

	public Ficha getFicha() {
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
		this.columna=col;
		this.ficha=color;
    }
    
    @Override
    public boolean ejecutaMovimiento(Tablero tab){
    
        boolean ret = true; // salida

	if (columna < 1 || columna > tab.getAncho()) { // si se intenta meter fuera del tablero
            ret = false;
            System.err.println("Movimiento incorrecto");
	} else if (tab.getCasilla(columna, fila(columna, tab)) != Ficha.VACIA) {
            // si columna completa
            ret = false;
            System.err.println("Movimiento incorrecto");
            /*} else if (terminada) {
            // si terminamos partida
            ret = false;
            System.err.println("Movimiento incorrecto");
			} else if (ficha !=color) {
            // el turno no pertenece al jugador
            ret = false;
            System.err.println("Movimiento incorrecto");
        	} else {*/
	}
            tab.setCasilla(columna, fila(columna, tab), ficha);
            //---->registro movimientos    moveStack[lastPos] = columna;
            /*   if (hayGanador() != Ficha.VACIA) {                
			System.out.println(tab.toString());
			System.out.print("Ganan las ");
                
			if (turno == Ficha.BLANCA)
            System.out.println("blancas");
			else if (turno == Ficha.NEGRA)
            System.out.println("negras");
            } else {*/
                           
            
	

	return ret;
    }
    
    
    @Override
    public void undo(Tablero tab){
        
    
    }

    @Override
    public Ficha getJugador(){
    	
    return ficha;
    }
    public int fila(int w, Tablero tablero) {
    	// devuelve la primera fila vacia de la columna que le pasas, si esta
    	// llena devuelve 1

    	int fila = tablero.getAlto();

    	while (fila > 1 && tablero.getCasilla(w, fila) != Ficha.VACIA) {
                fila--;

    	}

    	return fila;

        }
}
