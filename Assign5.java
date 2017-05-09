/**
 * 	Author: 		Linda Crane (adapted menu from previous assignment)
 *
 * 	Contributor:	Mohamed Elmekki - Student No.: 040847947
 *
 * 	Topic: 			CST 8130 Assignment 5 - Dictionary (using hash table) (Winter 2017)
 *
 *	Purpose:		In this Assignment, we will write a program to handle
 * 					inserts and searches to a dynamically allocated array using
 * 					a hash algorithm and a collision resolution algorithm. We
 * 					will rewrite Assignment 4 (which had O(log n) efficiency for
 * 					insert and search) to have O(1) insert and search.
 * 
 * 	Notes:			The initial capacity of the collection is 15000 entries.
 * 					The user is presented with an option to set a new capacity
 * 					when resetting the collection.
 *
 *	Data fields:	in: Scanner - Scanner linked to keyboard
 *					inFile: Scanner - Scanner to be linked to a file
 *					dictionary: Dictionary - Object of collection class
 *
 *  Methods:		Main method
 *  				openFile(Scanner, Scanner): Scanner
 *  					- links inFile to file (if file exists); returns inFile
 */
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Assign5 {

	public static void main(String[] args) {

		Scanner in = new Scanner (System.in);
		Scanner inFile = null;
		Dictionary dictionary = new Dictionary();

		int menuChoice = 1;

		while (menuChoice != 0) {
			do {
				System.out.println("\nEnter\n"
						+ "1 for clear dictionary\n"
						+ "2 to add text from keyboard\n"
						+ "3 to add text from file\n"
						+ "4 to search for a word count\n"
						+ "5 to display number of entries\n"
						+ "0 to quit");

				if (in. hasNextInt()) {
					menuChoice = in.nextInt();
				} else {
					in.next();
					System.out.println("Invalid menu choice....reenter: ");
					menuChoice = -1;
				}
			} while (menuChoice < 0 || menuChoice > 5);

			switch (menuChoice) {
				case 1:
					dictionary.setSize(in);
					break;
	
				case 2:
					if (!dictionary.processInput(in, 'k')) {
						System.out.println("Dictionary is now full. No additional words can be added.");
					}
					break;
	
				case 3:
					inFile = openFile(inFile, in);
	
					if (inFile != null) {
						while (inFile.hasNext()) {
							if (!dictionary.processInput(inFile, 'f')) {
								System.out.println("Dictionary is now full. No additional words can be added.");
								break;
							}
						}
					}
					break;
	
				case 4:
					dictionary.wordCount(in);
					break;
	
				case 5:
					System.out.println("There is/are "+ dictionary.hashTableSize() + " word(s) in the dictionary.");
					break;
	
				case 0:
					break;
			}

		}

		in.close();

	}

	public static Scanner openFile(Scanner inFile, Scanner in) {
		String fileName = new String();

		System.out.print("\n\nEnter name of file to process: ");
		fileName = in.next();

		File file = new File(fileName);
		try {
			if (file.exists()) {
				inFile = new Scanner(file);
			} else {
				System.out.println ("File does not exist.");
			}
			return inFile;
		} catch (IOException e) {
			System.out.println("Could not open file " + fileName + ".\nExiting");
			return null;
		}
	}

}