package tp.pr4.GUI;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr4.control.FactoriaComplica;
import tp.pr4.control.FactoriaConecta4;
import tp.pr4.control.FactoriaGravity;
import tp.pr4.control.FactoriaTipoJuego;
import tp.pr4.control.Jugador;
import tp.pr4.logica.Juego;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.Partida;
import tp.pr4.logica.ReglasJuego;

public class ControladorGUI {

	
	private ArrayList<Observer> observers;
	private FactoriaTipoJuego f;
	private Partida p;
	private ReglasJuego r;
	private Juego j;
	
	public ControladorGUI(FactoriaTipoJuego factoria, Partida partida) {
		

		f=factoria;
		p=partida;
		r=f.creaReglas();
		j=r.getTipo();
		observers= new ArrayList<Observer>();
		
	}

	public FactoriaTipoJuego getFactoria() {
		return f;
	}

	public Partida getPartida() {
		return p;
	}

	public ReglasJuego getReglas() {
		return r;
	}
	
	public void addObserver(Observer o){
		observers.add(o);
	}
	
	
	/*
	 * reinicio de juego
	 */
	
	public void reset(Partida p, ReglasJuego r){
		p.reset(r);
		j=r.getTipo();
		
	}

	
	
	public void undo(){
		
		p.undo();
	}

	public void reset() {
		reset(p,r);
		
	}

	public void movAleatorio() {
		
		//crear movimiento 'aleatorio' con el turno del jugador al que le toca y mandarselo a partida
		
		
	}

	public void cambiaJuego(Juego seleccionado, String filas, String columnas) {

		if(seleccionado==Juego.GRAVITY){
			int h;
			int w;
			if (filas=="" && columnas==""){
				f=new FactoriaGravity();
			}
				else{
					try{
						h= Integer.parseInt(filas);
						w= Integer.parseInt(columnas);
						if (h<=0 ){
							h=1;
					
						}
						if (w<=0){
							w=1;
						}
						f= new FactoriaGravity(h,w);	
					} catch (NumberFormatException e){
						JFrame frame= new JFrame();
						JOptionPane.showMessageDialog(frame, "Numero erroneo");
					}
			
				}
			
			} else if(seleccionado==Juego.CONECTA4){
			f=new FactoriaConecta4();
			
			} else if (seleccionado==Juego.COMPLICA){
			f=new FactoriaComplica();
		}
		
		reset(p,r);
		
		
		
		
	}
	
	public void cambiaJuego(FactoriaTipoJuego f) {

		r = f.creaReglas();
		reset(p,r);

	}

}
