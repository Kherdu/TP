package tp.pr5.control;

import tp.pr5.GUI.ControladorGUI;
import tp.pr5.logica.Ficha;

public class HiloJuego extends Thread{

	private ControladorGUI c;
	public HiloJuego(ControladorGUI c){
		this.c=c;
		
		
	}
	
	public void run(){
		try {
			sleep(1000);
			c.movMaquina();
		} catch (InterruptedException e) {

		}

	}
	
	public void deshacer(){
		c.undoMaquina();
		
	}
	
}
