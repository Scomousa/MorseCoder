package project_3;
import java.util.*;
import java.io.*;

public class MorseTree {
	
	public static void main(String[] args) throws Exception {
		Scanner scnr = new Scanner(new File("Morse_Code.txt"));
		Branch<Character> morseTree = new Branch<>();
		
		while (scnr.hasNext()) {
			String line = scnr.nextLine();
			String morseCode = line.substring(1);
			char alphabetChar = line.charAt(0);
			
			morseTree.setValue(morseCode, alphabetChar);
		}
		
		System.out.println("Get f: " + morseTree.getValue("..-."));
        System.out.println("Get e: " + morseTree.getValue("."));
        System.out.println("Get t: " + morseTree.getValue("-"));
        System.out.println("Get q: " + morseTree.getValue("--.-"));
        
        System.out.println()
		
	}
	
	private static class Branch<T> {
		private Branch<T> left;
		private Branch<T> right;
		private T value;
		
		private Branch() {
			left = null;
			right = null;
		}
		
//		public T getValue(String key) {
//			if (key.isEmpty()) {
//				return value;
//			}
//			
//			return getDirection(key)
//					.getValue(key.substring(1));
//		}
		
		public String getValue(Character key) {
			String result = "";
			
			if (key == null) {
				return result += " ";
			}
			//TODO: check left subtree and return string with '.' appended
			
			//TODO: check right subtree and return string with '-' appended
		}
		
		public void setValue(String key, T value) {
			if (key.isEmpty()) {
				this.value = value;
				return;
			}
			
			getDirection(key)
					.setValue(key.substring(1), value);
			
		}
		
		private Branch<T> getDirection(String key) {
			if (key.charAt(0) == '.') {
				if (left == null) {
					left = new Branch<>();
				}
				
				return left;
			} else {
				if (right == null) {
					right = new Branch<>();
				}
				return right;
			}
		}

	}
	
}
