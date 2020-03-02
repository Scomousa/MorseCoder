package project_3;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		ArrayList<String> a1 = FileRead.getFile();
		
		for (String s : a1) {
			System.out.println(s);
		}
		
		BinaryTree mt = new BinaryTree(a1.get(0).charAt(0));
		
		mt.buildTree(a1);
		
		for (int i = 0; i<a1.size(); i++) {
			
		}
		
	}

}
