package tp.pr3.logica.test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import tp.pr3.control.FactoriaConecta4;
import tp.pr3.control.Jugador;
import tp.pr3.control.JugadorAleatorioConecta4;
import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoConecta4;
import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasConecta4;
import tp.pr3.logica.Tablero;

public class FactoriaConecta4Test {

	FactoriaConecta4 f;

	@Before
	public void init() {
		f = new FactoriaConecta4();
	}

	@Test
	public void testInterfaz() {
		ReglasConecta4 r = (ReglasConecta4) f.creaReglas();
		assertNotNull("La FactoriaConecta4 no devuelve una reglas válida de conecta 4", r);

		MovimientoConecta4 m = (MovimientoConecta4) f.creaMovimiento(1, 1, Ficha.BLANCA);
		assertNotNull("La FactoriaConecta4 no devuelve un movimiento válido de conecta 4", m);

		JugadorAleatorioConecta4 ja = (JugadorAleatorioConecta4) f.creaJugadorAleatorio();
		assertNotNull("La FactoriaConecta4 no devuelve un jugador aleatorio válido de conecta 4", ja);

		Scanner sc = new Scanner(System.in);
		Jugador jh = f.creaJugadorHumanoConsola(sc);
		assertNotNull("La FactoriaConecta4 no devuelve un jugador humano válido de conecta 4", jh);
	}

	@Test
	public void testJugadorAleatorio() throws MovimientoInvalido {

		// Jugar 50 partidas aleatorias
		for (int i=0; i<50; i++) {
			
			Partida p = new Partida(f.creaReglas());
			Tablero t = p.getTablero();
			Jugador[] js = new Jugador[2];
			js[0] = f.creaJugadorAleatorio();
			js[1] = f.creaJugadorAleatorio();

			int k=0;
			while (!p.isTerminada()) {
				Movimiento m = js[k].getMovimiento(t, p.getTurno());
				p.ejecutaMovimiento(m);
				k = (k + 1) % 2;
			}
		}
	}
}
