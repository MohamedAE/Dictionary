/**
 * 	A class modeling a dictionary entry.
 *
 * 	Author:			Mohamed Elmekki - Student No.: 040847947
 *
 * 	Data Fields:	word: String
 * 						- String key value
 * 					count: int
 * 						- number of instances of given word
 *
 * 	Methods:		Default Constructor
 * 					readWord(Scanner, char): void
 * 						- assigns a String value to entry
 * 					formatString(String): String
 * 						- returns string stripped of non-alpha characters and
 *						  forces lower case
 * 					incrementCount(): void
 * 						- adds one to count of String value
 * 					getWord(): String
 * 						- returns held String value
 * 					getCount(): int
 * 						- returns count of held String value
 * 					isEqual(String): boolean
 * 						- checks held String value against String query
 */
import java.util.*;

public class DictionaryEntry {

	private String word = "";
	private int count = 1;

	public DictionaryEntry() {}

	public void readWord(Scanner in, char prompt) {
		if (prompt == 'k') {
			System.out.println("Enter a word: ");
		}
		word = formatString(in.next());
	}

	//Format String
	public String formatString(String word) {
		return word.toLowerCase().replaceAll("[^a-zA-Z]", "");
	}

	//Increment word count by 1
	public void incrementCount() {
		count++;
	}

	//Return key
	public String getWord() {
		return word;
	}

	//Return word count
	public int getCount() {
		return count;
	}

	public boolean isEqual(String word) {
		if (this.word.equals(word)) {
			return true;
		} else {
			return false;
		}
	}

}