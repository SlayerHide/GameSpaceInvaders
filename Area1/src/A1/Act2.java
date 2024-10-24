package A1;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;

public class Act2 {

	    private static int[] originalArray;
	    private static int[] shellSortedArray;
	    private static int[] quickSortedArray;
	    private static JTextArea textArea;
	    
	    public static void main(String[] args) {
	        originalArray = generateRandomArray(100);
	        shellSortedArray = Arrays.copyOf(originalArray, originalArray.length);
	        quickSortedArray = Arrays.copyOf(originalArray, originalArray.length);

	        JFrame frame = new JFrame("Tiempos de Ordenamiento");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLayout(new BorderLayout());

	        JButton shellSortButton = new JButton("Ordenar con Shell Sort");
	        shellSortButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                long startTime = System.currentTimeMillis();
	                shellSort(shellSortedArray);
	                long endTime = System.currentTimeMillis();
	                displayResults("Shell Sort", endTime - startTime);
	            }
	        });

	        JButton quickSortButton = new JButton("Ordenar con Quick Sort");
	        quickSortButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                long startTime = System.currentTimeMillis();
	                quickSort(quickSortedArray, 0, quickSortedArray.length - 1);
	                long endTime = System.currentTimeMillis();
	                displayResults("Quick Sort", endTime - startTime);
	            }
	        });

	        JPanel buttonPanel = new JPanel();
	        buttonPanel.add(shellSortButton);
	        buttonPanel.add(quickSortButton);

	        textArea = new JTextArea(10, 30);
	        textArea.setWrapStyleWord(true);
	        textArea.setLineWrap(true);
	        JScrollPane scrollPane = new JScrollPane(textArea);

	        frame.add(buttonPanel, BorderLayout.NORTH);
	        frame.add(scrollPane, BorderLayout.CENTER);
	        frame.pack();
	        frame.setVisible(true);
	    }
	    
	    public static int[] generateRandomArray(int size) {
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

	    public static void shellSort(int[] arr) {
	        int n = arr.length;
	        for (int gap = n / 2; gap > 0; gap /= 2) {
	            for (int i = gap; i < n; i++) {
	                int temp = arr[i];
	                int j;
	                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
	                    arr[j] = arr[j - gap];
	                }
	                arr[j] = temp;
	            }
	        }
	    }

	    public static void quickSort(int[] arr, int low, int high) {
	        if (low < high) {
	            int pivotIndex = partition(arr, low, high);
	            quickSort(arr, low, pivotIndex - 1);
	            quickSort(arr, pivotIndex + 1, high);
	        }
	    }

	    public static int partition(int[] arr, int low, int high) {
	        int pivot = arr[high];
	        int i = (low - 1);
	        for (int j = low; j < high; j++) {
	            if (arr[j] < pivot) {
	                i++;
	                int temp = arr[i];
	                arr[i] = arr[j];
	                arr[j] = temp;
	            }
	        }
	        int temp = arr[i + 1];
	        arr[i + 1] = arr[high];
	        arr[high] = temp;
	        return i + 1;
	    }

	    public static void displayResults(String algorithm, long time) {
			textArea.append(algorithm + " tomó " + time + " ms\n");
	    }
	    
}
