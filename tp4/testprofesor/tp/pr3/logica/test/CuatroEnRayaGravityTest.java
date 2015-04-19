package tp.pr3.logica.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoGravity;
import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.Partida;
import tp.pr3.logica.ReglasGravity;
import tp.pr3.logica.ReglasJuego;
import tp.pr3.logica.Tablero;

public class CuatroEnRayaGravityTest {
	
	private static final int[][][] DIR = {
		{{ -1, -1}, { -1, 0}, { -1, 0}, { -1, 0}, { -1, 0}, { -1, 0}, { -1, 0}, { -1, 0}, { -1, 0}, { -1, 1}, },
		{{ 0, -1}, { -1, -1}, { -1, 0}, { -1, 0}, { -1, 0}, { -1, 0}, { -1, 0}, { -1, 0}, { -1, 1}, { 0, 1}, },
		{{ 0, -1}, { 0, -1}, { -1, -1}, { -1, 0}, { -1, 0}, { -1, 0}, { -1, 0}, { -1, 1}, { 0, 1}, { 0, 1}, },
		{{ 0, -1}, { 0, -1}, { 0, -1}, { -1, -1}, { -1, 0}, { -1, 0}, { -1, 1}, { 0, 1}, { 0, 1}, { 0, 1}, },
		{{ 0, -1}, { 0, -1}, { 0, -1}, { 0, -1}, { -1, -1}, { -1, 1}, { 0, 1}, { 0, 1}, { 0, 1}, { 0, 1}, },
		{{ 0, -1}, { 0, -1}, { 0, -1}, { 0, -1}, { 1, -1}, { 1, 1}, { 0, 1}, { 0, 1}, { 0, 1}, { 0, 1}, },
		{{ 0, -1}, { 0, -1}, { 0, -1}, { 1, -1}, { 1, 0}, { 1, 0}, { 1, 1}, { 0, 1}, { 0, 1}, { 0, 1}, },
		{{ 0, -1}, { 0, -1}, { 1, -1}, { 1, 0}, { 1, 0}, { 1, 0}, { 1, 0}, { 1, 1}, { 0, 1}, { 0, 1}, },
		{{ 0, -1}, { 1, -1}, { 1, 0}, { 1, 0}, { 1, 0}, { 1, 0}, { 1, 0}, { 1, 0}, { 1, 1}, { 0, 1}, },
		{{ 1, -1}, { 1, 0}, { 1, 0}, { 1, 0}, { 1, 0}, { 1, 0}, { 1, 0}, { 1, 0}, { 1, 0}, { 1, 1}, },
	};

	
	protected ReglasJuego r;
	
	@Before
	public void init() {
		r = getReglas();
	}
	
	protected ReglasJuego getReglas() {
		return new ReglasGravity(10,10);
	}
	
	protected Movimiento getMovimiento(int columna, int fila, Ficha color) {
		return new MovimientoGravity(columna, fila, color);
	}
	
	// Tableros sin 4 en raya
	@Test
	public void testNoCuatroEnRaya() throws MovimientoInvalido {
		Partida p = new Partida(r);
		Tablero t = p.getTablero();

		for (int y=1; y<=3; y++) {
			for (int x=y; x <= t.getAncho()-y+1; x++) {
				Movimiento mov = getMovimiento(x, y, p.getTurno());
				p.ejecutaMovimiento(mov);
				
				assertEquals("Detectado incorrectamente 4 en línea tras ejecutar movimiento", Ficha.VACIA, r.hayGanador(mov, t));
				assertEquals("Detectado incorrectamente ganador tras ejecutar movimiento", p.getGanador(), Ficha.VACIA);
				assertFalse("Detectado incorrectamente tablas tras ejecutar movimiento", r.tablas(mov.getJugador(), t));		
				assertFalse("Detectado incorrectamente partida terminada tras ejecutar movimiento", p.isTerminada());	
			}
		}
	}
	
	// DIR[x][y] = {dx,dy}
	/*
	private static final int[][][] DIR = {
		{},
		
	}
	*/
	
