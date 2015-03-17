package tp.pr4.control;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import tp.pr4.constants.Constants;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.InstruccionInvalida;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Partida;
import tp.pr4.logica.ReglasJuego;

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
		this.jugadores.add(0, jugador1);
		this.jugadores.add(1, jugador2);
		this.reglas = f.creaReglas();

	}

	public void run() {

		int i = 0;
		while (!partida.isTerminada() && i < jugadores.size()) {
			String lectura;
			System.out.print(partida.pintaTablero());
			System.out.print("Juegan ");

			if (partida.getTurno() == Ficha.BLANCA) {
				System.out.println("blancas");
			} else if (partida.getTurno() == Ficha.NEGRA) {
				System.out.println("negras");
			}

			System.out.print("Qué quieres hacer? ");
			try {
				lectura = in.nextLine();
				parse(lectura, jugadores.get(i));

			} catch (InstruccionInvalida e) {
				System.err.println(e.getMessage());
			}
			i++;
			if (i == jugadores.size()) {
				i = 0;
			}

		}
		if (!(partida.getGanador() == Ficha.VACIA)) {
			System.out.println("Ganan las " + partida.getGanador().toString());
		} else
			System.out.println("Partida terminada en tablas.");

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
						System.err.print(e.getMessage()+ "\n");
					}

				} else if (aux.compareToIgnoreCase("reiniciar") == 0) {
					partida.reset(f.creaReglas());
					System.out.print("Partida reiniciada.");

				} else if (aux.compareToIgnoreCase("salir") == 0) {

					System.exit(0);

				} else if (aux.compareToIgnoreCase("deshacer") == 0) {
					if (!partida.undo())
						throw new InstruccionInvalida("Imposible deshacer.");
				} else if (aux.compareToIgnoreCase("ayuda") == 0) {

					System.out.println(Constants.MensajeAyuda);
				} else
					throw new InstruccionInvalida("No te entiendo.");

			} else if (st.countTokens() == 1) {

				if (aux.compareToIgnoreCase("jugar") == 0) {
					String ju = st.nextToken();

					if (ju.compareToIgnoreCase("c4") == 0) {
						this.f = new FactoriaConecta4();
						this.jugador1 = f.creaJugadorHumanoConsola(in);
						this.jugador2 = f.creaJugadorHumanoConsola(in);
						jugadores = new ArrayList<Jugador>();
						jugadores.add(jugador1);
						jugadores.add(jugador2);
						reglas = f.creaReglas();
						partida.reset(reglas);
						System.out.println("Partida reiniciada.");
					} else if (ju.compareToIgnoreCase("co") == 0) {
						f = new FactoriaComplica();
						this.jugador1 = f.creaJugadorHumanoConsola(in);
						this.jugador2 = f.creaJugadorHumanoConsola(in);
						jugadores = new ArrayList<Jugador>();
						jugadores.add(jugador1);
						jugadores.add(jugador2);
						reglas = f.creaReglas();
						partida.reset(reglas);
						System.out.println("Partida reiniciada.");
					} else if (ju.compareToIgnoreCase("gr") == 0) {
						f = new FactoriaGravity();
						this.jugador1 = f.creaJugadorHumanoConsola(in);
						this.jugador2 = f.creaJugadorHumanoConsola(in);
						jugadores = new ArrayList<Jugador>();
						jugadores.add(jugador1);
						jugadores.add(jugador2);
						reglas = f.creaReglas();
						partida.reset(reglas);
						System.out.println("Partida reiniciada.");
					}
				} else
					throw new InstruccionInvalida("No te entiendo.");

			} else if (st.countTokens() == 2) {

				if (aux.compareToIgnoreCase("jugador") == 0) {
					String color = st.nextToken();
					if (color.compareToIgnoreCase("blancas") == 0) {
						// jugador blanco = jugador 1, jugador negro= jugador 2
						String ju = st.nextToken();
						if (ju.compareToIgnoreCase("humano") == 0) {
							jugadores.remove(jugador1); // cargarnos arraylist y
														// volver a meter el
														// jugador nuevo
							this.jugador1 = f.creaJugadorHumanoConsola(in);
							jugadores.add(jugador1);
						} else if (ju.compareToIgnoreCase("aleatorio") == 0)
							jugadores.remove(jugador1); // cargarnos arraylist y
						jugadores.remove(jugador2); // volver a meter el
						this.jugador1 = f.creaJugadorAleatorio(); // jugador
																	// nuevo
						this.jugador2 = f.creaJugadorHumanoConsola(in);
						jugadores.add(jugador1);
						jugadores.add(jugador2);
					} else if (color.compareToIgnoreCase("negras") == 0) {
						String ju = st.nextToken();
						if (ju.compareToIgnoreCase("humano") == 0) {
							jugadores.remove(jugador2); // cargarnos arraylist y
														// volver a meter el
														// jugador nuevo
							this.jugador2 = f.creaJugadorHumanoConsola(in);
							jugadores.add(jugador2);
						} else if (ju.compareToIgnoreCase("aleatorio") == 0)
							jugadores.remove(jugador1); // cargarnos arraylist y
							jugadores.remove(jugador2); // volver a meter el
							this.jugador1 = f.creaJugadorHumanoConsola(in); // jugador nuevo
							this.jugador2 = f.creaJugadorAleatorio();
							jugadores.add(jugador1);
							jugadores.add(jugador2);
					} else
						throw new InstruccionInvalida("No te entiendo.");
				} else
					throw new InstruccionInvalida("No te entiendo.");

			} else if (st.countTokens() == 3) {

				if (aux.compareToIgnoreCase("jugar") == 0) {
					String ju = st.nextToken();
					if (ju.compareToIgnoreCase("gr") == 0) {
						int tabX = 0; // ancho
						int tabY = 0; // alto
						try {
							tabX = Integer.parseInt(st.nextToken(" "));
							tabY = Integer.parseInt(st.nextToken(" "));
						} catch (NumberFormatException e) {
							System.err.println("NO");
						}
						if (tabX > 0 && tabY > 0) {
							f = new FactoriaGravity(tabX, tabY);
							this.jugador1 = f.creaJugadorHumanoConsola(in);
							this.jugador2 = f.creaJugadorHumanoConsola(in);
							jugadores = new ArrayList<Jugador>();
							jugadores.add(jugador1);
							jugadores.add(jugador2);
							reglas = f.creaReglas();
							partida.reset(reglas);
							System.out.println("Partida reiniciada.");
						} else
							throw new InstruccionInvalida("No te entiendo.");
					}
				} else
					throw new InstruccionInvalida("No te entiendo.");
			} else
				throw new InstruccionInvalida("No te entiendo");
		}
	}

}