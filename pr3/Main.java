package tp.pr3;

import java.util.Scanner;

import org.apache.commons.cli.*;

import tp.pr3.constants.Constants;
import tp.pr3.control.Controlador;
import tp.pr3.control.FactoriaComplica;
import tp.pr3.control.FactoriaConecta4;
import tp.pr3.control.FactoriaGravity;
import tp.pr3.control.FactoriaTipoJuego;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasJuego;

public class Main {

	public static void main(String[] args) {

		int salida=0;
		int columnNumber = 0;
		int rowNumber = 0;
		String game = null;
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
		if (args == null || args.length < 1 || args[0] == null) {
			c.run();
		} else
			try {
				parser = new BasicParser();
				cmdLine = parser.parse(options, args, false);
				
				if (cmdLine.hasOption("h")) {
<<<<<<< HEAD
					new HelpFormatter()
							.printHelp(
									"tp.pr3.Main [-g <game>] [-h] [-x <columnNumber>] [-y <rowNumber>]",
									options);
=======
					new HelpFormatter().printHelp(Constants.MensajeAyudaConsola, options);
>>>>>>> origin/master

				} else if (cmdLine.hasOption("g")) {
					game = cmdLine.getOptionValue("game");
<<<<<<< HEAD
					if (game == "c4") {
						c.run();
=======
					if (game.compareToIgnoreCase("c4") == 0) {
						ok=true;
>>>>>>> origin/master
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
							f= new FactoriaGravity(rowNumber,columnNumber);
							reglas = f.creaReglas();
							p = new Partida(reglas);
							c = new Controlador(f, p, in);
							c.run();
						}
						
					}else throw new ParseException("Juego '" + game + "' Incorrecto");

				}
<<<<<<< HEAD
				
			} catch (Exception e) {
				System.out.println(cmdLine.getArgs().toString());
				salida=1;
=======
				if (ok){
					reglas = f.creaReglas();
					p = new Partida(reglas);
					c = new Controlador(f, p, in);
					c.run();
				}
			}  catch (ParseException ex){
				System.err.println(ex.getMessage());
				System.err.println("Use -h|--help para más detalles.");
>>>>>>> origin/master
			}
	}

}
