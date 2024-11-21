package editor;

import java.util.Arrays;

public class A_StringEditor {
	private static final int INITIAL_CAPACITY = 1000;  // The initial size of the data array
	
	private char[] data;		// contents of string
	private int stringSize;		// size of string
	private int cursorPos;		// index of cursor position
	
	// creates empty string for string constructor
	public A_StringEditor() {
		cursorPos = 0;
		stringSize = 0;
		data = (char[]) new char[INITIAL_CAPACITY];
	}
	

	public void insert(char c) {
		//long start = System.currentTimeMillis();
		if(stringSize == data.length-1) { // if the array is full than expand the length by 10 indexes
			char [] copyData = (char[]) new char[data.length+10];
			for(int i = 0; i < this.data.length; i++) {
				copyData[i] = this.data[i]; // copy the old data into a new array
			}
			this.data = copyData; // set this array to our new longer array
		}
		
		if(stringSize == 0) { // if the string is empty insert the first character
			cursorPos++;
			data[cursorPos] = c;
		} else { // otherwise shift everything after the cursor to the right by one index and insert the new character
			int index = stringSize;
			while(index != cursorPos) {
				data[index+1] = data[index];
				index--;
			}
			cursorPos++;
			data[cursorPos] = c;
		}
		
		stringSize++; // the string got a new character
		//long finish = System.currentTimeMillis();
		//System.out.println("time: " + (finish - start) / 1000.0);

	}
	
	public void moveForward() {
		if(cursorPos == stringSize) { // if the cursor is at the end of the string do nothing
			return;
		} else { // otherwise it can move forward
			cursorPos++;
		}
	}
	
	public void moveBackward() {
		if(cursorPos == 0) { //if the cursor is at the front of the string, do nothing
			return;
		} else { // otherwise it can move back
			cursorPos--;
		}
	}
	
	public void delete() {
		if(cursorPos == stringSize) { // if the cursor is at the end of the string, do nothing
			return;
		} else { // otherwise delete the character after the cursor and shift the characters after the cursor to the left
			data[cursorPos+1] = '\0';
			int index = cursorPos + 1;
			while(index < stringSize) {
				data[index] = data[index+1];
				index++;
			}
		}
		
		stringSize--; // the string has one less character
	}
	
	public void backspace() {
		if(cursorPos == 0) { //if the cursor is at the front of the string, do nothing
			return;
		} else { // otherwise delete the character before the cursor, move the cursor back, and shift all the characters after the cursor to the left
			data[cursorPos] = '\0';
			cursorPos--;
			int index = cursorPos + 1;
			while(index < stringSize) {
				data[index] = data[index+1];
				index++;
			}
		}
		
		stringSize--; // the string has one less character
	}
	
	public String lettersBeforeCursor(int count) {
		String result = "";
		
		if(cursorPos == 0) { // if the cursor is at the beginning of the string, there are no characters before it
			return "";
		}
		if(count >= cursorPos) { // if the count is greater than the number of letters before the cursor return everything before the cursor
			int i = cursorPos;
			while(i>0) {
				result = data[i] + result; 
				i--;
			}
		} else if(count < cursorPos){ // if the cursor has more chars before it than count, than return count number of characters before the cursor
			int i = cursorPos;
			while(i > 0) {
				result = data[i] + result;
				i--;
			}
		}
		return result;
	}
	
	public String lettersAfterCursor(int count) {
		String result = "";
		int i = cursorPos + 1;
		
		if(cursorPos == stringSize) { //if the cursor is at the end of the string return nothing
			return result;
		}
		
		if(cursorPos + count >= stringSize) { // if count characters after the cursor is more than the length of the string, than return the rest of the string
			while(i <= stringSize) {
				result = result + data[i];
				i++;
			} 
		} else { // returns count characters after the cursor
			while(i <= cursorPos + count) {
				result = result + data[i];
				i++;
			}
		}
		
		return result;
	}
}
