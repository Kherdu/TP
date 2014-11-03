package pr1;

import java.lang.annotation.RetentionPolicy;

public class Partida {
	
	private static final int height=7;
	private static final int width=7;
	
	private static int n=10;
	private Tablero tablero;
	private Ficha turno; //jugador que tiene el turno
	private boolean terminada; 
	private Ficha ganador; //
	private int[] moveStack; //array posiciones
	private int lastPos; //puntero para array circular
	private int numJugadas;
	
	public Partida(){
		
		tablero = new Tablero(height,width);
		turno=Ficha.VACIA;
		terminada=false;
		moveStack= new int[n];
		ganador=Ficha.VACIA;
		lastPos=0;
		numJugadas=0;
		
		
	}
	
	public void reset(){
		tablero.reset();
	}
	
	public boolean undo(){
		//deshacer movimiento
		boolean ret=false;
		
		if (numJugadas != 0){
			
			int h=0;
			while (h<=tablero.getAlto() && tablero.getCasilla(h, moveStack[lastPos])!=Ficha.VACIA){
				
				h++;
			}
			tablero.setCasilla(h, moveStack[lastPos], Ficha.VACIA);
			ret=true;
			numJugadas--;
			prevPointer();
			
		}
		
		
		return ret;
	}
	
	
	private void nextPointer(){
		
		if (lastPos==9){
			lastPos=0;
		}
		else lastPos++;
		
		
	}
	
	private void prevPointer(){
		if (lastPos==0){
			lastPos=9;
		}
		else lastPos--;
		
		
	}
	public Ficha getTurno(){
		
		Ficha ret;
		ret=Ficha.VACIA;
		
		if (!terminada){
			
			ret=searchCol();
			
		}
				
		return ret;
	}
	
	public Ficha searchCol(){
		
		
		Ficha ret;
		ret=Ficha.VACIA;
		int n=moveStack[lastPos];
		boolean flag=false;
		int i=0;
		while (i<tablero.getAlto() && !flag){
			
			if (tablero.getCasilla(i, n)!=Ficha.VACIA){
				ret=tablero.getCasilla(i, n);
				flag=true;
			}
		i++;
		}
			
		return ret;
	}
	
	
	
	public Ficha getGanador(){
		
		//sacar ganador, comprobar vertical, horizontal y DIAGONAL.
		Ficha ret;
		ret=Ficha.VACIA;
		
		if (terminada){
			
			
		}
		
		
		return ret;
	}
	
	public boolean isTerminada(){
		boolean ret=false;
		
		int n=0;
		while (n<tablero.getAncho() && tablero.getCasilla(tablero.getAlto(), n)!=Ficha.VACIA){ 
			n++;
		}
		if (n== (tablero.getAncho()-1)) ret=true; //si tablero lleno
		
		n=0;
		
		terminada=ret;
		return ret;
	}
	
	
	public Tablero getTablero(){
		
		return tablero;
		
	}
	public boolean ejecutaMovimiento(Ficha f, int w){
		
		boolean ret=true; //salida

		boolean fullCol=false;
		
		if (tablero.getCasilla(0, w)!=Ficha.VACIA) 
			fullCol=true;
		
		if (terminada|| f!=turno || fullCol) {
			//si termianmos partida, el turno no es del jugador o la columna esta completa
			ret=false;
			
			
		}	else {
			
			int h=tablero.getAlto();
			while (h>=0 && tablero.getCasilla(h, w)!=Ficha.VACIA){
				h--;
				
			}
			
			if (numJugadas!=10){
				
				numJugadas++;
			}
			
			tablero.setCasilla(h, w, f);
			nextPointer();
			if (turno==f)
				turno=Ficha.NEGRA;
			else if (turno==f)
				turno=Ficha.BLANCA;
			
		}
		
		
		
		return ret;
	}











}
