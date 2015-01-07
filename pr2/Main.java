package tp.pr2;
import java.util.Scanner;

import tp.pr2.control.Controlador;
import tp.pr2.logica.Partida;
import tp.pr2.logica.ReglasConecta4;
public class Main {

	public static void main(String[] args) {
            ReglasConecta4 reglas = new ReglasConecta4();
            Scanner in = new Scanner(System.in);
            Partida p = new Partida(reglas);
            Controlador C = new Controlador(p, in);
            C.run();

	}

}
