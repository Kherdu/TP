package tp.pr3.control;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import tp.pr3.constants.Constants;
import tp.pr3.logica.Ficha;
import tp.pr3.logica.InstruccionInvalida;
import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.Partida;
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
		this.jugadores.add(0,jugador1);
		this.jugadores.add(1,jugador2);
		this.reglas = f.creaReglas();

	}

	public void run() {

		String lectura;
		this.in = new Scanner(System.in);
		while (!partida.isTerminada()) {
			try {
				for (Jugador j : jugadores) {
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
					parse(lectura, j);
				}
			} catch (InstruccionInvalida e) {

				e.printStackTrace();
			}
		}
		in.close();
	}

	public void parse(String s, Jugador j) throws InstruccionInvalida {
		// parser-ejecucion, cambiar para que lance excepciones... en
		// ejecutamovimiento deberia lanzarlas cuando sea fuera del tablero, en
		// sitio ocupado y cuando esta finalizada la partida
		// elegir jugar contra la maquina

		StringTokenizer st = new StringTokenizer(s);

		if (st.countTokens() > 0) {
			String aux = st.nextToken(" ");

			if (st.countTokens() == 0) { // ya hemos pasado un token

				if ((aux.compareToIgnoreCase("poner")) == 0) {

					try {
						partida.Mover(j, in);
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
						throw new InstruccionInvalida();
				} else if (aux.compareToIgnoreCase("ayuda") == 0) {

					System.out.println(Constants.MensajeAyuda);
				} else
					System.out.println("controlador 98");

			} else if (st.countTokens() == 1) {

				if (aux.compareToIgnoreCase("jugar") == 0) {
					String ju = st.nextToken();

					if (ju.compareToIgnoreCase("c4") == 0) {
						this.f = new FactoriaConecta4();
						this.jugador1 = f.creaJugadorHumanoConsola(in);
						this.jugador2 = f.creaJugadorHumanoConsola(in);
						jugadores.remove(0);
						jugadores.add(0, jugador1);
						jugadores.remove(1);						
						jugadores.add(1, jugador2);
						reglas = f.creaReglas();						
						partida.reset(reglas);
						System.out.println("Partida reiniciada.");
					} else if (ju.compareToIgnoreCase("co") == 0) {
						f = new FactoriaComplica();
						this.jugador1 = f.creaJugadorHumanoConsola(in);
						this.jugador2 = f.creaJugadorHumanoConsola(in);
						jugadores.remove(0);
						jugadores.add(0, jugador1);
						jugadores.remove(1);						
						jugadores.add(1, jugador2);
						reglas = f.creaReglas();
						partida.reset(reglas);
						System.out.println("Partida reiniciada.");
					} else if (ju.compareToIgnoreCase("gr") == 0) {
						f = new FactoriaGravity();
						this.jugador1 = f.creaJugadorHumanoConsola(in);
						this.jugador2 = f.creaJugadorHumanoConsola(in);
						jugadores.remove(0);
						jugadores.add(0, jugador1);
						jugadores.remove(1);						
						jugadores.add(1, jugador2);
						reglas = f.creaReglas();
						partida.reset(reglas);
						System.out.println("Partida reiniciada.");
					}
				} else
					System.out.println("controlador 119");

			} else if (st.countTokens() == 2) {

				if (aux.compareToIgnoreCase("jugador") == 0) {
					String color = st.nextToken();
					if (color.compareToIgnoreCase("blancas") == 0) {
						// jugador blanco = jugador 1, jugador negro= jugador 2
						String ju = st.nextToken();
						if (ju.compareToIgnoreCase("humano") == 0) {
							jugador1 = f.creaJugadorHumanoConsola(in);//cargarnos arraylist y volver a meter el jugador nuevo
							jugadores.remove(0);
							jugadores.add(0, jugador1);
						} else if (ju.compareToIgnoreCase("aleatorio") == 0)
							jugador1 = f.creaJugadorAleatorio(); 
							jugadores.remove(0);
							jugadores.add(0, jugador1);
					} else if (color.compareToIgnoreCase("negras") == 0) {
						String ju = st.nextToken();
						if (ju.compareToIgnoreCase("humano") == 0) {
							jugador2 = f.creaJugadorHumanoConsola(in);
							jugadores.remove(1);
							jugadores.add(1, jugador1);
						} else if (ju.compareToIgnoreCase("aleatorio") == 0)
							jugador2 = f.creaJugadorAleatorio();
							jugadores.remove(1);
							jugadores.add(1, jugador1);
					}
				}

			} else if (st.countTokens() == 3) {

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
			} else
				System.out.println("controlador 139");
		} else
			System.out.println("controlador 140");
	}

}
