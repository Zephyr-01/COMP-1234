import java.util.Comparator;

public class AvengerComparatorMentionOrder implements Comparator<Avenger> 
{
  /**
	 * This method compare two Avengers, and returns the different between one's mentionIndex and another mentionIndex
	 */
	@Override
	  public int compare(Avenger a1, Avenger a2){
	    int diff = a1.getMentionOrder() - a2.getMentionOrder();
	    return diff;
	  }
}