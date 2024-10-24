import java.util.Iterator;
import java.util.Random;
import A4.Final3;
import javax.swing.text.StyledEditorKit.ForegroundAction;

public class yes {

	static int datos1[];
	static int datos2[];
	static int datos3[];
	static Final3 f3 = new Final3();

	public static void main(String... XxSlayer_HidexX) {

		pruebas();

	}

	public static void pruebas() {
		generaraArreglos();
		f3.mergeSort(datos2, 0, datos2.length -1);
		f3.mergeSort(datos3, 0, datos3.length -1);
		long tiempoInicio = System.currentTimeMillis();
		int elementoE1 = 0;
		int elementoE2 = 0;

		for (int num : datos1) {
			if (busqueda(num, datos2)) {
				elementoE1++;
			}
			if (busqueda(num, datos3)) {
				elementoE2++;
			}

		}

		long tiempoFinal = System.currentTimeMillis();
		long tiempoBusqueda = tiempoFinal - tiempoInicio;
		System.out.println("Elementos encontrados en arreglo1 : " + elementoE1);
		System.out.println("Elementos encontrados en arreglo2 : " + elementoE2);
		System.out.println("Tiempo de busqueda : " + tiempoBusqueda + "ms");
	}

	public static boolean busqueda(int elementoB, int[] vector) {
		int bajo = 0;
		int alto = vector.length - 1;

		while (bajo <= alto && elementoB >= vector[bajo] && elementoB <= vector[alto]) {
			if (bajo == alto) {
				return vector[bajo] == elementoB;

			}
			int posicion_estimada = bajo + ((elementoB - vector[bajo]) * (alto - bajo)) / (vector[alto] - vector[bajo]);

			if (vector[posicion_estimada] == elementoB) {
				return true;
			}

			if (vector[posicion_estimada] < elementoB) {
				bajo = posicion_estimada + 1;
			} else {
				alto = posicion_estimada - 1;
			}
		}

		return false;

	}

	public static int[] generarArregloRandoms(int tamaño) {
		Random random = new Random();
		int arreglo[] = new int[tamaño];
		for (int i = 0; i < tamaño; i++) {
			arreglo[i] = random.nextInt(999999) + 100000;
		}

		return arreglo;
	}

	public static void generaraArreglos() {
		datos1 = generarArregloRandoms(10000);
		datos2 = generarArregloRandoms(1000);
		datos3 = generarArregloRandoms(1000);
	}

}
