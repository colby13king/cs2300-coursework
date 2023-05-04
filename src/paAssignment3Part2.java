/*
 * Colby King
 * CS2300
 * Programming Assignment 3 Part 2
 * Due: Apr 14, 2023
 */


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.File;

public class paAssignment3Part2 {


    public static void main(String[] args) throws IOException {
        // Read input file
        double[][] matrix = new double[2][2];
        Scanner scanner = new Scanner(new File("test_input_2.txt"));

        // Fill the matrix with values from input file
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        scanner.close();

        // Calculate eigenvalues
        double a = matrix[0][0];
        double b = matrix[0][1];
        double c = matrix[1][0];
        double d = matrix[1][1];

        double trace = a + d;
        double det = a * d - b * c;
        double discriminant = trace * trace - 4 * det;

        // Create a DecimalFormat object to format the output
        DecimalFormat df = new DecimalFormat("#.####");

        if (discriminant < 0) {
            // No real eigenvalues
            System.out.println("No real eigenvalues");
        } else {
            // Calculate real eigenvalues
            double lambda1 = (trace + Math.sqrt(discriminant)) / 2;
            double lambda2 = (trace - Math.sqrt(discriminant)) / 2;

            // Calculate eigenVector4s
            double[][] R = new double[2][2];

            for (int i = 0; i < 2; i++) {
                double lambda = (i == 0) ? lambda1 : lambda2;
                double norm = Math.sqrt((b * b) + (lambda - a) * (lambda - a));
                R[0][i] = b / norm;
                R[1][i] = (lambda - a) / norm;
            }

            // Calculate RΛRᵀ
            double[][] composition = new double[2][2];
            double[][] Lambda = {{lambda1, 0}, {0, lambda2}};

            // Matrix multiplication R * Λ
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        composition[i][j] += R[i][k] * Lambda[k][j];
                    }
                }
            }

            double[][] finalMatrix = new double[2][2];

            // Matrix multiplication (R * Λ) * Rᵀ
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        finalMatrix[i][j] += composition[i][k] * R[j][k];
                    }
                }
            }

            // Print output
            // Λ matrix
            System.out.println(df.format(lambda1) + " 0");
            System.out.println("0 " + df.format(lambda2));

            // R matrix
            for (int i = 0; i < 2; i++) {
                System.out.println(df.format(R[i][0]) + " " + df.format(R[i][1]));
            }

            // RΛRᵀ matrix
            for (int i = 0; i < 2; i++) {
                System.out.println(df.format(finalMatrix[i][0]) + " " + df.format(finalMatrix[i][1]));
            }
        }
    }
}

