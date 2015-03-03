package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.constants.Constants;
import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoGravity;
import tp.pr3.logica.ReglasGravity;
import tp.pr3.logica.ReglasJuego;

public class FactoriaGravity implements FactoriaTipoJuego{

	private int ancho;
	private int alto;
	
	FactoriaGravity(){
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
