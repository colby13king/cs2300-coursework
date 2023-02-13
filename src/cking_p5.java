/*
 * Colby King
 * Programming Assignment 1
 * CS2300
 * Due Feb 12, 2023
 * 
 * PART 5
 */


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class cking_p5 {

	public static void main(String[] args) throws IOException {
	    int[] r = new int[]{-1, -2};
	    int[] s = new int[]{-3, 3};
	    int[] u = new int[]{2, -1};
	    int[] v = new int[]{3, 1};
	    int[] w = new int[]{1, 3};

	    int[] rs = addVectors(r, s);
	    int[] su = addVectors(s, u);
	    int[] uv = subtractVectors(u, v);

	    writeVectorToFile(rs, "name_p5_rs.txt");
	    writeVectorToFile(su, "name_p5_su.txt");
	    writeVectorToFile(uv, "name_p5_uv.txt");
	  }

	  // This method adds two vectors
	  private static int[] addVectors(int[] firstVector, int[] secondVector) {
	    int[] result = new int[firstVector.length];
	    for (int i = 0; i < firstVector.length; i++) {
	      result[i] = firstVector[i] + secondVector[i];
	    }
	    return result;
	  }

	  // This method subtracts two vectors
	  private static int[] subtractVectors(int[] firstVector, int[] secondVector) {
	    int[] result = new int[firstVector.length];
	    for (int i = 0; i < firstVector.length; i++) {
	      result[i] = firstVector[i] - secondVector[i];
	    }
	    return result;
	  }

	  // This method writes a vector to a file
	  private static void writeVectorToFile(int[] vector, String filename) throws IOException {
	    FileWriter fileWriter = new FileWriter(filename);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.println(vector[0] + " " + vector[1]);
	    printWriter.close();
	  }
	}



