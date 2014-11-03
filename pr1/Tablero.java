package pr1;

import java.util.Arrays;

public class Tablero {

	

	private Ficha[][]  tablero;
	private int alto;
	private int ancho;
	
	public Tablero (int h, int w){// h = alto  w = ancho(del ingles)
		
		alto = h;
		ancho = w;
		Ficha tablero[][] = new Ficha [alto][ancho];
		iniciaTablero(tablero);
		
		
	}

	public int getAlto() {
		return alto;
	}

	
	public int getAncho() {
		return ancho;
	}

	public Ficha getCasilla(int h, int w){
		
		return tablero[h][w];
		
		
		
	}
	
	public void setCasilla(int h, int w, Ficha f){
		
		tablero[h][w] = f;
					
		
	}	
	public void reset(){
		iniciaTablero(tablero);
		
		
		
	}
	
	private void iniciaTablero(Ficha[][] tab){
		
		for (int i=0; i<alto;i++){
			for (int j=0;j<ancho;j++){
				tab[i][j]=Ficha.VACIA;
				
			}
		}
		
	}
	
	
	private char parserFicha(Ficha f){
		
		char ret='\0';
		switch (f){
		
			case VACIA:
				ret=' ';
				break;
			case BLANCA:
				ret='O';
				break;
			case NEGRA:
				ret='X';
				break;
		}
		
		
		return ret;
	}
	
	@Override
	public String toString() {
		
		String ret=null;
		
		for (int i=0;i<alto;i++){
			ret+=('|');
			for (int j=0;j<ancho;j++){
				ret+=parserFicha(getCasilla(i,j));
				
			}
			ret+=('|' + '\n');
		}
		ret+=('+');
		for (int k=0;k<ancho;k++){
			ret+=('-');
		}
		ret+=('+' + '\n');
		
		ret+=(' ');
		for (int l=0;l<ancho;l++){
			ret+=(l+1);
		}
		ret+=(' ' + '\n');
		return ret;
	}
	
	
	
	
}
