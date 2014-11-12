package tp.pr1.logica;

public class Partida {
	
	private static final int height=6;
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
		
               // reset(); //opcion 1
		this.tablero = new Tablero(width,height);
		this.turno=Ficha.BLANCA;
		this.terminada=false;
		this.moveStack= new int[n];
		this.ganador=Ficha.VACIA;
		this.lastPos=0;
		this.numJugadas=0;
		
		
	}
	
	public void reset(){
                
		this.turno=Ficha.BLANCA;
		this.terminada=false;
		this.moveStack= new int[n];
		this.ganador=Ficha.VACIA;
		this.lastPos=0;
		this.numJugadas=0;
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
			fwdPointer();
			
		}
		
		
		return ret;
	}
	
	
	private void advPointer(){
		//advance Pointer
		if (lastPos==9){
			lastPos=0;
		}
		else lastPos++;
		
		
	}
	
	private void fwdPointer(){
		//forward Pointer
		if (lastPos==0){
			lastPos=9;
		}
		else lastPos--;
		
		
	}
	public Ficha getTurno(){
		//saca el turno actual
		
                
		Ficha ret=this.turno;
		
		if (terminada){
			ret=Ficha.VACIA;
		}
				
                return this.turno;
	}
	
	public Ficha searchCol(){
		
		//devuelve la ultima columna introducida
		Ficha ret;
		ret=Ficha.VACIA;
		int n=moveStack[lastPos];
		boolean flag=false;
		int i=0;
		while (i<tablero.getAlto() && !flag){
			
			if (tablero.getCasilla(n, i)!=Ficha.VACIA){
				ret=tablero.getCasilla(n, i);
				flag=true;
			}
		i++;
		}
			
		return ret;
	}
	
	
	
	public Ficha getGanador(){
		
		//sacar ganador, comprobar vertical, horizontal y DIAGONAL.

                           int col=moveStack[lastPos];
		int i = fila(col);
                Ficha ret= Ficha.VACIA;
                    if ((tablero.getCasilla(col, i)==turno)){//para blancas
                        int j=col;
                        int cont=1;
                        //horizontal
                        while(j>0 && (tablero.getCasilla(j, i)==turno) && cont<4){//izquierda
                            cont++;
                            j--;
                        }
                        j=col; //reiniciamos j
                        while(j<(tablero.getAncho()-1) && (tablero.getCasilla(j, i)==turno) && cont<4){//derecha
                            cont++;
                            j--;
                        }
                        if (cont==4) this.ganador=turno;
                        //fin horizontal
                        cont=1;
                        j=col;
                        //vertical
                        while(i>0 && tablero.getCasilla(col, i)==turno && cont<4 ){
                            cont++;
                            i--;
                        }
                        if (cont==4) this.ganador=turno;
                        
                        //diagonales
              
                         cont=1;
                        while (i>0 && col>0 && cont<4 && tablero.getCasilla(col,i)==turno){ //izquierda
                         cont++;
                         i--;
                         col--;
                        }
                        if (cont==4) this.ganador=turno;
                        while (i>0 && col<tablero.getAncho() && cont<4 && tablero.getCasilla(col,i)==turno){ //derecha
                         cont++;
                         i--;
                         col--;
                        }
                        if (cont==4) this.ganador=turno;
                    }
                
		
		return ganador;
	}
	
	public boolean isTerminada(){
		
		//probar si la partida ha terminado, usar tras cada movimiento
            if (!terminada){
               int n=0;
		while (n<tablero.getAncho() && tablero.getCasilla(n, tablero.getAlto()-1)!=Ficha.VACIA){ 
			n++;
		}
		if (n== (tablero.getAncho()-1)) terminada=true; //si tablero lleno
		
		n=0;
               
               if (getGanador()!=Ficha.VACIA){
                terminada=true; //si hay ganador
               }
             
            }
	
		
		return terminada;
	}
	
	
	public Tablero getTablero(){
		
		return this.tablero;
		
	}
	public boolean ejecutaMovimiento(Ficha f, int w){
		
		boolean ret=true; //salida
		boolean fullCol=false;
		
		if (tablero.getCasilla(w, tablero.getAlto())!=Ficha.VACIA) 
			fullCol=true;
			ret=false;
			System.err.println("Movimiento Incorrecto");
		if (isTerminada()|| f!=turno) {
			//si terminamos partida, el turno no es del jugador o la columna esta completa
			ret=false;
			
			
		}	else {
			
			tablero.setCasilla(w , fila(w) , f);
            moveStack[lastPos]=w;
			advPointer();
            if (numJugadas!=10){
              numJugadas++;
			}   
			if (turno==Ficha.BLANCA)
               turno=Ficha.NEGRA;
            else if (turno==Ficha.NEGRA)
               turno=Ficha.BLANCA;
                
			
		}
		
		
		
		return ret;
	}


        public int fila(int w){
         
                 int h=tablero.getAlto();
                        h--;
                        
			while (h>=0 && tablero.getCasilla(w, h)!=Ficha.VACIA){
				h--;
				
			}
               
                    
            return h;
            
        }
        


}
