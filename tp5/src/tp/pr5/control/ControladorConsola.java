package tp.pr5.control;

import java.util.Scanner;
import java.util.StringTokenizer;

import tp.pr5.GUI.Observer;
import tp.pr5.constants.Constants;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.InstruccionInvalida;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.Partida;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.logica.Tablero;

public class ControladorConsola {

	private Partida partida;
	private Scanner in;
	private FactoriaTipoJuego f;

	
	//estos van a ser los jugadores
	private Jugador blanco;
	private Jugador negro;
	//si alguno de los dos es aleatorio
	private boolean iablanca;
	private boolean ianegra;
	private ReglasJuego reglas;
	private VistaConsola vista;

	public ControladorConsola(FactoriaTipoJuego factoria, Partida partida,
			Scanner sc) {
		this.partida = partida;
		this.f = factoria;
		this.in = sc;
		this.reglas = f.creaReglas();
		//inicialmente los dos jugadores son humanos
		this.blanco = jugadorPorJuego(false);
		this.negro= jugadorPorJuego(false);

		
		

	}

	public void run() {
		
		vista = new VistaConsola(this,reglas.jugadorInicial());
		vista.run();
	}

	

	public void parse() {
		// parser-ejecucion, cambiar para que lance excepciones... en
		// ejecutamovimiento deberia lanzarlas cuando sea fuera del tablero, en
		// sitio ocupado y cuando esta finalizada la partida
		// elegir jugar contra la maquina

		String s = in.nextLine();
		StringTokenizer st = new StringTokenizer(s);

		if (st.countTokens() > 0) {
			String aux = st.nextToken(" ");

			if (st.countTokens() == 0) { // ya hemos pasado un token

				if ((aux.compareToIgnoreCase("poner")) == 0) {

					
						partida.Mover(jugadorQueToca());
					

				} else if (aux.compareToIgnoreCase("reiniciar") == 0) {
					reset();
					

				} else if (aux.compareToIgnoreCase("salir") == 0) {

					System.exit(0);

				} else if (aux.compareToIgnoreCase("deshacer") == 0) {
					partida.undo();
					
				} else if (aux.compareToIgnoreCase("ayuda") == 0) {

					System.out.println(Constants.MensajeAyuda);
				} else
					partida.instruccionInvalida(s);

			} else if (st.countTokens() == 1) {

				if (aux.compareToIgnoreCase("jugar") == 0) {
					String ju = st.nextToken();

					if (ju.compareToIgnoreCase("c4") == 0) {
						f = new FactoriaConecta4();
						cambiaJuego(f);
					} else if (ju.compareToIgnoreCase("co") == 0) {
						f = new FactoriaComplica();
						cambiaJuego(f);
					} else if (ju.compareToIgnoreCase("gr") == 0) {
						f = new FactoriaGravity();
						cambiaJuego(f);
					}else if (ju.compareToIgnoreCase("rv") == 0) {
						f = new FactoriaReversi();
						cambiaJuego(f);
					}
					
				} else
					partida.instruccionInvalida(s);

			} else if (st.countTokens() == 2) {

				if (aux.compareToIgnoreCase("jugador") == 0) {
					String color = st.nextToken();
					if (color.compareToIgnoreCase("blancas") == 0) {
						
						String ju = st.nextToken();
						if (ju.compareToIgnoreCase("humano") == 0) {
							iablanca=false;
							this.blanco=jugadorPorJuego(iablanca);

						} else if (ju.compareToIgnoreCase("aleatorio") == 0)
							iablanca=true;
							this.blanco=jugadorPorJuego(iablanca);

					} else if (color.compareToIgnoreCase("negras") == 0) {
						String ju = st.nextToken();
						if (ju.compareToIgnoreCase("humano") == 0) {
							ianegra=false;
							this.negro=jugadorPorJuego(ianegra);

						} else if (ju.compareToIgnoreCase("aleatorio") == 0)
							ianegra=true;
							this.negro = jugadorPorJuego(ianegra);
							

					} else
						partida.instruccionInvalida(s);
				} else
					partida.instruccionInvalida(s);

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
							partida.instruccionInvalida(s);
					}
				} else
					partida.instruccionInvalida(s);
			} else
				partida.instruccionInvalida(s);
		}

	}

	private Jugador jugadorQueToca() {
		Jugador j = null;
		if (partida.getTurno()==Ficha.BLANCA){
			j= blanco;
		}if (partida.getTurno()==Ficha.NEGRA){
			j= negro;
		}
		return j;
		
	}


	/*
	 * reinicio de juego
	 */

	private void reset() {
		partida.reset(reglas);

	}

	private void cambiaJuego(FactoriaTipoJuego f) {
		reglas=f.creaReglas();
		partida.reset(reglas);
		blanco=jugadorPorJuego(iablanca);
		negro=jugadorPorJuego(ianegra);

		

	}

	private Jugador jugadorPorJuego(boolean ia){
		Jugador j = null;
		switch (reglas.getTipo()){
		
			case CONECTA4: 	{
							if (!ia){
								j= new JugadorHumanoConecta4(in);
							}
							else j=new JugadorAleatorioConecta4();
							break;
							}
			case COMPLICA: 	{
							if (!ia){
								j=new JugadorHumanoComplica(in);
							}
							else j= new JugadorAleatorioComplica();
							break;
							}
		
			case GRAVITY: 	{
							if (!ia){
								j=new JugadorHumanoGravity(in);
							}
							else j= new JugadorAleatorioGravity();
							break;
							}
		}
		
		return j;
	}

	public void addObserver(Observer o) {
		partida.addObserver(o);

	}
	
	public void inicio() {
		partida.inicio();
		
	}
}
