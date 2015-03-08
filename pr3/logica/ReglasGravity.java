package tp.pr3.logica;

import tp.pr3.constants.Constants;

public class ReglasGravity implements ReglasJuego{

	private Tablero tablero;
	private final int alto;
	private final int ancho;
	private Ficha ganador;
	private Juego tipo;

	public ReglasGravity() {

		this.ancho = Constants.anchoGv;
		this.alto = Constants.altoGv;
		this.ganador = Ficha.VACIA;
		this.tipo = Juego.GRAVITY;
	}
	
	public ReglasGravity(int ancho, int alto){
		
		this.ancho=ancho;
		this.alto=alto;
		this.ganador = Ficha.VACIA;
		this.tipo = Juego.GRAVITY;
	}
	
	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		// sacar ganador, comprobar vertical, horizontal y DIAGONAL.
				ganador = Ficha.VACIA;
				int cont = 0;

				// vertical
				for (int j = 1; j <= t.getAncho() && cont < 4 && !isGanador(); j++) {
					cont = 0;// reiniciamos contador

					for (int i = 1; i <= t.getAlto() && cont < 4 && !isGanador(); i++) {
						if (t.getCasilla(j, i) != ultimoMovimiento.getJugador())
							cont = 0;
						else
							cont++;
						if (cont == 4) {
							ganador = ultimoMovimiento.getJugador();

						}
					}
				}

				// horizontales
				for (int i = 1; i <= t.getAlto() && cont < 4 && !isGanador(); i++) {
					cont = 0;// reiniciamos contador

					for (int j = 1; j <= t.getAncho() && cont < 4 && !isGanador(); j++) {
						if (t.getCasilla(j, i) != ultimoMovimiento.getJugador())
							cont = 0;
						else
							cont++;
						if (cont == 4) {
							ganador = ultimoMovimiento.getJugador();

						}
					}
				}

				// diagonales hacia la derecha \
				// diagonales que empiezan con j=1
				for (int j = 1; j <= (t.getAncho() - 3) && cont < 4 && !isGanador(); j++) {
					int i = 1;// Doble indice para saltar por la diagonal
					int j2 = j;
					cont = 0;
					while (i <= t.getAlto() && j2 <= t.getAncho() && cont < 4
							&& !isGanador()) {

						if (t.getCasilla(j2, i) != ultimoMovimiento.getJugador())
							cont = 0;
						else
							cont++;
						if (cont == 4) {
							ganador = ultimoMovimiento.getJugador();

						}
						i++;
						j2++;
					}
				}

				// diagonales hacia la derecha \
				// diagonales con i=1
				for (int i = 1; i <= (t.getAlto() - 3) && cont < 4 && !isGanador(); i++) {
					int i2 = i;// Doble indice para saltar por la diagonal
					int j = 1;
					cont = 0;
					while (i2 <= t.getAlto() && j <= t.getAncho() && cont < 4
							&& !isGanador()) {

						if (t.getCasilla(j, i2) != ultimoMovimiento.getJugador())
							cont = 0;
						else
							cont++;
						if (cont == 4) {
							ganador = ultimoMovimiento.getJugador();

						}
						i2++;
						j++;
					}
				}

				// diagonales hacia la izquierda /
				// diagonales con j=1
				for (int j = t.getAncho(); j >= (1 + 3) && cont < 4 && !isGanador(); j--) {
					int i = 1;// Doble indice para saltar por la diagonal
					int j2 = j;
					cont = 0;
					while (i <= t.getAlto() && j2 >= 1 && cont < 4 && !isGanador()) {
						if (t.getCasilla(j2, i) != ultimoMovimiento.getJugador())
							cont = 0;
						else
							cont++;
						if (cont == 4) {
							ganador = ultimoMovimiento.getJugador();

						}
						i++;
						j2--;
					}
				}
				// diagonales hacia la izquierda /
				// diagonales con j=7
				for (int i = 1; i <= (t.getAlto() - 3) && cont < 4 && !isGanador(); i++) {
					int i2 = i;// Doble indice para saltar por la diagonal
					int j = t.getAncho();
					cont = 0;
					while (i2 <= t.getAlto() && j >= 1 && cont < 4 && !isGanador()) {
						if (t.getCasilla(j, i2) != ultimoMovimiento.getJugador())
							cont = 0;
						else
							cont++;
						if (cont == 4) {
							ganador = ultimoMovimiento.getJugador();

						}
						i2++;
						j--;
					}
				}

				if (ganador != Ficha.VACIA) {

					ganador = ultimoMovimiento.getJugador();
				}
				return ganador;
	}

	@Override
	public Tablero iniciaTablero() {
		this.tablero = new Tablero(ancho, alto);
		return tablero;
	}

	@Override
	public Ficha jugadorInicial() {
	
		return Ficha.BLANCA;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		
		Ficha ret = Ficha.VACIA;
		if (ultimoEnPoner == Ficha.BLANCA) {

			ret = Ficha.NEGRA;

		} else if (ultimoEnPoner == Ficha.NEGRA) {

			ret = Ficha.BLANCA;

		}

		return ret;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		//Recorre todo el tablero si hay alguna posicion vacia, no hay tablas.
		//Si esta lleno hay tablas.
		
		boolean ret = true;
		
		for (int i = 1; i <= t.getAncho(); i++){
			
			for (int j = 1; j <= t.getAlto(); j++){
				
				if (t.getCasilla(i, j) == Ficha.VACIA){
					
					ret = false;
					
				}
				
			}
			
			
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
		
		return tipo;
	}

	public boolean isGanador() {
		boolean ret = false;

		if (ganador != Ficha.VACIA)
			ret = true;
		return ret;
	}
}

