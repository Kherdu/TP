package tp.pr4.GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PanelTablero extends JPanel {

	private ControladorGUI c;
	public PanelTablero() {

	}

	public PanelTablero(ControladorGUI c,int alto, int ancho) {
		
		this.c=c;
		setLayout(new GridLayout(2, 1, 30, 30));

		JPanel panel_Tablero = new JPanel();
		//for que añade botones al tablero, con gridLayout de anchura ancho y filas alto
		panel_Tablero.setLayout(new GridLayout(alto,ancho));
		
		
		for (int i=0;i<alto;i++){
			//añadir botones casilla que hereden de observador
			for (int j=0;j<ancho;j++){
				Casilla cas= new Casilla(i,j);
				panel_Tablero.add(cas);
				
			}
			
		}
		
		
		JPanel panel = new JPanel();
		//hay que pasar el turno BIEN, me acabo de cargar la encapsulacion a saco, pero funciona
		LabelTurno turno= new LabelTurno(c.getPartida().getTurno().toString());
		
		

		JButton aleatorio = new JButton("Movimiento Aleatorio");
		aleatorio.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				
			}
			
		});
		
		panel.add(aleatorio, BorderLayout.SOUTH);
		panel.add(turno);
		add(panel_Tablero);
		
		add(panel);
	}

}
