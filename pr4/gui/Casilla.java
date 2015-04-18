package tp.pr4.GUI;

import javax.swing.JButton;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.TableroInmutable;

public class Casilla extends JButton{
	
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


	

}
