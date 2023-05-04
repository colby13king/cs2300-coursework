/*
 * Colby King
 * CS2300
 * Program Assigment 2 Part 1
 */

import java.util.Scanner;

public class paAssignment2 {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 1 for implicit equation, 2 for parametric equation:");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Enter coefficients A, B, and C for the implicit equation Ax + By = C:");
            double a = scanner.nextDouble();
            double b = scanner.nextDouble();
            double c = scanner.nextDouble();

            convertImplicitToParametric(a, b, c);
        } else if (choice == 2) {
            System.out.println("Enter x0, y0, and the direction Vector4 (vx, vy) for the parametric equation:");
            double x0 = scanner.nextDouble();
            double y0 = scanner.nextDouble();
            double vx = scanner.nextDouble();
            double vy = scanner.nextDouble();

            convertParametricToImplicit(x0, y0, vx, vy);
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    public static void convertImplicitToParametric(double a, double b, double c) {
        if (a == 0 && b == 0) {
            System.out.println("Invalid coefficients. A and B cannot both be zero.");
            return;
        }

        double x0, y0, vx, vy;
        if (b != 0) {
            x0 = 0;
            y0 = c / b;
            vx = 1;
            vy = -a / b;
        } else {
            x0 = c / a;
            y0 = 0;
            vx = 0;
            vy = 1;
        }

        System.out.printf("Parametric equation: x = %.2f + %.2f * t, y = %.2f + %.2f * t\n", x0, vx, y0, vy);
    }

    public static void convertParametricToImplicit(double x0, double y0, double vx, double vy) {
        if (vx == 0 && vy == 0) {
            System.out.println("Invalid direction Vector4. vx and vy cannot both be zero.");
            return;
        }

        double a = vy;
        double b = -vx;
        double c = x0 * vy - y0 * vx;

        System.out.printf("Implicit equation: %.2f * x + %.2f * y = %.2f\n", a, b, c);
    }
}