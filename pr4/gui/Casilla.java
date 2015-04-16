package tp.pr4.GUI;

import javax.swing.JButton;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.TableroInmutable;

public class Casilla extends JButton implements Observer{
	
	private Ficha color;
	private Integer x;
	private Integer y;
	
	Casilla(){
		this.color=Ficha.VACIA;
		
		
	}
	
	Casilla(Integer x, Integer y){
		this.x=x;
		this.y=y;
		this.color=Ficha.VACIA;
	}

	
	
	public Ficha getColor() {
		return color;
	}

	public void setColor(Ficha color) {
		this.color = color;
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMovimientoIncorrecto(TableroInmutable movimientoException) {
		// TODO Auto-generated method stub
		
	}

	

}
