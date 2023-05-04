/*
 * Colby King
 * CS2300
 * Programming Assignment 4 Part 1
 * Due: May 5, 2023
 */

import java.io.*;
import java.util.*;

public class paAssignment4Par1 {
	public static void main(String[] args) throws FileNotFoundException {
		// Read input data from the input file
		File inputFile = new File("input_1.txt");
		Scanner scanner = new Scanner(inputFile);
		Vector4 planePoint = new Vector4(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
		Vector4 planeNormal = new Vector4(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
		Vector4 projectionDirection = new Vector4(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());

		// Read the points from the input file
		List<Vector4> points = new ArrayList<>();
		while (scanner.hasNext()) {
			points.add(new Vector4(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble()));
		}
		scanner.close();

		// Perform parallel and perspective projections
		List<Vector4> parallelProjection = parallelProjection(points, planePoint, planeNormal, projectionDirection);
		List<Vector4> perspectiveProjection = perspectiveProjection(points, planePoint, planeNormal);

		// Save the projected points to separate output files
		savePointsToFile(parallelProjection, "parallel_projection1.txt");
		savePointsToFile(perspectiveProjection, "perspective_projection1.txt");
	}

	// Function to perform parallel projection
	private static List<Vector4> parallelProjection(List<Vector4> points, Vector4 planePoint, Vector4 planeNormal,
			Vector4 projectionDirection) {
		List<Vector4> result = new ArrayList<>();
		for (Vector4 point : points) {
			Vector4 Vector4ToPoint = point.subtract(planePoint);
			double scale = -planeNormal.dot(Vector4ToPoint) / planeNormal.dot(projectionDirection);
			Vector4 projectedPoint = point.add(projectionDirection.scale(scale));
			result.add(projectedPoint);
		}
		return result;
	}

	// Function to perform perspective projection
	private static List<Vector4> perspectiveProjection(List<Vector4> points, Vector4 planePoint, Vector4 planeNormal) {
		List<Vector4> result = new ArrayList<>();
		for (Vector4 point : points) {
			Vector4 projectionDirection = point.scale(-1);
			Vector4 Vector4ToPoint = point.subtract(planePoint);
			double scale = -planeNormal.dot(Vector4ToPoint) / planeNormal.dot(projectionDirection);
			Vector4 projectedPoint = point.add(projectionDirection.scale(scale));
			result.add(projectedPoint);
		}
		return result;
	}

	// Function to save projected points to a file
	private static void savePointsToFile(List<Vector4> points, String fileName) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(fileName);
		for (Vector4 point : points) {
			writer.printf(Locale.US, "%.6f %.6f %.6f\n", point.x, point.y, point.z);
		}
		writer.close();
	}
}

//Class to represent 3D Vector4s and perform Vector4 operations
class Vector4 {
	double x, y, z;

	// Constructor
	Vector4(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// Add two Vector4s
	Vector4 add(Vector4 v) {
		return new Vector4(x + v.x, y + v.y, z + v.z);
	}

	// Subtract a Vector4 from another Vector4
	Vector4 subtract(Vector4 v) {
		return new Vector4(x - v.x, y - v.y, z - v.z);
	}

	// Scale a Vector4 by a scalar value
	Vector4 scale(double scalar) {
		return new Vector4(x * scalar, y * scalar, z * scalar);
	}

	// Calculate the dot product of two Vector4s
	double dot(Vector4 v) {
		return x * v.x + y * v.y + z * v.z;
	}

	// Calculate the cross product of two Vector4s
	Vector4 cross(Vector4 v) {
		return new Vector4(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	}

	// Normalize a Vector4 (convert to a unit Vector4)
	Vector4 normalize() {
		double length = Math.sqrt(x * x + y * y + z * z);
		return new Vector4(x / length, y / length, z / length);
	}
}// Vector4 Class
