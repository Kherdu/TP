package tp.pr1;



import java.util.Scanner;

import logica.Partida;
import control.Controlador;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Scanner in = new Scanner(System.in);
			Partida p=new Partida();
			Controlador C=new Controlador(p,in);
			C.run();
			
			
	}

}
