package tp.pr3;
import java.util.Scanner;

import tp.pr3.control.Controlador;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasConecta4;
public class Main {

	public static void main(String[] args) {
            ReglasConecta4 reglas = new ReglasConecta4();
            Scanner in = new Scanner(System.in);
            Partida p = new Partida(reglas);
            Controlador C = new Controlador(p, in);
            C.run();

	}

}
