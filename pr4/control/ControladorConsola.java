package tp.pr4.control;


import java.util.Scanner;
import java.util.StringTokenizer;

import tp.pr4.GUI.Observer;
import tp.pr4.constants.Constants;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.InstruccionInvalida;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Partida;
import tp.pr4.logica.ReglasJuego;
import tp.pr4.logica.Tablero;

public class ControladorConsola {

	private Partida partida;
	private Scanner in;
	private FactoriaTipoJuego f;
	private Jugador jugador1;
	private Jugador jugador2;
	private Jugador[] jugadores;
	private ReglasJuego reglas;

	public ControladorConsola(FactoriaTipoJuego factoria, Partida partida, Scanner sc) {
		this.partida = partida;
		this.f = factoria;
		this.in = sc;
		this.jugador1 = f.creaJugadorHumanoConsola(in);
		this.jugador2 = f.creaJugadorHumanoConsola(in);
		this.jugadores = new Jugador[Constants.numJugadores];
		this.jugadores[0] = jugador1;
		this.jugadores[1] = jugador2;
		this.reglas = f.creaReglas();
	
	}

	public void run() {

		int i = 0;
		// bucle principal si la partida no ha terminado y hay 2 jugadores
		while (!partida.isTerminada() && i < jugadores.length && !partida.isTablas()) {
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
				parse(lectura, jugadores[i]);

			} catch (InstruccionInvalida e) {
				System.err.println(e.getMessage());
			}
			i++;
			if (i == 2) {
				i = 0;
			}

		}
		if (!(partida.getGanador() == Ficha.VACIA)) {
			System.out.print(partida.pintaTablero());
			System.out.println("Ganan las " + partida.getGanador().toString());
		} else if (partida.isTablas()){
			System.out.print(partida.pintaTablero());
			System.out.println("Partida terminada en tablas.");
		}
		in.close();
	}

	public Tablero getTablero(){
		
		return partida.getTablero();
		
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
						partida.Mover(j);
					} catch (MovimientoInvalido e) {
						System.err.print(e.getMessage() + "\n");
					}

				} else if (aux.compareToIgnoreCase("reiniciar") == 0) {
					reset();
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
						f = new FactoriaConecta4();
						

					} else if (ju.compareToIgnoreCase("co") == 0) {
						f = new FactoriaComplica();
						

					}else throw new InstruccionInvalida("No te entiendo.");
					cambiaJuego(f);
				} else
					throw new InstruccionInvalida("No te entiendo.");

			} else if (st.countTokens() == 2) {

				if (aux.compareToIgnoreCase("jugador") == 0) {
					String color = st.nextToken();
					if (color.compareToIgnoreCase("blancas") == 0) {
						// jugador blanco = jugador 1, jugador negro= jugador 2
						String ju = st.nextToken();
						if (ju.compareToIgnoreCase("humano") == 0) {

							this.jugador1 = f.creaJugadorHumanoConsola(in);
							cambiaJugadores(jugador1, 0);

						} else if (ju.compareToIgnoreCase("aleatorio") == 0)

							this.jugador1 = f.creaJugadorAleatorio();
							cambiaJugadores(jugador1, 0);

					} else if (color.compareToIgnoreCase("negras") == 0) {
						String ju = st.nextToken();
						if (ju.compareToIgnoreCase("humano") == 0) {

							this.jugador2 = f.creaJugadorHumanoConsola(in);
							cambiaJugadores(jugador1, 1);

						} else if (ju.compareToIgnoreCase("aleatorio") == 0)

							this.jugador2 = f.creaJugadorAleatorio();
							cambiaJugadores(jugador1, 1);

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
							
						}
						if (tabX > 0 || tabY > 0) {
							if (tabX <= 0) {
								tabX = 1;
							}
							if (tabY <= 0) {
								tabY = 1;
							}
							f = new FactoriaGravity(tabX, tabY);
							cambiaJuego(f);
							
						} else if (tabX < 0 && tabY < 0) {
							f = new FactoriaGravity(1, 1);
							cambiaJuego(f);
							
						} else
							throw new InstruccionInvalida("No te entiendo.");
					}
				} else
					throw new InstruccionInvalida("No te entiendo.");
			} else
				throw new InstruccionInvalida("No te entiendo");
		}

	}

	/*
	 * cargarnos arraylist y volver a meter el jugador nuevo
	 */
	private void cambiaJugadores(Jugador j, int pos) {
		jugadores[pos] = j;

	}

	/*
	 * reinicio de juego
	 */
	
	private void reset(){
		partida.reset(reglas);
		
	}

	private void cambiaJuego(FactoriaTipoJuego f) {

		
		this.jugador1 = f.creaJugadorHumanoConsola(in);
		this.jugador2 = f.creaJugadorHumanoConsola(in);

		jugadores[0] = jugador1;
		jugadores[1] = jugador2;
		reglas = f.creaReglas();
		partida.reset(reglas);
		System.out.println("Partida reiniciada.");

	}
	
	

	public void addObserver(Observer o) {
		partida.addObserver(o);
		
	}
}
