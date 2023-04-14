/*
 * Colby King
 * CS2300
 * Program Assigment 2 Part 4
 */



import java.util.Scanner;

public class paAssignment2D {
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

	        // Calculate the trace and determinant
	        double trace = matrix[0][0] + matrix[1][1];
	        double determinant = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

	        // Display the characteristic equation
	        System.out.printf("Characteristic equation: λ^2 - %.2fλ + %.2f = 0\n", trace, determinant);

	        // Calculate the eigenvalues
	        double eigenvalue1 = (trace + Math.sqrt(trace * trace - 4 * determinant)) / 2;
	        double eigenvalue2 = (trace - Math.sqrt(trace * trace - 4 * determinant)) / 2;

	        // Determine the dominant eigenvalue
	        double dominantEigenvalue = Math.abs(eigenvalue1) > Math.abs(eigenvalue2) ? eigenvalue1 : eigenvalue2;

	        // Display the eigenvalues and the dominant eigenvalue
	        System.out.printf("Eigenvalues: λ1 = %.2f, λ2 = %.2f\n", eigenvalue1, eigenvalue2);
	        System.out.printf("Dominant eigenvalue: %.2f\n", dominantEigenvalue);

	        scanner.close();
	    }
}
