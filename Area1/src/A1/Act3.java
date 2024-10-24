package A1;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;

public class Act3 {
	private static int[] originalArray;
    private static int[] sortedArray;
    private static JTextArea textArea;
    private static JTextField targetTextField;

    public static void main(String[] args) {
        originalArray = generateRandomArray(1000);
        sortedArray = Arrays.copyOf(originalArray, originalArray.length);
        Arrays.sort(sortedArray);

        JFrame frame = new JFrame("Tiempos de Búsqueda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JButton sequentialSearchButton = new JButton("Búsqueda Secuencial");
        sequentialSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int target = Integer.parseInt(targetTextField.getText());
                long startTime = System.currentTimeMillis();
                int index = sequentialSearch(originalArray, target);
                long endTime = System.currentTimeMillis();
                displayResults("Búsqueda Secuencial", target, index, endTime - startTime);
            }
        });

        JButton binarySearchButton = new JButton("Búsqueda Binaria");
        binarySearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int target = Integer.parseInt(targetTextField.getText());
                long startTime = System.currentTimeMillis();
                int index = binarySearch(sortedArray, target);
                long endTime = System.currentTimeMillis();
                displayResults("Búsqueda Binaria", target, index, endTime - startTime);
            }
        });

        targetTextField = new JTextField(10);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JLabel("Número a buscar: "));
        buttonPanel.add(targetTextField);
        buttonPanel.add(sequentialSearchButton);
        buttonPanel.add(binarySearchButton);

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

	    public static int sequentialSearch(int[] arr, int target) {
	        for (int i = 0; i < arr.length; i++) {
	            if (arr[i] == target) {
	                return i;
	            }
	        }
	        return -1;
	    }

	    public static int binarySearch(int[] arr, int target) {
	        int left = 0;
	        int right = arr.length - 1;
	        while (left <= right) {
	            int mid = left + (right - left) / 2;
	            if (arr[mid] == target) {
	                return mid;
	            }
	            if (arr[mid] < target) {
	                left = mid + 1;
	            } else {
	                right = mid - 1;
	            }
	        }
	        return -1;
	    }

	    public static void displayResults(String algorithm, int target, int index, long time) {
	        textArea.append(algorithm + " para buscar " + target + " tomó " + time + " ms. Índice encontrado: " + index + "\n");
	    }

}
