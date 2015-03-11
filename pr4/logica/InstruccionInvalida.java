package tp.pr4.logica;

public class InstruccionInvalida extends Exception {

	public InstruccionInvalida(){
		
		super();
	}
	
	public InstruccionInvalida(String msg){
		
		super (msg);
	}
	public InstruccionInvalida(String msg, Throwable arg){
		
		super(msg,arg);
	}
	
	public InstruccionInvalida(Throwable arg){
		super(arg);
	}
}
