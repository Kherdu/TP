package tp.pr4.control;


import tp.pr4.GUI.Observer;
import tp.pr4.constants.Constants;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.InstruccionInvalida;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Tablero;
import tp.pr4.logica.TableroInmutable;



public class VistaConsola implements Observer{
	private ControladorConsola cntr;
	private Tablero tablero;

		
	public VistaConsola(ControladorConsola c){
		tablero = cntr.getTablero();
		this.cntr = c;
		cntr.addObserver(this);
		
	}



	@Override
	public void onReset(TableroInmutable tab, Ficha turno) {
		System.out.println("Partida reiniciada");
		System.out.println("Juegan " + turno);
	}



	@Override
	public void onPartidaTerminada(TableroInmutable tab, Ficha ganador) {
		tablero.toString();
		System.out.println("Partida terminada");
		System.out.println("Ganan" + ganador);
		
		
	}
		
		


	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {
		
		System.out.println("juegan " + turno);
		
		
		
	}



	@Override
	public void onUndoNotPossible(TableroInmutable tab, Ficha turno) {
		
		System.out.println("Imposible deshacer");
		tablero.toString();
		System.out.println("Juegan "+ turno);
		
		
	}



	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas) {
		
		tablero.toString();
		System.out.println("Juegan" + turno);
		
	}



	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha jugador, Ficha turno) {
		
		tablero.toString();
		System.out.println("Juegan"+ turno);
		
	}



	@Override
	public void onMovimientoIncorrecto(TableroInmutable movimientoException) {
		// TODO Auto-generated method stub
		
	}

		System.err.println("Movimiento invalido");
	
}
	