	// Prepara la partida para que se pueda colocar, en el siguiente movimiento
	// la ficha del color dado en la posición indicada. Para eso utiliza
	// las reglas de la partida de Gravity.
	private boolean preparaColocacionFicha(Partida p, Ficha color, int x, int y) throws MovimientoInvalido {

		if (p.isTerminada()) return false;
		
		Tablero t = p.getTablero();

		int dx = DIR[x-1][y-1][0];
		int dy = DIR[x-1][y-1][1];
		
		// Cuantas fichas tenemos que poner
		int aPoner = 0;
		if ((dx != 0) || (dy != 0)) {
			int nx = x + dx;
			int ny = y  + dy;
			while ((nx >= 1) && (nx <= 10) && (ny >= 1) && (ny <= 10) && t.getCasilla(nx, ny) == Ficha.VACIA) {
				aPoner++;
				
				nx += dx;
				ny += dy;
			}
		}
		
		// Hay que ajustar turno
		if ((aPoner % 2 == 0) != (p.getTurno() == color)) {
			// HACK: poner y quitar la ficha para cambiar el turno
			if ((t.getCasilla(1, 1) == Ficha.VACIA) && 
				(t.getCasilla(1, 2) == Ficha.VACIA) && 
				(t.getCasilla(2, 1) == Ficha.VACIA) &&
				(t.getCasilla(2, 2) == Ficha.VACIA)){
				
				p.ejecutaMovimiento(getMovimiento(1, 1, p.getTurno()));
				t.setCasilla(1, 1, Ficha.VACIA);
			} else {
				p.ejecutaMovimiento(getMovimiento(10, 10, p.getTurno()));
				t.setCasilla(10, 10, Ficha.VACIA);	
			}
		}
		
		// Poner las fichas
		while (aPoner >= 1) {
			Movimiento mov = getMovimiento(x, y, p.getTurno());
			p.ejecutaMovimiento(mov);
			
			assertTrue("Detectado ganador incorrectamente tras ejecutar movimiento", r.hayGanador(mov, t) == Ficha.VACIA);
			assertFalse("Detectado tablas incorrectamente tras ejecutar movimiento", r.tablas(mov.getJugador(), t));
			
			aPoner--;
		}
		
		return true;
	}	
	
	private void testCuatroEnRaya(int posX[], int posY[], int ultima, Ficha color, int idxPrepara) throws MovimientoInvalido {
		Partida p = new Partida(getReglas());
		Tablero t = p.getTablero();

		for (int i = 0; i < posX.length; ++i) {
			if (i != ultima)
				t.setCasilla(posX[i], posY[i], color);
		}
		
		if (!preparaColocacionFicha(p, color, posX[idxPrepara], posY[idxPrepara]))
			fail("Error interno en los test :-?");
		
		assertFalse("Partida terminada de forma anticipada con un tablero con tres fichas de color " + color, p.isTerminada());
		Movimiento mov = getMovimiento(posX[ultima], posY[ultima], color);
		p.ejecutaMovimiento(mov);

		assertTrue("Partida no terminada tras cuatro en raya de " + color, p.isTerminada());
		assertTrue("HayGanador incorrecto tras victoria de" + color, r.hayGanador(mov, t) == color);
		assertFalse("tablas incorrecto tras victoria de" + color, r.tablas(color, t));
		assertEquals("Ganador incorrecto tras victoria de " + color, color, p.getGanador());
		
		for (int x = 1; x <= t.getAncho(); ++x) {
			try {
				p.ejecutaMovimiento(getMovimiento(x, 1, Ficha.BLANCA));
				fail("No se debe poder poner tras terminar la partida.");
			} catch(MovimientoInvalido e) {};
			
			try {
				p.ejecutaMovimiento(getMovimiento(x, 1, Ficha.NEGRA));
				fail("No se debe poder poner tras terminar la partida.");
			} catch(MovimientoInvalido e) {};
		}
	}
	
	private void pruebaCuatroEnRaya(int posX[], int posY[]) throws MovimientoInvalido {
		for (int i = 0; i < posX.length; ++i) {
			testCuatroEnRaya(posX, posY, i, Ficha.BLANCA, i);
			testCuatroEnRaya(posX, posY, i, Ficha.NEGRA, i);
		}
	}
	
	// Partidas que terminan con todas las posibles 4 en raya
	// horizontal
	@Test
	public void testCuatroEnRayaHorizontal() throws MovimientoInvalido {
		Tablero t = r.iniciaTablero();
		
		int []posX = new int[4];
		int []posY = new int[4];
		for (int x = 1; x <= t.getAncho() - 3; ++x) {
			for (int y = 1; y <= t.getAlto(); ++y) {
				for (int l = 0; l < 4; ++l) {
					posX[l] = x + l;
					posY[l] = y;
				}
				pruebaCuatroEnRaya(posX, posY);
			}
		}
	}
	
	// Partidas que terminan con todas las posibles 4 en raya
	// vertical
	@Test
	public void testCuatroEnRayaVertical() throws MovimientoInvalido {
		Tablero t = r.iniciaTablero();
		
		int []posX = new int[4];
		int []posY = new int[4];
		for (int x = 1; x <= t.getAncho(); ++x) {
			for (int y = 1; y <= t.getAlto() - 3; ++y) {
				for (int l = 0; l < 4; ++l) {
					posX[l] = x;
					posY[l] = y + l;
				}
				pruebaCuatroEnRaya(posX, posY);
			}
		}
	}
	
