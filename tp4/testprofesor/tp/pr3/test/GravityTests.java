package tp.pr3.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tp.pr3.logica.test.FactoriaGravityTest;
import tp.pr3.logica.test.TableroTest;
import tp.pr3.logica.test.MovimientoGravityTest;
import tp.pr3.logica.test.ReglasGravityTest;
import tp.pr3.logica.test.PartidaGravityTest;
import tp.pr3.logica.test.UndoGravityTest;
import tp.pr3.logica.test.CuatroEnRayaGravityTest;

@RunWith(Suite.class) 
@Suite.SuiteClasses( { 
	TableroTest.class,
	MovimientoGravityTest.class,
	ReglasGravityTest.class,
	PartidaGravityTest.class,
	UndoGravityTest.class,
	CuatroEnRayaGravityTest.class,
	FactoriaGravityTest.class,
	})
public class GravityTests {

}
