package tp.pr1.control;

import java.util.Scanner;

import tp.pr1.logica.Ficha;
import tp.pr1.logica.Partida;

public class Controlador {
	
	private Partida partida;
	private Scanner in;
	
	public Controlador(Partida partida, Scanner in){
		this.partida= partida;
		
	}
	
	public void run(){
		
		String lectura;
		this.in=new Scanner(System.in);
		
			do{
				
			lectura=null;
			System.out.print(partida.getTablero().toString());;
			System.out.print("Juegan ");
			if (partida.getTurno()==Ficha.BLANCA) 
				System.out.println("blancas");
			else if (partida.getTurno()==Ficha.NEGRA) 
				System.out.println("negras");
			System.out.print("Qué quieres hacer? ");
			lectura=scan(in);
			parse(lectura);
			
	
			}while(!partida.isTerminada());
			in.close();
	
	}
	
	public void parse(String s){
		//parser-ejecucion
			int col;
			String st;
			
			if (s.compareToIgnoreCase("poner")==0){
				if (partida.isTerminada()==true){
                     System.out.println("acabada");
				}else {
				System.out.print("Introduce la columna: ");
				st=scan(in);
				col=Integer.parseInt(st);
				partida.ejecutaMovimiento(partida.getTurno(), col);
                }
			}
		   else if (s.compareToIgnoreCase("reiniciar")==0){
        	   partida.reset();}
           
		   else if (s.compareToIgnoreCase("salir")==0){
               System.exit(0);}
           
		   else if (s.compareToIgnoreCase("deshacer")==0){
               if(!partida.undo()) System.err.println("Imposible deshacer.");
               
           
		   } else System.err.println("orden incorrecta");
                        
			
	}

	
	public String scan(Scanner in){
		//funcion para leer
		String ret=null;
		
		ret=in.next();
		
		return ret;
		
	}


}
