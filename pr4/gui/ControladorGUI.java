package tp.pr4.GUI;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr4.control.FactoriaComplica;
import tp.pr4.control.FactoriaConecta4;
import tp.pr4.control.FactoriaGravity;
import tp.pr4.control.FactoriaTipoJuego;
import tp.pr4.control.Jugador;
import tp.pr4.control.JugadorAleatorioComplica;
import tp.pr4.control.JugadorAleatorioConecta4;
import tp.pr4.control.JugadorAleatorioGravity;
import tp.pr4.logica.Juego;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoComplica;
import tp.pr4.logica.MovimientoConecta4;
import tp.pr4.logica.MovimientoGravity;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Partida;
import tp.pr4.logica.ReglasJuego;
import tp.pr4.logica.TableroInmutable;

public class ControladorGUI {

	
	private ArrayList<Observer> observers;
	private FactoriaTipoJuego f;
	private Partida p;
	private ReglasJuego r;
	private Juego j;
	private TableroInmutable tab;
	public ControladorGUI(FactoriaTipoJuego factoria, Partida partida) {
		

		f=factoria;
		p=partida;
		r=f.creaReglas();
		j=r.getTipo();
		observers= new ArrayList<Observer>();
		tab=p.getTablero();
		
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
	
	public void reset(FactoriaTipoJuego f){
		r=f.creaReglas();
		p = new Partida(r);
		j=r.getTipo();
		
	}

	
	
	public void undo(){
		
		p.undo();
	}

	public void reset() {
		reset(f);
		
	}

	public void movAleatorio() {
		
		
		try{
			if(j==Juego.COMPLICA){
			Jugador jug= new JugadorAleatorioComplica();
			Movimiento m = jug.getMovimiento(p.getTablero(),p.getTurno());
			p.ejecutaMovimiento(m);
			}else if (j==Juego.CONECTA4){
			Jugador jug= new JugadorAleatorioConecta4();
			Movimiento m = jug.getMovimiento(p.getTablero(),p.getTurno());
			p.ejecutaMovimiento(m);
			}else {
			Jugador jug= new JugadorAleatorioGravity();
			Movimiento m = jug.getMovimiento(p.getTablero(),p.getTurno());
			p.ejecutaMovimiento(m);
			}
		
		}catch (MovimientoInvalido e){
			JFrame frame= new JFrame();
			JOptionPane.showMessageDialog(frame,
				    e.getMessage(),
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void cambiaJuego(Juego seleccionado, String filas, String columnas) {

		if(seleccionado==Juego.GRAVITY){
			int h;
			int w;
			if (filas.isEmpty() && columnas.isEmpty()){
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
						JOptionPane.showMessageDialog(frame,
							    "Numero incorrecto",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
					}
			
				}
			
			} else if(seleccionado==Juego.CONECTA4){
			f=new FactoriaConecta4();
			
			} else if (seleccionado==Juego.COMPLICA){
			f=new FactoriaComplica();
		}
		
		reset(f);
		//falta que el observador mire cuando ha cambiado para repintarse
		
		
		
	}
	
	
}
