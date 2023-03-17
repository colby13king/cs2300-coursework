

import java.util.Scanner;

public class paAssignment2C {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read vertices of the source triangle
        System.out.println("Enter the vertices of the source triangle (x1 y1 x2 y2 x3 y3):");
        double[] sourceVertices = new double[6];
        for (int i = 0; i < 6; i++) {
            sourceVertices[i] = scanner.nextDouble();
        }

        // Read vertices of the target triangle
        System.out.println("Enter the vertices of the target triangle (x1 y1 x2 y2 x3 y3):");
        double[] targetVertices = new double[6];
        for (int i = 0; i < 6; i++) {
            targetVertices[i] = scanner.nextDouble();
        }

        // Calculate the linear map
        double[][] linearMap = findLinearMap(sourceVertices, targetVertices);

        // Display the linear map
        System.out.println("The linear map is:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.printf("%.2f ", linearMap[i][j]);
            }
            System.out.println();
        }

        scanner.close();
    }

    public static double[][] findLinearMap(double[] sourceVertices, double[] targetVertices) {
        // Calculate the source matrix
        double[][] sourceMatrix = {
            {sourceVertices[2] - sourceVertices[0], sourceVertices[4] - sourceVertices[0]},
            {sourceVertices[3] - sourceVertices[1], sourceVertices[5] - sourceVertices[1]}
        };

        // Calculate the target matrix
        double[][] targetMatrix = {
            {targetVertices[2] - targetVertices[0], targetVertices[4] - targetVertices[0]},
            {targetVertices[3] - targetVertices[1], targetVertices[5] - targetVertices[1]}
        };

        // Find the inverse of the source matrix
        double determinant = sourceMatrix[0][0] * sourceMatrix[1][1] - sourceMatrix[0][1] * sourceMatrix[1][0];
        double[][] inverseSourceMatrix = {
            {sourceMatrix[1][1] / determinant, -sourceMatrix[0][1] / determinant},
            {-sourceMatrix[1][0] / determinant, sourceMatrix[0][0] / determinant}
        };

        // Calculate the linear map
        double[][] linearMap = new double[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                linearMap[i][j] = 0;
                for (int k = 0; k < 2; k++) {
                    linearMap[i][j] += targetMatrix[i][k] * inverseSourceMatrix[k][j];
                }
            }
        }

        return linearMap;
    }
}

