package tp.pr3.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tp.pr3.logica.test.CuatroEnRayaComplicaTest;
import tp.pr3.logica.test.FactoriaComplicaTest;
import tp.pr3.logica.test.MovimientoComplicaTest;
import tp.pr3.logica.test.PartidaComplicaTest;
import tp.pr3.logica.test.ReglasComplicaTest;
import tp.pr3.logica.test.TableroTest;
import tp.pr3.logica.test.UndoComplicaTest;

@RunWith(Suite.class) 
@Suite.SuiteClasses( { 
	TableroTest.class,
	MovimientoComplicaTest.class,
	ReglasComplicaTest.class,
	PartidaComplicaTest.class,
	UndoComplicaTest.class,
	CuatroEnRayaComplicaTest.class,
	FactoriaComplicaTest.class,
	})
public class ComplicaTests {

}
