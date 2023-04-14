/*
 * Colby King
 * CS2300
 * Program Assigment 2 Part 2
 */

import java.util.Scanner;

public class paAssignment2B {
	
		public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        // Prompt the user to enter a 2x2 matrix
	        System.out.println("Enter the elements of the 2x2 matrix:");
	        double[][] matrix = new double[2][2];
	        for (int i = 0; i < 2; i++) {
	            for (int j = 0; j < 2; j++) {
	                matrix[i][j] = scanner.nextDouble();
	            }
	        }

	        // Calculate the determinant to check if an inverse exists
	        double determinant = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
	        if (determinant == 0) {
	            System.out.println("The matrix has no inverse.");
	        } else {
	            // Calculate the inverse using Gauss elimination
	            double[][] inverse = new double[2][2];
	            inverse[0][0] = matrix[1][1] / determinant;
	            inverse[0][1] = -matrix[0][1] / determinant;
	            inverse[1][0] = -matrix[1][0] / determinant;
	            inverse[1][1] = matrix[0][0] / determinant;

	            // Display the inverse matrix
	            System.out.println("The inverse matrix is:");
	            for (int i = 0; i < 2; i++) {
	                for (int j = 0; j < 2; j++) {
	                    System.out.printf("%.2f ", inverse[i][j]);
	                }
	                System.out.println();
	            }
	        }

	        scanner.close();
	    }
	}