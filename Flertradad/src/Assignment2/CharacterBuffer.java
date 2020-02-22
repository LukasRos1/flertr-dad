package Assignment2;

import java.util.ArrayList;

public class CharacterBuffer {

	// char[] buffer;
	private ArrayList<Character> buffer = new ArrayList<Character>();
	private char[] buffer1 = new char[1];
	boolean HasCharacter = false;
	public CharacterBuffer() {
		
	}
	
//	public synchronized char synchedRead() {
//		
//		if (buffer.get(0) != null) {
//			return buffer.get(0);
//		} else {
//			return 0;
//		}
//
//	}
	
	public synchronized Boolean synchedWrite(char ch) {
		if (HasCharacter==false) {
			buffer1[0] = ch;
			HasCharacter=true;
			return true;
		}
		return false;
	}
	
	public synchronized char synchedRead() {
		char a = 0;
		if (HasCharacter==true) {
			char first = buffer.get(0);
			buffer.remove(0);
			return first;
		} else {
			return a;
		}
	}
	
	public char read() {
		char last = buffer.get(buffer.size() - 1);
		char first = buffer.get(0);
		buffer.remove(0);
		return first;
	}

	public void write(String stringToBuffer) {
		for (int i = 0; i < stringToBuffer.length(); i++) {
			buffer.add(stringToBuffer.charAt(i));
			System.out.println("Adding: " + stringToBuffer.charAt(i));
		}
	}

}
