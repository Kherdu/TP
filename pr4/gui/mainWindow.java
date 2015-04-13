package tp.pr4.GUI;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	public MainWindow(ControladorGUI c) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,2,20,20));
		
		
		JPanel panelIz= new PanelTablero();
		
		
		JPanel panelDr= new PanelBotones();
		
		panelIz.setVisible(true);
		panelDr.setVisible(true);
		
		
		
		this.add(panelIz);
		this.add(panelDr);
		
		
	}

}
