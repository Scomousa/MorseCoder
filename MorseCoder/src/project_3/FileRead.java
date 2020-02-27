package project_3;

import java.util.*;
import java.io.File;

public class FileRead {
	private static Scanner scnr;
	private static void openFile() {
		try {
			scnr = new Scanner(getMorseCode());
		} // catch if file doesn't exist
		catch (Exception e) {
			System.out.println("Could not find file");
		}
	}
	
	public static File getMorseCode() {
		return new File("Morse_Code.txt");
	}
}
