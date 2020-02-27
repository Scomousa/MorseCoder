package project_3;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		String test = "j.---";
		String codeString = "";
		ArrayList<String> codeList = new ArrayList<String>();
		ArrayList<Character> charList = new ArrayList<Character>();
		
		charList.add(test.charAt(0));
		codeList.add(test.substring(1, test.length()));
		
		for (int i=0; i < charList.size(); i++) {
			System.out.println(charList.get(i) + " " + codeList.get(i));
		}
		
		System.out.println("dot compared to dash" + " = " + ".".compareTo("-"));
		System.out.println("dash compared to dot" + " = " + "-".compareTo("."));
	}

}
