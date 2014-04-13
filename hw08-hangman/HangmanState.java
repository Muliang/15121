/**
 * 
 * @author [First Name] [Last Name] <[Andrew ID]>
 * @section [Section Letter]
 *
 */

// You may not import any additional classes or packages.
import java.util.*;

public class HangmanState {
	//Do not change any of these global fields.
	public static final int NORMAL_MODE = 0;
	public static final int HURTFUL_MODE = 1;
	public static final int HELPFUL_MODE = 2;

	public String theAnswer;
	public Set<String> lettersGuessed;
	public String feedbackToUser;
	public Set<String> possibleAnswers;

	/**
	 * Complete the HangmanState constructor as indicated in the spec.
	 */
	public HangmanState(Set<String> knownWords) {

	}

	/**
	 * Complete the feedbackFor method as indicated in the spec.
	 */
	public String feedbackFor(String answer) {
		return null; // remove this line when you're done
	}

	/**
	 * Complete the wrongGuesses method as indicated in the spec.
	 */
	public Set<String> wrongGuesses() {
		return null; // remove this line when you're done
	}

	/**
	 * Complete the letterGuessedByUser method as indicated in the spec.
	 */
	public void letterGuessedByUser(String letter, int mode) {
		return; // remove this line when you're done
	}

	/**
	 * Complete the updatePossibleAnswers() as indicated in the spec.
	 */
	public void updatePossibleAnswers() {
		return; // remove this line when you're done
	}

	/**
	 * Complete the generateFeedbackMap method as indicated in the spec.
	 */
	public Map<String, Integer> generateFeedbackMap() {
		return null; // remove this line when you're done
	}

	/**
	 * Complete the mostCommonFeedback method as indicated in the spec.
	 */
	public String mostCommonFeedback(Map<String, Integer> feedbackMap) {
		return null; // remove this line when you're done
	}

    /* Do not modify the methods below. */

	public String getFeedbackToUser() {
		return feedbackToUser;
	}

	public String toString() {
		return feedbackToUser + "\t\t" + wrongGuesses() + "\t\t"
				+ possibleAnswers.size();
	}
}
