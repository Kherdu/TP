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
		
		if (numJugadas > 0){
			if(lastPos == 0)lastPos = 10;
			
			int pos=moveStack[lastPos-1];
			int h=(fila(pos)+1); //
		
			tablero.setCasilla(pos,h, Ficha.VACIA);
			ret=true;
			numJugadas--;
			fwdPointer();
			cambiaTurno();
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
	
	
	public Ficha getGanador(){
		
		//sacar ganador, comprobar vertical, horizontal y DIAGONAL.

        int col=moveStack[lastPos]; //columna de la casilla
		int i = (fila(col)+1); //fila de la casilla
                Ficha ret= Ficha.VACIA;
                    if ((tablero.getCasilla(col, i)==turno)){
                        int j=col;
                        int cont=0;//la propia casilla
                        //horizontal
                        while(j>0 && (tablero.getCasilla(j, i)==turno) && cont<4){//izquierda
                            cont++;
                            j--;
                        }
                        j=col+1; //reiniciamos j para contar a la derecha de la casilla
                        while(j<=(tablero.getAncho()) && (tablero.getCasilla(j, i)==turno) && cont<4){//derecha
                            cont++;
                            j++;
                        }
                        if (cont==4) this.ganador=turno;
                        //fin horizontal
                        
                        
                        cont=0;
                        j=col;
                        //vertical
                        while(i<=tablero.getAlto() && tablero.getCasilla(j, i)==turno && cont<4 ){
                            cont++;
                            i++;
                        }
                        if (cont==4) this.ganador=turno;
                        
                        //diagonales
                        i=(fila(col)+1);
                        cont=0;
                        j=col;
                        while (i<=tablero.getAlto() && j>0 && cont<4 && tablero.getCasilla(j,i)==turno){ //izquierda
                        	cont++;
                        	i++;
                        	j--;
                        }
                        if (cont==4) this.ganador=turno;
                        cont=0;
                        j=col;
                        i=(fila(col)+1);
                        while (i<=tablero.getAlto() && j<=tablero.getAncho() && cont<4 && tablero.getCasilla(j,i)==turno){ //derecha
                         cont++;
                         i++;
                         j++;
                        }
                        if (cont==4) this.ganador=turno;
                    }
                    if (ganador!=Ficha.VACIA) terminada=true;
                    return ganador;
	}
	
	public boolean isTerminada(){
		
		//probar si la partida ha terminado, usar tras cada movimiento
        if (!terminada){
        	int n=1;
        	
        	while (n<tablero.getAncho() && tablero.getCasilla(n,1)!=Ficha.VACIA){ 
        		n++;
        	}
        	if (n==(tablero.getAncho())) terminada=true; //si tablero lleno==fila mas alta llena sin vacios
		
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
		
		if (w<1 || w>tablero.getAncho()){ //si se intenta meter fuera del tablero
			ret=false;
			System.err.println("Movimiento Incorrecto");
			
		}
		else if (tablero.getCasilla(w,fila(w))!=Ficha.VACIA) {
			//si columna completa
			ret=false;
			System.err.println("Movimiento Incorrecto");
		}
		else if (terminada) {
			//si terminamos partida
			ret=false;
			System.err.println("Movimiento incorrecto");
		}
		else if (f!=turno){
			//el turno no pertenece al jugador
			ret=false;
			System.err.println("Movimiento incorrecto");
		} 
		else {
			int fila=fila(w);
			tablero.setCasilla(w , fila(w) , f);
			moveStack[lastPos]=w;
            if (getGanador()!=Ficha.VACIA){
            	System.out.println(tablero.toString());
				System.out.print("Ganan las ");
				if (turno==Ficha.BLANCA) 
					System.out.println("blancas");
				else if (turno==Ficha.NEGRA) 
					System.out.println("negras");
				
			}else{ 
				
				advPointer();
				if (numJugadas!=10){
					numJugadas++;
				}   
				cambiaTurno();
			}
		}
		
		
		return ret;
	}


        public int fila(int w){
         //devuelve la primera fila vacia de la columna que le pasas
        		 
            int fila=tablero.getAlto();        
                        
			while (fila>1 && tablero.getCasilla(w, fila)!=Ficha.VACIA){
				fila--;
				
			}
               
                    
            return fila;
            
        }
        
        private void cambiaTurno(){
        	if (turno==Ficha.BLANCA)
				turno=Ficha.NEGRA;
			else if (turno==Ficha.NEGRA)
				turno=Ficha.BLANCA;
        	
        }
    
}
