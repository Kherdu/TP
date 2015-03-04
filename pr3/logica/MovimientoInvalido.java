package tp.pr3.logica;

public class MovimientoInvalido extends java.lang.Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovimientoInvalido(){
	
		super();
	}
	
	public MovimientoInvalido(String msg){
		
		super (msg);
	}
	public MovimientoInvalido(String msg, Throwable arg){
		
		super(msg,arg);
	}
	
	public MovimientoInvalido (Throwable arg){
		super(arg);
	}
}
