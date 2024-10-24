package A4;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Final2 extends JFrame {
	private JButton ordenarButton, hashButton, interpolationSearchButton, salirButton;
	private JTextArea resultadosArea;

	private int[] arreglo1;
	private int[] arreglo2;
	private int[] arregloHash;

	public Final2() {
		setTitle("Ordenamiento y Búsqueda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		inicializarComponentes();
		generarArreglos();
	}

	private void inicializarComponentes() {
		JPanel botonesPanel = new JPanel(new GridLayout(4, 1));
		ordenarButton = new JButton("Ordenar");
		hashButton = new JButton("Buscar con Hash");
		interpolationSearchButton = new JButton("Búsqueda por Interpolación");
		salirButton = new JButton("Salir");

		ordenarButton.addActionListener(e -> ordenar());
		hashButton.addActionListener(e -> buscarHash());
		interpolationSearchButton.addActionListener(e -> busquedaInterpolacion());
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

	private void generarArreglos() {
		arreglo1 = generarArregloAleatorio(1000);
		arreglo2 = generarArregloAleatorio(1000);
		arregloHash = generarArregloAleatorio(50000);
	}

	private void ordenar() {
		int[] arreglo = generarArregloAleatorio(50000);

		resultadosArea.setText("Arreglo antes de ordenar:\n" + Arrays.toString(arreglo) + "\n\n");

		long startTime = System.currentTimeMillis();
		mergeSort(arreglo, 0, arreglo.length - 1); // Método de ordenamiento: MergeSort
		long endTime = System.currentTimeMillis();
		long tiempoMergeSort = endTime - startTime;

		resultadosArea.append("Arreglo después de ordenar:\n" + Arrays.toString(arreglo) + "\n");
		resultadosArea.append("Tiempo de MergeSort: " + tiempoMergeSort + " milisegundos");
	}

	private void buscarHash() {
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		for (int num : arreglo2) {
			hashMap.put(num, num);
		}

		long startTimeHash = System.currentTimeMillis();
		int encontradosHash = 0;
		for (int num : arregloHash) {
			if (hashMap.containsKey(num)) {
				encontradosHash++;
			}
		}
		long endTimeHash = System.currentTimeMillis();
		long tiempoHash = endTimeHash - startTimeHash;

		resultadosArea.setText("Resultados de búsqueda con Hash:\n");
		resultadosArea.append("Elementos encontrados: " + encontradosHash + "\n");
		resultadosArea.append("Tiempo de búsqueda con Hash: " + tiempoHash + " milisegundos");
	}

	private void busquedaInterpolacion() {
		// Ordenar arreglo1 y arreglo2
		mergeSort(arreglo1, 0, arreglo1.length - 1);
		mergeSort(arreglo2, 0, arreglo2.length - 1);

		int encontradosInterpolacion = 0;
		long startTimeInterpolacion = System.currentTimeMillis();

		for (int num : arreglo1) {
			if (busquedaInterpolacionEnArreglo(num, arreglo2)) {
				encontradosInterpolacion++;
			}
		}

		long endTimeInterpolacion = System.currentTimeMillis();
		long tiempoInterpolacion = endTimeInterpolacion - startTimeInterpolacion;

		resultadosArea.setText("Resultados de búsqueda por Interpolación:\n");
		resultadosArea.append("Elementos encontrados: " + encontradosInterpolacion + "\n");
		resultadosArea.append("Tiempo de búsqueda por Interpolación: " + tiempoInterpolacion + " milisegundos");
	}

	private boolean busquedaInterpolacionEnArreglo(int elementoBuscado, int[] arreglo) {
		int bajo = 0;
		int alto = arreglo.length - 1;

		while (bajo <= alto && elementoBuscado >= arreglo[bajo] && elementoBuscado <= arreglo[alto]) {
			if (bajo == alto) {
				return arreglo[bajo] == elementoBuscado;
			}

			int posicionEstimada = bajo
					+ ((elementoBuscado - arreglo[bajo]) * (alto - bajo)) / (arreglo[alto] - arreglo[bajo]);

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

	public int[] generarArregloAleatorio(int tamaño) {
		Random random = new Random();
		int[] arreglo = new int[tamaño];
		for (int i = 0; i < tamaño; i++) {
			arreglo[i] = random.nextInt(999999) + 100000; // Rango de valores entre 100,000 y 999,999
		}
		return arreglo;
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

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Final2 app = new Final2();
			app.setVisible(true);
		});
	}
}