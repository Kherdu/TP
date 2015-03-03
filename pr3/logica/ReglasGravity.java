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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tablero iniciaTablero() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ficha jugadorInicial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}

}
