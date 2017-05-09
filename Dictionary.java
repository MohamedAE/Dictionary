/**
 * 	A collection class modeling a dictionary.
 *
 * 	Author:			Mohamed Elmekki - Student No.: 040847947
 *
 * 	Data Fields:	capacity: int
 * 						- dictionary capacity
 * 					hashTable: DictionaryEntry[]
 * 						- Hash table (employing an array) to hold and organize
 *						  values
 * 					numEntries: int
 * 						- counter tracking total number of unique entries
 *
 * 	Methods:		Default Constructor
 * 						- declares array
 * 					processInput(Scanner, char): boolean
 * 						- determines whether an entry can be added to collection
 * 						- returns true when value is added
 * 						- returns false if addition fails
 * 					setSize(Scanner): void
 * 						- set collection's maximum capacity
 * 					hash(String): int
 * 						- calculates index-destination of entry based on key
 * 					wordCount(Scanner): void
 * 						- returns the total number of instances of a given query
 * 					hashTableSize(): int
 * 						- returns number of unique entries
 */
import java.util.*;

public class Dictionary {

	private int capacity = 15000;
	private DictionaryEntry[] hashTable;
	private int numEntries = 0;

	public Dictionary() {
		hashTable = new DictionaryEntry[capacity];
	}
	public void setSize(Scanner in){
		System.out.println("Enter the maximum dictionary capacity: ");

		capacity = -1;

		while (capacity < 0) {
			if (in.hasNextInt()) {
				capacity = in.nextInt();
			} else {
				in.next();
				System.out.println("Invalid size. Please reenter: ");
			}
		}

		hashTable = new DictionaryEntry[capacity];
		System.out.println("Dictionary has been reset.");
	}

	public boolean processInput(Scanner in, char prompt) {
		DictionaryEntry temp = new DictionaryEntry();
		temp.readWord(in, prompt);

		int index = hash(temp.getWord());

		//If first hash index is valid, add to collection
		if (hashTable[index] == null) {
			hashTable[index] = temp;
			numEntries++;
			return true;
		}

		//Else, cycle for next available index
		while (index < capacity && hashTable[index] != null) {
			if (hashTable[index].isEqual(temp.getWord())) {
				hashTable[index].incrementCount();
				return true;
			}
			index++;
		}

		//Else, do not add
		if (index >= capacity) {
			return false;
		}

		hashTable[index] = temp;
		numEntries++;

		return true;
	}

	public int hash(String word) {
		int total = 0;

		for (int i = 0; i < word.length(); i++) {
			total += (int) word.charAt(i);
		}

		return total % capacity;
	}

	public void wordCount(Scanner in) {
		int wordCount = 0;
		DictionaryEntry temp = new DictionaryEntry();
		temp.readWord(in, 'k');

		int index = hash(temp.getWord());

		while (index < capacity && hashTable[index] != null) {
			if (hashTable[index].isEqual(temp.getWord())) {
				wordCount = hashTable[index].getCount();
				break;
			}
			index++;
		}

		System.out.println(temp.getWord() + " occurs " + wordCount + " time(s).");
	}

	public int hashTableSize() {
		return numEntries;
	}

}