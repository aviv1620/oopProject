package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	

	// ******** add your code below *********

	/** Compares its two arguments for order.
	 *  Returns a negative integer,zero, or a positive integer
	 *  as the first argument is less than, equalto, or greater than the second
	 *  
	 * @return - a negative integer, zero, or a positive integer as thefirst argument is less than, equal to, or greater than thesecond.
	 */
	@Override
	public int compare(Monom m1, Monom m2) {
		
		return m1.get_power() - m2.get_power();
	}
}
