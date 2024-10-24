package A3;

import java.util.*;
import javax.swing.*;

public class Class1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Date date = new Date();
		System.out.println(date);
	   ciclosRandom(scanner);

	}

	public static void ciclosRandom(Scanner scanner) {
		String respuesta = "si";
		while (respuesta.equalsIgnoreCase("si")) {

			Double x[][] = new Double[3][3];
			for (int i = 0; i < x.length; i++) {

				for (int j = 0; j < x.length; j++) {
				x[i][j] = (Math.random() +(Math.random()));
					
				if(x[i][j] == (x[i][j] - 1)) {
					System.out.println("Imprimio desde el if "+x[i][j]);
					
				}
				else {
					System.out.println("Imprimio desde el else"+x[i][j]);
				}
						
				}

			}
			System.out.println("Desea regresar");
			respuesta = scanner.next();
		}
	}
   
}
