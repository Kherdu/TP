package tp.pr3.logica.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.ReglasGravity;
import tp.pr3.logica.ReglasJuego;
import tp.pr3.logica.Tablero;

public class ReglasGravityTest {
	
	protected ReglasJuego r;
	
	@Before
	public void init() {
		r = getReglas();
	}
	
	protected ReglasJuego getReglas() {
		return new ReglasGravity(20,10);
	}

	@Test
	public void testIniciaTablero() {
		Tablero t = r.iniciaTablero();
		assertEquals("El tablero no tiene el ancho adecuado", 20, t.getAncho());
		assertEquals("El tablero no tiene el alto adecuado", 10, t.getAlto());
		assertTrue("El tablero debe empezar vacío", Utils.tableroVacio(t));
	}
	
	@Test
	public void testJugadorInicial() {
		assertEquals("Deberían empezar juango blancas", Ficha.BLANCA, r.jugadorInicial());
	}
	
	@Test
	public void testSiguienteTurno() {
		Tablero t = r.iniciaTablero();
		
		// Independiente del estado
		assertEquals("Después de blancas deberían jugar negras", Ficha.NEGRA, r.siguienteTurno(Ficha.BLANCA, t));
		assertEquals("Después de blancas deberían jugar negras", Ficha.NEGRA, r.siguienteTurno(Ficha.BLANCA, t));
		assertEquals("Después de negras deberían jugar blancas", Ficha.BLANCA, r.siguienteTurno(Ficha.NEGRA, t));
		assertEquals("Después de negras deberían jugar blancas", Ficha.BLANCA, r.siguienteTurno(Ficha.NEGRA, t));
		
		// Independiente del tablero
		Ficha ficha = Ficha.NEGRA;
		for (int x=t.getAncho(); x>=1; x--) {
			for (int y=t.getAlto(); y>=1; y--) {
				ficha = Utils.contraria(ficha);
				t.setCasilla(x, y, ficha);
				assertEquals("Después de negras deberían jugar blancas", Ficha.NEGRA, r.siguienteTurno(Ficha.BLANCA, t));
				assertEquals("Después de negras deberían jugar blancas", Ficha.BLANCA, r.siguienteTurno(Ficha.NEGRA, t));
			}
		}
	}
}
