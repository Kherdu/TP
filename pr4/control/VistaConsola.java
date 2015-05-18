package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.GUI.Observer;
import tp.pr4.constants.Constants;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.InstruccionInvalida;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Tablero;
import tp.pr4.logica.TableroInmutable;

public class VistaConsola implements Observer {
	private ControladorConsola cntr;
	private TableroInmutable tablero;
	private boolean terminada;
	private boolean tablas;
	private Ficha turno;

	public VistaConsola(ControladorConsola c, Ficha jugadorInicial) {
		// para inicializar necesitamos el tablero inmutable
		this.cntr = c;
		cntr.addObserver(this);
		cntr.inicio();
		
		terminada=false;
		tablas=false;
		turno=jugadorInicial;
		
	}

	public void run() {
		while (!tablas && !terminada){
			
			System.out.println(tablero.toString());
			System.out.println("Juegan "+turno.toString());
			System.out.print("Qué quieres hacer? ");
			cntr.parse();
		}
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno) {
		tablero=tab;
		this.turno=turno;
		System.out.println("Partida reiniciada");
		
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tab, Ficha ganador) {
		tablero=tab;
		terminada=true;
		System.out.println(tab.toString());
		System.out.println("Partida terminada");
		System.out.println("Ganan" + ganador);
		System.exit(0);

	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {
		tablero=tab;
		
		System.out.println("Juego cambiado");
		

	}

	@Override
	public void onUndoNotPossible(TableroInmutable tab, Ficha turno) {
		this.turno=turno;
		System.out.println("Imposible deshacer");
		System.out.println(tab.toString());
		System.out.println("Juegan " + turno);

	}

	@Override
	public void onUndo(TableroInmutable tab, Ficha turno, boolean hayMas) {
		tablero=tab;
		this.turno=turno;
		System.out.println(tab.toString());
		System.out.println("Juegan " + turno);

	}

	@Override
	public void onMovimientoEnd(TableroInmutable tab, Ficha jugador, Ficha turno) {
		tablero=tab;
		this.turno=turno;
		

	}

	@Override
	public void onMovimientoIncorrecto(TableroInmutable movimientoException) {
		
		System.err.println("Movimiento Incorrecto");

	}

	@Override
	public void onMovimientoInvalido(MovimientoInvalido e) {
		System.err.println(e);
		
	}

	@Override
	public void onInstruccionInvalida(InstruccionInvalida e) {
		System.err.println(e);
		
	}

	@Override
	public void onInicio(TableroInmutable tin, Ficha turno) {
		tablero=tin;
		this.turno=turno;
		
	}

}
