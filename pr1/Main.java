package tp.pr1;

import java.util.Scanner;

import tp.pr1.control.Controlador;

import tp.pr1.logica.Partida;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		Partida p = new Partida();
		Controlador C = new Controlador(p, in);
		C.run();

	}

}
