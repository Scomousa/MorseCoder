package project_3;

public class MorseNode {
	//fields
	private char letter;
	private String code;
	private MorseNode left;
	private MorseNode right;
	private static final String EMPTY_STRING = " ";
	private static final char EMPTY_CHAR = ' ';
	
	//constructor
	public MorseNode() {
		letter = EMPTY_CHAR;
		code = EMPTY_STRING;
	}
	
	//getLetter
	public char getLetter() {
		return letter;
	}
	
	//get code from letter character
	public String getCode() {
		return code;
	}
	
		
}
