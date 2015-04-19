package tp.pr3.logica.test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import tp.pr3.control.FactoriaGravity;
import tp.pr3.control.Jugador;
import tp.pr3.control.JugadorAleatorioGravity;
import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoGravity;
import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasGravity;
import tp.pr3.logica.Tablero;

public class FactoriaGravityTest {
	
	FactoriaGravity f;
	
	@Before
	public void init() {
		f = new FactoriaGravity(11,15);
	}
	
	@Test
	public void testInterfaz() {
		ReglasGravity r = (ReglasGravity) f.creaReglas();
		assertNotNull("La FactoriaGravity no devuelve una reglas válida de gravity", r);
		
		MovimientoGravity m = (MovimientoGravity) f.creaMovimiento(1, 1, Ficha.BLANCA);
		assertNotNull("La FactoriaGravity no devuelve un movimiento válido de gravity", m);
		
		JugadorAleatorioGravity ja = (JugadorAleatorioGravity) f.creaJugadorAleatorio();
		assertNotNull("La FactoriaGravity no devuelve un jugador aleatorio válido de gravity", ja);
		
		Scanner sc = new Scanner(System.in);
		Jugador jh = f.creaJugadorHumanoConsola(sc);
		assertNotNull("La FactoriaGravity no devuelve un jugador humano válido de gravity", jh);
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
