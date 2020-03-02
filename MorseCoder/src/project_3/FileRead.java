package project_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class FileRead {
	public static ArrayList<String> getFile() {
		ArrayList<String> al = new ArrayList<String>();
		al.add(" ");
		try {
			Scanner scanner = new Scanner(new File("Morse_Code.txt"));
			while (scanner.hasNextLine()) {
				al.add(scanner.nextLine());
			}
			scanner.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		al.sort(Comparator.comparing(String::length));
		
		return al;
	}

}