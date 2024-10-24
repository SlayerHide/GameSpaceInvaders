package A4;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
public class Final extends JFrame{
	 private JButton ordenarButton, hashButton, exponentialSearchButton, salirButton;
	    private JTextArea resultadosArea;

	    private int[] arreglo1;
	    private int[] arreglo2;
	    private int[] arregloHash;

	    public Final() {
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
	        ordenarButton = new JButton("Ordenar Marge");
	        hashButton = new JButton("Buscar con Hash");
	        exponentialSearchButton = new JButton("Búsqueda Exponencial");
	        salirButton = new JButton("Salir");

	        ordenarButton.addActionListener(e -> ordenar());
	        hashButton.addActionListener(e -> buscarHash());
	        exponentialSearchButton.addActionListener(e -> busquedaExponencial());
	        salirButton.addActionListener(e -> System.exit(0));

	        botonesPanel.add(ordenarButton);
	        botonesPanel.add(hashButton);
	        botonesPanel.add(exponentialSearchButton);
	        botonesPanel.add(salirButton);
	        add(botonesPanel, BorderLayout.NORTH);

	        resultadosArea = new JTextArea();
	        JScrollPane scrollPane = new JScrollPane(resultadosArea);
	        add(scrollPane, BorderLayout.CENTER);
	    		    }
	    
	    private void generarArreglos() {
	        arreglo1 = generarArregloAleatorio(100000);
	        arreglo2 = generarArregloAleatorio(100000);
	        arregloHash = generarArregloAleatorio(50000);
	    }

	    public void ordenar() {
	    	 int[] arreglo = generarArregloAleatorio(50000);

	         resultadosArea.setText("Arreglo antes de ordenar:\n" + Arrays.toString(arreglo) + "\n\n");

	         long startTime = System.currentTimeMillis();
	         mergeSort(arreglo, 0, arreglo.length - 1); // Método de ordenamiento: MergeSort
	         long endTime = System.currentTimeMillis();
	         long tiempoMergeSort = endTime - startTime;

	         resultadosArea.append("Arreglo después de ordenar:\n" + Arrays.toString(arreglo) + "\n");
	         resultadosArea.append("Tiempo de MergeSort: " + tiempoMergeSort + " milisegundos");
	    }
	    
	    //Metodo de Busqueda

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

			    resultadosArea.setText("Resultados de búsqueda con Hash en Arreglos:\n");
			    resultadosArea.append("Elementos encontrados en arreglo1: " + encontradosEnArreglo1 + "\n");
			    resultadosArea.append("Elementos encontrados en arreglo2: " + encontradosEnArreglo2 + "\n");
			    resultadosArea.append("Tiempo de búsqueda con Hash: " + tiempoHash + " milisegundos");
	    }
	    
	    //Metodo de Busqueda
	    
	    private void busquedaExponencial() {
	    	Arrays.sort(arreglo2); // Asegurar que el arreglo esté ordenado

	        long startTimeExponential = System.currentTimeMillis();
	        int encontradosExponential = 0;

	        for (int num : arreglo1) {
	            if (busquedaExponencialEnArreglo(num, arreglo2)) {
	                encontradosExponential++;
	            }
	        }

	        long endTimeExponential = System.currentTimeMillis();
	        long tiempoExponential = endTimeExponential - startTimeExponential;

	        resultadosArea.setText("Resultados de búsqueda Exponencial:\n");
	        resultadosArea.append("Elementos encontrados: " + encontradosExponential + "\n");
	        resultadosArea.append("Tiempo de búsqueda Exponencial: " + tiempoExponential + " milisegundos");
	    }
	    
	    private boolean busquedaExponencialEnArreglo(int elementoBuscado, int[] arreglo) {
	        if (arreglo[0] == elementoBuscado) {
	            return true;
	        }

	        int i = 1;
	        while (i < arreglo.length && arreglo[i] <= elementoBuscado) {
	            i *= 2;
	        }

	        int low = i / 2;
	        int high = Math.min(i, arreglo.length - 1);

	        while (low <= high) {
	            int mid = low + (high - low) / 2;
	            if (arreglo[mid] == elementoBuscado) {
	                return true;
	            } else if (arreglo[mid] < elementoBuscado) {
	                low = mid + 1;
	            } else {
	                high = mid - 1;
	            }
	        }

	        return false;
	    }

	    public static int[] generarArregloAleatorio(int tamaño) {
	        Random random = new Random();
	        int[] arreglo = new int[tamaño];
	        for (int i = 0; i < tamaño; i++) {
	            arreglo[i] = random.nextInt(999999) + 100000; // Rango de valores entre 100,000 y 999,999
	        }
	        return arreglo;
	    }

	    //Metodo de Ordenamiento 
	    public static void mergeSort(int[] arreglo, int izquierda, int derecha) {
	        if (izquierda < derecha) {
	            int medio = (izquierda + derecha) / 2;
	            mergeSort(arreglo, izquierda, medio);
	            mergeSort(arreglo, medio + 1, derecha);
	            merge(arreglo, izquierda, medio, derecha);
	        }
	    }

	    public static void merge(int[] arreglo, int izquierda, int medio, int derecha) {
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
	            Final app = new Final();
	            app.setVisible(true);
	        });
	    }
}
