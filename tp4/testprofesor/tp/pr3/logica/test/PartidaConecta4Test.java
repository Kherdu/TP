package tp.pr3.logica.test;

import org.junit.*;

import static org.junit.Assert.*;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoConecta4;
import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasConecta4;
import tp.pr3.logica.ReglasJuego;
import tp.pr3.logica.Tablero;

public class PartidaConecta4Test {
	
	private Partida p;
	
	@Before
	public void init() {
		p = new Partida(getReglas());
	}
	
	private ReglasJuego getReglas() {
		return new ReglasConecta4();
	}
	
	private Movimiento getMovimiento(int donde, Ficha color) {
		return new MovimientoConecta4(donde, color);
	}
	
	@Test
	public void testCtor() {
		assertFalse("Una partida recien empezada no debería estar terminada", p.isTerminada());
		assertEquals("Las partidas las empiezan siempre las blancas.", Ficha.BLANCA, p.getTurno());
		assertEquals("El tablero del juego es de 7x6", 7, p.getTablero().getAncho());
		assertEquals("El tablero del juego es de 7x6", 6, p.getTablero().getAlto());
		assertFalse("Al principio de la partida no hay nada que deshacer.", p.undo());
	}
	
	@Test
	public void testEjecutaMovimientoSimple() throws MovimientoInvalido {
		p.ejecutaMovimiento(getMovimiento(1, Ficha.BLANCA));
		assertEquals("Tras colocar en la columna 1, la casilla (1, 6) del tablero deberia estar ocupada por las blancas",
				Ficha.BLANCA,
				p.getTablero().getCasilla(1,  6));
		assertFalse("Tras un movimiento, la partida no debería haber terminado.", p.isTerminada());
		assertEquals("Después de las blancas, juegan las negras.", Ficha.NEGRA, p.getTurno());
	}
	
	@Test(expected=MovimientoInvalido.class)
	public void testEjecutaMovimientoInvalido1() throws MovimientoInvalido {
		p.ejecutaMovimiento(getMovimiento(1, Ficha.NEGRA));
		fail("ejecutaMovimiento() no debe admitir movimiento de ficha que no tiene el turno");
	}
	
	@Test
	public void testEjecutaMovimientoInvalido2() throws MovimientoInvalido {
		p.ejecutaMovimiento(getMovimiento(3, Ficha.BLANCA));
		p.ejecutaMovimiento(getMovimiento(3, Ficha.NEGRA));
		p.ejecutaMovimiento(getMovimiento(3, Ficha.BLANCA));
		p.ejecutaMovimiento(getMovimiento(3, Ficha.NEGRA));
		p.ejecutaMovimiento(getMovimiento(3, Ficha.BLANCA));
		p.ejecutaMovimiento(getMovimiento(3, Ficha.NEGRA));
		try {
			p.ejecutaMovimiento(getMovimiento(3, Ficha.BLANCA));
			fail("ejecutaMovimiento() debe fallar con columna llena.");
		} catch(MovimientoInvalido e) { }
	}
	
	@Test
	public void testEjecutaMovimientoInvalido3() {
		for (int x = -10; x <= 10; ++x) {
			if ((1 <= x) && (x <= 7)) continue;
			try {
				p.ejecutaMovimiento(getMovimiento(x, Ficha.BLANCA));
				fail("ejecutaMovimiento() debe fallar con columna invalida.");
			} catch(MovimientoInvalido e) { }
		}
	}
	
	@Test
	public void persistenciaTablero() throws MovimientoInvalido {
		// Comprobación que no está en la documentación pero de implementación
		// de sentido común (y, dicho sea de paso, que nos permite tomar atajos
		// en los test del cuatro en raya).
		Tablero t = p.getTablero();
		p.ejecutaMovimiento(getMovimiento(3, Ficha.BLANCA));
		assertTrue("No se debe cambiar el objeto tablero en medio de una partida (solo admitido si se llama a reset()).",
				t == p.getTablero());
		assertEquals("Tras colocar en la columna 3, la casilla (3, 6) del tablero deberia estar ocupada por las blancas",
				Ficha.BLANCA,
				t.getCasilla(3,  6));
	}
	
	
	@Test
	public void partidaEnTablas() throws MovimientoInvalido {
		for (int x = 1; x <= 7; ++x) {
			if (x == 4) continue;
			for (int i = 0; i < 6; ++i) {
				if ((x == 7) && (i == 5)) continue;
				p.ejecutaMovimiento(getMovimiento(x, p.getTurno()));
			}
		}
		
		for (int i = 0; i < 6; ++i) {
			p.ejecutaMovimiento(getMovimiento(4, p.getTurno()));
		}
		
		p.ejecutaMovimiento(getMovimiento(7, p.getTurno()));

		assertTrue("Partida con tablero completo debe ser tablas.", p.isTerminada());
		assertEquals("Las partidas en tablas no las gana nadie.", Ficha.VACIA, p.getGanador());
		
		for (int i = 1; i <= 7; ++i) {
			try {
				p.ejecutaMovimiento(getMovimiento(i, p.getTurno()));
				fail("Tras partida en tablas, no se puede poner.");
			} catch(MovimientoInvalido e) {}
		}
	}
	
	@Test
	public void testReset1() throws MovimientoInvalido {
		
		p.ejecutaMovimiento(getMovimiento(3, Ficha.BLANCA));
		p.reset(getReglas());
		assertTrue("Tras reset, el tablero debe quedar vacio", Utils.tableroVacio(p.getTablero()));
		assertEquals("Tras reset, el turno debe ser de las blancas", Ficha.BLANCA, p.getTurno());

	}
}
