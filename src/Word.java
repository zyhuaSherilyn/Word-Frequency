import java.util.Comparator;

/**
 * Constructs Word objects and holds their information; keeps track of each Word
 * and its frequency Includes all necessary methods for operation with Word
 * objects
 * @author Sherilyn Hua
 * @version March 4, 2015
 */
public class Word implements Comparable<Word>
{
	// Declares instance variables
	private String word;
	private int frequency;
	// Declares the order in which the objects are sorted
	public static final Comparator<Word> FREQUENCY_ORDER = new FrequencyOrder();

	/**
	 * Constructs a new Word object with the given word and a frequency of 1
	 * @param newWord the name field of the new word object being constructed
	 */
	public Word(String newWord)
	{
		// Sets up the instance variables
		word = newWord;
		// frequency is initialized to be 1 since the word has to occur at least
		// once in order for it to be made into an object
		frequency = 1;
	}

	/**
	 * Checks to see if the given Object is a Word with the same word field as
	 * this Word object
	 * @param other the object to compare to this Word
	 * @return true if the given object is a Word with the same word field as
	 *         this Word, false otherwise
	 */
	public boolean equals(Object other)
	{
		// Uses the "instanceof" operator to check if other is a reference to a
		// Word object
		if (!(other instanceof Word))
			return false;

		// Casts other to a Word reference in order to access the word field
		Word otherWord = (Word) other;
		// Returns whether word field of this is the same as the word field of
		// other
		return this.word.equals(otherWord.word);
	}

	/**
	 * Adds 1 to the frequency of the word
	 */
	public void addFrequency()
	{
		this.frequency++;// Adds 1 to the frequency every time the word appears
	}

	/**
	 * Compares this Word to another Word by comparing the word fields in each
	 * Word object
	 * @param other the Word to compare to this Word
	 * @return a value <0, if the word in this Word object comes alphabetically
	 *         before the name in the other account; a value<0, if this word
	 *         comes after the other name; and 0, if the words in the two Words
	 *         are the same
	 */
	public int compareTo(Word other)
	{
		// Comparing this and another Word object by their word fields
		return this.word.compareTo(other.word);
	}

	/**
	 * Returns the Word information as String objects
	 * @return the word field and the frequency field of a Word object
	 */
	public String toString()
	{
		// Returns the formatted String
		return String.format("%13s%-15s%7s%n", " ", word, frequency);
	}

	/**
	 * An inner Comparator class that compares two Words by their frequencies
	 */
	private static class FrequencyOrder implements Comparator<Word>
	{
		/**
		 * Compares the frequencies of two Word objects
		 * @param first the first Word to compare
		 * @param second the second Word to compare
		 * @return a value<0, if the first Word has a higher frequency; a
		 *         value>0, if first Word has a lower frequency; 0, if the
		 *         frequencies of the Words are the same
		 */
		public int compare(Word first, Word second)
		{
			// Returns certain integers according to the comparison between
			// frequencies of two Word objects
			if (first.frequency > second.frequency)
				return -1;
			else if (first.frequency < second.frequency)
				return 1;
			return 0;
		}
	}
}
