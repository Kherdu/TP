package tp.pr2.logica;

public class Partida {
   
	private int height;
    private int width;

    private static int n = 10;
    private Tablero tablero;
    private Ficha turno; // jugador que tiene el turno
    private boolean terminada;
    private Ficha ganador; //
    private Movimiento[] moveStack; // array posiciones, sustituir por clase nueva que guarde posicion y color de ficha
    private int lastPos; // puntero para array circular
    private int numJugadas;
    private ReglasJuego juego;
    
    public Partida(ReglasJuego reglas) {


	this.tablero = reglas.iniciaTablero();
	this.turno = reglas.jugadorInicial();
	this.terminada = false;
	if (reglas.getTipo() == "c4"){
		this.moveStack = new MovimientoConecta4[n]; 
	}else if(reglas.getTipo() == "co"){
		this.moveStack = new MovimientoComplica[n];	
	}
	this.ganador = Ficha.VACIA;
	this.lastPos = 0;
	this.numJugadas = 0;
    this.height = reglas.getAlto();
    this.width = reglas.getAncho();
    this.juego = reglas;
    
	}

    
    public Ficha getGanador() {
	/*Devuelve el color del ganador. Sólo válido si la partida ya ha terminado (isTerminada() == true).
    	Returns:
    	Color del ganador. Si la partida terminó en tablas, Ficha.VACIA. Si la partida no ha terminado aún, el resultado es indeterminado.
    */
    	return ganador;
    }
    
    public Ficha getTurno() {
		// saca el turno actual
		Ficha ret = this.turno;
		if (terminada) {
			ret = Ficha.VACIA;
		}

		return ret;
        
    }
    
    public boolean isTerminada() {

	// probar si la partida ha terminado, usar tras cada movimiento
	
	return terminada; 	
   
    }

    public void reset(ReglasJuego reglas) {

	this.turno = Ficha.BLANCA;
	this.terminada = false;
	if (reglas.getTipo() == "c4"){
		this.moveStack = new MovimientoConecta4[n]; 
	}else if(reglas.getTipo() == "co"){
		this.moveStack = new MovimientoComplica[n];	
	}
	this.ganador = Ficha.VACIA;
	this.lastPos = 0;
	this.numJugadas = 0;
	tablero.reset();
    this.height = reglas.getAlto();
    this.width = reglas.getAncho();
        
    }

    
    public boolean ejecutaMovimiento(Movimiento mov) {
        
    	boolean ret=true;
    	if (ganador!=Ficha.VACIA || mov.getJugador()!= turno || mov.getColumna()>tablero.getAncho() || mov.getColumna()<1){ //si hay ya ganador o el movimiento no pertenece al jugador al que le toca o es una casilla de fuera del tablero
    		ret=false;
    	}else{
    		ret=mov.ejecutaMovimiento(tablero);
    		moveStack[lastPos]=mov;
    		if (ret) avanzaTurno();      
    	}
    	ganador= juego.hayGanador(mov, tablero);
    	if (juego.tablas(mov.getJugador(),tablero)||ganador!=Ficha.VACIA){
    		terminada=true;
    	}
    	return ret;
    	/*
    	boolean ret=mov.ejecutaMovimiento(tablero);
    	if (ret && mov.getJugador()==turno && ganador==Ficha.VACIA){
    		moveStack[lastPos]=mov;
    		avanzaTurno();  
    		
    	}
    	ganador= juego.hayGanador(mov, tablero);
    	if (juego.tablas(mov.getJugador(),tablero)||ganador!=Ficha.VACIA){
    		terminada=true;
    	}
    	return ret;
    */
    }
    
    public boolean undo() {
	// deshacer movimiento
	boolean ret = false;
	if (numJugadas>0 && lastPos>=0){
		Movimiento deshaz;
		
		retrocedeTurno();
		deshaz=moveStack[lastPos];
		deshaz.undo(tablero);
		
		ret=true;
	}
    return ret;
    	
    }
    
    public ReglasJuego getJuego() {
        return juego;
    }
    
   
    //-----------------------------------------------------------------------
    
    private void avanzaTurno() {
	// advance Pointer
	if (lastPos == 9) {
            lastPos = 0;
	} else lastPos++;
	
	cambiaTurno();
	if (numJugadas != 10) {
			numJugadas++;
		}
    }

    private void retrocedeTurno() {
	// forward Pointer
	if (lastPos == 0) {
            lastPos = 9;
	} else
            lastPos--;
	cambiaTurno();
		
	numJugadas--;
    }

    
    
    private void cambiaTurno() {
	if (turno == Ficha.BLANCA)
            turno = Ficha.NEGRA;
	else if (turno == Ficha.NEGRA)
            turno = Ficha.BLANCA;

    }

	public Tablero getTablero() {
		return tablero;
	}

	
    
    
}
