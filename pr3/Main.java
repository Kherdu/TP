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

		
		int columnNumber = 0;
		int rowNumber = 0;
		String game = null;
		CommandLineParser parser = null;
		CommandLine cmdLine = null;
		Options options = generateOptions();
		boolean ok=false;
		
		FactoriaTipoJuego f = new FactoriaConecta4();
		ReglasJuego reglas = f.creaReglas();
		Scanner in = new Scanner(System.in);
		Partida p = new Partida(reglas);
		Controlador c = new Controlador(f, p, in);
		
		// aqui irian los optionGroup para incompatibilidades de argumentos
		/*if (args == null || args.length < 1 || args[0] == null) {
			ok=true;
		} else */
			try {
				
				parser = new BasicParser();
				cmdLine = parser.parse(options, args, false);
				String[] argumentos=cmdLine.getArgs();
				String salida="Argumentos no entendidos: ";
				if (argumentos.length>0){
					for (int i=0;i<argumentos.length;i++){
						salida+=argumentos[i]+=" ";
						
					}
					System.err.println(salida);
				}
				
				if (cmdLine.hasOption("h")) {
					new HelpFormatter().printHelp(Constants.MensajeAyudaConsola, options);
					
				} else if (cmdLine.hasOption("g")){
						game = cmdLine.getOptionValue("game");
						
						if (game=="c4"){
							ok=true;
						}else if (game == "co") {
							f= new FactoriaComplica();
							ok=true;
						}else if (game == "gr") {
							if (cmdLine.hasOption("x") && cmdLine.hasOption("y")){
								try{
									columnNumber=Integer.parseInt(cmdLine.getOptionValue("x"));
									rowNumber=Integer.parseInt(cmdLine.getOptionValue("y"));
									f=new FactoriaGravity(rowNumber,columnNumber);
									ok=true;
								}catch ( NumberFormatException e){
									System.exit(2);
								}
									
							}else f=new FactoriaGravity();
								
						}else throw new ParseException("Juego '" + game + "' Incorrecto");
					}
						
			} catch (ParseException ex) {
				System.err.println(ex.getMessage());
				System.err.println("Use -h|--help para más detalles.");
				System.exit(1);
			}
		c.run();
	}
	
	
	
	private static Options generateOptions(){
		
		Options options = new Options();
		
		options.addOption("h", "help", false, "Muestra esta ayuda.");
		options.addOption("g", "game", true,
				"Tipo de juego (c4, co, gr). Por defecto, c4.");
		options.addOption("x", "tamX", true,
				"Número de columnas del tablero (sólo para Gravity). Por defecto, 10.");
		options.addOption("y", "tamY", true,
				"Número de filas del tablero (sólo para Gravity). Por defecto, 10.");
		
		options.getOption("g").setArgName("game");
		options.getOption("x").setArgName("columnNumber");
		options.getOption("y").setArgName("rowNumber");
		return options;
		
		
		
	}

}
