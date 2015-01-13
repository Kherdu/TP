package tp.pr2.logica;

public class MovimientoComplica extends Movimiento {
	
	private Ficha ficha;
	private int columna;
	private MovimientoComplica[] borrados;
	private int ultimaBorrada;
	
	 public MovimientoComplica( int donde, Ficha color) {
		this.ficha=color;
		this.columna=donde;
		borrados=null;
		ultimaBorrada=0;
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
	public boolean ejecutaMovimiento(Tablero tab) {
		 boolean ret = true; // salida
		 MovimientoComplica mov= new MovimientoComplica(columna,tab.getCasilla(columna, tab.getAlto()));
		 if( fila(columna,tab)==0){ //si columna llena
			
			borrados[ultimaBorrada]=mov;
			ultimaBorrada++;
			bajaColumna(tab,columna);
			
		 }if (columna < 1 || columna > tab.getAncho() ) { // si se intenta meter fuera del tablero
		            ret = false;
		            System.err.println("Movimiento incorrecto");
		 } 
		 
		 tab.setCasilla(columna, fila(columna, tab), ficha); //si no esta llena movimiento normal y guardar vacio en el array
		 	mov.setFicha(Ficha.VACIA);
		 	borrados[ultimaBorrada]=mov;
			ultimaBorrada++;       
		 return ret;
	}

	@Override
	public void undo(Tablero tab) {
		
		
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
	 public void bajaColumna(Tablero t, int w){
		 
		 Ficha aux;
		 for (int i=(t.getAlto());i>0;i--){ //bajamos todas las fichas una casilla
			 aux=t.getCasilla(w, i);
			 t.setCasilla(w, i+1, aux);
		 }
		 t.setCasilla(w, 1, Ficha.VACIA);//vaciamos la de arriba para luego colocar
	 }
	

}
