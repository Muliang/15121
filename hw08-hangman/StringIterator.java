/**
 * 
 * @author Yichao Xue <yichaox>
 * @section Section B
 *
 */

// You may not import any additonal classes or packages.
import java.util.*;

public class StringIterator implements Iterator<String> {
	private String string;
	private int index;

	// Do not change this method.
	public StringIterator(String s) {
		string = s;
		index = 0;
	}

	/**
	 * Complete the hasNext() method as indicated in the spec.
	 */
	public boolean hasNext() {
		return (index != string.length());
	}

	/**
	 * Complete the next() method as indicated in the spec.
     * This method should not perform any checks or validations ensuring
     * the current index is valid. 
	 */
	public String next() {
		String s = String.valueOf(string.charAt(index));
		index++;
		return s;
	}

	// Do not implement this method!
	public void remove() {
		return;
	}
}
