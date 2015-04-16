package tp.pr4;

import java.util.Scanner;

import org.apache.commons.cli.*;

import tp.pr4.GUI.ControladorGUI;
import tp.pr4.GUI.MainWindow;
import tp.pr4.constants.Constants;
import tp.pr4.control.ControladorConsola;
import tp.pr4.control.FactoriaComplica;
import tp.pr4.control.FactoriaConecta4;
import tp.pr4.control.FactoriaGravity;
import tp.pr4.control.FactoriaTipoJuego;
import tp.pr4.logica.Partida;
import tp.pr4.logica.ReglasJuego;

public class Mainp4 {

	public static void main(String[] args) {

		
		// variables del commons.cli
		
		String ui = null;
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
		

		try {
			//si los argumentos son incorrectos
			parser = new BasicParser();
			cmdLine = parser.parse(options, args, false);
			String[] argumentos = cmdLine.getArgs();
			
			if (argumentos.length > 0) {
				String salida = "Argumentos no entendidos: ";
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
			} else  if (cmdLine.hasOption("g")) {

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
			} if (cmdLine.hasOption("u")) {
				// interfaz grafica o por consola
				ui = cmdLine.getOptionValue("ui");
				
				if (ui.equalsIgnoreCase("console"))
					UIs = false;
				else if (ui.equalsIgnoreCase("window"))
					UIs = true;
				else
					throw new ParseException("Opcion no valida");

			}

		} catch (ParseException ex) {
			System.err.println("Uso incorrecto: " + ex.getMessage());
			System.err.println("Use -h|--help para más detalles.");
			System.exit(1);
		}

		//creamos el modelo
		reglas = f.creaReglas();
		p = new Partida(reglas);

		//creamos los controladores segun tenga interfaz grafica o no
		if (!UIs) {
			ControladorConsola c = new ControladorConsola(f, p, in);
			
			c.run();
		}else {
			
			ControladorGUI c= new ControladorGUI(f,p); //controlador con metodos observer
			MainWindow w= new MainWindow(c); //crear vista con el controlador
			
			
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
				"Número de columnas del tablero (sólo para Gravity). Por defecto, 10.");
		options.addOption("y", "tamY", true,
				"Número de filas del tablero (sólo para Gravity). Por defecto, 10.");
		options.addOption("u", "ui", true,
				" Tipo de interfaz (console, window). Por defecto, console.");
		options.getOption("g").setArgName("game");
		options.getOption("x").setArgName("columnNumber");
		options.getOption("y").setArgName("rowNumber");
		options.getOption("u").setArgName("ui");

		return options;
	}

}
