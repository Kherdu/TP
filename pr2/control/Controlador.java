package tp.pr2.control;
import java.util.Scanner;

import tp.pr2.logica.Movimiento;
import tp.pr2.logica.MovimientoConecta4;
import tp.pr2.logica.Partida;
import tp.pr2.logica.ReglasConecta4;
import tp.pr2.logica.Ficha;
public class Controlador {
    
    private Partida partida;
    private Scanner in;
    private Movimiento m;
    private String tipo; //si es co la partida es de complica, si es c4 conecta4
    
    public Controlador(Partida partida, Scanner in) {
	this.partida = partida;
	this.tipo="c4";
    }   
    
    public void run() {

        String lectura;
        this.in = new Scanner(System.in);

        while (!partida.isTerminada()) {

            lectura = null;
            System.out.print(partida.getTablero().toString());
            System.out.print("Juegan ");
            
            if (partida.getTurno() == Ficha.BLANCA){
		System.out.println("blancas");            
            }else if (partida.getTurno() == Ficha.NEGRA){
		System.out.println("negras");
            }
            
            System.out.print("Qué quieres hacer? ");
            lectura = in.nextLine();
            parse(lectura);

        }
	in.close();
    }
    
    public void parse(String s) {
		// parser-ejecucion
		int col;
		String st;
		if (s.compareToIgnoreCase("poner") == 0) {
                    if (partida.isTerminada() == true) {
                    	System.out.println("acabada");
                    } else {
                    		System.out.print("Introduce la columna: ");
                    		st = in.nextLine();
                    		col = Integer.parseInt(st);
                    		if(tipo.compareTo("c4")==0){
                    			m= new MovimientoConecta4(col,partida.getTurno());
                    			partida.ejecutaMovimiento(m);
                    		} //else reglas co
                    }
		} else if (s.compareToIgnoreCase("reiniciar") == 0) {
                    partida.reset(partida.getJuego());
                    System.out.print("Partida reiniciada.");
					
		} else if (s.compareToIgnoreCase("salir") == 0) {

                    System.exit(0);

		} else if (s.compareToIgnoreCase("deshacer") == 0) {
                    if (!partida.undo())
			System.err.println("Imposible deshacer.");

		}else if (s.compareToIgnoreCase("jugar") == 0){
                    st = in.nextLine();
                    if (st.compareToIgnoreCase("c4") == 0){
                        ReglasConecta4 reglas = new ReglasConecta4();
                        partida.reset(reglas);
                        tipo="c4";
                    }else if(st.compareToIgnoreCase("co") == 0){
                        //ReglasComplica reglas = new ReglasComplica(); 
                    	//incluir comprobacion si esta en un juego que no pueda re-elegirlo
                        System.out.print("Jugando al complica");
                        tipo="co";
                    }else System.out.println("No te entiendo.");
                    
              
                } else
			System.err.println("No te entiendo.");
    }                 
		
}
