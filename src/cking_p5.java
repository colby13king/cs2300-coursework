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

	    int[] rs = addVector4s(r, s);
	    int[] su = addVector4s(s, u);
	    int[] uv = subtractVector4s(u, v);

	    writeVector4ToFile(rs, "name_p5_rs.txt");
	    writeVector4ToFile(su, "name_p5_su.txt");
	    writeVector4ToFile(uv, "name_p5_uv.txt");
	  }

	  // This method adds two Vector4s
	  private static int[] addVector4s(int[] firstVector4, int[] secondVector4) {
	    int[] result = new int[firstVector4.length];
	    for (int i = 0; i < firstVector4.length; i++) {
	      result[i] = firstVector4[i] + secondVector4[i];
	    }
	    return result;
	  }

	  // This method subtracts two Vector4s
	  private static int[] subtractVector4s(int[] firstVector4, int[] secondVector4) {
	    int[] result = new int[firstVector4.length];
	    for (int i = 0; i < firstVector4.length; i++) {
	      result[i] = firstVector4[i] - secondVector4[i];
	    }
	    return result;
	  }

	  // This method writes a Vector4 to a file
	  private static void writeVector4ToFile(int[] Vector4, String filename) throws IOException {
	    FileWriter fileWriter = new FileWriter(filename);
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.println(Vector4[0] + " " + Vector4[1]);
	    printWriter.close();
	  }
	}



