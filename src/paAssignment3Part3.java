/*
 * Colby King
 * CS2300
 * Programming Assignment 3 Part 3
 * Due: Apr 14, 2023
 */


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.File;

public class paAssignment3Part3 {
	 public static void main(String[] args) throws IOException {
	        // Read input file
	        Scanner scanner = new Scanner(new File("3D_test_input_1.txt"));

	        // Count the number of points
	        int pointCount = scanner.nextInt();

	        // Check if the input is 2D or 3D
	        boolean is3D = pointCount == 3;

	        // Read the points from input file
	        double[][] points = new double[pointCount][is3D ? 3 : 2];
	        for (int i = 0; i < pointCount; i++) {
	            for (int j = 0; j < (is3D ? 3 : 2); j++) {
	                points[i][j] = scanner.nextDouble();
	            }
	        }
	        scanner.close();

	        // Calculate area of the triangle
	        double area = calculateArea(points);

	        // Calculate distance from third point to line or plane
	        double distance = calculateDistance(points, is3D);

	        // Create a DecimalFormat object to format the output
	        DecimalFormat df = new DecimalFormat("#.####");

	        // Print output
	        System.out.println(df.format(area));
	        System.out.println(df.format(distance));
	    }

	    private static double calculateArea(double[][] points) {
	        // Use the Shoelace formula to calculate the area
	        return 0.5 * Math.abs((points[0][0] * (points[1][1] - points[2][1])) +
	                (points[1][0] * (points[2][1] - points[0][1])) +
	                (points[2][0] * (points[0][1] - points[1][1])));
	    }

	    private static double calculateDistance(double[][] points, boolean is3D) {
	        if (is3D) {
	            // Calculate distance to the plane
	            double[] normal = calculateNormal(points);
	            double planeConstant = -normal[0] * points[0][0] - normal[1] * points[0][1] - normal[2] * points[0][2];
	            return Math.abs(normal[0] * points[2][0] + normal[1] * points[2][1] + normal[2] * points[2][2] + planeConstant) /
	                    Math.sqrt(normal[0] * normal[0] + normal[1] * normal[1] + normal[2] * normal[2]);
	        } else {
	            // Calculate distance to the line
	            double[] line = calculateLine(points);
	            return Math.abs(line[0] * points[2][0] - points[2][1] + line[1]) / Math.sqrt(line[0] * line[0] + 1);
	        }
	    }
	    
	    private static double[] calculateNormal(double[][] points) {
	        double[] Vector41 = {points[1][0] - points[0][0], points[1][1] - points[0][1], points[1][2] - points[0][2]};
	        double[] Vector42 = {points[2][0] - points[0][0], points[2][1] - points[0][1], points[2][2] - points[0][2]};
	        return new double[]{
	                Vector41[1] * Vector42[2] - Vector41[2] * Vector42[1],
	                Vector41[2] * Vector42[0] - Vector41[0] * Vector42[2],
	                Vector41[0] * Vector42[1] - Vector41[1] * Vector42[0]};
	    }


	    private static double[] calculateLine(double[][] points) {
	        double slope = (points[1][1] - points[0][1]) / (points[1][0] - points[0][0]);
	        double intercept = points[0][1] - slope * points[0][0];
	        return new double[]{slope, intercept};
	    }
	}
