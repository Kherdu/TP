package tp.pr1.control;

import java.util.Scanner;

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
			System.out.println(partida.getTablero().toString());;
			System.out.println("juegan "+partida.getTurno().toString());
			System.out.print("Que quieres hacer?: ");
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
                        if (s.compareToIgnoreCase("reiniciar")==0){
                            partida.reset();
                        }
                        if (s.compareToIgnoreCase("salir")==0){
                           System.exit(0);
                        }
                        if (s.compareToIgnoreCase("deshacer")==0){
                            if(partida.undo()){
                                System.out.println("deshecho");}
                            else System.out.println("no");
                          
                        }
                        
			
	}

	
	public String scan(Scanner in){
		//funcion para leer
		String ret=null;
		
		ret=in.next();
		
		return ret;
		
	}


}
