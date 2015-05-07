package tp.pr4.logica;

public enum Juego {

	COMPLICA, CONECTA4, GRAVITY;
	
	
	
	public static String[] names() {
	    Juego[] juegos = values();
	    String[] nombres = new String[juegos.length];

	    for (int i = 0; i < juegos.length; i++) {
	    	nombres[i] = juegos[i].name();
	    }

	    return nombres;
	}
	
	public boolean redimensionable(Juego j){
		boolean ret=false;

		if (j==GRAVITY) ret=true;
		
		return ret;
		
	}
}
