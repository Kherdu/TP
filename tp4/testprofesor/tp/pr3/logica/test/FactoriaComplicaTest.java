package tp.pr3.logica.test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import tp.pr3.control.FactoriaComplica;
import tp.pr3.control.Jugador;
import tp.pr3.control.JugadorAleatorioComplica;
import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoComplica;
import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasComplica;
import tp.pr3.logica.Tablero;

public class FactoriaComplicaTest {
	
	FactoriaComplica f;
	
	@Before
	public void init() {
		f = new FactoriaComplica();
	}
	
	@Test
	public void testInterfaz() {
		ReglasComplica r = (ReglasComplica) f.creaReglas();
		assertNotNull("La FactoriaComplica no devuelve una reglas válida de complica", r);
		
		MovimientoComplica m = (MovimientoComplica) f.creaMovimiento(1, 1, Ficha.BLANCA);
		assertNotNull("La FactoriaComplica no devuelve un movimiento válido de complica", m);
		
		JugadorAleatorioComplica ja = (JugadorAleatorioComplica) f.creaJugadorAleatorio();
		assertNotNull("La FactoriaComplica no devuelve un jugador aleatorio válido de complica", ja);
		
		Scanner sc = new Scanner(System.in);
		Jugador jh = f.creaJugadorHumanoConsola(sc);
		assertNotNull("La FactoriaComplica no devuelve un jugador humano válido de complica", jh);
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
