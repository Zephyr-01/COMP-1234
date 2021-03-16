/*
 * COMP 2503 Assignment 3 Avenger Class
 * <p>
 * The Avenger class implements comparable to compare each avenger 
 * to make sure there are no duplicates. An Avenger has an alias, name 
 * and frequency that is mentioned.
 * <p>
 * @author Daniel Baranowski and David Le
 * @date: 11/25/2020
 *
 */
public class Avenger implements Comparable<Avenger> 
{
	private String heroAlias;
	private String heroName;
	private int frequency;
	private int mentionOrder;

  /**
     * Class Constructor
     * @param heroAlias the alias name of the avenger
     * @param heroName  the name of the avenger
     * @param id        the frequency of the avenger mentioned
     */
	public Avenger(String alias, String name, int id) 
  {
		heroName = name;
		heroAlias = alias;
		frequency = 1;
		mentionOrder = 0;
	}

  /**
     * @return the heroAlias
     */
	public String getAlias() 
  {
		return heroAlias;
	}

  /**
     * @return the heroName
     */
	public String getName() 
  {
		return heroName;
	}

  /**
     * @return the frequency
     */
	public int getFrequency() 
  {
		return frequency;
	}

  /**
     * Updates the frequency by 1
     */
	public void addFrequency() 
  {
		this.frequency++;
	}

  /**
     * @return the mentioned index
     */
	public int getMentionOrder() 
  {
		return mentionOrder;
	}
  /**
  * Adds the frequency index by 1
  */
	public void addMentionOrder() 
  {
		this.mentionOrder++;
	}
	
  /**
  * Changes the frequncy index
  */
	public void setMentionOrder(int mentionIndex) 
  {
		this.mentionOrder = mentionIndex;
	}

  /**
     * compareTo method as placeholder
     * @param other as an object
     * @return  the value of whether they are the same.
     */
	@Override
	public int compareTo(Avenger other) 
  {
		if (other == null)
			return -1;
		return this.getAlias().compareTo(other.getAlias());
	}

  /**
	 * @param other comparing to other object
	 * @return		true or false if it is equal
	 */
	@Override
	public boolean equals(Object other) 
  {
		if (other == null)
    {
			return false;
    }
		Avenger a = (Avenger) other;
		if (this.getAlias().equals(a.getAlias()) && this.getName().equals(a.getName()))
    {
			return true;
    }
		else
    {
			return false;
    }
	}
  
  /**
  * Returns the unique reference number to that avenger object
  */
	@Override
	public int hashCode() 
  {
		String aliasAndName = heroAlias + heroName;
		return aliasAndName.hashCode();
	}

  /**
   * To string method prints out format of output
   * @return  format of avenger name and frequency
   */
  @Override
	public String toString() 
  {
		return heroAlias + " aka " + heroName + " mentioned " + frequency + " time(s)";
	}
}