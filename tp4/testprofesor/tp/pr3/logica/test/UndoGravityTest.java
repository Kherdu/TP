package tp.pr3.logica.test;

import java.util.Stack;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoGravity;
import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasGravity;
import tp.pr3.logica.ReglasJuego;
import tp.pr3.logica.Tablero;

public class UndoGravityTest {
	
	protected ReglasJuego r;
	
	@Before
	public void init() {
		r = getReglas(10,10);
	}
	
	protected ReglasJuego getReglas(int columnas, int filas) {
		return new ReglasGravity(columnas, filas);
	}
	
	protected Movimiento getMovimiento(int columna, int fila, Ficha color) {
		return new MovimientoGravity(columna, fila, color);
	}
	
	@Test
	public void testUndoTrasMovimiento() throws MovimientoInvalido {
		Partida p = new Partida(r);
		
		p.ejecutaMovimiento(getMovimiento(1, 1, Ficha.BLANCA));
		assertTrue("Tras un movimiento, undo() debería funcionar", p.undo());
		assertTrue("Al hacer undo() tras un movimiento, el tablero debe quedar vacío.", Utils.tableroVacio(p.getTablero()));
		assertEquals("Al hacer undo() tras un movimiento, debe ser turno de las blancas.", Ficha.BLANCA, p.getTurno());
		assertFalse("Al hacer undo() tras un movimiento, la partida no ha debido terminar.", p.isTerminada());
	}
	
	@Test
	public void testUndo() throws MovimientoInvalido {
		Partida p = new Partida(r);
		Tablero t = p.getTablero();
		
		Stack<Tablero> pila = new Stack<Tablero>();
		for (int x=1; x<=t.getAncho(); x++) {
			pila.push(Utils.copiaTablero(t));

			p.ejecutaMovimiento(getMovimiento(x, 2, p.getTurno()));
		}
		
		for (int i=0; i<Math.min(t.getAncho(), 10); i++) {
			assertTrue("undo() debería poder ejecutarse pero devuelve false", p.undo());
			assertTrue("undo() no deja el tablero como estaba", Utils.TablerosIguales(t, pila.pop()));
		}
		
		
		pila.push(Utils.copiaTablero(t));
		p.ejecutaMovimiento(getMovimiento(9, 2, p.getTurno()));
		assertTrue("undo() debería poder ejecutarse pero devuelve false", p.undo());
		assertTrue("undo() no deja el tablero como estaba", Utils.TablerosIguales(t, pila.pop()));
	}
	

	@Test
	public void testUndoMuchasVeces() throws MovimientoInvalido {
		Partida p = new Partida(r);
		Tablero t = p.getTablero();
		
		Stack<Tablero> pila = new Stack<Tablero>();
		
		for (int y=1; y<=3; y++) {
			for (int x=1; x<=t.getAncho(); x++) {
				if (t.getCasilla(x, y) == Ficha.VACIA) {
					pila.push(Utils.copiaTablero(t));
					p.ejecutaMovimiento(getMovimiento(x, y, p.getTurno()));
				}
			}
		}
		
		for (int y=t.getAlto(); y>t.getAlto()-3; y--) {
			for (int x=1; x<=t.getAncho(); x++) {
				if (t.getCasilla(x, y) == Ficha.VACIA) {
					pila.push(Utils.copiaTablero(t));
					p.ejecutaMovimiento(getMovimiento(x, y, p.getTurno()));
				}
			}
		}
				
		for (int i=0; i<10; i++) {
			assertTrue("undo() debería poder ejecutarse pero devuelve false", p.undo());
			assertTrue("undo() no deja el tablero como estaba cuando se ha hecho más de 10 movimientos", Utils.TablerosIguales(t, pila.pop()));
		}
	}
	
	@Test
	public void testNoUndoTrasReset() throws MovimientoInvalido {
		Partida p = new Partida(r);
		
		p.ejecutaMovimiento(getMovimiento(3, 1, p.getTurno()));
		p.reset(getReglas(10,10));
		assertFalse("Tras reset, undo() no debe funcionar.", p.undo());
	}
}
