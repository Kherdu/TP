package tp.pr4.logica;
//Herencia de movimiento para el juego Gravity
public class MovimientoGravity extends Movimiento{
	private int columna;
	private Ficha color;
	private int fila;
	private String cercania = ""; // va a valer para saber los lados de los que esta cerca.
	
	public MovimientoGravity(int colum, int f, Ficha c){
	
		this.columna = colum;
		this.fila = f;
		this.color = c;
		
		
	}
	
	@Override
	public Ficha getJugador() {
		
		return color;
		
	}

	
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		
		
		boolean aux = false;
			if (columna < 1 || columna > tab.getColumnas() || fila < 1 || fila > tab.getFilas()){
				//Si se poner fuera del tablero
				throw new MovimientoInvalido("Posición incorrecta.");
			}else if (tab.getCasilla(columna, fila) != Ficha.VACIA){
				//Si la casilla esta ocupada
				throw new MovimientoInvalido("Casilla ocupada.");
				
			}		
			else{
			
			// usar el parseo de direccion. para saber hacia donde se va a desplazar la ficha.
			if (parseoDireccion(tab) == "LRTD"){
				//Centro
				tab.setCasilla(columna, fila, color);
				
			}else if (parseoDireccion(tab).equalsIgnoreCase("L")){
				//Izquierda
				for (int i = columna; i >= 1 && aux == false; i--){
					
					if (tab.getCasilla((i-1), fila) != Ficha.VACIA || (i == 1)){						
						
						tab.setCasilla(i, fila, color);
						aux = true;
						
					}
					
				}
			aux = false;
				
			}else if (parseoDireccion(tab).equalsIgnoreCase("R")){
				//Derecha
				for (int i = columna; i <= tab.getColumnas() && aux == false; i++){
					
					if (tab.getCasilla((i+1), fila) != Ficha.VACIA || (i == tab.getColumnas())){						
						
						tab.setCasilla(i, fila, color);
						aux = true;
						
					}
					
				}
			aux = false;
			
			}else if(parseoDireccion(tab).equalsIgnoreCase("T")){
				//Arriba
				for (int i = fila; i >= 1 && aux == false; i--){
					
					if (tab.getCasilla(columna, (i-1)) != Ficha.VACIA || (i == 1)){						
						
						tab.setCasilla(columna, i, color);
						aux = true;
						
					}
					
				}
				
			aux = false;	
				
			}else if (parseoDireccion(tab).equalsIgnoreCase("D")){
				//Abajo
				for (int i = fila; i <= tab.getFilas() && aux == false; i++){
					
					if (tab.getCasilla(columna, (i+1)) != Ficha.VACIA || (i == tab.getFilas())){						
						
						tab.setCasilla(columna, i, color);
						aux = true;
						
					}
					
				}
				
				aux = false;
				
			}else if(parseoDireccion(tab).equalsIgnoreCase("LT")){
				//Diagonal hacia arriba a la izquierda
				int c = columna;
				int f = fila;
				while (c >= 1 && f >= 1 && aux == false){
					
					if (tab.getCasilla(c-1, f-1) != Ficha.VACIA || c == 1 || f == 1){
						
						tab.setCasilla(c, f, color);
						aux = true;
					}else {
						c--;
						f--;
						
					}
					
				}
				
			aux = false;
				
			}else if (parseoDireccion(tab).equalsIgnoreCase("LD")){
				//Diagonal hacia abajo a la izquierda
				int c = columna;
				int f = fila;
				while (c >= 1 && f <= tab.getFilas() && aux == false){
					
					if (tab.getCasilla(c-1, f+1) != Ficha.VACIA || c == 1 || f == tab.getFilas()){
						
						tab.setCasilla(c, f, color);
						aux = true;
					}else {
						c--;
						f++;
						
					}
					
				}
				
				aux = false;
				
			}else if(parseoDireccion(tab).equalsIgnoreCase("RT")){
				//Diagonal hacia arriba a la derecha
				int c = columna;
				int f = fila;
				while (c <= tab.getColumnas() && f >= 1 && aux == false){
					
					if (tab.getCasilla(c+1, f-1) != Ficha.VACIA || c == tab.getColumnas() || f == 1){
						
						tab.setCasilla(c, f, color);
						aux = true;
					}else {
						c++;
						f--;
						
					}
					
				}
				
			aux = false;	
				
			}else if (parseoDireccion(tab).equalsIgnoreCase("RD")){
				//Diagonal hacia abajo a la derecha
				int c = columna;
				int f = fila;
				while (c <= tab.getColumnas() && f <= tab.getFilas() && aux == false){
					
					if (tab.getCasilla(c+1, f+1) != Ficha.VACIA || c == tab.getColumnas() || f == tab.getFilas()){
						
						tab.setCasilla(c, f, color);
						aux = true;
					}else {
						c++;
						f++;
						
					}
					
				}
				
				aux = false;
				
			}
			
		}
		
		
	}

	@Override
	public void undo(Tablero tab) {
		//Funcion que se encarga de deshacer movimiento. Funciona muy parecido a ejecutar 
		//movimiento pero insertando una ficha vacia.
		boolean aux = false;
		// usar el parseo de direccion.
					if (parseoDireccion(tab) == "LRTD"){
						
						tab.setCasilla(columna, fila, Ficha.VACIA);
						
					}else if (parseoDireccion(tab).equalsIgnoreCase("L")){
						
						for (int i = columna; i >= 1 && aux == false; i--){
							
							if (tab.getCasilla((i), fila) != Ficha.VACIA || (i == 1)){						
								
								tab.setCasilla(i, fila, Ficha.VACIA);
								aux = true;
								
							}
							
						}
					aux = false;
						
					}else if (parseoDireccion(tab).equalsIgnoreCase("R")){
						
						for (int i = columna; i <= tab.getColumnas() && aux == false; i++){
							
							if (tab.getCasilla((i), fila) != Ficha.VACIA || (i == tab.getColumnas())){						
								
								tab.setCasilla(i, fila, Ficha.VACIA);
								aux = true;
								
							}
							
						}
					aux = false;
					
					}else if(parseoDireccion(tab).equalsIgnoreCase("T")){
						
						for (int i = fila; i >= 1 && aux == false; i--){
							
							if (tab.getCasilla(columna, (i)) != Ficha.VACIA || (i == 1)){						
								
								tab.setCasilla(columna, i, Ficha.VACIA);
								aux = true;
								
							}
							
						}
						
					aux = false;	
						
					}else if (parseoDireccion(tab).equalsIgnoreCase("D")){
						
						for (int i = fila; i <= tab.getFilas() && aux == false; i++){
							
							if (tab.getCasilla(columna, (i)) != Ficha.VACIA || (i == tab.getFilas())){						
								
								tab.setCasilla(columna, i, Ficha.VACIA);
								aux = true;
								
							}
							
						}
						
						aux = false;
						
					}else if(parseoDireccion(tab).equalsIgnoreCase("LT")){
						int c = columna;
						int f = fila;
						while (c >= 1 && f >= 1 && aux == false){
							
							if (tab.getCasilla(c, f) != Ficha.VACIA || c == 1 || f == 1){
								
								tab.setCasilla(c, f, Ficha.VACIA);
								aux = true;
							}else {
								c--;
								f--;
								
							}
							
						}
						
					aux = false;
						
					}else if (parseoDireccion(tab).equalsIgnoreCase("LD")){
						
						int c = columna;
						int f = fila;
						while (c >= 1 && f <= tab.getFilas() && aux == false){
							
							if (tab.getCasilla(c, f) != Ficha.VACIA || c == 1 || f == tab.getFilas()){
								
								tab.setCasilla(c, f, Ficha.VACIA);
								aux = true;
							}else {
								c--;
								f++;
								
							}
							
						}
						
						aux = false;
						
					}else if(parseoDireccion(tab).equalsIgnoreCase("RT")){
						
						int c = columna;
						int f = fila;
						while (c <= tab.getColumnas() && f >= 1 && aux == false){
							
							if (tab.getCasilla(c, f) != Ficha.VACIA || c == tab.getColumnas() || f == 1){
								
								tab.setCasilla(c, f, Ficha.VACIA);
								aux = true;
							}else {
								c++;
								f--;
								
							}
							
						}
						
					aux = false;	
						
					}else if (parseoDireccion(tab).equalsIgnoreCase("RD")){
						
						int c = columna;
						int f = fila;
						while (c <= tab.getColumnas() && f <= tab.getFilas() && aux == false){
							
							if (tab.getCasilla(c, f) != Ficha.VACIA || c == tab.getColumnas() || f == tab.getFilas()){
								
								tab.setCasilla(c, f, Ficha.VACIA);
								aux = true;
							}else {
								c++;
								f++;
								
							}
							
						}
						
						aux = false;
						
					}
					
				}
				
	@Override
	public int getColumna() {
		
		return columna;
	}

	private String parseoDireccion(Tablero tab){
		/*L = LEFT
		 *D = DOWN
		 *T = TOP
		 *R = RIGHT 
		 *
		 *Realiza una comprobacion de hacia donde se va a desplazar la ficha
		 *insertada en el tablero teniendo en cuanta la cercania a los laterales.*/
		cercania = "";
		
			if (tab.getColumnas() - columna == columna - 1){
				
				cercania += "LR";
				
				if (tab.getFilas() - fila == fila - 1){
						
					cercania = "LRTD";
				
				}else if (tab.getFilas() - fila > fila - 1){
					// lo de abajo es lo maximo.
					cercania = "T";
					
				}else if (tab.getFilas() - fila < fila - 1){
					
					cercania = "D";
					
				}
				
			}else if (tab.getColumnas() - columna > columna - 1){
				
				cercania += "L";
				
				if (tab.getFilas() - fila == fila - 1){
					
					cercania += "";
				
				}else if (tab.getFilas() - fila > fila - 1){
					// lo de abajo es lo maximo.
					if (columna - 1 == fila - 1){
						
						cercania += "T";
						
					}else if (columna - 1 > fila - 1){
						
						cercania = "T";
						
					}
					
				}else if (tab.getFilas() - fila < fila - 1){
					
					if (columna - 1 == tab.getFilas() - fila){
						
						cercania += "D";
						
					}else if (columna - 1 > tab.getFilas() - fila){
						
						cercania = "D";
						
					}
					
				}
				
			}else if (tab.getColumnas() - columna < columna - 1){
				
				cercania += "R";
				
				if (tab.getFilas() - fila == fila - 1){
					
					cercania += "";
				
				}else if (tab.getFilas() - fila > fila - 1){
					// lo de abajo es lo maximo.
					if (tab.getColumnas() - columna == fila - 1){
						
						cercania += "T";
						
					}else if (tab.getColumnas() - columna > fila - 1){
						
						cercania = "T";
						
					}
					
				}else if (tab.getFilas() - fila < fila - 1){
					
					if (tab.getColumnas() - columna == tab.getFilas() - fila){
						
						cercania += "D";
						
					}else if (tab.getColumnas() - columna > tab.getFilas() - fila){
						
						cercania = "D";
						
					}
					
				}
				
			}
			
		return cercania;	
		}
	

}

