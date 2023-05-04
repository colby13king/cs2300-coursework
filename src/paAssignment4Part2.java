/*
 * Colby King
 * CS2300
 * Programming Assignment 4 Part 2
 * Due: May 5, 2023
 */

import java.io.*;
import java.util.*;

public class paAssignment4Part2 {
	

    public static void main(String[] args) throws FileNotFoundException {
        // Read input data from the input file
        File inputFile = new File("input_2.txt");
        Scanner scanner = new Scanner(inputFile);

        // Read all input lines
        List<String> inputLines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            inputLines.add(scanner.nextLine());
        }
        scanner.close();

        // Compute distances between points and planes
        List<Double> pointPlaneDistances = new ArrayList<>();
        for (String line : inputLines) {
            String[] nums = line.split("\\s+");
            Vector3 planePoint = new Vector3(Double.parseDouble(nums[0]), Double.parseDouble(nums[1]), Double.parseDouble(nums[2]));
            Vector3 planeNormal = new Vector3(Double.parseDouble(nums[3]), Double.parseDouble(nums[4]), Double.parseDouble(nums[5]));
            Vector3 point = new Vector3(Double.parseDouble(nums[6]), Double.parseDouble(nums[7]), Double.parseDouble(nums[8]));
            double distance = pointToPlaneDistance(point, planePoint, planeNormal);
            pointPlaneDistances.add(distance);
        }
        // Save point-plane distances to a file
        saveDistancesToFile(pointPlaneDistances, "point_plane_distances2.txt");

        // Compute line-triangle intersections
        String[] firstLineNums = inputLines.get(0).split("\\s+");
        Vector3 linePoint1 = new Vector3(Double.parseDouble(firstLineNums[0]), Double.parseDouble(firstLineNums[1]), Double.parseDouble(firstLineNums[2]));
        Vector3 linePoint2 = new Vector3(Double.parseDouble(firstLineNums[3]), Double.parseDouble(firstLineNums[4]), Double.parseDouble(firstLineNums[5]));

        List<String> lineTriangleIntersections = new ArrayList<>();
        for (int i = 1; i < inputLines.size(); i++) {
            String[] nums = inputLines.get(i).split("\\s+");
            Vector3 trianglePoint1 = new Vector3(Double.parseDouble(nums[0]), Double.parseDouble(nums[1]), Double.parseDouble(nums[2]));
            Vector3 trianglePoint2 = new Vector3(Double.parseDouble(nums[3]), Double.parseDouble(nums[4]), Double.parseDouble(nums[5]));
            Vector3 trianglePoint3 = new Vector3(Double.parseDouble(nums[6]), Double.parseDouble(nums[7]), Double.parseDouble(nums[8]));

            // Check for intersection and save the result
            Optional<Vector3> intersection = lineTriangleIntersection(linePoint1, linePoint2, trianglePoint1, trianglePoint2, trianglePoint3);
            if (intersection.isPresent()) {
                lineTriangleIntersections.add(String.format(Locale.US, "%.6f %.6f %.6f", intersection.get().x, intersection.get().y, intersection.get().z));
            } else {
                lineTriangleIntersections.add("Does not intersect.");
            }
        }
        // Save line-triangle intersections to a file
        saveIntersectionsToFile(lineTriangleIntersections, "line_triangle_intersections2.txt");
    }

    // Function to calculate the distance between a point and a plane
    private static double pointToPlaneDistance(Vector3 point, Vector3 planePoint, Vector3 planeNormal) {
        Vector3 vectorToPoint = point.subtract(planePoint);
        return Math.abs(planeNormal.dot(vectorToPoint)) / planeNormal.length();
    }
    
    // Function to check for intersection between a line and a triangle
    private static Optional<Vector3> lineTriangleIntersection(Vector3 linePoint1, Vector3 linePoint2, Vector3 trianglePoint1, Vector3 trianglePoint2, Vector3 trianglePoint3) {
        Vector3 lineDirection = linePoint2.subtract(linePoint1);
        Vector3 edge1 = trianglePoint2.subtract(trianglePoint1);
        Vector3 edge2 = trianglePoint3.subtract(trianglePoint1);
        Vector3 h = lineDirection.cross(edge2);
        double a = edge1.dot(h);

        if (a > -1e-8 && a < 1e-8) {
            // Line is parallel to the triangle
            return Optional.empty();
        }

        double f = 1.0 / a;
        Vector3 s = linePoint1.subtract(trianglePoint1);
        double u = f * s.dot(h);

        if (u < 0.0 || u > 1.0) {
            return Optional.empty();
        }

        Vector3 q = s.cross(edge1);
        double v = f * lineDirection.dot(q);

        if (v < 0.0 || u + v > 1.0) {
            return Optional.empty();
        }

        double t = f * edge2.dot(q);
        if (t > 1e-8) {
            Vector3 intersection = linePoint1.add(lineDirection.multiply(t));
            return Optional.of(intersection);
        }

        return Optional.empty();
    }

    private static void saveDistancesToFile(List<Double> distances, String filename) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            for (Double distance : distances) {
                writer.println(String.format(Locale.US, "%.6f", distance));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error saving distances to file: " + e.getMessage());
        }
    }

    private static void saveIntersectionsToFile(List<String> intersections, String filename) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            for (String intersection : intersections) {
                writer.println(intersection);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error saving intersections to file: " + e.getMessage());
        }
    }
}
    
class Vector3 {
    public double x, y, z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 add(Vector3 other) {
        return new Vector3(x + other.x, y + other.y, z + other.z);
    }

    public Vector3 subtract(Vector3 other) {
        return new Vector3(x - other.x, y - other.y, z - other.z);
    }

    public Vector3 multiply(double scalar) {
        return new Vector3(x * scalar, y * scalar, z * scalar);
    }

    public double dot(Vector3 other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public Vector3 cross(Vector3 other) {
        return new Vector3(
            y * other.z - z * other.y,
            z * other.x - x * other.z,
            x * other.y - y * other.x
        );
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }
}