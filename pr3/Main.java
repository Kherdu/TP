package tp.pr3;

import java.util.Scanner;

import tp.pr3.control.Controlador;
import tp.pr3.control.FactoriaConecta4;
import tp.pr3.control.FactoriaComplica;
import tp.pr3.control.FactoriaGravity;
import tp.pr3.control.FactoriaTipoJuego;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasJuego;

public class Main {

	public static void main(String[] args) {
		
		
		
		//con la entrada de argumentos cambiar el tipo de juego, reglas y factoria
		
		FactoriaTipoJuego f = new FactoriaConecta4();
	    ReglasJuego reglas= f.creaReglas(); 
		Scanner in = new Scanner(System.in);
		Partida p = new Partida(reglas);
		
		Controlador C = new Controlador(f,p, in);
		C.run();

	}

}
