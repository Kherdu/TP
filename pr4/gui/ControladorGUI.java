package tp.pr4.GUI;

import java.util.ArrayList;

import tp.pr4.control.FactoriaTipoJuego;
import tp.pr4.control.Jugador;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.Partida;
import tp.pr4.logica.ReglasJuego;

public class ControladorGUI {

	
	private ArrayList<Observer> observers;
	private FactoriaTipoJuego f;
	private Partida p;
	private ReglasJuego r;
	
	public ControladorGUI(FactoriaTipoJuego factoria, Partida partida) {
		
		f=factoria;
		p=partida;
		r=f.creaReglas();
		observers= new ArrayList<Observer>();
		
	}

	public FactoriaTipoJuego getFactoria() {
		return f;
	}

	public Partida getPartida() {
		return p;
	}

	public ReglasJuego getReglas() {
		return r;
	}
	
	public void addObserver(Observer o){
		observers.add(o);
	}
	
	
	/*
	 * reinicio de juego
	 */
	
	public void reset(Partida p, ReglasJuego r){
		p.reset(r);
		
	}

	public void cambiaJuego(FactoriaTipoJuego f) {

		r = f.creaReglas();
		reset(p,r);

	}
	
	public void undo(){
		
		p.undo();
	}

	public void reset() {
		reset(p,r);
		
	}

	public void movAleatorio() {
		//nose como hacerlo... investigando
		
	}

}
