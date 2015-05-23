package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.TableroInmutable;

public class JugadorAleatorioReversi implements Jugador {

	@Override
	public Movimiento getMovimiento(TableroInmutable tab, Ficha color) {
		int columnaRandom=0;
		int filaRandom=0;
		FactoriaTipoJuego f= new FactoriaReversi();
		do {
			columnaRandom = (int) ((Math.random()*tab.getColumnas())+1);
			filaRandom = (int) ((Math.random()*tab.getFilas())+1);
		}while(!sePuede(tab, columnaRandom, filaRandom, color));
		
		return f.creaMovimiento(columnaRandom,filaRandom,color);
		
	}

	public boolean sePuede(TableroInmutable tablero, int col, int fil, Ficha ficha){
		
		int x = col;
		int y = fil;
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
		
		x = col;
		y = fil;
		salir = false;
		if (sePuede == false && tablero.getCasilla(x+1, y) != ficha && tablero.getCasilla(x+1, y) != Ficha.VACIA
				&& (x+1) <= tablero.getColumnas()){
			x++;
			while(!salir && x <= tablero.getColumnas()){
				
				if (tablero.getCasilla(x, y) == Ficha.VACIA){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == ficha){
					
					salir = true;
					sePuede = true;
					
				}else x++;
				
			}
			
		}
		
		//ARRIBA
		x = col;
		y = fil;
		salir = false;
		if (sePuede == false && tablero.getCasilla(x, y-1) != ficha && tablero.getCasilla(x, y-1) != Ficha.VACIA
				&& y-1 > 0){
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
		x = col;
		y = fil;
		salir = false;
		if (sePuede == false && tablero.getCasilla(x, y+1) != ficha && tablero.getCasilla(x, y+1) != Ficha.VACIA
				&& y+1 <= tablero.getFilas()){
			y++;
			while(!salir && y <= tablero.getFilas()){
				
				if (tablero.getCasilla(x, y) == Ficha.VACIA){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == ficha){
					
					salir = true;
					sePuede = true;
					
				}else y++;	
			}
		}
		
		// ARIBA IZQDA
		x = col;
		y = fil;
		salir = false;
		if (sePuede == false && tablero.getCasilla(x-1, y-1) != ficha && tablero.getCasilla(x-1, y-1) != Ficha.VACIA 
				&& x-1 > 0 && y-1 > 0){
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
		x = col;
		y = fil;
		salir = false;
		if (sePuede == false && tablero.getCasilla(x+1, y-1) != ficha && tablero.getCasilla(x+1, y-1) != Ficha.VACIA 
				&& x+1 <= tablero.getColumnas() && y-1 > 0){
			x++;
			y--;
			while(!salir && x <= tablero.getColumnas() && y > 0){
				
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
		x = col;
		y = fil;
		salir = false;
		if (sePuede == false && tablero.getCasilla(x-1, y+1) != ficha && tablero.getCasilla(x-1, y+1) != Ficha.VACIA 
				&& x-1 > 0 && y+1 <= tablero.getFilas()){
			x--;
			y++;
			while(!salir && x > 0 && y <= tablero.getFilas()){
				
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
		x = col;
		y = fil;
		salir = false;
		if (sePuede == false && tablero.getCasilla(x+1, y+1) != ficha && tablero.getCasilla(x+1, y+1) != Ficha.VACIA 
				&& x+1 <= tablero.getColumnas() && y+1 <= tablero.getFilas()){
			x++;
			y++;
			while(!salir && x <= tablero.getColumnas() && y <= tablero.getFilas()){
				
				if (tablero.getCasilla(x, y) == Ficha.VACIA){
					
					salir = true;
					
				}else if (tablero.getCasilla(x, y) == ficha){
					
					salir = true;
					sePuede = true;
					
				}else{	
					x++; y++;
				}
				
			}
		}else if(tablero.getCasilla(col, fil) != Ficha.VACIA){
			
			salir = true;
			sePuede = false;
			
		}
		
		
		return sePuede;
	}
	
	
	
	
}





