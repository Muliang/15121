/**
 * 
 * @author Yichao Xue <yichaox>
 * @section Section B
 *
 */

// You may not import any additional classes or packages.
import java.util.*;

public class HangmanState {
	// Do not change any of these global fields.
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
		/* initialize answer */
		int index = (int) (Math.random() * knownWords.size());
		Iterator<String> itr = knownWords.iterator();
		for (int i = -1; i < index - 1; i++) {
			itr.next();
		}
		this.theAnswer = itr.next();

		this.lettersGuessed = new HashSet<String>();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < theAnswer.length(); i++) {
			sb.append("-");
		}
		feedbackToUser = sb.toString();

		/* initialize possibleAnswers */
		possibleAnswers = new HashSet<String>();
		for (String word : knownWords) {
			if (word.length() == theAnswer.length()) {
				possibleAnswers.add(word);
			}
		}
		updatePossibleAnswers();
	}

	/**
	 * Complete the feedbackFor method as indicated in the spec.
	 */
	public String feedbackFor(String answer) {
		StringBuilder sb = new StringBuilder();
		StringIterator itr = new StringIterator(answer);

		while (itr.hasNext()) {
			String letter = itr.next();

			if (lettersGuessed.contains(letter)) {
				sb.append(letter);
			} else {
				sb.append("-");
			}
		}
		return sb.toString();
	}

	/**
	 * Complete the wrongGuesses method as indicated in the spec.
	 */
	public Set<String> wrongGuesses() {
		Set<String> correctGuess = new HashSet<String>();
		Set<String> wrongGuesses = new HashSet<String>(lettersGuessed);

		for (int i = 0; i < feedbackToUser.length(); i++) {
			char letter = feedbackToUser.charAt(i);
			if (letter != '-') {
				correctGuess.add(String.valueOf(letter));
			}
		}

		wrongGuesses.removeAll(correctGuess);
		return wrongGuesses;
	}

	/**
	 * Complete the letterGuessedByUser method as indicated in the spec.
	 */
	public void letterGuessedByUser(String letter, int mode) {

		switch (mode) {
		case NORMAL_MODE:
			lettersGuessed.add(letter);
			feedbackToUser = feedbackFor(theAnswer);
			updatePossibleAnswers();
			break;
		case HURTFUL_MODE:
			lettersGuessed.add(letter);
			feedbackToUser = mostCommonFeedback(generateFeedbackMap());
			updatePossibleAnswers();
			break;
		case HELPFUL_MODE:
			lettersGuessed.add(letter);

			Map<String, Integer> feedbackMap = generateFeedbackMap();
			String mostCommon = mostCommonFeedback(feedbackMap);

			Iterator<Map.Entry<String, Integer>> itr = feedbackMap.entrySet()
					.iterator();

			while (itr.hasNext()) {
				Map.Entry<String, Integer> e = itr.next();

				if (!e.getKey().contains(letter)) {
					itr.remove();
				}
			}

			if (feedbackMap.size() != 0) {
				feedbackToUser = mostCommonFeedback(feedbackMap);
			} else
				feedbackToUser = mostCommon;

			updatePossibleAnswers();
			break;
		default:
			break;
		}
	}

	/**
	 * Complete the updatePossibleAnswers() as indicated in the spec.
	 */
	public void updatePossibleAnswers() {
		Iterator<String> itr = possibleAnswers.iterator();

		while (itr.hasNext()) {
			String word = itr.next();
			String feedback = feedbackFor(word);

			if (!feedback.equals(feedbackToUser)) {
				itr.remove();
			}
		}
	}

	/**
	 * Complete the generateFeedbackMap method as indicated in the spec.
	 */
	public Map<String, Integer> generateFeedbackMap() {
		Map<String, Integer> feedbackMap = new HashMap<String, Integer>();

		for (String word : possibleAnswers) {
			String feedback = feedbackFor(word);

			Integer count = feedbackMap.get(feedback);
			if (count == null) {
				count = 0;
			}
			count++;

			feedbackMap.put(feedback, count);

		}

		return feedbackMap;
	}

	/**
	 * Complete the mostCommonFeedback method as indicated in the spec.
	 */
	public String mostCommonFeedback(Map<String, Integer> feedbackMap) {
		int max = 0;
		String res = "";

		for (Map.Entry<String, Integer> e : feedbackMap.entrySet()) {
			if (e.getValue() > max) {
				max = e.getValue();
				res = e.getKey();
			}
		}

		return res;
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
