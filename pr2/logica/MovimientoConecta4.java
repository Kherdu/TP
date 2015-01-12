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

	if (columna < 1 || columna > tab.getAncho() || fila(columna,tab)==0) { // si se intenta meter fuera del tablero
            ret = false;
            System.err.println("Movimiento incorrecto");
	} else if (tab.getCasilla(columna, (fila(columna, tab))) != Ficha.VACIA) {
            // si columna completa
            ret = false;
            System.err.println("Movimiento incorrecto");
           
	}
            tab.setCasilla(columna, fila(columna, tab), ficha);
             
            
	

	return ret;
    }
    
    
    @Override
    public void undo(Tablero tab){
        
    tab.setCasilla(columna,(fila(columna,tab)+1), Ficha.VACIA);
   
    }

    @Override
    public Ficha getJugador(){
    	
    return ficha;
    }
    
    
    
    public int fila(int w, Tablero tablero) {
    	// devuelve la primera fila vacia de la columna que le pasas, si esta
    	// llena devuelve 0

    	int fila = tablero.getAlto();

    	while(tablero.getCasilla(w, fila)!=Ficha.VACIA && fila>=1){
    		fila--;
    	}
    	
    	return fila;

        }
}
