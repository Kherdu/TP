package tp.pr5.logica;

import tp.pr5.constants.Constants;

public class ReglasReversi implements ReglasJuego {

	private Tablero tablero;
	private final int alto;
	private final int ancho;
	private Ficha ganador;
	private Juego tipo;

	public ReglasReversi() {

		this.ancho = Constants.anchoRv;
		this.alto = Constants.altoRv;
		this.ganador = Ficha.VACIA;
		this.tipo = Juego.REVERSI;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, TableroInmutable t) {
		int x = 0;
		int y = 0;
		Ficha ret=Ficha.VACIA;

		int contNegras = 0;
		int contBlancas = 0;

		if (movimientoImposible(t) || tableroLleno(t)) {

			if (!comprobacion(t, Ficha.NEGRA) && !comprobacion(t, Ficha.BLANCA)) {
				while (y < tablero.getFilas()) {
					x = 0;
					while (x < tablero.getColumnas()) {
						if (t.getCasilla(x, y) == Ficha.NEGRA) {
							contNegras++;

						} else if (t.getCasilla(x, y) == Ficha.BLANCA) {
							contBlancas++;

						}
						x++;
					}
					y++;
				}

			}

			if (contBlancas > contNegras) {
				ganador=Ficha.BLANCA;
				ret = Ficha.BLANCA;

			} else if (contBlancas < contNegras) {
				ganador=Ficha.NEGRA;
				ret = Ficha.NEGRA;

			} else
				ganador=Ficha.VACIA;
				ret = Ficha.VACIA;

		}

		return ret;
	}

	private boolean tableroLleno(TableroInmutable t) {
		boolean ret = true;
		for (int x = 1; x < Constants.anchoRv; x++) {
			for (int y = 1; y < Constants.altoRv; y++) {
				if (t.getCasilla(y, x) == Ficha.VACIA) {
					ret = false;
				}
			}
		}
		return ret;
	}

	private boolean movimientoImposible(TableroInmutable t) {
		// no se puede mover a ninguna casilla
		boolean ret = false;
		for (int x = 1; x < Constants.anchoRv; x++) {
			for (int y = 1; y < Constants.altoRv; y++) {
				if (!sePuede(t, x, y, Ficha.BLANCA)) {
					ret = true;
				}
				if (!sePuede(t, x, y, Ficha.NEGRA)) {
					ret = true;
				}
			}
		}
		return ret;

	}

	@Override
	public Tablero iniciaTablero() {
		this.tablero = new Tablero(ancho, alto);
		tablero.setCasilla(4, 4, Ficha.BLANCA);
		tablero.setCasilla(4, 5, Ficha.NEGRA);
		tablero.setCasilla(5, 5, Ficha.BLANCA);
		tablero.setCasilla(5, 4, Ficha.NEGRA);

		return tablero;
	}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, TableroInmutable t) {
		Ficha ret = Ficha.VACIA;
		if (ultimoEnPoner == Ficha.BLANCA) {
			if (comprobacion(t, Ficha.NEGRA)) {
				ret = Ficha.NEGRA;
			} else
				ret = Ficha.BLANCA;
		} else if (ultimoEnPoner == Ficha.NEGRA) {
			if (comprobacion(t, Ficha.BLANCA)) {
				ret = Ficha.BLANCA;
			} else
				ret = Ficha.NEGRA;
		}

		return ret;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, TableroInmutable t) {

		int x = 0;
		int y = 0;
		boolean ret = false;

		int contNegras = 0;
		int contBlancas = 0;
		if (comprobacion(t, Ficha.NEGRA) == false
				&& comprobacion(t, Ficha.BLANCA) == false) {
			while (y < tablero.getFilas()) {
				x = 0;
				while (x < tablero.getColumnas()) {
					if (t.getCasilla(x, y) == Ficha.NEGRA) {
						contNegras++;

					} else if (t.getCasilla(x, y) == Ficha.BLANCA) {
						contBlancas++;

					}
					x++;
				}
				y++;
			}

		}

		if (contNegras == contBlancas && !comprobacion(t, Ficha.NEGRA)
				&& !comprobacion(t, Ficha.BLANCA)) {

			ret = true;

		}

		return ret;
	}

	@Override
	public int getAlto() {
		return this.alto;
	}

	@Override
	public int getAncho() {
		return this.ancho;
	}

	@Override
	public Juego getTipo() {

		return this.tipo;
	}

	@Override
	public int getNumJugadores() {

		return 0;
	}

	public boolean comprobacion(TableroInmutable tablero, Ficha color) {

		boolean salir = false;
		int x = 1;
		int y = 1;

		while (y < tablero.getFilas() && !salir) {
			x = 0;
			while (x < tablero.getColumnas() && !salir) {
				if (tablero.getCasilla(x, y) == Ficha.VACIA) {
					if (sePuede(tablero, x, y, color)) {
						salir = true;

					}
				}
				x++;
			}

			y++;
		}

		return salir;
	}

	public boolean isGanador() {
		boolean ret = false;

		if (ganador != Ficha.VACIA)
			ret = true;
		return ret;
	}
	public boolean sePuede(TableroInmutable tablero, int col, int fil, Ficha ficha){
		int x = col;
		int y = fil;
		boolean salir = false;
		boolean sePuede = false;
		//IZQUIERDA
		if (sePuede == false && tablero.getCasilla(x-1, y) != ficha && tablero.getCasilla(x-1, y) != Ficha.VACIA
				&& x > 0){
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
				&& x <= tablero.getColumnas()){
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
				&& y > 0){
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
				&& y <= tablero.getFilas()){
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
		x = col;
		y = fil;
		salir = false;
		if (sePuede == false && tablero.getCasilla(x+1, y-1) != ficha && tablero.getCasilla(x+1, y-1) != Ficha.VACIA 
				&& x <= tablero.getColumnas() && y > 0){
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
				&& x > 0 && y <= tablero.getFilas()){
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
				&& x <= tablero.getColumnas() && y <= tablero.getFilas()){
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
		}
		
		if(tablero.getCasilla(col, fil) != Ficha.VACIA){
			
			salir = true;
			sePuede = false;
		}
		
		
		return sePuede;
	}

}
