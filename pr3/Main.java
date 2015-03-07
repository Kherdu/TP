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
		
		FactoriaTipoJuego f = new FactoriaConecta4();
		ReglasJuego reglas = f.creaReglas();
		Scanner in = new Scanner(System.in);
		Partida p = new Partida(reglas);
		Controlador c = new Controlador(f, p, in);
	
			try {
				
				parser = new BasicParser();
				cmdLine = parser.parse(options, args, false);
				String[] argumentos=cmdLine.getArgs();
				String salida="Argumentos no entendidos: ";
				if (argumentos.length>0){
					for (int i=0;i<argumentos.length;i++){
						salida+=argumentos[i]+=" ";
						
					}
					System.err.println("Uso incorrecto:"+salida);
					System.err.println("Use -h|--help para más detalles.");
					System.exit(1);
				}
				
				if (cmdLine.hasOption("h")) {
					new HelpFormatter().printHelp(Constants.MensajeAyudaConsola, options);
					System.exit(0);
				} else if (cmdLine.hasOption("g")){
						game = cmdLine.getOptionValue("game");
						
						if (game.equalsIgnoreCase("c4")){
							f=new FactoriaConecta4();
						}else if (game.equalsIgnoreCase("co")) {
							f= new FactoriaComplica();
							
						}else if (game.equalsIgnoreCase("gr")) {
							
							if (cmdLine.hasOption("x") && cmdLine.hasOption("y")){
								try{
									columnNumber=Integer.parseInt(cmdLine.getOptionValue("y"));
									rowNumber=Integer.parseInt(cmdLine.getOptionValue("x"));
									f=new FactoriaGravity(rowNumber,columnNumber);
									
								}catch ( NumberFormatException e){
									System.exit(1);
								}
									
							}else f=new FactoriaGravity();
								
						}else throw new ParseException("Uso incorrecto: "+ "Juego '" + game + "' Incorrecto");
					}
						
			} catch (ParseException ex) {
				System.err.println(ex.getMessage());
				System.err.println("Use -h|--help para más detalles.");
				System.exit(1);
			}
			
			
			reglas = f.creaReglas();
			
			p = new Partida(reglas);
			c = new Controlador(f, p, in);
			
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
