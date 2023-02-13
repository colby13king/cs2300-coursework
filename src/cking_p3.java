/*
 * Colby King
 * Programming Assignment 1
 * CS2300
 * Due Feb 12, 2023
 * 
 * PART 3
 */

import java.io.*;
import java.util.Scanner;

public class cking_p3 {

	public static void main(String[] args) throws IOException {

		 Scanner in = new Scanner(System.in);
		    System.out.println("Enter the names of the two matrices to be multiplied: ");
		    String firstMatrixName = in.next();
		    String secondMatrixName = in.next();
		    int[][] firstMatrix = readMatrixFromFile(firstMatrixName + ".txt");
		    int[][] secondMatrix = readMatrixFromFile(secondMatrixName + ".txt");
		    int[][] result = multiplyMatrices(firstMatrix, secondMatrix);
		    String outputFilename = "name_p3_out" + firstMatrixName.substring(3) + secondMatrixName.substring(3) + ".txt";
		    writeMatrixToFile(result, outputFilename);
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

		  // This method computes the matrix multiplication of two matrices
		  private static int[][] multiplyMatrices(int[][] firstMatrix, int[][] secondMatrix) {
		    int rows = firstMatrix.length;
		    int columns = secondMatrix[0].length;
		    int[][] result = new int[rows][columns];
		    for (int i = 0; i < rows; i++) {
		      for (int j = 0; j < columns; j++) {
		        result[i][j] = 0;
		        for (int k = 0; k < firstMatrix[0].length; k++) {
		          result[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
		        }
		      }
		    }
		    return result;
		  }
		}