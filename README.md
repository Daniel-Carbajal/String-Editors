# String-Editors
The goal of this project was to create two simple string editors. The first utilizing an array, while the second utilizes Nodes. They both support funcstions such as inserting a character into the string, backspacing, deleting, and moving the cursor position in either direction. A program for the GUI(not coded by me) is also included.

# Array String Editor (A_StringEditor.java)
This program utilizes a char Array to store the characters in the string. Things such as cursor position and string size are stored in seperate variables. The char array is default set to size 100 and when the string reaches that size, it creates a copy of the string with a larger size to replace the current string. 

# List String Editor (L_StringEditor.java)
This program utilizes a Node List to store the characters in the string and the cursors position. This program did not need any loops and only needed to check for certain cases of error.

# GUI.java
Running this creates a GUI to use the Array String Editor or the List String Editor. Make sure that you comment out the correct initializations of ed in lines 12/13 and lines 16/17 dependning on which editor you are testing.
This file was NOT coded by me and was provided by my professor for testing purposes.
