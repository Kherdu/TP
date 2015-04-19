package tp.pr3.logica.test;

import org.junit.*;

import static org.junit.Assert.*;
import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoGravity;
import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasGravity;
import tp.pr3.logica.ReglasJuego;
import tp.pr3.logica.Tablero;

public class PartidaGravityTest {
	
	private Partida p;
	
	@Before
	public void init() {
		p = new Partida(getReglas());
	}
	
	protected ReglasJuego getReglas() {
		return new ReglasGravity(10, 15);
	}
	
	protected Movimiento getMovimiento(int columna, int fila, Ficha color) {
		return new MovimientoGravity(columna, fila, color);
	}
	
	protected void reset() {
		p.reset(getReglas());
	}
	
	@Test
	public void testCtor() {
		reset();
		
		assertFalse("Una partida recien empezada no debería estar terminada", p.isTerminada());
		assertEquals("Las partidas las empiezan siempre las blancas.", Ficha.BLANCA, p.getTurno());
		assertEquals("El tablero del juego es de 10x15", 10, p.getTablero().getAncho());
		assertEquals("El tablero del juego es de 10x15", 15, p.getTablero().getAlto());
		assertFalse("Al principio de la partida no hay nada que deshacer.", p.undo());
	}
	
	@Test
	public void testEjecutaMovimientoSimple() throws MovimientoInvalido {
		reset();
		
		p.ejecutaMovimiento(getMovimiento(1, 1, Ficha.BLANCA));
		assertEquals("Tras colocar BLANCA en la casilla (1, 1) del tablero en esa casilla no hay blancas",
				Ficha.BLANCA,
				p.getTablero().getCasilla(1,  1));
		assertFalse("Tras un movimiento, la partida no debería haber terminado.", p.isTerminada());
		assertEquals("Después de las blancas, juegan las negras.", Ficha.NEGRA, p.getTurno());
	}
	
	@Test(expected=MovimientoInvalido.class)
	public void testEjecutaMovimientoInvalido1() throws MovimientoInvalido {
		reset();
		
		Ficha ficha = Utils.contraria(p.getTurno());
		p.ejecutaMovimiento(getMovimiento(2, 2, ficha));
		fail("ejecutaMovimiento() no debe admitir movimiento de ficha que no tiene el turno.");
	}
	
	@Test
	public void testEjecutaMovimientoInvalido2() throws MovimientoInvalido {
		reset();
		Tablero t = p.getTablero();
		
		for (int y=1; y<=3; y++) {
			for (int x=1+y-1; x<=t.getAncho()-y+1; x++) {
				p.ejecutaMovimiento(getMovimiento(x, y, p.getTurno()));
				try {
					p.ejecutaMovimiento(getMovimiento(x, y, p.getTurno()));;
					fail("ejecutaMovimiento() debe fallar si se coloca una ficha en una posición ocupada.");
				} catch (MovimientoInvalido e) { }
			}
		}
	}
	
	@Test
	public void testEjecutaMovimientoInvalido3() {
		reset();
		Tablero t = p.getTablero();
		
		for (int x=-20; x<=20; x++) {
			for (int y=-20; y<=20; y++) { 
				if ((x < 1) || (x > t.getAncho()) || (y < 1) || (y > t.getAlto())) {
					try {
						p.ejecutaMovimiento(getMovimiento(x, y, Ficha.BLANCA));
						fail("ejecutaMovimiento() debe fallar si se coloca una ficha fuera del tablero.");
					} catch(MovimientoInvalido e) { }
				}
			}
		}
	}

	@Test
	public void testEjecutaMovimientoInvalido4() throws MovimientoInvalido {
		reset();
		Tablero t = p.getTablero();
		
		p.ejecutaMovimiento(getMovimiento(3, 2, Ficha.BLANCA));		
		assertEquals("Tras colocar en la casilla (3, 2) del tablero, la casilla (3, 1) deberia estar ocupada por las blancas",
				Ficha.BLANCA, t.getCasilla(3,  1));
		
		try
		{
			p.ejecutaMovimiento(getMovimiento(3, 1, Ficha.NEGRA)); 		
			fail("ejecuta movimiento debe fallar porque la casilla (3,1) ya está ocupada");
		}
		catch (MovimientoInvalido e) {	 }

		
		p.ejecutaMovimiento(getMovimiento(3, 3, Ficha.NEGRA));
		assertEquals("Tras colocar en la casilla (3, 3) del tablero, la casilla (1, 1) deberia estar ocupada por las negras",
				Ficha.NEGRA, t.getCasilla(1,  1));
		
	
		p.ejecutaMovimiento(getMovimiento(3, 14, Ficha.BLANCA));		
		assertEquals("Tras colocar en la casilla (3, 14) del tablero, la casilla (3, 15) deberia estar ocupada por las blancas",
				Ficha.BLANCA, t.getCasilla(3,  15));
		
		try
		{
			p.ejecutaMovimiento(getMovimiento(3, 15, Ficha.NEGRA)); 		
			fail("ejecuta movimiento debe fallar porque la casilla (3,15) ya está ocupada");
		}
		catch (MovimientoInvalido e) {	 }
	}
	
	
	
	@Test
	public void persistenciaTablero() throws MovimientoInvalido {
		// Comprobación que no está en la documentación pero de implementación
		// de sentido común (y, dicho sea de paso, que nos permite tomar atajos
		// en los test del cuatro en raya).
		Tablero t = p.getTablero();
		p.ejecutaMovimiento(getMovimiento(3, 15, Ficha.BLANCA));
		assertTrue("No se debe cambiar el objeto tablero en medio de una partida (solo admitido si se llama a reset()).",
				t == p.getTablero());
		assertEquals("Tras colocar en la casilla (3, 15) del tablero deberia estar ocupada por las blancas",
				Ficha.BLANCA,
				t.getCasilla(3,  15));
	}
	
	@Test
	public void partidaEnTablas() throws MovimientoInvalido {
		p.reset(new ReglasGravity(4, 4));
		
		for (int y=1; y<=2; y++) {
			for (int x=1; x<=4; x++) {
				p.ejecutaMovimiento(getMovimiento(x, y, p.getTurno()));
				assertFalse("La partida no puede terminar si no hay tablas ni ganador.", p.isTerminada());
			}
		}
		
		for (int y=4; y>=3; y--) {
			for (int x=4; x>=1; x--) {
				if ((x==1) && (y==3))
					continue;
				
				p.ejecutaMovimiento(getMovimiento(x, y, p.getTurno()));
				assertFalse("La partida no puede terminar si no hay tablas ni ganador.", p.isTerminada());
			}
		}
		
		p.ejecutaMovimiento(getMovimiento(1, 3, p.getTurno()));
		assertTrue("Partida con tablero completo debe ser tablas.", p.isTerminada());
		assertEquals("Las partidas en tablas no las gana nadie.", Ficha.VACIA, p.getGanador());	
	}
	
	@Test
	public void testReset1() throws MovimientoInvalido {
		p = new Partida(getReglas());
		
		p.ejecutaMovimiento(getMovimiento(3, 1, Ficha.BLANCA));
		reset();
		assertTrue("Tras reset, el tablero debe quedar vacio", Utils.tableroVacio(p.getTablero()));
		assertEquals("Tras reset, el turno debe ser de las blancas", Ficha.BLANCA, p.getTurno());

	}
}
