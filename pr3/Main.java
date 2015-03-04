package tp.pr3;

import java.util.Scanner;

import org.apache.commons.cli.Options;

import tp.pr3.control.Controlador;
import tp.pr3.control.FactoriaConecta4;
import tp.pr3.control.FactoriaComplica;
import tp.pr3.control.FactoriaGravity;
import tp.pr3.control.FactoriaTipoJuego;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasJuego;

public class Main {

	public static void main(String[] args) {
		
		Options options= new Options();
		options.addOption("h", false, "Muestra esta ayuda.");
		options.addOption("g", true, "Tipo de juego (c4, co, gr). Por defecto, c4.");
		
		FactoriaTipoJuego f = new FactoriaConecta4();
	    ReglasJuego reglas= f.creaReglas(); 
		Scanner in = new Scanner(System.in);
		Partida p = new Partida(reglas);
		
		Controlador C = new Controlador(f,p, in);
		C.run();

	}

}
