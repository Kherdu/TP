package tp.pr3.logica;

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
	public boolean ejecutaMovimiento(Tablero tab) {
		
		boolean ret = true;
		boolean aux = false;
		if (columna < 1 || columna > tab.getAncho() || fila < 1 || fila > tab.getAlto()){
			ret = false;
			System.err.println("Movimiento incorrecto");
		}else{
			
			// usar el parseio de direccion.
			if (parseoDireccion(tab) == "LRTD"){
				
				tab.setCasilla(columna, fila, color);
				
			}else if (parseoDireccion(tab) == "L"){
				
				for (int i = columna; i >= 1 && aux == false; i--){
					
					if (tab.getCasilla((i-1), fila) != Ficha.VACIA || (i == 1)){						
						
						tab.setCasilla(i, fila, color);
						aux = true;
						
					}
					
				}
			aux = false;
				
			}else if (parseoDireccion(tab) == "R"){
				
				for (int i = columna; i <= tab.getAncho() && aux == false; i++){
					
					if (tab.getCasilla((i+1), fila) != Ficha.VACIA || (i == tab.getAncho())){						
						
						tab.setCasilla(i, fila, color);
						aux = true;
						
					}
					
				}
			aux = false;
			
			}else if(parseoDireccion(tab) == "T"){
				
				for (int i = fila; i >= 1 && aux == false; i--){
					
					if (tab.getCasilla(columna, (i-1)) != Ficha.VACIA || (i == 1)){						
						
						tab.setCasilla(columna, i, color);
						aux = true;
						
					}
					
				}
				
			aux = false;	
				
			}else if (parseoDireccion(tab) == "D"){
				
				for (int i = columna; i <= tab.getAlto() && aux == false; i++){
					
					if (tab.getCasilla(columna, (i+1)) != Ficha.VACIA || (i == tab.getAlto())){						
						
						tab.setCasilla(columna, i, color);
						aux = true;
						
					}
					
				}
				
			}
			
		}
		
		return ret;
	}

	@Override
	public void undo(Tablero tab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getColumna() {
		// TODO Auto-generated method stub
		return columna;
	}

	private String parseoDireccion(Tablero tab){
		
		
			if (tab.getAncho() - columna == columna - 1){
				
				cercania += "LR";
				
				if (tab.getAlto() - fila == fila - 1){
						
					cercania += "LRTD";
				
				}else if (tab.getAlto() - fila > fila - 1){
					// lo de abajo es lo maximo.
					cercania = "T";
					
				}else if (tab.getAlto() - fila < fila - 1){
					
					cercania = "D";
					
				}
				
			}else if (tab.getAncho() - columna > columna - 1){
				
				cercania += "L";
				
				if (tab.getAlto() - fila == fila - 1){
					
					cercania += "";
				
				}else if (tab.getAlto() - fila > fila - 1){
					// lo de abajo es lo maximo.
					if (columna - 1 == fila - 1){
						
						cercania += "T";
						
					}else if (columna - 1 > fila - 1){
						
						cercania = "T";
						
					}
					
				}else if (tab.getAlto() - fila < fila - 1){
					
					if (columna - 1 == tab.getAlto() - fila){
						
						cercania += "D";
						
					}else if (columna - 1 > tab.getAlto() - fila){
						
						cercania = "D";
						
					}
					
				}
				
			}else if (tab.getAncho() - columna < columna - 1){
				
				cercania += "R";
				
				if (tab.getAlto() - fila == fila - 1){
					
					cercania += "";
				
				}else if (tab.getAlto() - fila > fila - 1){
					// lo de abajo es lo maximo.
					if (tab.getAncho() - columna == fila - 1){
						
						cercania += "T";
						
					}else if (tab.getAncho() - columna > fila - 1){
						
						cercania = "T";
						
					}
					
				}else if (tab.getAlto() - fila < fila - 1){
					
					if (tab.getAncho() - columna == tab.getAlto() - fila){
						
						cercania += "D";
						
					}else if (tab.getAncho() - columna > tab.getAlto() - fila){
						
						cercania = "D";
						
					}
					
				}
				
			}
			
		return cercania;	
		}
	}

