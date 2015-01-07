package tp.pr2.logica;

public class ReglasConecta4 implements ReglasJuego {
    
    private Tablero tablero;
    private final int alto;
    private final int ancho;
    
    public ReglasConecta4() {
	
        this.ancho = 7;
        this.alto = 6;
        
        
    }

    @Override // hay que acerla aunque no se para que si ya esta en partida....
    public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t){
        
    return Ficha.BLANCA;
    
    }

    @Override // hecha aunque no se para que si ya esta en partida...
    public Tablero iniciaTablero(){
        
        
        tablero = new Tablero(this.ancho, this.alto);
        return tablero;
        
        
    }

   
    @Override // repito lo anterior....
    public Ficha jugadorInicial(){
    
        return Ficha.BLANCA;
        
    }

    @Override // repito lo anterior
    public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t){
        Ficha ret = Ficha.VACIA;
        if (ultimoEnPoner == Ficha.BLANCA){
            
            ret = Ficha.NEGRA;
            
        }else if (ultimoEnPoner == Ficha.NEGRA){
            
            ret = Ficha.BLANCA;
            
        } 
        
        return ret;
    }
 
    @Override // repito lo anterior....
    public boolean tablas(Ficha ultimoEnPoner, Tablero t){
    
        
        return true;
    }

    @Override
    public int getAlto() {
        return alto;
    }

    @Override
    public int getAncho() {
        return ancho;
    }
    
   
        
}
