package tp.pr3.logica;

public class MovimientoInvalido extends java.lang.Exception{



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
