/*
 * Colby King
 * Programming Assignment 1
 * CS2300
 * Due Feb 12, 2023
 * 
 * PART 4
 */
import java.io.*;

public class cking_p4 {

	public static void main(String[] args) throws IOException {
	    int[] r = {-1, -2};
	    int[] s = {-3, 3};
	    int[] u = {2, -1};
	    int[] v = {3, 1};
	    int[] w = {1, 3};

	    // Calculate dot products
	    int rs = dotProduct(r, s);
	    int uv = dotProduct(u, v);
	    int sv = dotProduct(s, v);

	    // Write results to files
	    writeResultToFile("name_p4_rs.txt", rs);
	    writeResultToFile("name_p4_uv.txt", uv);
	    writeResultToFile("name_p4_sv.txt", sv);
	  }

	  // This method calculates the dot product of two vectors
	  private static int dotProduct(int[] firstVector, int[] secondVector) {
	    int dotProduct = 0;
	    for (int i = 0; i < firstVector.length; i++) {
	      dotProduct += firstVector[i] * secondVector[i];
	    }
	    return dotProduct;
	  }

	  // This method writes a result to a file
	  private static void writeResultToFile(String filename, int result) throws IOException {
	    FileWriter fileWriter = new FileWriter(filename);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.println(result);
	    printWriter.close();
	  }
	}


