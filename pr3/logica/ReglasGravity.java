package tp.pr3.logica;

public class ReglasGravity implements ReglasJuego{

	private Tablero tablero;
	private final int alto;
	private final int ancho;
	private Ficha ganador;
	private Juego tipo;

	public ReglasGravity() {

		this.ancho = 10;
		this.alto = 10;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAncho() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Juego getTipo() {
		// TODO Auto-generated method stub
		return null;
	}

}
