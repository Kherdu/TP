package tp.pr4.GUI;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

	private static ControladorGUI controlador;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow(controlador);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public MainWindow(ControladorGUI c) {
		controlador=c;
		initialize(c);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ControladorGUI c) {
		
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,2,20,20));
		
		//cambiar esto, pasarlo con el tablero inmutable
		JPanel panelIz= new PanelTablero(c.getReglas().getAlto(),c.getReglas().getAncho());
		
		
		JPanel panelDr= new PanelBotones();
		
		panelIz.setVisible(true);
		panelDr.setVisible(true);
		
		
		
		this.add(panelIz);
		this.add(panelDr);
		
		
	}

}
