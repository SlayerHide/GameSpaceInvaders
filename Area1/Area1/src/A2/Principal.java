package A2;

import javax.swing.JFrame;

public class Principal {
	
	/*"Made in Mexico"*/
	/*Equipo 1
	 * Joshua Joseph Sanchez Robles
	 * Edson Abraham Martinez Herrera 
	 * */
	
	public static void main(String...XxSlayerHidexX) {
		JFrame frame = new JFrame("Space Invaders");
		pruebas game = new pruebas();
		frame.add(game);
		frame.pack(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
