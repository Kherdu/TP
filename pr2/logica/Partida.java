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


	tablero = reglas.iniciaTablero();
	this.turno = reglas.jugadorInicial();
	this.terminada = false;
	this.moveStack = new MovimientoConecta4[n];
	this.ganador = Ficha.VACIA;
	this.lastPos = 0;
	this.numJugadas = 0;
    this.height = reglas.getAlto();
    this.width = reglas.getAncho();
    this.juego = reglas;
    
	}

    public boolean ejecutaMovimiento(Movimiento mov) {
    
    	boolean ret=mov.ejecutaMovimiento(tablero);
    	ganador= juego.hayGanador(mov, tablero);
    	cambiaTurno();
    	if (numJugadas != 10) {
              numJugadas++;
          }
        advPointer();      
      
    	return ret;
		
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
	if (!terminada && ganador == Ficha.VACIA) {
            int n = 1;

            while (n < tablero.getAncho()
            && tablero.getCasilla(n, 1) != Ficha.VACIA) {
		
                n++;
            }
            
            if (n == (tablero.getAncho()))
		terminada = true; // si tablero lleno==fila mas alta llena sin vacios

		if (getGanador() != Ficha.VACIA) {
                    terminada = true; // si hay ganador
		}

	}
	return terminada;
    }

    public void reset(ReglasJuego reglas) {

	this.turno = Ficha.BLANCA;
	this.terminada = false;
	this.moveStack = new MovimientoConecta4[n];
	this.ganador = Ficha.VACIA;
	this.lastPos = 0;
	this.numJugadas = 0;
	tablero.reset();
    this.height = reglas.getAlto();
    this.width = reglas.getAncho();
        
    }

    public boolean undo() {
	// deshacer movimiento
	boolean ret = false;

	/*if (numJugadas > 0) {
            if (lastPos == 0)	lastPos = 10;

            int pos = moveStack[lastPos - 1];
            int h = (fila(pos) + 1); //

            tablero.setCasilla(pos, h, Ficha.VACIA);
            ret = true;
            numJugadas--;
            fwdPointer();
            cambiaTurno();
	}*/ 
	
	Movimiento deshaz;
	deshaz=moveStack[lastPos-1];
	deshaz.undo(tablero);
    return ret;
    	
    }
    
    public ReglasJuego getJuego() {
        return juego;
    }
    //-----------------------------------------------------------------------
    
    private void advPointer() {
	// advance Pointer
	if (lastPos == 9) {
            lastPos = 0;
	} else
            lastPos++;

    }

    private void fwdPointer() {
	// forward Pointer
	if (lastPos == 0) {
            lastPos = 9;
	} else
            lastPos--;

    }

    public int fila(int w) {
	// devuelve la primera fila vacia de la columna que le pasas, si esta
	// llena devuelve 1

	int fila = tablero.getAlto();

	while (fila > 1 && tablero.getCasilla(w, fila) != Ficha.VACIA) {
            fila--;

	}

	return fila;

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
