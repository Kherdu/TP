package tp.pr5.logica;

import java.util.ArrayList;

public class MovimientoReversi extends Movimiento{
	
	private int columna;
	private int fila;
	private Ficha ficha;
	private ArrayList<MemCasilla> memoria = new ArrayList<MemCasilla>(); 
	private MemCasilla casilla;
	
	public MovimientoReversi(int col, int fila, Ficha color) {
		this.columna = col;
		this.fila = fila;
		this.ficha = color;
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
			
		}else if (!sePuede(tablero, columna, fila)) {
		
			throw new MovimientoInvalido("Posicion Incorrecta");
		}else if (sePuede(tablero,columna,fila)){
			
			tablero.setCasilla(columna, fila, ficha);
			ejecutaCambios(tablero);
			
		}
	}
	
	private void ejecutaCambios(Tablero tablero){
		
		int cont = 0;
		int x = columna-1;
		int y = fila;
		boolean salir = false;
		
		//IZQUIERDA
		
		while (!salir){
			if (x > 0){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					x--;
					cont++;
					
				}else if(tablero.getCasilla(x,y) == ficha && cont > 0){
					x++;
					while(x < columna){
						tablero.setCasilla(x, y, ficha);
						casilla = new MemCasilla(x, y);
						memoria.add(casilla);
						x++;						
					}
					
					salir = true;					
					
				}else if(tablero.getCasilla(x,y) == ficha && cont == 0){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}
		cont = 0;
		salir = false;
		x = columna+1;
		y = fila;
		
		//DERECHA
		
		while (!salir){
			if (x <= tablero.getColumnas()){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					x++;
					cont++;
					
				}else if(tablero.getCasilla(x,y) == ficha && cont > 0){
					x--;
					while(x > columna){
						tablero.setCasilla(x, y, ficha);
						casilla = new MemCasilla(x, y);
						memoria.add(casilla);
						x--;						
					}
					
					salir = true;					
					
				}else if(tablero.getCasilla(x,y) == ficha && cont == 0){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}
		cont = 0;
		salir = false;
		x = columna;
		y = fila-1;

		//ARRIBA
		
		while (!salir){
			if (y > 0){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					y--;
					cont++;
				}else if(tablero.getCasilla(x,y) == ficha && cont>0){
					y++;
					while(y < fila){
						tablero.setCasilla(x, y, ficha);
						casilla = new MemCasilla(x, y);
						memoria.add(casilla);
						y++;						
					}
					
					salir = true;					
					
				}else if(tablero.getCasilla(x,y) == ficha && cont == 0){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}
		cont = 0;
		salir = false;
		x = columna;
		y = fila+1;
		

		//ABAJO
		
		while (!salir){
			if (y <= tablero.getFilas()){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					y++;
					cont++;
				}else if(tablero.getCasilla(x,y) == ficha && cont > 0){
					y--;
					while(y > fila){
						tablero.setCasilla(x, y, ficha);
						casilla = new MemCasilla(x, y);
						memoria.add(casilla);
						y--;						
					}
					
					salir = true;					
					
				}else if(tablero.getCasilla(x,y) == ficha && cont == 0){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}
		cont = 0;
		salir = false;
		x = columna-1;
		y = fila-1;
		

		// ARRIBA IZQUIERDA
		while (!salir){
			if (x > 0 && y > 0){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					x--;
					y--;
					cont++;
				}else if(tablero.getCasilla(x,y) == ficha && cont > 0){
					x++;
					y++;
					while(x < columna && y < fila){
						tablero.setCasilla(x, y, ficha);
						casilla = new MemCasilla(x, y);
						memoria.add(casilla);
						x++;
						y++;
					}
					
					salir = true;					
					
				}else if(tablero.getCasilla(x,y) == ficha && cont == 0){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}
		cont = 0;
		salir = false;
		x = columna+1;
		y = fila-1;
		
		
		//ARRIBA DERECHA
		while (!salir){
			if (x <= tablero.getColumnas() && y > 0){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					x++;
					y--;
					cont++;
					
				}else if(tablero.getCasilla(x,y) == ficha && cont > 0){
					x--;
					y++;
					while(x > columna && y < fila){
						tablero.setCasilla(x, y, ficha);
						casilla = new MemCasilla(x, y);
						memoria.add(casilla);
						x--;
						y++;
					}
					
					salir = true;					
					
				}else if(tablero.getCasilla(x,y) == ficha && cont == 0){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}
		cont = 0;
		salir = false;
		x = columna-1;
		y = fila+1;
		
		//ABAJO IZQUIERDA
		while (!salir){
			if (x > 0 && y <= tablero.getFilas()){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					x--;
					y++;
					cont++;
				}else if(tablero.getCasilla(x,y) == ficha && cont >0){
					x++;
					y--;
					while(x < columna && y > fila){
						tablero.setCasilla(x, y, ficha);
						casilla = new MemCasilla(x, y);
						memoria.add(casilla);
						x++;
						y--;
					}
					
					salir = true;					
					
				}else if(tablero.getCasilla(x,y) == ficha && cont == 0){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}
		cont = 0;
		salir = false;
		x = columna+1;
		y = fila+1;
		
		//ABAJO DERECHA
		while (!salir){
			if (x <= tablero.getColumnas() && y <= tablero.getFilas()){
				if (tablero.getCasilla(x,y) != ficha && tablero.getCasilla(x,y) != Ficha.VACIA){
					
					x++;
					y++;
					cont++;
				}else if(tablero.getCasilla(x,y) == ficha && cont > 0){
					x--;
					y--;
					while(x > columna && y > fila){
						tablero.setCasilla(x, y, ficha);
						casilla = new MemCasilla(x, y);
						memoria.add(casilla);
						x--;
						y--;
					}
					
					salir = true;					
					
				}else if(tablero.getCasilla(x,y) == ficha && cont == 0){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == Ficha.VACIA){					
					
					salir = true;					
					
				}
			}else salir = true;
		}
		
	}
		
	public boolean sePuede(Tablero tablero, int col, int fil){
		int x = col;
		int y = fil;
		boolean sePuede = false;
		
		if (Utiles.sePuede(tablero, x, y, ficha, -1, 0)
			|| Utiles.sePuede(tablero, x, y, ficha, +1, 0)
			|| Utiles.sePuede(tablero, x, y, ficha, 0, -1)
			|| Utiles.sePuede(tablero, x, y, ficha, 0, +1)
			|| Utiles.sePuede(tablero, x, y, ficha, 0, -1)
			|| Utiles.sePuede(tablero, x, y, ficha, -1, -1)
			|| Utiles.sePuede(tablero, x, y, ficha, -1, +1)
			|| Utiles.sePuede(tablero, x, y, ficha, +1, -1)
			|| Utiles.sePuede(tablero, x, y, ficha, +1, +1)){
			
			sePuede = true;
			
		}
		
		return sePuede;
	}
	
	@Override
	public void undo(Tablero tablero) {
			tablero.setCasilla(columna, fila, Ficha.VACIA);
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
