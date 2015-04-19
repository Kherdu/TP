package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.constants.Constants;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoGravity;
import tp.pr4.logica.ReglasGravity;
import tp.pr4.logica.ReglasJuego;

public class FactoriaGravity implements FactoriaTipoJuego{

	private int ancho;
	private int alto;
	
	public FactoriaGravity(){
		this.alto=Constants.altoGv;
		this.ancho=Constants.anchoGv;
	}
	
	public FactoriaGravity(int alto, int ancho){
		this.alto=alto;
		this.ancho=ancho;
		
	}
	@Override
	public ReglasJuego creaReglas() {
		return new ReglasGravity(alto, ancho);
	}
	
	

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoGravity(col,fila,color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoGravity(in);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity();
	}

}
