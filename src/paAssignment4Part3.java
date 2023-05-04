/*
 * Colby King
 * CS2300
 * Programming Assignment 4 Part 3
 * Due: May 5, 2023
 */

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class paAssignment4Part3 {
	 // Tolerance and maximum number of iterations for the power method
    private static final double TOLERANCE = 1e-8;
    private static final int MAX_ITERATIONS = 1000;
    
    // Input file name
    private static final String INPUT_FILENAME = "input_2.txt";

    public static void main(String[] args) {
        

        String inputFilename = INPUT_FILENAME;
        double[][] matrix = readMatrixFromFile(inputFilename);

        // Check if the matrix is stochastic and non-negative
        if (matrix == null || !isValidStochasticMatrix(matrix)) {
            System.out.println("Invalid input");
            return;
        }

        int n = matrix.length;
        double[] initialVector = new double[n];
        Arrays.fill(initialVector, 1.0 / n);

        // Calculate the eigenvector using the power method
        double[] eigenvector = powerMethod(matrix, initialVector);
        // Sort the indices based on the eigenvector values (PageRank scores)
        Integer[] indices = sortIndicesByRank(eigenvector);

        // Print the eigenvector and sorted indices
        printEigenvector(eigenvector);
        printSortedIndices(indices);
    }

    // Read the input matrix from the file
    private static double[][] readMatrixFromFile(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            int n = lines.size();
            double[][] matrix = new double[n][n];

            for (int i = 0; i < n; i++) {
                String[] elements = lines.get(i).split("\\s+");
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Double.parseDouble(elements[j]);
                }
            }

            return matrix;
        } catch (IOException | NumberFormatException e) {
            return null;
        }
    }

    // Check if the matrix is stochastic and has non-negative elements
    private static boolean isValidStochasticMatrix(double[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                double value = matrix[i][j];
                if (value < 0.0 || value > 1.0) {
                    return false;
                }
                sum += value;
            }
            if (Math.abs(sum - 1.0) > TOLERANCE) {
                return false;
            }
        }
        return true;
    }

    // Perform the power method to find the eigenvector
    private static double[] powerMethod(double[][] matrix, double[] initialVector) {
        int n = matrix.length;
        double[] x = initialVector;
        double[] y = new double[n];

        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            // Multiply the matrix by the current vector
            for (int i = 0; i < n; i++) {
                y[i] = 0.0;
                for (int j = 0; j < n; j++) {
                    y[i] += matrix[i][j] * x[j];
                }
            }
            
            // Normalize the resulting vector
            double norm = norm(y);
            for (int i = 0; i < n; i++) {
                y[i] /= norm;
            }

            // Check for convergence
            if (difference(x, y) < TOLERANCE) {
                return y;
            }

            // Update the vector for the next iteration
            double[] tmp = x;
            x = y;
            y = tmp;
        }

        return x;
    }

    private static double norm(double[] vector) {
        double sum = 0.0;
        for (double value : vector) {
            sum += value * value;
        }
        return Math.sqrt(sum);
    }

    private static double difference(double[] a, double[] b) {
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += (a[i] - b[i]) * (a[i] - b[i]);
        }
        return Math.sqrt(sum);
    }

    private static Integer[] sortIndicesByRank(double[] eigenvector) {
        int n = eigenvector.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (i, j) -> -Double.compare(eigenvector[i], eigenvector[j]));

        return indices;
    }

    private static void printEigenvector(double[] eigenvector) {
        for (double value : eigenvector) {
            System.out.printf("%.8f ", value);
        }
        System.out.println();
    }

    private static void printSortedIndices(Integer[] indices) {
        for (int index : indices) {
            System.out.print(index + " ");
        }
        System.out.println();
    }
}
            
            