package tp.pr3.logica.test;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoGravity;
import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.ReglasGravity;
import tp.pr3.logica.ReglasJuego;
import tp.pr3.logica.Tablero;

public class MovimientoGravityTest {
	
	protected ReglasJuego r;
	
	@Before
	public void init() {
		r = getReglas(10,10);  
	}
	
	protected Movimiento getMovimiento(int columna, int fila, Ficha color) {
		return new MovimientoGravity(columna, fila, color);
	}
	
	protected ReglasJuego getReglas(int columnas, int filas) {
		return new ReglasGravity(columnas, filas);
	}
	
	@Test
	public void testGetJugador() {
		Movimiento mov = getMovimiento(1, 1,Ficha.BLANCA);
		assertEquals("getJugador() no devuelve el jugador con el que fue creado el movimiento", mov.getJugador(), Ficha.BLANCA);
		
		mov = getMovimiento(1, 1, Ficha.NEGRA);
		assertEquals("getJugador() no devuelve el jugador con el que fue creado el movimiento", mov.getJugador(), Ficha.NEGRA);
	}
	
	// Movimientos con posiciones dentro del tablero
	@Test
	public void testEjecutaMovimientoDentro() {
		Tablero t = r.iniciaTablero(); 

		// Poner fichas hasta que el tablero se llene
		int puestas = 0;
		while (puestas < t.getAlto()*t.getAlto()) {
			
			for (int x=1; x<=t.getAncho(); x++)
				for (int y=1; y<=t.getAlto(); y++) {
					
					Movimiento mov = getMovimiento(x, y, Ficha.BLANCA);
					
					if (t.getCasilla(x, y) == Ficha.VACIA) {
						// Se puede poner
						puestas++;	
						try {
							mov.ejecutaMovimiento(t);
						} catch(MovimientoInvalido e) {
							fail("ejecutaMovimiento() no debe fallar cuando se coloca la ficha en una posición válida");
						}
					} else {
						
						// No se puede poner
						try {
							mov.ejecutaMovimiento(t);
							fail("ejecutaMovimiento() debe fallar cuando se coloca la ficha en una posición ocupada");
						} catch(MovimientoInvalido e) { }
					}
					
			}
		}
		
		
	}
	
	// Movimientos posiciones fuera del tablero
	@Test
	public void testEjecutaMovimientoFuera() {
		Tablero t = r.iniciaTablero(); 

		for (int x=-20; x<=20; x++)
			for (int y=-20; y<=20; y++) {
				if ((x < 1) || (x > t.getAncho()) || (y < 1) || (y > t.getAlto())) {			
					try {
						Movimiento mov = getMovimiento(x, y, Ficha.BLANCA);
						mov.ejecutaMovimiento(t);
						fail("ejecutaMovimiento() debe fallar cuando se coloca la ficha en una posición incorrecta");
					} catch(MovimientoInvalido e) {	}
			}
		}
	}
	
	
	// Colocar ficha en (x,y) y debería caer hasta (x2,y2)
	private void coloca(Tablero t, int x, int y, int x2, int y2) throws MovimientoInvalido {
		Ficha f = Ficha.BLANCA;
		Movimiento m = getMovimiento(x, y, f);
		assertTrue(t.getCasilla(x2, y2) == Ficha.VACIA);
		
		m.ejecutaMovimiento(t);
		assertTrue(t.getCasilla(x2, y2) == f);
	}
	
