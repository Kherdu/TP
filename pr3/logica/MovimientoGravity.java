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

	// falla porque no esta puesto void en la intefaz.
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		
		
		boolean aux = false;
		if (columna < 1 || columna > tab.getAncho() || fila < 1 || fila > tab.getAlto()){
			throw new MovimientoInvalido("PENE");
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
				
				aux = false;
				
			}else if(parseoDireccion(tab) == "LT"){
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
				
			}else if (parseoDireccion(tab) == "LD"){
				
				int c = columna;
				int f = fila;
				while (c >= 1 && f <= tab.getAlto() && aux == false){
					
					if (tab.getCasilla(c-1, f+1) != Ficha.VACIA || c == 1 || f == tab.getAlto()){
						
						tab.setCasilla(c, f, color);
						aux = true;
					}else {
						c--;
						f++;
						
					}
					
				}
				
				aux = false;
				
			}else if(parseoDireccion(tab) == "RT"){
				
				int c = columna;
				int f = fila;
				while (c <= tab.getAncho() && f >= 1 && aux == false){
					
					if (tab.getCasilla(c+1, f-1) != Ficha.VACIA || c == tab.getAncho() || f == 1){
						
						tab.setCasilla(c, f, color);
						aux = true;
					}else {
						c++;
						f--;
						
					}
					
				}
				
			aux = false;	
				
			}else if (parseoDireccion(tab) == "RD"){
				
				int c = columna;
				int f = fila;
				while (c <= tab.getAncho() && f >= tab.getAlto() && aux == false){
					
					if (tab.getCasilla(c+1, f+1) != Ficha.VACIA || c == tab.getAncho() || f == tab.getAlto()){
						
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

