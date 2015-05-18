package tp.pr5.GUI;

import javax.swing.JButton;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;

public class Casilla extends JButton{
	
	private Ficha color;
	private Integer columna;
	private Integer fila;
	
	Casilla(){
		this.color=Ficha.VACIA;
		
		
	}
	
	Casilla(Integer columna, Integer fila, Ficha color){
		this.columna=columna;
		this.fila=fila;
		this.color=color;
	}

	
	
	public Integer getColumna() {
		return columna;
	}

	public Integer getFila() {
		return fila;
	}

	public Ficha getColor() {
		return color;
	}

	public void setColor(Ficha color) {
		this.color = color;
	}


	

}
