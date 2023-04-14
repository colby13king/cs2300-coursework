/*
 * Colby King
 * CS2300
 * Programming Assignment 3 Part 1
 * Due: Apr 14, 2023
 */

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class paAssignment3 {
	 public static void main(String[] args) throws IOException {
	        // Read input file
	        double[][] matrix = new double[2][3];
	        Scanner scanner = new Scanner(new File("pa3_test_input_1.txt"));
	        
	        // Fill the matrix with values from input file
	        for (int i = 0; i < 2; i++) {
	            for (int j = 0; j < 3; j++) {
	                matrix[i][j] = scanner.nextDouble();
	            }
	        }
	        scanner.close();

	        // Extract values from the matrix
	        double a = matrix[0][0];
	        double b = matrix[0][1];
	        double c = matrix[1][0];
	        double d = matrix[1][1];
	        double e = matrix[0][2];
	        double f = matrix[1][2];

	        // Calculate the determinant
	        double det = a * d - b * c;

	        // Create a Decimal Format object to format the output
	        DecimalFormat df = new DecimalFormat("#.####");

	        // Check if the determinant is non-zero
	        if (det != 0) {
	            // Unique solution
	            double x1 = (d * e - b * f) / det;
	            double x2 = (a * f - c * e) / det;

	            System.out.println(df.format(x1));
	            System.out.println(df.format(x2));
	        } else if (a == 0 && b == 0 && e != 0 || c == 0 && d == 0 && f != 0) {
	            // Inconsistent system
	            System.out.println("System inconsistent");
	        } else {
	            // Underdetermined system
	            System.out.println("System underdetermined");
	        }
	    }
}
