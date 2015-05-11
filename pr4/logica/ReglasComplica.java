package tp.pr4.logica;
import tp.pr4.constants.Constants;

public class ReglasComplica implements ReglasJuego {

	private final int jugadores=2;
	private Tablero tablero;
	private final int alto;
	private final int ancho;
	private Ficha ganador;
	private Juego tipo;

	public ReglasComplica() {

		this.ancho = Constants.AnchoCo;
		this.alto = Constants.AltoCo;
		this.ganador = Ficha.VACIA;
		this.tipo = Juego.COMPLICA;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, TableroInmutable t) {
		// tiene que contar el numero de 4 en rayas, hay que comprobar esto
		// antes que el ganador... absurdo en este modo el tablero
		// sacar ganador, comprobar vertical, horizontal y DIAGONAL.
		ganador = Ficha.VACIA;
		int contUltimo = 0;
		int contContrario = 0;
		int vecesGanaUltimo = 0;
		int vecesGanaContrario = 0;

		// vertical
		for (int j = 1; j <= t.getColumnas() && !isGanador(); j++) {
			contUltimo = 0;// reiniciamos contador
			contContrario = 0;
			for (int i = 1; i <= t.getFilas() && !isGanador(); i++) {
				if (t.getCasilla(j, i) != ultimoMovimiento.getJugador()
						&& t.getCasilla(j, i) != Ficha.VACIA) {
					contUltimo = 0;
					contContrario++;
				} else if (t.getCasilla(j, i) == ultimoMovimiento.getJugador()) {
					contUltimo++;
					contContrario = 0;
				} else {
					contUltimo = 0;
					contContrario = 0;

				}
				if (contUltimo == 4) {
					vecesGanaUltimo++;
					contUltimo = 0;
				} else if (contContrario == 4) {
					vecesGanaContrario++;
					contUltimo = 0;
				}

			}
		}
		// horizontales
		for (int i = 1; i <= t.getFilas() && !isGanador(); i++) {
			contUltimo = 0;// reiniciamos contador
			contContrario = 0;
			for (int j = 1; j <= t.getColumnas() && !isGanador(); j++) {
				if (t.getCasilla(j, i) != ultimoMovimiento.getJugador()
						&& t.getCasilla(j, i) != Ficha.VACIA) {
					contUltimo = 0;
					contContrario++;
				} else if (t.getCasilla(j, i) == ultimoMovimiento.getJugador()) {
					contUltimo++;
					contContrario = 0;
				} else {
					contUltimo = 0;
					contContrario = 0;
				}

				if (contUltimo == 4) {
					vecesGanaUltimo++;
					contUltimo = 0;
				} else if (contContrario == 4) {
					vecesGanaContrario++;
					contUltimo = 0;
				}
			}
		}

		// diagonales hacia la derecha \
		// diagonales que empiezan con j=1
		for (int j = 1; j <= (t.getColumnas() - 3) && !isGanador(); j++) {
			int i = 1;// Doble indice para saltar por la diagonal
			int j2 = j;
			contUltimo = 0;
			contContrario = 0;

			while (i <= t.getFilas() && j2 <= t.getColumnas() && !isGanador()) {

				if (t.getCasilla(j2, i) != ultimoMovimiento.getJugador()
						&& t.getCasilla(j2, i) != Ficha.VACIA) {
					contUltimo = 0;
					contContrario++;
				} else if (t.getCasilla(j2, i) == ultimoMovimiento.getJugador()) {
					contUltimo++;
					contContrario = 0;
				} else {
					contUltimo = 0;
					contContrario = 0;
				}

				if (contUltimo == 4) {
					vecesGanaUltimo++;
					contUltimo = 0;
				} else if (contContrario == 4) {
					vecesGanaContrario++;
					contUltimo = 0;
				}

				i++;
				j2++;
			}
		}

		// diagonales hacia la derecha \
		// diagonales con i=1
		for (int i = 1; i <= (t.getFilas() - 3) && !isGanador(); i++) {
			int i2 = i;// Doble indice para saltar por la diagonal
			int j = 1;
			contUltimo = 0;
			contContrario = 0;

			while (i2 <= t.getFilas() && j <= t.getColumnas() && !isGanador()) {

				if (t.getCasilla(j, i2) != ultimoMovimiento.getJugador()
						&& t.getCasilla(j, i2) != Ficha.VACIA) {
					contUltimo = 0;
					contContrario++;
				} else if (t.getCasilla(j, i2) == ultimoMovimiento.getJugador()) {
					contUltimo++;
					contContrario = 0;
				} else {
					contUltimo = 0;
					contContrario = 0;
				}

				if (contUltimo == 4) {
					vecesGanaUltimo++;
					contUltimo = 0;
				} else if (contContrario == 4) {
					vecesGanaContrario++;
					contUltimo = 0;
				}

				i2++;
				j++;
			}
		}

		// diagonales hacia la izquierda /
		// diagonales con j=1
		for (int j = t.getColumnas(); j >= (1 + 3) && !isGanador(); j--) {
			int i = 1;// Doble indice para saltar por la diagonal
			int j2 = j;
			contUltimo = 0;
			contContrario = 0;

			while (i <= t.getFilas() && j2 >= 1 && !isGanador()) {
				if (t.getCasilla(j2, i) != ultimoMovimiento.getJugador()
						&& t.getCasilla(j2, i) != Ficha.VACIA) {
					contUltimo = 0;
					contContrario++;
				} else if (t.getCasilla(j2, i) == ultimoMovimiento.getJugador()) {
					contUltimo++;
					contContrario = 0;
				} else {
					contUltimo = 0;
					contContrario = 0;
				}
				if (contUltimo == 4) {
					vecesGanaUltimo++;
					contUltimo = 0;
				} else if (contContrario == 4) {
					vecesGanaContrario++;
					contUltimo = 0;
				}
				i++;
				j2--;
			}
		}
		// diagonales hacia la izquierda /
		// diagonales con j=7
		for (int i = 1; i <= (t.getFilas() - 3) && !isGanador(); i++) {
			int i2 = i;// Doble indice para saltar por la diagonal
			int j = t.getColumnas();
			contUltimo = 0;
			contContrario = 0;
			while (i2 <= t.getFilas() && j >= 1 && !isGanador()) {

				if (t.getCasilla(j, i2) != ultimoMovimiento.getJugador()
						&& t.getCasilla(j, i2) != Ficha.VACIA) {
					contUltimo = 0;
					contContrario++;
				} else if (t.getCasilla(j, i2) == ultimoMovimiento.getJugador()) {
					contUltimo++;
					contContrario = 0;
				} else {
					contUltimo = 0;
					contContrario = 0;
				}

				if (contUltimo == 4) {
					vecesGanaUltimo++;
					contUltimo = 0;
				} else if (contContrario == 4) {
					vecesGanaContrario++;
					contUltimo = 0;
				}
				i2++;
				j--;
			}
		}

		if (vecesGanaUltimo > vecesGanaContrario) {
			ganador = ultimoMovimiento.getJugador();
		} else if (vecesGanaContrario > vecesGanaUltimo) {

			if (ultimoMovimiento.getJugador() == Ficha.BLANCA) {
				ganador = Ficha.NEGRA;
			} else
				ganador = Ficha.BLANCA;
		}
		if (vecesGanaUltimo > 0 && vecesGanaContrario > 0) {
			ganador = Ficha.VACIA;
		}

		return ganador;
	}

	@Override
	public Tablero iniciaTablero() {
		tablero = new Tablero(this.ancho, this.alto);
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

			ret = Ficha.NEGRA;

		} else if (ultimoEnPoner == Ficha.NEGRA) {

			ret = Ficha.BLANCA;

		}

		return ret;
	}

	public boolean isGanador() {
		boolean ret = false;

		if (ganador != Ficha.VACIA)
			ret = true;
		return ret;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, TableroInmutable t) {
		//No existen tablas en el complica.
		return false;
	}

	@Override
	public int getAlto() {
		return alto;
	}

	@Override
	public int getAncho() {
		return ancho;
	}

	@Override
	public Juego getTipo() {
		return tipo;
	}

	@Override
	public int getNumJugadores() {
		
		return jugadores;
	}
}
