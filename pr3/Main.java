package tp.pr3;

import java.io.OutputStream;
import java.util.Scanner;

import org.apache.commons.cli.*;

import tp.pr3.control.Controlador;
import tp.pr3.control.FactoriaComplica;
import tp.pr3.control.FactoriaConecta4;
import tp.pr3.control.FactoriaGravity;
import tp.pr3.control.FactoriaTipoJuego;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasJuego;

public class Main {

	public static void main(String[] args) {

		int columnNumber = 0;
		int rowNumber = 0;
		String game = null;
		OutputStream output = null;
		CommandLineParser parser = null;
		CommandLine cmdLine = null;
		Options options = new Options();
		options.addOption("h", "help", false, "Muestra esta ayuda.");
		options.addOption("g", "game", true,
				"Tipo de juego (c4, co, gr). Por defecto, c4.");
		options.addOption("x", "tamX", true,
				"Número de columnas del tablero (sólo para Gravity). Por defecto, 10.");
		options.addOption("y", "tamY", true,
				"Número de filas del tablero (sólo para Gravity). Por defecto, 10.");

		FactoriaTipoJuego f = new FactoriaConecta4();
		ReglasJuego reglas = f.creaReglas();
		Scanner in = new Scanner(System.in);
		Partida p = new Partida(reglas);
		Controlador c = new Controlador(f, p, in);

		// aqui irian los optionGroup para incompatibilidades de argumentos
		if (args == null) {
			
			c.run();
		} else
			try {
				parser = new BasicParser();
				cmdLine = parser.parse(options, args);

				if (cmdLine.hasOption("h")) {
					new HelpFormatter()
							.printHelp(
									"tp.pr3.Main [-g <game>] [-h] [-x <columnNumber>] [-y <rowNumber>]",
									options);

				}
				if (cmdLine.hasOption("g")) {
					game = cmdLine.getOptionValue("game");
					if (game == "c4") {
						c.run();
					} else if (game == "co") {
						f= new FactoriaComplica();
						reglas = f.creaReglas();
						p = new Partida(reglas);
						c = new Controlador(f, p, in);
						c.run();
					} else if (game == "gr") {
						if (cmdLine.hasOption("x") && cmdLine.hasOption("y")){
							columnNumber=Integer.parseInt(cmdLine.getOptionValue("x"));
							rowNumber=Integer.parseInt(cmdLine.getOptionValue("y"));
							f= new FactoriaGravity();
							reglas = f.creaReglas();
							p = new Partida(reglas);
							c = new Controlador(f, p, in);
							c.run();
						}
						
					}

				}
				if (cmdLine.hasOption("h")) {

				}
			} catch (Exception e) {

			}

	}

}