	// Partidas que terminan con todas las posibles 4 en raya
	// diagonal /
	@Test
	public void testCuatroEnRayaDiag1() throws MovimientoInvalido {
		Tablero t = r.iniciaTablero();
		
		int []posX = new int[4];
		int []posY = new int[4];
		for (int i = 1; i <= t.getAlto() + t.getAncho() - 1; ++i) {
			int sx = Math.max(1, i-t.getAlto()-1);
			int sy = Math.min(i, t.getAlto());
			while ((sy - 4 >= 0) && (sx + 3 <= t.getAncho())) {
				for (int l = 0; l < 4; ++l) {
					posX[l] = sx + l;
					posY[l] = sy - l;
				}
				pruebaCuatroEnRaya(posX, posY);
				sy--; sx++;
			}
		}
	}
	
	// Partidas que terminan con todas las posibles 4 en raya
	// diagonal \
	@Test
	public void testCuatroEnRayaDiag2() throws MovimientoInvalido {
		Tablero t = r.iniciaTablero();
		
		int []posX = new int[4];
		int []posY = new int[4];
		for (int i = 1; i <= t.getAlto() + t.getAncho() - 1; ++i) {
			int sx = Math.min(i,  t.getAncho());
			int sy = Math.min(t.getAncho() + t.getAlto() - i, t.getAlto());
			while ((sy - 4 >= 0) && (sx - 4 >= 0)) {
				for (int l = 0; l < 4; ++l) {
					posX[l] = sx - l;
					posY[l] = sy - l;
				}
				pruebaCuatroEnRaya(posX, posY);
				sy--; sx--;
			}
		}
	}
	
	// La comprobación de 4 en raya se hace después de dejar caer la ficha 
	// y no antes
	@Test
	public void testCuatroEnRayaDespuesDeGravedad() throws MovimientoInvalido {
		Partida p = new Partida(r);
		Tablero t = p.getTablero();
		
		t.setCasilla(2, 1, Ficha.NEGRA);
		t.setCasilla(3, 2, Ficha.NEGRA);
		t.setCasilla(4, 3, Ficha.NEGRA);
		t.setCasilla(8, 1, Ficha.NEGRA);
		t.setCasilla(7, 2, Ficha.NEGRA);
		t.setCasilla(6, 3, Ficha.NEGRA);
		t.setCasilla(2, 4, Ficha.NEGRA);
		t.setCasilla(3, 4, Ficha.NEGRA);
		t.setCasilla(4, 4, Ficha.NEGRA);
		t.setCasilla(6, 4, Ficha.NEGRA);
		t.setCasilla(7, 4, Ficha.NEGRA);
		t.setCasilla(8, 4, Ficha.NEGRA);
		t.setCasilla(4, 5, Ficha.NEGRA);
		t.setCasilla(3, 6, Ficha.NEGRA);
		t.setCasilla(2, 7, Ficha.NEGRA);
		t.setCasilla(6, 5, Ficha.NEGRA);
		t.setCasilla(7, 6, Ficha.NEGRA);
		t.setCasilla(8, 7, Ficha.NEGRA);
		t.setCasilla(5, 5, Ficha.NEGRA);
		t.setCasilla(5, 6, Ficha.NEGRA);
		t.setCasilla(5, 7, Ficha.NEGRA);
		
		// Para que el turno sea de negras
		Movimiento m = getMovimiento(10, 10, Ficha.BLANCA);
		p.ejecutaMovimiento(m);
		assertFalse(p.isTerminada());
		
		// No comprobar 4 en raya antes de aplicar gravedad
		m = getMovimiento(5, 4, Ficha.NEGRA);
		p.ejecutaMovimiento(m);
		assertFalse("La comprobación de 4 en raya se debe hacer después aplicar la gravedad a la ficha.", p.isTerminada());
		
		t.setCasilla(5, 1, Ficha.VACIA);
		t.setCasilla(3, 1, Ficha.NEGRA);
		t.setCasilla(4, 1, Ficha.NEGRA);
		
		// Para que el turno sea de negras
		m = getMovimiento(10, 9, Ficha.BLANCA);
		p.ejecutaMovimiento(m);
		assertFalse(p.isTerminada());
		
		// Comprobar 4 en raya después de aplicar gravedad
		m = getMovimiento(5, 4, Ficha.NEGRA);
		p.ejecutaMovimiento(m);
		assertTrue("No se detecta 4 en raya tras aplicar la gravedad a la ficha.", p.isTerminada());
		assertTrue("No se detecta bien ganador tras 4 en raya de negras.", p.getGanador() == Ficha.NEGRA);
	}
}
