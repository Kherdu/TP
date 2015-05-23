package tp.pr5.control;

import tp.pr5.GUI.ControladorGUI;
import tp.pr5.logica.Ficha;

public class HiloJuego extends Thread{

	private ControladorGUI c;
	private Ficha color;
	public HiloJuego(ControladorGUI c, Ficha color){
		this.c=c;
		this.color=color;
		
	}
	
	public void run(){
		
		try {
			sleep(1000);
			c.movMaquina(color);
		} catch (InterruptedException e) {

		}

	}
	
	public void deshacer(){
		c.undoMaquina();
		
	}

	
	
}
