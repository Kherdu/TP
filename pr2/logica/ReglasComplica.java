package tp.pr2.logica;

public class ReglasComplica implements ReglasJuego {
	
	private Tablero tablero;
	private final int alto;
	private final int ancho;
	private Ficha ganador;
	
	public ReglasComplica() {
		
		this.ancho = 4;
        this.alto = 7;
        this.ganador=Ficha.VACIA;
		
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		// tiene que contar el numero de 4 en rayas, hay que comprobar esto antes que el ganador... absurdo en este modo el tablero
		return null;
	}

	@Override
	public Tablero iniciaTablero() {
		tablero = new Tablero(this.ancho, this.alto);
        return tablero;
	}

	@Override
	public Ficha jugadorInicial() {
		 return Ficha.BLANCA;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		 Ficha ret = Ficha.VACIA;
	        if (ultimoEnPoner == Ficha.BLANCA){
	            
	            ret = Ficha.NEGRA;
	            
	        }else if (ultimoEnPoner == Ficha.NEGRA){
	            
	            ret = Ficha.BLANCA;
	            
	        } 
	        
	        return ret;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		
		
		return false;
	}

	@Override
	public int getAlto() {
		 return alto;
	}

	@Override
	public int getAncho() {
		return ancho;
	}

}
