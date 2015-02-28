package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Juego;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoComplica;
import tp.pr3.logica.MovimientoConecta4;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasComplica;
import tp.pr3.logica.ReglasConecta4;

public class Controlador {

	private Partida partida;
	private Scanner in;
	private Movimiento m;
	private FactoriaTipoJuego f;
	private Jugador jugador1;
	private Jugador jugador2;

	public Controlador(FactoriaTipoJuego factoria,Partida partida, Scanner sc) {
		this.partida = partida;
		this.f=factoria;
		this.in=sc;
	}

	public void run() {

		String lectura;
		this.in = new Scanner(System.in);

		while (!partida.isTerminada()) {

			lectura = null;
			System.out.print(partida.pintaTablero());
			System.out.print("Juegan ");

			if (partida.getTurno() == Ficha.BLANCA) {
				System.out.println("blancas");
			} else if (partida.getTurno() == Ficha.NEGRA) {
				System.out.println("negras");
			}

			System.out.print("Qué quieres hacer? ");
			lectura = in.nextLine();
			parse(lectura);

		}
		in.close();
	}

	public void parse(String s) {
		// parser-ejecucion, cambiar para que lance excepciones... en ejecutamovimiento deberia lanzarlas cuando sea fuera del tablero, en sitio ocupado y cuando esta finalizada la partida
		int col;
		String st;
		if (s.compareToIgnoreCase("poner") == 0) {
			if (partida.isTerminada() == true) {
				System.out.println("acabada");
			} else {
				System.out.print("Introduce la columna: ");
				st = in.nextLine();
				boolean correcto = true;
				col = Integer.parseInt(st.trim());
				if (partida.getJuego().getTipo() == Juego.CONECTA4) { 
				
					// creamos movimiento tipo c4 o co segun sea un juego u otro, ahora se encarga la factoria
					m = new MovimientoConecta4(col, partida.getTurno());
					correcto = partida.ejecutaMovimiento(m);

				} else if (partida.getJuego().getTipo() == Juego.COMPLICA) {
					m = new MovimientoComplica(col, partida.getTurno());
					correcto = partida.ejecutaMovimiento(m);
				}
				if (correcto == false)
					System.err.println("Movimiento incorrecto");
			}
		} else if (s.compareToIgnoreCase("reiniciar") == 0) {
			partida.reset(partida.getJuego());
			System.out.print("Partida reiniciada.");

		} else if (s.compareToIgnoreCase("salir") == 0) {

			System.exit(0);

		} else if (s.compareToIgnoreCase("deshacer") == 0) {
			if (!partida.undo())
				System.err.println("Imposible deshacer.");

		} else if (s.compareToIgnoreCase("jugar c4") == 0) {
			ReglasConecta4 reglas = new ReglasConecta4();
			partida.reset(reglas);
			System.out.println("Partida reiniciada.");
		} else if (s.compareToIgnoreCase("jugar co") == 0) {
			ReglasComplica reglas = new ReglasComplica();
			partida.reset(reglas);
			System.out.println("Partida reiniciada.");

		} else
			System.err.println("No te entiendo.");

	}

}
