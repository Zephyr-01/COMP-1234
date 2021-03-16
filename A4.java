import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Collection;

/**
 * COMP 2503 Winter 2020 Assignment 4
 * 
 * This program must read a input stream and keeps track of the frequency at
 * which an avenger is mentioned either by name or alias. The program must use
 * HashMaps for keeping track of the Avenger Objects, and it must use TreeMaps
 * for storing the data.
 * 
 * @author Maryam Elahi
 * @date Fall 2020
 */

public class A4 {

	public String[][] avengerRoster = { { "captainamerica", "rogers" }, { "ironman", "stark" },
			{ "blackwidow", "romanoff" }, { "hulk", "banner" }, { "blackpanther", "tchalla" }, { "thor", "odinson" },
			{ "hawkeye", "barton" }, { "warmachine", "rhodes" }, { "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

	private int topN = 4;
	private int totalwordcount = 0;
	private Scanner input = new Scanner(System.in);
	HashMap<String, Avenger> map = new HashMap<String, Avenger>();
	AvengerComparatorFreqDesc freqDesc = new AvengerComparatorFreqDesc();
	AvengerComparatorFreqAsc freqAsc = new AvengerComparatorFreqAsc();
	AvengerComparatorMentionOrder mentionOrder = new AvengerComparatorMentionOrder();

	/**
	 * Declare the tree maps to be used to order the avengers
	 */
	private TreeMap<Avenger, Avenger> alphabticalTreeMap = new TreeMap<Avenger, Avenger>();
	private TreeMap<Avenger, Avenger> mentionTreeMap = new TreeMap<Avenger, Avenger>(
			new AvengerComparatorMentionOrder());
	private TreeMap<Avenger, Avenger> mostPopularTreeMap = new TreeMap<Avenger, Avenger>(
			new AvengerComparatorFreqDesc());
	private TreeMap<Avenger, Avenger> leastPopularTreeMap = new TreeMap<Avenger, Avenger>(
			new AvengerComparatorFreqAsc());

	public static void main(String[] args) {
		A4 a4 = new A4();
		a4.run();
	}

	public void run() 
	{
		readInput();
		createdOrderedTreeMaps();
		printResults();
	}

	/**
	 * Creates the tree maps for each of the orders.
	 */
	private void createdOrderedTreeMaps() 
	{
		Collection<Avenger> avengers = map.values();
		for (Avenger avenger : avengers) 
		{
			alphabticalTreeMap.put(avenger, avenger);
			mentionTreeMap.put(avenger, avenger);
			mostPopularTreeMap.put(avenger, avenger);
			leastPopularTreeMap.put(avenger, avenger);
		}
	}

	/**
	 * Read the input stream and keep track how many times avengers are mentioned by
	 * alias or last name.
	 */
	private void readInput() 
	{
		int mentionOrder = 1;
		while (input.hasNext()) 
		{
			String word = cleanWord(input.next());
			if (!word.isEmpty()) 
			{
				totalwordcount++;
				if (!isAvenger(word))
					continue;
				Avenger newAvengerObject = createAvengerObject(word);
				if (newAvengerObject == null) 
				{
					continue;
				} 
				else 
				{
					String alias = newAvengerObject.getAlias();
					if (map.containsKey(alias)) 
					{
						map.get(alias).addFrequency();
					} else 
					{
						newAvengerObject.setMentionOrder(mentionOrder);
						map.put(alias, newAvengerObject);
						mentionOrder++;
					}
				}
			}
		}
		input.close();
	}

	/**
	 * Checks if the word is an Avenger and returns true or false.
	 * 
	 * @param word that has been converted
	 * @return true or false if it is a avenger
	 */
	private boolean isAvenger(String word) 
	{
		for (int i = 0; i < avengerRoster.length; i++) 
		{
			if ((avengerRoster[i][0].equals(word)) || (avengerRoster[i][1].equals(word))) 
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * This method cleans the word by removing space and punctuation.
	 * 
	 * @param next the word that is taken in from scanner
	 * @return the word that is converted.
	 */
	private String cleanWord(String next) 
	{
		String ret;
		int inx = next.indexOf('\'');
		if (inx != -1) 
		{
			ret = next.substring(0, inx).toLowerCase().trim().replaceAll("[^a-z]", "");
		} else 
		{
			ret = next.toLowerCase().trim().replaceAll("[^a-z]", "");
		}
		return ret;
	}

	/**
	 * A method to create avenger objects if they are equal to the avenger roster
	 */
	private Avenger createAvengerObject(String word) 
	{
		int inx = -1;
		for (int i = 0; i < avengerRoster.length; i++) 
		{
			if (avengerRoster[i][0].equals(word) || avengerRoster[i][1].equals(word)) 
			{
				inx = i;
				break;
			}
		}
		if (inx != -1) 
		{
			return new Avenger(avengerRoster[inx][0], avengerRoster[inx][1], inx);
		} else
			return null;
	}

	/**
	 * A method to print all avengers using different tree maps
	 */
	public void printAllAvengers(Map<Avenger, Avenger> avengers) 
	{
		for (Avenger a : avengers.keySet()) 
		{
			System.out.println(avengers.get(a).toString());
		}
	}

	/**
	 * A method to print top avengers using different tree maps
	 */
	public void printTopAvengers(TreeMap<Avenger, Avenger> Avenger) 
	{
		Queue<String> freqQueue = new LinkedList<String>();
		for (Avenger a : Avenger.keySet()) 
		{
			freqQueue.add(Avenger.get(a).toString());
		}

		if (Avenger.size() > topN) 
		{
			for (int i = 0; i < topN; i++) 
			{
				System.out.println(freqQueue.remove());
			}
		} 
		else 
		{
			for (int i = 0; i < Avenger.size(); i++) 
			{
				System.out.println(freqQueue.remove());
			}
		}
	}

	/**
	 * Print the results
	 */

	private void printResults() 
	{

		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + map.size());
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		printAllAvengers(mentionTreeMap);
		System.out.println();

		System.out.println("Top " + topN + " most popular avengers:");
		printTopAvengers(mostPopularTreeMap);
		System.out.println();

		System.out.println("Top " + topN + " least popular avengers:");
		printTopAvengers(leastPopularTreeMap);
		System.out.println();

		System.out.println("All mentioned avengers in alphabetical order:");
		printAllAvengers(alphabticalTreeMap);
		System.out.println();
	}
}