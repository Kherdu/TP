package tp.pr5.logica;

public class ModoMan implements ModoJuego {

	
	private TipoJugador j;
	
	public ModoMan(){
		this.j=TipoJugador.MANUAL;
	}
	@Override
	public void comenzar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void terminar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deshacer() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public TipoJugador getJ() {
		return j;
	}

}
