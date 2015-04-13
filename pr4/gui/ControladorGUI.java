package tp.pr4.GUI;

import tp.pr4.control.FactoriaTipoJuego;
import tp.pr4.logica.Partida;
import tp.pr4.logica.ReglasJuego;

public class ControladorGUI {

	
	private FactoriaTipoJuego f;
	private Partida p;
	private ReglasJuego r;
	
	public ControladorGUI(FactoriaTipoJuego factoria, Partida partida) {
		
		f=factoria;
		p=partida;
		r=f.creaReglas();
		
	}

}
