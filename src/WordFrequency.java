import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Goes through a list of Strings and counts and displays the number of
 * different words and the frequency in which the words occur
 * @author Sherilyn Hua
 * @version March 2, 2015
 */
public class WordFrequency
{
	/**
	 * Searches for a target object within an ArrayList
	 * @param list the ArrayList in which the target is being searched for
	 * @param target the object that is searched for
	 * @return the target index if the same target has been found within the
	 *         list; return negative (index position-1) if target is not found
	 *         within the list
	 */
	static int binarySearch(ArrayList<Word> list, Word target)
	{
		int lower = 0;
		int upper = list.size() - 1;
		while (lower <= upper)
		{
			int middle = (lower + upper) / 2;
			int compareValue = target.compareTo(list.get(middle));
			if (compareValue > 0)
				lower = middle + 1;
			else if (compareValue < 0)
				upper = middle - 1;
			else
				return middle;
		}
		return (-lower - 1);
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		String file = new String("MOBY.txt");
		Scanner inFile = new Scanner(new File(file));
		// Uses delimiter to skip delimiters
		inFile.useDelimiter("[^\\p{Alpha}]+");

		int numOfWord = 0;
		long start = System.nanoTime();

		ArrayList<Word> textList = new ArrayList<Word>();

		while (inFile.hasNext())
		{
			String nextWord = inFile.next().toLowerCase();
			Word target = new Word(nextWord);

			int index = binarySearch(textList, target);
			//int index = textList.indexOf(target);

			if (index < 0)
			{
				// Adds the Word to an appropriate position
				textList.add(-index - 1, target);
				//textList.add(target);
			}
			else
			{
				// Adds one to the number of times the word appears
				textList.get(index).addFrequency();
			}
			numOfWord++;// Adds 1 to the number of Words
		}
		inFile.close();// Closes the scanner
		// Sorts the list of words
		Collections.sort(textList, Word.FREQUENCY_ORDER);

		long stop = System.nanoTime();// Stops the timer

		// Prints out the information about the text file
		System.out.printf("Most Frequent Words -- File: %s%n", file);
		System.out.println("Total number of words: " + numOfWord);
		System.out.println("Number of different words: " + textList.size());
		System.out.printf("Total Time: %.1f milliseconds%n%n",
				(stop - start) / 1000000.0);
		System.out.println("               Word            Frequency");
		System.out.println("              ------           ---------");

		// If there are less than 20 words in the ArrayList
		if (textList.size() <= 20)
		{
			for (int index = 0; index < textList.size(); index++)
			{
				// Prints out the formatted words and frequencies
				System.out.printf("%2d)", index + 1);
				System.out.print(textList.get(index));
			}
		}
		// If there are more than 20 words in the ArrayList
		else
		{
			for (int index = 0; index < 20; index++)
			{
				System.out.printf("%2d)", index + 1);
				System.out.print(textList.get(index));
			}
		}

	}
}