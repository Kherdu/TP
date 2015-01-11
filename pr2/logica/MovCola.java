package tp.pr2.logica;




public class MovCola {

	
		private int columna;
		private Ficha jugador;


public MovCola(int col, Ficha jug){
	
	this.columna=col;
	this.jugador=jug;
	
}


public int getColumna() {
	return columna;
}


public Ficha getJugador() {
	return jugador;
}


public void setColumna(int columna) {
	this.columna = columna;
}


public void setJugador(Ficha jugador) {
	this.jugador = jugador;
}

}