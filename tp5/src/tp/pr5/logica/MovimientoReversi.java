package tp.pr5.logica;

import java.util.ArrayList;

public class MovimientoReversi extends Movimiento{
	
	private int columna;
	private int fila;
	private Ficha ficha;
	private ArrayList<Casilla> memoria = new ArrayList<Casilla>(); 
	private Casilla casilla;
	
	public MovimientoReversi(int col, int fila, Ficha color) {
		this.columna = col;
		this.fila = fila;
		this.ficha = color;
		this.casilla = new Casilla();
	}

	@Override
	public Ficha getJugador() {
		return ficha;
	}

	@Override
	public void ejecutaMovimiento(Tablero tablero) throws MovimientoInvalido {
	
		
		if (columna < 1 || columna > tablero.getColumnas() || fila < 1 || fila > tablero.getFilas()){
			
			throw new MovimientoInvalido("Posición Incorrecta");
			
			
		}else if(tablero.getCasilla(columna, fila) != Ficha.VACIA){
			
			throw new MovimientoInvalido("Casilla Ocupada");
			
		}else if (!sePuede(tablero)) {
		
			throw new MovimientoInvalido("Psicion Incorrecta");
		}else{
			
			tablero.setCasilla(columna, fila, ficha);
			compruebaCambios(tablero);
			
		}
	}
	

	
	private void compruebaCambios(Tablero tablero){
		
		
		int x = columna-1;
		int y = fila;
		boolean salir = false;
		
		//IZQUIERDA
		
		while (!salir){
			if (x > 0){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					x--;
					
				}else if(tablero.getCasilla(x,y) == ficha){
					while(x < columna){
						tablero.setCasilla(x, y, ficha);
						casilla.setX(x);
						casilla.setY(y);
						memoria.add(casilla);
						x++;						
					}
					
					salir = true;					
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}
		
		salir = false;
		x = columna+1;
		y = fila;
		
		//DERECHA
		
		while (!salir){
			if (x < tablero.getColumnas()){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					x++;
					
				}else if(tablero.getCasilla(x,y) == ficha){
					while(x > columna){
						tablero.setCasilla(x, y, ficha);
						casilla.setX(x);
						casilla.setY(y);
						memoria.add(casilla);
						x--;						
					}
					
					salir = true;					
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}

		salir = false;
		x = columna;
		y = fila-1;

		//ARRIBA
		
		while (!salir){
			if (y > 0){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					y--;
					
				}else if(tablero.getCasilla(x,y) == ficha){
					while(y < fila){
						tablero.setCasilla(x, y, ficha);
						casilla.setX(x);
						casilla.setY(y);
						memoria.add(casilla);
						y++;						
					}
					
					salir = true;					
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}

		salir = false;
		x = columna;
		y = fila+1;
		

		//ABAJO
		
		while (!salir){
			if (y < tablero.getFilas()){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					y++;
					
				}else if(tablero.getCasilla(x,y) == ficha){
					while(y > fila){
						tablero.setCasilla(x, y, ficha);
						casilla.setX(x);
						casilla.setY(y);
						memoria.add(casilla);
						y--;						
					}
					
					salir = true;					
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}
		
		salir = false;
		x = columna-1;
		y = fila-1;
		

		// ARRIBA IZQUIERDA
		while (!salir){
			if (x > 0 && y > 0){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					x--;
					y--;
					
				}else if(tablero.getCasilla(x,y) == ficha){
					while(x < columna && y < fila){
						tablero.setCasilla(x, y, ficha);
						casilla.setX(x);
						casilla.setY(y);
						memoria.add(casilla);
						x++;
						y++;
					}
					
					salir = true;					
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}

		salir = false;
		x = columna+1;
		y = fila-1;
		
		
		//ARRIBA DERECHA
		while (!salir){
			if (x < tablero.getColumnas() && y > 0){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					x++;
					y--;
					
				}else if(tablero.getCasilla(x,y) == ficha){
					while(x > columna && y < fila){
						tablero.setCasilla(x, y, ficha);
						casilla.setX(x);
						casilla.setY(y);
						memoria.add(casilla);
						x--;
						y++;
					}
					
					salir = true;					
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}
		
		salir = false;
		x = columna-1;
		y = fila+1;
		
		//ABAJO IZQUIERDA
		while (!salir){
			if (x > 0 && y < tablero.getFilas()){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					x--;
					y++;
					
				}else if(tablero.getCasilla(x,y) == ficha){
					while(x < columna && y > fila){
						tablero.setCasilla(x, y, ficha);
						casilla.setX(x);
						casilla.setY(y);
						memoria.add(casilla);
						x++;
						y--;
					}
					
					salir = true;					
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}
		
		salir = false;
		x = columna+1;
		y = fila+1;
		
		//ABAJO DERECHA
		while (!salir){
			if (x < tablero.getColumnas() && y < tablero.getFilas()){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					x++;
					y++;
					
				}else if(tablero.getCasilla(x,y) == ficha){
					while(x > columna && y > fila){
						tablero.setCasilla(x, y, ficha);
						casilla.setX(x);
						casilla.setY(y);
						memoria.add(casilla);
						x--;
						y--;
					}
					
					salir = true;					
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}
		
	}
		
	public boolean sePuede(Tablero tablero){
		int x = columna;
		int y = fila;
		boolean salir = false;
		boolean sePuede = false;
		//IZQUIERDA
		if (sePuede == false && tablero.getCasilla(x-1, y) != ficha && tablero.getCasilla(x-1, y) != Ficha.VACIA
				&& (x-1) > 0){
			x--;
			while(!salir && x > 0){
				
				if (tablero.getCasilla(x, y) == Ficha.VACIA){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == ficha){
					
					salir = true;
					sePuede = true;
					
				}else x--;
				
			}
			
		}
		
		//DERECHA
		
		x = columna;
		y = fila;
		salir = false;
		if (sePuede == false && tablero.getCasilla(x+1, y) != ficha && tablero.getCasilla(x+1, y) != Ficha.VACIA
				&& (x+1) < tablero.getColumnas()){
			x++;
			while(!salir && x < tablero.getColumnas()){
				
				if (tablero.getCasilla(x, y) == Ficha.VACIA){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == ficha){
					
					salir = true;
					sePuede = true;
					
				}else x++;
				
			}
			
		}
		
		//ARRIBA
		
		if (sePuede == false && tablero.getCasilla(x, y-1) != ficha && tablero.getCasilla(x, y-1) != Ficha.VACIA
				&& tablero.getFilas() > 0){
			y--;
			while(!salir && y > 0){
				
				if (tablero.getCasilla(x, y) == Ficha.VACIA){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == ficha){
					
					salir = true;
					sePuede = true;
					
				}else y--;	
			}
		}
		
		//ABAJO
		
		if (sePuede == false && tablero.getCasilla(x, y+1) != ficha && tablero.getCasilla(x, y+1) != Ficha.VACIA
				&& y < tablero.getFilas()){
			y++;
			while(!salir && y < tablero.getFilas()){
				
				if (tablero.getCasilla(x, y) == Ficha.VACIA){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == ficha){
					
					salir = true;
					sePuede = true;
					
				}else y++;	
			}
		}
		
		// ARIBA IZQDA
		
		if (sePuede == false && tablero.getCasilla(x-1, y-1) != ficha && tablero.getCasilla(x-1, y-1) != Ficha.VACIA 
				&& x > 0 && y > 0){
			x--;
			y--;
			while(!salir && x > 0 && y > 0){
				
				if (tablero.getCasilla(x, y) == Ficha.VACIA){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == ficha){
					
					salir = true;
					sePuede = true;
					
				}else{	
					x--; y--;
				}
				
			}
		}
		
		//ARRIBA DRCHA
		
		if (sePuede == false && tablero.getCasilla(x-1, y-1) != ficha && tablero.getCasilla(x+1, y-1) != Ficha.VACIA 
				&& x < tablero.getColumnas() && y > 0){
			x++;
			y--;
			while(!salir && x < tablero.getColumnas() && y < 0){
				
				if (tablero.getCasilla(x, y) == Ficha.VACIA){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == ficha){
					
					salir = true;
					sePuede = true;
					
				}else{	
					x++; y--;
				}
				
			}
		}
		
		//ABAJO IZQDA
		
		if (sePuede == false && tablero.getCasilla(x-1, y+1) != ficha && tablero.getCasilla(x-1, y+1) != Ficha.VACIA 
				&& x > 0 && y < tablero.getFilas()){
			x--;
			y++;
			while(!salir && x > 0 && y < tablero.getFilas()){
				
				if (tablero.getCasilla(x, y) == Ficha.VACIA){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == ficha){
					
					salir = true;
					sePuede = true;
					
				}else{	
					x--; y++;
				}
				
			}
		}
		
		
		//ABAJO DRCHA
		
		if (sePuede == false && tablero.getCasilla(x+1, y+1) != ficha && tablero.getCasilla(x+1, y+1) != Ficha.VACIA 
				&& x < tablero.getColumnas() && y < tablero.getFilas()){
			x++;
			y++;
			while(!salir && x < tablero.getColumnas() && y < tablero.getFilas()){
				
				if (tablero.getCasilla(x, y) == Ficha.VACIA){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == ficha){
					
					salir = true;
					sePuede = true;
					
				}else{	
					x++; y++;
				}
				
			}
		}
		
		
		return sePuede;
	}
	
	@Override
	public void undo(Tablero tablero) {
		
		while (!memoria.isEmpty()){
			
			cambiaColor(tablero);
			memoria.remove(0);
			
		}
		
	}

	@Override
	public int getColumna() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void cambiaColor(Tablero tablero){
		
		if (tablero.getCasilla(memoria.get(0).getX(), memoria.get(0).getY()) == Ficha.BLANCA){
			
			tablero.setCasilla(memoria.get(0).getX(), memoria.get(0).getY(), Ficha.NEGRA);
			
		}else if (tablero.getCasilla(memoria.get(0).getX(), memoria.get(0).getY()) == Ficha.NEGRA){
			
			tablero.setCasilla(memoria.get(0).getX(), memoria.get(0).getY(), Ficha.BLANCA);
			
		}
		
		
		
	}

}
