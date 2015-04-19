package tp.pr3.control.test;

import java.util.Scanner;

import org.junit.Test;

import static org.junit.Assert.fail;
import tp.pr3.control.Controlador;
import tp.pr3.control.FactoriaConecta4;
import tp.pr3.control.FactoriaTipoJuego;
import tp.pr3.logica.Partida;

public class ControladorTest {
	
	// Comprobar que existe la clase Controlador
	@Test
	public void testCtor() {
		try {
			FactoriaTipoJuego f = new FactoriaConecta4();
			Partida p = new Partida(f.creaReglas());
			Scanner sc = new Scanner(System.in);
			new Controlador(f, p, sc);
		} catch (Exception e) {
			fail("El constructor de Controlador falla.");
		}
	}
}
