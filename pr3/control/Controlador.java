package tp.pr3.control;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import tp.pr3.constants.Constants;
import tp.pr3.logica.Ficha;
import tp.pr3.logica.Juego;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoComplica;
import tp.pr3.logica.MovimientoConecta4;
import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasComplica;
import tp.pr3.logica.ReglasConecta4;
import tp.pr3.logica.ReglasGravity;
import tp.pr3.logica.ReglasJuego;

public class Controlador {

	private Partida partida;
	private Scanner in;
	private FactoriaTipoJuego f;
	private Jugador jugador1;
	private Jugador jugador2;
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private ReglasJuego reglas;

	public Controlador(FactoriaTipoJuego factoria, Partida partida, Scanner sc) {
		this.partida = partida;
		this.f = factoria;
		this.in = sc;
		this.jugador1 = f.creaJugadorHumanoConsola(in);
		this.jugador2 = f.creaJugadorHumanoConsola(in);
		this.jugadores.add(jugador1);
		this.jugadores.add(jugador2);
		this.reglas = f.creaReglas();

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
		// parser-ejecucion, cambiar para que lance excepciones... en
		// ejecutamovimiento deberia lanzarlas cuando sea fuera del tablero, en
		// sitio ocupado y cuando esta finalizada la partida

		StringTokenizer st = new StringTokenizer(s);
		if (st.hasMoreTokens()) {
			String aux = st.nextToken(" ");
			if (st.countTokens() == 1) {

				if ((aux.compareToIgnoreCase("poner")) == 0) {
					try {
						for (Jugador j : jugadores) {
							partida.Mover(j, in);
						}
					} catch (MovimientoInvalido e) {
						e.printStackTrace();
					}
				} else if (aux.compareToIgnoreCase("reiniciar") == 0) {
					partida.reset(f.creaReglas());
					System.out.print("Partida reiniciada.");

				} else if (aux.compareToIgnoreCase("salir") == 0) {

					System.exit(0);

				} else if (aux.compareToIgnoreCase("deshacer") == 0) {
					if (!partida.undo())
						System.err.println("Imposible deshacer.");
				} else if (aux.compareToIgnoreCase("ayuda") == 0) {

					System.out.println(Constants.MensajeAyuda);
				} else System.out.println("controlador 98");

			} else if (st.countTokens() == 2) {

				if (aux.compareToIgnoreCase("jugar") == 0) {
					String ju = st.nextToken();

					if (ju.compareToIgnoreCase("c4") == 0) {
						f = new FactoriaConecta4();
						reglas = f.creaReglas();
						partida.reset(reglas);
						System.out.println("Partida reiniciada.");
					} else if (ju.compareToIgnoreCase("co") == 0) {
						f = new FactoriaComplica();
						reglas = f.creaReglas();
						System.out.println("Partida reiniciada.");
					} else if (ju.compareToIgnoreCase("gr") == 0) {
						f = new FactoriaGravity();
						reglas = f.creaReglas();
						System.out.println("Partida reiniciada.");
					}
				}else System.out.println("controlador 119");

			} else if (st.countTokens() == 4) {

				if (aux.compareToIgnoreCase("jugar") == 0) {
					String ju = st.nextToken();
					if (ju.compareToIgnoreCase("gr") == 0) {
						int c = 0;
						int fi = 0;
						try {
							c = Integer.parseInt(st.nextToken(" "));
							fi = Integer.parseInt(st.nextToken(" "));
						} catch (NumberFormatException e) {
							System.err.println("NO");
						}

						f = new FactoriaGravity(fi, c);
						System.out.println("Partida reiniciada.");
					}
				}
			}else System.out.println("controlador 139");
		}else System.out.println("controlador 140");
	}
}
