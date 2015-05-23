package tp.pr5.logica;

import tp.pr5.GUI.ControladorGUI;
import tp.pr5.control.HiloJuego;

public class ModoAuto implements ModoJuego {
	
	private ControladorGUI c;
	private HiloJuego h;
	private TipoJugador j;
	private Ficha color;
	public ModoAuto(ControladorGUI c, Ficha color){
		this.c=c;
		this.j=TipoJugador.AUTOMATICO;
		this.color=color;
		
	}
	@Override
	public void comenzar() {
		
		h= new HiloJuego(c, color);
		
		h.start();
		
		
	}

	@Override
	public void terminar() {
		h.interrupt();
		
	}

	@Override
	public void deshacer() {
		h.deshacer();
		
	}
	
	@Override
	public TipoJugador getJ() {
		return j;
	}

}