	@Test
	public void testMovimientoGravedad1() throws MovimientoInvalido {
		ReglasJuego reglasAntiguas = r; 
		r = new ReglasGravity(8, 8);
		
		Tablero t = r.iniciaTablero();
		assertTrue(Utils.tableroVacio(t));
		
		// diag1
		coloca(t, 4, 4, 1, 1);
		coloca(t, 4, 4, 2, 2);
		coloca(t, 4, 4, 3, 3);
		coloca(t, 4, 4, 4, 4);
		
		// diag2
		coloca(t, 5, 4, 8, 1);
		coloca(t, 5, 4, 7, 2);
		coloca(t, 5, 4, 6, 3);
		coloca(t, 5, 4, 5, 4);
		
		// diag3
		coloca(t, 4, 5, 1, 8);
		coloca(t, 4, 5, 2, 7);
		coloca(t, 4, 5, 3, 6);
		coloca(t, 4, 5, 4, 5);
		
		// diag4
		coloca(t, 5, 5, 8, 8);
		coloca(t, 5, 5, 7, 7);
		coloca(t, 5, 5, 6, 6);
		coloca(t, 5, 5, 5, 5);
		
		// arriba
		coloca(t, 4, 3, 4, 1);
		coloca(t, 4, 3, 4, 2);
		coloca(t, 4, 3, 4, 3);
		coloca(t, 3, 2, 3, 1);
		coloca(t, 3, 2, 3, 2);
		coloca(t, 2, 1, 2, 1);		
		coloca(t, 5, 3, 5, 1);
		coloca(t, 5, 3, 5, 2);
		coloca(t, 5, 3, 5, 3);
		coloca(t, 6, 2, 6, 1);
		coloca(t, 6, 2, 6, 2);
		coloca(t, 7, 1, 7, 1);
		
		// abajo
		coloca(t, 4, 6, 4, 8);
		coloca(t, 4, 6, 4, 7);
		coloca(t, 4, 6, 4, 6);
		coloca(t, 3, 7, 3, 8);
		coloca(t, 3, 7, 3, 7);
		coloca(t, 2, 8, 2, 8);		
		coloca(t, 5, 6, 5, 8);
		coloca(t, 5, 6, 5, 7);
		coloca(t, 5, 6, 5, 6);
		coloca(t, 6, 7, 6, 8);
		coloca(t, 6, 7, 6, 7);
		coloca(t, 7, 8, 7, 8);
		
		// izquierda
		coloca(t, 3, 4, 1, 4);
		coloca(t, 3, 4, 2, 4);
		coloca(t, 3, 4, 3, 4);
		coloca(t, 2, 3, 1, 3);
		coloca(t, 2, 3, 2, 3);
		coloca(t, 1, 2, 1, 2);		
		coloca(t, 3, 5, 1, 5);
		coloca(t, 3, 5, 2, 5);
		coloca(t, 3, 5, 3, 5);
		coloca(t, 2, 6, 1, 6);
		coloca(t, 2, 6, 2, 6);
		coloca(t, 1, 7, 1, 7);
		
		// derecha
		coloca(t, 6, 4, 8, 4);
		coloca(t, 6, 4, 7, 4);
		coloca(t, 6, 4, 6, 4);
		coloca(t, 7, 3, 8, 3);
		coloca(t, 7, 3, 7, 3);
		coloca(t, 8, 2, 8, 2);		
		coloca(t, 6, 5, 8, 5);
		coloca(t, 6, 5, 7, 5);
		coloca(t, 6, 5, 6, 5);
		coloca(t, 7, 6, 8, 6);
		coloca(t, 7, 6, 7, 6);
		coloca(t, 8, 7, 8, 7);
		
		r = reglasAntiguas;
	}
	
	@Test
	public void testMovimientoGravedad2() throws MovimientoInvalido {
		ReglasJuego reglasAntiguas = r; 
		r = new ReglasGravity(7, 7);
		
		Tablero t = r.iniciaTablero();
		assertTrue(Utils.tableroVacio(t));
		
		// equilibrio
		coloca(t, 4, 4, 4, 4);
		
		// diag1
		coloca(t, 3, 3, 1, 1);
		coloca(t, 3, 3, 2, 2);
		coloca(t, 3, 3, 3, 3);
		
		// diag2
		coloca(t, 5, 3, 7, 1);
		coloca(t, 5, 3, 6, 2);
		coloca(t, 5, 3, 5, 3);
		
		// diag3
		coloca(t, 3, 5, 1, 7);
		coloca(t, 3, 5, 2, 6);
		coloca(t, 3, 5, 3, 5);
		
		// diag4
		coloca(t, 5, 5, 7, 7);
		coloca(t, 5, 5, 6, 6);
		coloca(t, 5, 5, 5, 5);
		
		// arriba
		coloca(t, 4, 3, 4, 1);
		coloca(t, 4, 3, 4, 2);
		coloca(t, 4, 3, 4, 3);
		coloca(t, 3, 2, 3, 1);
		coloca(t, 3, 2, 3, 2);
		coloca(t, 2, 1, 2, 1);	
		coloca(t, 5, 2, 5, 1);
		coloca(t, 5, 2, 5, 2);
		coloca(t, 6, 1, 6, 1);
		
		// abajo
		coloca(t, 4, 5, 4, 7);
		coloca(t, 4, 5, 4, 6);
		coloca(t, 4, 5, 4, 5);
		coloca(t, 3, 6, 3, 7);
		coloca(t, 3, 6, 3, 6);
		coloca(t, 2, 7, 2, 7);	
		coloca(t, 5, 6, 5, 7);
		coloca(t, 5, 6, 5, 6);	
		coloca(t, 6, 7, 6, 7);
		
		
		// izquierda
		coloca(t, 3, 4, 1, 4);
		coloca(t, 3, 4, 2, 4);
		coloca(t, 3, 4, 3, 4);
		coloca(t, 2, 3, 1, 3);
		coloca(t, 2, 3, 2, 3);
		coloca(t, 1, 2, 1, 2);
		coloca(t, 2, 5, 1, 5);
		coloca(t, 2, 5, 2, 5);
		coloca(t, 1, 6, 1, 6);
		
		// derecha
		coloca(t, 5, 4, 7, 4);
		coloca(t, 5, 4, 6, 4);
		coloca(t, 5, 4, 5, 4);
		coloca(t, 6, 3, 7, 3);
		coloca(t, 6, 3, 6, 3);
		coloca(t, 7, 2, 7, 2);
		coloca(t, 6, 5, 7, 5);
		coloca(t, 6, 5, 6, 5);
		coloca(t, 7, 6, 7, 6);
		
		r = reglasAntiguas;
	}
	
