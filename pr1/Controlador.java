package pr1;

import java.util.Scanner;

public class Controlador {
	
	private Partida partida;
	private Scanner in;
	
	public Controlador(Partida partida, Scanner in){
		partida= new Partida();
		
	}
	
	public void run(){
		
		String lectura;
			do{
			partida.getTablero().toString();
			System.out.println("juegan"+partida.getTurno().toString());
			System.out.print("Qué quieres hacer?");
			lectura=in.next();
			parse(lectura);
			
	
			}while(!partida.isTerminada());
	
	}
	
	public void parse(String s){
			int col;
			Scanner lec = new Scanner(System.in);
			
			if (s.compareToIgnoreCase("poner")==0){
					
				System.out.println("Introduce la columna");
				
				
				
			}
		
		
	}
	
	

}
