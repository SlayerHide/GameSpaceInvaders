package A1;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Act1 {
	 public static void main(String...args) {
	        int[] arr = randomArray(10000); 

	        long tiempoIni, tiempoFinal;
	        String resultados = "";
	        int datoBuscar = 10;

	        int[] burbujadArr = Arrays.copyOf(arr, arr.length);
	        tiempoIni = System.currentTimeMillis();
	        burbuja(burbujadArr);
	        tiempoFinal = System.currentTimeMillis();
	        resultados += "Tiempo de burbuja: " + (tiempoFinal - tiempoIni) + " ms\n";

	        int[] seleccionardArr = Arrays.copyOf(arr, arr.length);
	        tiempoIni = System.currentTimeMillis();
	        seleccionar(seleccionardArr);
	        tiempoFinal = System.currentTimeMillis();
	        resultados += "Tiempo de selección: " + (tiempoFinal - tiempoIni) + " ms\n";

	        int[] insersiondArr = Arrays.copyOf(arr, arr.length);
	        tiempoIni = System.currentTimeMillis();
	        insersion(insersiondArr);
	        tiempoFinal = System.currentTimeMillis();
	        resultados += "Tiempo de inserción: " + (tiempoFinal - tiempoIni) + " ms\n";

	        JTextArea textArea = new JTextArea(10, 30);
	        textArea.setText(resultados);
	        textArea.setWrapStyleWord(true);
	        textArea.setLineWrap(true);

	        JScrollPane scrollPane = new JScrollPane(textArea);
	        
	        JFrame frame = new JFrame("Tiempos de Ordenamiento");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.add(scrollPane);
	        frame.pack();
	        frame.setVisible(true);
	    }

	    public static int[] randomArray(int size) {
	        int[] arr = new int[size];
	        for (int i = 0; i < size; i++) {
	            arr[i] = i;
	        }
	        Random rand = new Random();
	        for (int i = size - 1; i > 0; i--) {
	            int j = rand.nextInt(i + 1);
	            int temp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = temp;
	        }
	        return arr;
	    }

	    public static void burbuja(int[] arr) {
	        int n = arr.length;
	        for (int i = 0; i < n - 1; i++) {
	            for (int j = 0; j < n - i - 1; j++) {
	                if (arr[j] > arr[j + 1]) {
	                    int temp = arr[j];
	                    arr[j] = arr[j + 1];
	                    arr[j + 1] = temp;
	                }
	            }
	        }
	    }

	    public static void seleccionar(int[] arr) {
	        int n = arr.length;
	        for (int i = 0; i < n - 1; i++) {
	            int minIndex = i;
	            for (int j = i + 1; j < n; j++) {
	                if (arr[j] < arr[minIndex]) {
	                    minIndex = j;
	                }
	            }
	            int temp = arr[i];
	            arr[i] = arr[minIndex];
	            arr[minIndex] = temp;
	        }
	    }

	    public static void insersion(int[] arr) {
	        int n = arr.length;
	        for (int i = 1; i < n; i++) {
	            int key = arr[i];
	            int j = i - 1;
	            while (j >= 0 && arr[j] > key) {
	                arr[j + 1] = arr[j];
	                j--;
	            }
	            arr[j + 1] = key;
	        }
	    }

}
