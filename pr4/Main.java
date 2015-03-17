package tp.pr4;

import java.util.Scanner;

import org.apache.commons.cli.*;

import tp.pr4.constants.Constants;
import tp.pr4.control.Controlador;
import tp.pr4.control.FactoriaComplica;
import tp.pr4.control.FactoriaConecta4;
import tp.pr4.control.FactoriaGravity;
import tp.pr4.control.FactoriaTipoJuego;
import tp.pr4.logica.Partida;
import tp.pr4.logica.ReglasJuego;

public class Main {

	public static void main(String[] args) {

		
		// variables del commons.cli
		
		String Ui = null;
		String game = null;
		CommandLineParser parser = null;
		CommandLine cmdLine = null;
		Options options = generateOptions();
		
		//variables para crear el juego
		Boolean UIs = false;
		int columnNumber = 0;
		int rowNumber = 0;
		FactoriaTipoJuego f = new FactoriaConecta4();
		ReglasJuego reglas = f.creaReglas();
		Scanner in = new Scanner(System.in);
		Partida p = new Partida(reglas);
		Controlador c = new Controlador(f, p, in);

		try {
			//si los argumentos son incorrectos
			parser = new BasicParser();
			cmdLine = parser.parse(options, args, false);
			String[] argumentos = cmdLine.getArgs();
			String salida = "Argumentos no entendidos: ";
			if (argumentos.length > 0) {
				for (int i = 0; i < argumentos.length; i++) {
					salida += argumentos[i] += " ";

				}
				throw new ParseException(salida);
			}
			//casos de argumentos de entrada
			if (cmdLine.hasOption("h")) {
				new HelpFormatter().printHelp(Constants.MensajeAyudaConsola,
						options);
				System.exit(0);
			} else if (cmdLine.hasOption("u")) {
				// interfaz grafica o por consola
				Ui = cmdLine.getOptionValue("tipo");
				if (Ui.equalsIgnoreCase("console"))
					UIs = false;
				else if (Ui.equalsIgnoreCase("window"))
					UIs = true;
				else
					throw new ParseException("Opcion no valida");

			} else if (cmdLine.hasOption("g")) {

				game = cmdLine.getOptionValue("game");

				if (game.equalsIgnoreCase("c4")) {
					f = new FactoriaConecta4();
				} else if (game.equalsIgnoreCase("co")) {
					f = new FactoriaComplica();

				} else if (game.equalsIgnoreCase("gr")) {

					if (cmdLine.hasOption("x") && cmdLine.hasOption("y")) {
						try {
							columnNumber = Integer.parseInt(cmdLine
									.getOptionValue("y"));
							rowNumber = Integer.parseInt(cmdLine
									.getOptionValue("x"));
							f = new FactoriaGravity(rowNumber, columnNumber);

						} catch (NumberFormatException e) {
							System.exit(1);
						}

					} else
						f = new FactoriaGravity();

				} else
					throw new ParseException("Juego '" + game + "' Incorrecto");
			}

		} catch (ParseException ex) {
			System.err.println("Uso incorrecto: " + ex.getMessage());
			System.err.println("Use -h|--help para m�s detalles.");
			System.exit(1);
		}

		reglas = f.creaReglas();
		p = new Partida(reglas);

		if (UIs) {
			c = new Controlador(f, p, in);
			c.run();
		}else {
			//ejecutar modo ventana
			
		}
	}
	
	/* 
	 * clase que genera las opciones de entrada con commons.cli
	 * 
	 */

	private static Options generateOptions() {

		Options options = new Options();

		options.addOption("h", "help", false, "Muestra esta ayuda.");
		options.addOption("g", "game", true,
				"Tipo de juego (c4, co, gr). Por defecto, c4.");
		options.addOption("x", "tamX", true,
				"N�mero de columnas del tablero (s�lo para Gravity). Por defecto, 10.");
		options.addOption("y", "tamY", true,
				"N�mero de filas del tablero (s�lo para Gravity). Por defecto, 10.");
		options.addOption("u", "ui", true,
				" Tipo de interfaz (console, window). Por defecto, console.");
		options.getOption("g").setArgName("game");
		options.getOption("x").setArgName("columnNumber");
		options.getOption("y").setArgName("rowNumber");
		options.getOption("u").setArgName("tipo");

		return options;
	}

}