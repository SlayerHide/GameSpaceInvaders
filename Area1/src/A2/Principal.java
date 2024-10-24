package A2;

import javax.swing.JFrame;

public class Principal {
	
	/*"Made in Mexico"*/
	/*Equipo 9
	 * Joshua Joseph Sanchez Robles
	 * Edson Abraham Martinez Herrera 
	 * Ruta almacenamiento C:\Users\js023\eclipse-workspace\Area1
	 * */
	
	public static void main(String...XxSlayerHidexX) {
		JFrame frame = new JFrame("Space Invaders");
		SpaceInvadersGame game = new SpaceInvadersGame();
		frame.add(game);
		frame.pack(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
