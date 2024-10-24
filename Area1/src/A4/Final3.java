package A4;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Final3 extends JFrame {
	private JButton ordenarButton, hashButton, interpolationSearchButton, salirButton;
	private JTextArea resultadosArea;

	private int[] arreglo1;
	private int[] arreglo2;
	private int[] arregloHash;

	public Final3() {
		setTitle("Ordenamiento y Busqueda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		inicializarComponentes();
		generarArreglos();
	}

	private void inicializarComponentes() {
		JPanel botonesPanel = new JPanel(new GridLayout(4, 1));
		ordenarButton = new JButton("Ordenar por margeSort");
		hashButton = new JButton("Buscar con Hash");
		interpolationSearchButton = new JButton("Busqueda por Interpolacion");
		salirButton = new JButton("Salir");

		ordenarButton.addActionListener(e -> ordenar());
		hashButton.addActionListener(e -> buscarHash());
		interpolationSearchButton.addActionListener(e -> buscarInterpolacion());
		salirButton.addActionListener(e -> System.exit(0));

		botonesPanel.add(ordenarButton);
		botonesPanel.add(hashButton);
		botonesPanel.add(interpolationSearchButton);
		botonesPanel.add(salirButton);
		add(botonesPanel, BorderLayout.NORTH);

		resultadosArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(resultadosArea);
		add(scrollPane, BorderLayout.CENTER);
	}

	public int[] generarArregloAleatorio(int tamaño) {
		Random random = new Random();
		int[] arreglo = new int[tamaño];
		for (int i = 0; i < tamaño; i++) {
			arreglo[i] = random.nextInt(999999) + 100000; // Rango de valores entre 100,000 y 999,999
		}
		return arreglo;
	}

	private void generarArreglos() {
		arreglo1 = generarArregloAleatorio(1000);
		arreglo2 = generarArregloAleatorio(1000);
		arregloHash = generarArregloAleatorio(50000);
	}

	private void ordenar() {

		resultadosArea.setText("Arreglo antes de ordenar:\n" + Arrays.toString(arregloHash) + "\n\n");

		long startTime = System.currentTimeMillis();
		mergeSort(arregloHash, 0, arregloHash.length - 1); // Método de ordenamiento: MergeSort
		long endTime = System.currentTimeMillis();
		long tiempoMergeSort = endTime - startTime;

		resultadosArea.append("Arreglo despues de ordenar:\n" + Arrays.toString(arregloHash) + "\n");
		resultadosArea.append("Tiempo de MergeSort: " + tiempoMergeSort + " milisegundos");
	}

	// Metodo de Ordenamiento
	public void mergeSort(int[] arreglo, int izquierda, int derecha) {
		if (izquierda < derecha) {
			int medio = (izquierda + derecha) / 2;
			mergeSort(arreglo, izquierda, medio);
			mergeSort(arreglo, medio + 1, derecha);
			merge(arreglo, izquierda, medio, derecha);
		}
	}

	public void merge(int[] arreglo, int izquierda, int medio, int derecha) {
		int n1 = medio - izquierda + 1;
		int n2 = derecha - medio;

		int[] izquierdo = new int[n1];
		int[] derecho = new int[n2];

		for (int i = 0; i < n1; ++i) {
			izquierdo[i] = arreglo[izquierda + i];
		}
		for (int j = 0; j < n2; ++j) {
			derecho[j] = arreglo[medio + 1 + j];
		}

		int i = 0, j = 0;
		int k = izquierda;
		while (i < n1 && j < n2) {
			if (izquierdo[i] <= derecho[j]) {
				arreglo[k] = izquierdo[i];
				i++;
			} else {
				arreglo[k] = derecho[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			arreglo[k] = izquierdo[i];
			i++;
			k++;
		}

		while (j < n2) {
			arreglo[k] = derecho[j];
			j++;
			k++;
		}
	}

	// Metodod de Busqueda Hash

	private void buscarHash() {
		HashMap<Integer, Integer> hashMap1 = new HashMap<>();
		HashMap<Integer, Integer> hashMap2 = new HashMap<>();

		for (int num : arreglo1) {
			hashMap1.put(num, num);
		}

		for (int num : arreglo2) {
			hashMap2.put(num, num);
		}

		long startTimeHash = System.currentTimeMillis();
		int encontradosEnArreglo1 = 0;
		int encontradosEnArreglo2 = 0;

		for (int num : arregloHash) {
			if (hashMap1.containsKey(num)) {
				encontradosEnArreglo1++;
			}
			if (hashMap2.containsKey(num)) {
				encontradosEnArreglo2++;
			}
		}

		long endTimeHash = System.currentTimeMillis();
		long tiempoHash = endTimeHash - startTimeHash;

		resultadosArea.setText("Resultados de busqueda con Hash en Arreglos:\n");
		resultadosArea.append("Elementos encontrados en arreglo1: " + encontradosEnArreglo1 + "\n");
		resultadosArea.append("Elementos encontrados en arreglo2: " + encontradosEnArreglo2 + "\n");
		resultadosArea.append("Tiempo de busqueda con Hash: " + tiempoHash + " milisegundos");
		System.out.println("Tiempo de busqueda con Hash: " + tiempoHash + " milisegundos");
	}

	// Metodo de Busqueda

	private void buscarInterpolacion() {
		mergeSort(arreglo1, 0, arreglo1.length - 1);
		mergeSort(arreglo2, 0, arreglo2.length - 1);

		long startTimeInterpolacion = System.currentTimeMillis();
		int encontradosEnArreglo1 = 0;
		int encontradosEnArreglo2 = 0;

		for (int num : arregloHash) {
			if (busquedaInterpolacionEnArreglo(num, arreglo1)) {
				encontradosEnArreglo1++;
			}
			if (busquedaInterpolacionEnArreglo(num, arreglo2)) {
				encontradosEnArreglo2++;
			}
		}

		long endTimeInterpolacion = System.currentTimeMillis();
		long tiempoInterpolacion = endTimeInterpolacion - startTimeInterpolacion;

		resultadosArea.setText("Resultados de busqueda con Interpolacion en Arreglos:\n");
		resultadosArea.append("Elementos encontrados en arreglo1: " + encontradosEnArreglo1 + "\n");
		resultadosArea.append("Elementos encontrados en arreglo2: " + encontradosEnArreglo2 + "\n");
		resultadosArea.append("Tiempo de busqueda con Interpolación: " + tiempoInterpolacion + " milisegundos");
		System.out.println("Tiempo de busqueda con Interpolacion: " + tiempoInterpolacion + " milisegundos");
	}

	public boolean busquedaInterpolacionEnArreglo(int elementoBuscado, int[] arreglo) {
		int bajo = 0;
		int alto = arreglo.length - 1;

		while (bajo <= alto && elementoBuscado >= arreglo[bajo] && elementoBuscado <= arreglo[alto]) {
			if (bajo == alto) {
				return arreglo[bajo] == elementoBuscado;
			}

			int posicionEstimada = bajo + ((elementoBuscado - arreglo[bajo]) * (alto - bajo)) / (arreglo[alto] - arreglo[bajo]);

			if (arreglo[posicionEstimada] == elementoBuscado) {
				return true;
			}

			if (arreglo[posicionEstimada] < elementoBuscado) {
				bajo = posicionEstimada + 1;
			} else {
				alto = posicionEstimada - 1;
			}
		}

		return false;
	}

	public static void main(String ... XxSlayer_HidexX) {
		SwingUtilities.invokeLater(() -> {
			Final3 app = new Final3();
			app.setVisible(true);
		});
	}

}
