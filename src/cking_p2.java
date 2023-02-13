/*
 * Colby King
 * Programming Assignment 1
 * CS2300
 * Due Feb 12, 2023
 * 
 * PART 2
 */

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.*;

public class cking_p2 {

	public static void main(String[] args) throws IOException {
		// Create a Scanner object to read input from the user
		Scanner input = new Scanner(System.in);
		// Prompt the user to enter the name of the first input matrix
		System.out.print("Enter the first input matrix name (e.g. Mat1): ");
		String firstMatrixName = input.nextLine();
		// Prompt the user to enter the name of the second input matrix
		System.out.print("Enter the second input matrix name (e.g. Mat2): ");
		String secondMatrixName = input.nextLine();
		// Read the first matrix from its file
		int[][] firstMatrix = readMatrixFromFile(firstMatrixName + ".txt");
		// Read the second matrix from its file
		int[][] secondMatrix = readMatrixFromFile(secondMatrixName + ".txt");

		// Check if the two matrices have the same dimensions
		if (firstMatrix.length != secondMatrix.length || firstMatrix[0].length != secondMatrix[0].length) {
			System.out.println("Error: The two matrices have different dimensions and cannot be added.");
			return;
		}

		// Compute the element-wise sum of the two matrices
		int[][] result = addMatrices(firstMatrix, secondMatrix);
		// Construct the name of the output file
		String outputFileName = "name_p2_out" + firstMatrixName.substring(3) + secondMatrixName.substring(3) + ".txt";
		// Write the result matrix to the output file
		writeMatrixToFile(result, outputFileName);
		// Print a message to the console indicating that the result has been written to
		// the output file
		System.out.println("The result matrix has been written to " + outputFileName);
		
		input.close();
	}

	// This method reads a matrix from a file and returns it as a 2D array of
	// integers
	private static int[][] readMatrixFromFile(String fileName) throws IOException {
		// Create a File object for the input file
		File file = new File(fileName);
		// Create a Scanner object to read the contents of the input file
		Scanner fileInput = new Scanner(file);
		// Read the number of rows and columns of the matrix
		int rows = fileInput.nextInt();
		int columns = fileInput.nextInt();
		// Create a 2D array to store the elements of the matrix
		int[][] matrix = new int[rows][columns];
		// Read the elements of the matrix from the input file
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				matrix[i][j] = fileInput.nextInt();
			}
		}
		// Close the file input stream
		fileInput.close();
		// Return the matrix as a 2D array of integers
		return matrix;
	}

	// This method writes a matrix to a file
	private static void writeMatrixToFile(int[][] matrix, String fileName) throws IOException {
		File file = new File(fileName);
		PrintWriter fileOutput = new PrintWriter(file);
		fileOutput.println(matrix.length + " " + matrix[0].length);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				fileOutput.print(matrix[i][j] + " ");
			}
			fileOutput.println();
		}
		fileOutput.close();
	}

	// This method computes the element-wise sum of two matrices
	private static int[][] addMatrices(int[][] firstMatrix, int[][] secondMatrix) {
		 // Get the number of rows in the matrices
		int rows = firstMatrix.length;
		// Get the number of columns in the matrices
		int columns = firstMatrix[0].length;
		// Create a result matrix to store the element-wise sum
		int[][] result = new int[rows][columns];
		   // Loop through the rows and columns of both matrices
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				// Compute the element-wise sum of the matrices and store in the result matrix
				result[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
			}
		}
		//Return the matrix that was resulted
		return result;
	}
}
