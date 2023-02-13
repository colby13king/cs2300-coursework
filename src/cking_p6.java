/*
 * Colby King
 * Programming Assignment 1
 * CS2300
 * Due Feb 12, 2023
 * 
 * PART 6
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class cking_p6 {

	public static void main(String[] args) throws IOException {
	    // Define the matrices from the files in part 1
		 int[][] mat1 = readMatrixFromFile("cking_mat1.txt");
		  int[][] mat2 = readMatrixFromFile("cking_mat2.txt");
		  int[][] mat3 = readMatrixFromFile("cking_mat3.txt");
		  int[][] mat4 = readMatrixFromFile("cking_mat4.txt");
		  int[][] mat5 = readMatrixFromFile("cking_mat5.txt");
		  int[][] mat6 = readMatrixFromFile("cking_mat6.txt");

		  //transposes the matrix
		  int[][] transposedMat1 = transposeMatrix(mat1);
		  int[][] transposedMat2 = transposeMatrix(mat2);
		  int[][] transposedMat3 = transposeMatrix(mat3);
		  int[][] transposedMat4 = transposeMatrix(mat4);
		  int[][] transposedMat5 = transposeMatrix(mat5);
		  int[][] transposedMat6 = transposeMatrix(mat6);

		  //writes the transposed matrix to a new file
		  writeMatrixToFile(transposedMat1, "cking_p6_mat1.txt");
		  writeMatrixToFile(transposedMat2, "cking_p6_mat2.txt");
		  writeMatrixToFile(transposedMat3, "cking_p6_mat3.txt");
		  writeMatrixToFile(transposedMat4, "cking_p6_mat4.txt");
		  writeMatrixToFile(transposedMat5, "cking_p6_mat5.txt");
		  writeMatrixToFile(transposedMat6, "cking_p6_mat6.txt");
	  }
	  
	  // This method transposes a matrix
	  private static int[][] transposeMatrix(int[][] matrix) {
	    int rows = matrix.length;
	    int columns = matrix[0].length;
	    int[][] transposedMatrix = new int[columns][rows];
	    for (int i = 0; i < rows; i++) {
	      for (int j = 0; j < columns; j++) {
	        transposedMatrix[j][i] = matrix[i][j];
	      }
	    }
	    return transposedMatrix;
	  }

	  // This method writes a matrix to a file
	  private static void writeMatrixToFile(int[][] matrix, String filename) throws IOException {
	    FileWriter fileWriter = new FileWriter(filename);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    int rows = matrix.length;
	    int columns = matrix[0].length;
	    printWriter.println(rows + " " + columns);
	    for (int i = 0; i < rows; i++) {
	      for (int j = 0; j < columns; j++) {
	        printWriter.print(matrix[i][j] + " ");
	      }
	      printWriter.println();
	    }
	    printWriter.close();
	  }
	  
	  // This method reads a matrix from a file
	  private static int[][] readMatrixFromFile(String filename) throws IOException {
	    File file = new File(filename);
	    Scanner in = new Scanner(file);
	    int rows = in.nextInt();
	    int columns = in.nextInt();
	    int[][] matrix = new int[rows][columns];
	    for (int i = 0; i < rows; i++) {
	      for (int j = 0; j < columns; j++) {
	        matrix[i][j] = in.nextInt();
	      }
	    }
	    return matrix;
	  }
	}