	@Test
	public void testMovimientoGravedad3() throws MovimientoInvalido {
		ReglasJuego reglasAntiguas = r; 
		r = new ReglasGravity(7, 5);
		
		Tablero t = r.iniciaTablero();
		assertTrue(Utils.tableroVacio(t));
		
		// equilibrio
		coloca(t, 4, 3, 4, 3);
		
		// diag1
		coloca(t, 2, 2, 1, 1);
		coloca(t, 2, 2, 2, 2);
		
		// diag2
		coloca(t, 6, 2, 7, 1);
		coloca(t, 6, 2, 6, 2);
		
		// diag3
		coloca(t, 2, 4, 1, 5);
		coloca(t, 2, 4, 2, 4);
		
		// diag4
		coloca(t, 6, 4, 7, 5);
		coloca(t, 6, 4, 6, 4);
		
		// arriba
		coloca(t, 2, 1, 2, 1);
		coloca(t, 3, 2, 3, 1);
		coloca(t, 3, 2, 3, 2);
		coloca(t, 4, 2, 4, 1);
		coloca(t, 4, 2, 4, 2);
		coloca(t, 5, 2, 5, 1);
		coloca(t, 5, 2, 5, 2);
		coloca(t, 6, 1, 6, 1);
		
		// abajo
		coloca(t, 2, 5, 2, 5);
		coloca(t, 3, 4, 3, 5);
		coloca(t, 3, 4, 3, 4);
		coloca(t, 4, 4, 4, 5);
		coloca(t, 4, 4, 4, 4);
		coloca(t, 5, 4, 5, 5);
		coloca(t, 5, 4, 5, 4);
		coloca(t, 6, 5, 6, 5);
		
		// izquierda
		coloca(t, 1, 2, 1, 2);
		coloca(t, 3, 3, 1, 3);
		coloca(t, 3, 3, 2, 3);
		coloca(t, 3, 3, 3, 3);
		coloca(t, 1, 4, 1, 4);
		
		// derecha
		coloca(t, 7, 2, 7, 2);
		coloca(t, 5, 3, 7, 3);
		coloca(t, 5, 3, 6, 3);
		coloca(t, 5, 3, 5, 3);
		coloca(t, 7, 4, 7, 4);
		
		r = reglasAntiguas;
	}


	@Test
	public void testUndo() throws MovimientoInvalido {
		Tablero t = r.iniciaTablero();
		
		Stack<Tablero> tableros = new Stack<>();
		Stack<Movimiento> movimientos = new Stack<>();
		
		// Hacer movimientos
		for (int i=1; i<=5; i++) {
			tableros.push(Utils.copiaTablero(t));
			Movimiento m = getMovimiento(5, 5, Ficha.NEGRA);
			movimientos.push(m);
			m.ejecutaMovimiento(t);
		}
		
		for (int i=1; i<=5; i++) {
			tableros.push(Utils.copiaTablero(t));
			Movimiento m = getMovimiento(6, 6, Ficha.BLANCA);
			movimientos.push(m);
			m.ejecutaMovimiento(t);
		}
		
		for (int i=1; i<=5; i++) {
			tableros.push(Utils.copiaTablero(t));
			Movimiento m = getMovimiento(5, 6, Ficha.NEGRA);
			movimientos.push(m);
			m.ejecutaMovimiento(t);
		}
		
		for (int i=1; i<=5; i++) {
			tableros.push(Utils.copiaTablero(t));
			Movimiento m = getMovimiento(6, 5, Ficha.BLANCA);
			movimientos.push(m);
			m.ejecutaMovimiento(t);
		}
		
		for (int i=1; i<=4; i++) {
			tableros.push(Utils.copiaTablero(t));
			Movimiento m = getMovimiento(5, 4, Ficha.NEGRA);
			movimientos.push(m);
			m.ejecutaMovimiento(t);
		}
		
		for (int i=1; i<=4; i++) {
			tableros.push(Utils.copiaTablero(t));
			Movimiento m = getMovimiento(5, 7, Ficha.BLANCA);
			movimientos.push(m);
			m.ejecutaMovimiento(t);
		}
		
		for (int i=1; i<=4; i++) {
			tableros.push(Utils.copiaTablero(t));
			Movimiento m = getMovimiento(4, 5, Ficha.NEGRA);
			movimientos.push(m);
			m.ejecutaMovimiento(t);
		}
		
		for (int i=1; i<=4; i++) {
			tableros.push(Utils.copiaTablero(t));
			Movimiento m = getMovimiento(7, 5, Ficha.BLANCA);
			movimientos.push(m);
			m.ejecutaMovimiento(t);
		}
		
		// Deshacer movimientos
		while (!movimientos.isEmpty()) {
			Movimiento mp = movimientos.pop();
			Tablero tp = tableros.pop();
			
			mp.undo(t);
			
			assertTrue("undo() no deja el tableto como estaba antes de ejecutaMovimiento()", Utils.TablerosIguales(t, tp));
		}
	}
	
}
