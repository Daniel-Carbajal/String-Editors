package editor;

public class L_StringEditor {
	
	//helper node class
	private class Node{
		private char c;
		private Node next;
		private Node prev;
		
	}
	
	private Node cursorPos;		// The position of the cursor
	private Node first; //The first node | This will be needed to delete when the cursorPos is null at the front of the string
	
	//String editor constructor
	public L_StringEditor() {
		this.cursorPos = null;
		this.first= null;	// needed so that the cursor can be at the front of the list
		}
	
	// Adds a new character at the cursor position
	public void insert(char c) {
		Node newChar = new Node();
		newChar.c = c; // new node that will get inserted with the new character
		
		if(cursorPos == null && first == null) { // if the list is empty than the cursor and first is set to the new character
			cursorPos = newChar; 
			first = newChar; 
		} else if(cursorPos == null && first != null) { // if the cursor is at the beginning of the string
			newChar.next = first;
			first.prev = newChar;
			cursorPos = newChar;
			first = newChar;
		} else if(cursorPos != null && cursorPos.next == null){ // if the cursor is at the last character of the string
			cursorPos.next = newChar;
			newChar.prev = cursorPos;
			cursorPos = cursorPos.next;
		} else if(cursorPos != null && cursorPos.next != null) { // if the cursor is between 2 characters
			cursorPos.next.prev = newChar;
			newChar.next = cursorPos.next;
			newChar.prev = cursorPos;
			cursorPos.next = newChar;
			cursorPos = cursorPos.next;
		}
	}

	// Moves the cursor position to the right
	public void moveForward() {
		if(cursorPos == null && first == null) { //if the list is empty do nothing
			return;
		} else if(cursorPos == null && first != null) { //if the cursor is at the beginning of the string the cursor moves to the first character
			cursorPos = first;
		} else if(cursorPos.next == null){ // if the cursor is at the end of the list than do nothing
			return;
		} else {
			cursorPos = cursorPos.next;
		}
	}

	// Moves the cursor position to the left
	public void moveBackward() {
		if(cursorPos == null) { //if the cursor is at the beginning of the string it will not move
			return;
		} else {
			cursorPos = cursorPos.prev;
		}
	}

	// Deletes the character to the right of the cursor
	public void delete() {
		if(cursorPos == null && first != null) { // if the cursor is at the beginning of the string and there are character(s) in front of it
			if(first.next == null) { //if there is only one character in the string, delete it
				first = null;
			} else {
				cursorPos = first;
				first = first.next;
				cursorPos.next = null;
				first.prev = null;
				cursorPos = first.prev;
			}
		} else if((cursorPos == null && first == null) || cursorPos.next == null) { // if there is no character after the cursor or there are no characters do nothing
			return;
		} else if(cursorPos.next.next == null) { // if there is only one character after the cursor
			cursorPos.next = null; 
		} else { // otherwise delete the character after the cursor
			cursorPos.next = cursorPos.next.next;
			cursorPos.next.prev = cursorPos;
		}
	}

	// Deletes the character to the left of cursor
	public void backspace() {
		if(cursorPos == null) { // if the cursor is at the beginning of the string do nothing
			return;
		} else if(cursorPos == first && first.next != null) { // if the cursor is at the first character in the string and there are characters after
			first = first.next;
			first.prev = null; // delete the first character, change the new first character to the next one, and move the cursor to the beginning of the string
			cursorPos.next = null;
			cursorPos = first.prev;
		} else if(cursorPos == first && first.next == null) { // if there is only 1 character in the string, make the string empty
			cursorPos = null;
			first = null;
		} else { // if the cursor is not at the beginning or first character it can move back
			cursorPos = cursorPos.prev;
			cursorPos.next = cursorPos.next.next;
		}
		
		
	}

	// Returns the string of characters to the left of the cursor
	public String lettersBeforeCursor(int count) {
		Node current = new Node();
		current = cursorPos;
		String result = "";
		int i = count;
		while(current != null && i > 0) {
			result = current.c + result;
			
			i--;
			current = current.prev;
		}
		
		return result;
	}

	// Returns the string of characters to the right of the cursor
	public String lettersAfterCursor(int count) {
		Node current = new Node();
		current = cursorPos;
		String result = "";
		int i = count;
		if(current == null && first == null) { //if the list is completely empty return nothing
			return "";
		} else if(current == null && first != null) { // if the cursor is at the beginning of the list, return the whole string
			current = first;
			while(current != null && i > 0) {
				result = result + current.c;
				i--;
				current = current.next;
			}
		} else if(current != null && first != null) { // if the cursor is anywhere but the beginning of the string
			while(current.next != null && i > 0) {
				result = result + current.next.c;
				
				i--;
				current = current.next;
			}
		}
		
		
		return result;
	}
}
