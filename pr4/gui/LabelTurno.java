package tp.pr4.GUI;

import javax.swing.JLabel;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.TableroInmutable;

public class LabelTurno extends JLabel implements Observer {

	private String etiqueta;
	
	public LabelTurno(String string) {
		this.etiqueta=string;
		this.setText(etiqueta);
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
