package tp.pr3.logica;
public class Tablero {

    private Ficha[][] tablero;
    private int alto;
    private int ancho;

    public Tablero(int w, int h) {
        // h = alto w = ancho(del ingles)
        this.alto = h;
        this.ancho = w;

        if (ancho < 1) ancho = 1;
        if (alto < 1)  alto = 1;

        this.tablero = new Ficha[ancho][alto];
        iniciaTablero(tablero);
    }


    public int getAlto() {
        return this.alto;
    }

    public int getAncho() {
        return this.ancho;
    }

    public Ficha getCasilla(int w, int h) {
        h--;
        w--;

        if (h < 0 || w < 0 || h >= alto || w >= ancho) {
            return Ficha.VACIA;
        }
   
        return tablero[w][h];
    }

    public void setCasilla(int w, int h, Ficha f) {
        h--;
        w--;

        if (!(h < 0 || w < 0 || h >= alto || w >= ancho)) {
            this.tablero[w][h] = f;
        }

    }
    
    public void reset() {
	iniciaTablero(tablero);
    }
    
    private void iniciaTablero(Ficha[][] tab) {
	// inicia el tablero a vacia
	for (int i = 0; i <= ancho; i++) {
            for (int j = 0; j <= alto; j++) {
		setCasilla(i, j, Ficha.VACIA);
            }
        }
    }
    
    private char parserFicha(Ficha f) {
	// saca el caracter equivalente de la ficha.
	char ret = '\0';
	switch (f) {

	case VACIA:
            ret = ' ';
            break;
	case BLANCA:
            ret = 'O';
            break;
	case NEGRA:
            ret = 'X';
            break;
	}

	return ret;
    }
    
    @Override
    public String toString() {
	// pinta tablero
	String ret = ("");

	for (int i = 1; i <= alto; i++) { // fichas
            ret += ('|');
            
            for (int j = 1; j <= ancho; j++) {
		ret += parserFicha(getCasilla(j, i));
            }
            
            ret += ('|' + System.lineSeparator());
	}
        
	ret += ('+');
        
	for (int k = 1; k <= ancho; k++) {
            ret += ('-');
	}
        
	ret += ('+' + System.lineSeparator());
	ret += (' ');
        
	for (int l = 1; l <= ancho; l++) {
            ret += (l);
	}

	ret += (System.lineSeparator());
	ret += (System.lineSeparator());
	return ret;
    }

}
