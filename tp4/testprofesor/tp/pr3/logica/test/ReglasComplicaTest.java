package tp.pr3.logica.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tp.pr3.logica.ReglasComplica;
import tp.pr3.logica.ReglasJuego;
import tp.pr3.logica.Tablero;

public class ReglasComplicaTest extends ReglasConecta4Test {

	@Override
	protected ReglasJuego getReglas() {
		return new ReglasComplica();
	}

	@Test
	@Override
	public void testIniciaTablero() {
		Tablero t = r.iniciaTablero();
		
		assertEquals("El tablero no tiene el ancho adecuado", 4, t.getAncho());
		assertEquals("El tablero no tiene el alto adecuado", 7, t.getAlto());
		assertTrue("El tablero debe empezar vacío", Utils.tableroVacio(t));
	}
}
