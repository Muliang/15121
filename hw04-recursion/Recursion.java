/**
 * 
 * @author Yichao Xue <yichaox>
 * @section B
 * 
 *
 */

// You may not import any additional classes or packages.
import java.util.ArrayList;

public class Recursion {

	/**
	 * count the number of occurrence of findData in the list
	 * 
	 * @param head
	 *            head Node
	 * @param findData
	 *            data to be found
	 * @return the occurrence of findData in the list
	 */
	public static int count(Node head, String findData) {
		if (head == null) {
			return 0;
		}

		if (head.data.equals(findData)) {
			return count(head.next, findData) + 1;
		}
		return count(head.next, findData);
	}

	/**
	 * check if two strings are reverse
	 * 
	 * @param string1
	 *            string to be compared
	 * @param string2
	 *            other string to be compared
	 * @return true if these two strings are reverse, otherwise false
	 */
	public static boolean isReverse(String string1, String string2) {
		/* not the same length */
		if (string1.length() != string2.length()) {
			return false;
		}
		if (string1.length() == 0 && string2.length() == 0) {
			return true;
		}

		/* compare two substrings */
		if (string1.charAt(0) == string2.charAt(string2.length() - 1)) {
			return isReverse(string1.substring(1),
					string2.substring(0, string2.length() - 1));
		}
		return false;
	}

	/**
	 * insert a node after a node with specific data
	 * 
	 * @param head
	 *            list's head node
	 * @param insertData
	 *            data of node to be inserted
	 * @param findData
	 *            specific data
	 */
	public static void insertAfter(Node head, String insertData, String findData) {
		if (head == null) {
			return;
		}

		/* insert */
		if (head.data.equals(findData)) {
			Node newNode = new Node(insertData);
			newNode.next = head.next;
			head.next = newNode;
			return;
		}
		insertAfter(head.next, insertData, findData);
	}

	/**
	 * check if all the Integers sum equals the target
	 * 
	 * @param list
	 *            an array list of Integers
	 * @param target
	 *            the target Integer
	 * @return true if all the Integers sum to the target, otherwise false
	 */
	public static boolean itAddsUp(ArrayList<Integer> list, Integer target) {
		if (list.size() == 0) {
			return (target == 0);
		}

		if (list.size() == 1) {
			return (list.get(0) == target);
		}

		/* check if sublist's sum equals target subs first element */
		Integer temp = list.remove(0);
		return itAddsUp(list, target - temp);

	}

	/**
	 * remove duplicates in a string
	 * 
	 * @param string
	 *            string with duplicates
	 * @return string without duplicate
	 */
	public static String removeDuplicates(String string) {
		if (string == null) {
			return null;
		}
		/* empty string */
		if (string.length() == 0) {
			return "";
		}

		if (string.length() == 1) {
			return string;
		}
		if (string.charAt(0) == string.charAt(1)) {
			return removeDuplicates(string.substring(1));
		} else {
			return string.charAt(0) + removeDuplicates(string.substring(1));
		}
	}

	/**
	 * create a string starts with the values of the even integers in descending
	 * order first, and then follows with the odd integers in ascending order
	 * 
	 * @param n
	 *            upper limit
	 * @return a String comprised of all the numbers from 1 to n separated by
	 *         dashes
	 */
	public static String stringNumbers(Integer n) {
		if (n == 0) {
			return "";
		}

		if (n == 1) {
			return "1";
		}

		/* even number */
		if (n % 2 == 0) {
			return n.toString() + "-" + stringNumbers(n - 1);
		} else {
			/* odd number */
			return stringNumbers(n - 1) + "-" + n.toString();
		}
	}

	/**
	 * removes all Nodes from a LinkedList whose data is exactly equal to the
	 * given length
	 * 
	 * @param head
	 *            head node of list
	 * @param length
	 *            length of specific data to be removed
	 * @return
	 */
	public static Node removeAll(Node head, Integer length) {
		if (head != null) {

			if (head.toString().length() == length.intValue()) {
				head = removeAll(head.next, length);
			} else {
				head.next = removeAll(head.next, length);
			}
		}
		return head;
	}

	/**
	 * create a mirror list
	 * 
	 * @param list
	 *            original list
	 * @return a mirror list
	 */
	public static ArrayList<Integer> mirrorList(ArrayList<Integer> list) {

		if (list.isEmpty()) {
			return list;
		}

		if (list.size() == 1) {
			Integer temp = list.get(0);
			list.add(temp);
			return list;
		}

		Integer i = list.remove(0);
		list = mirrorList(list);
		/* add the first element before the head and after the end */
		list.add(i);
		list.add(0, i);

		return list;
	}

	/**
	 * return the sum of all digits
	 * 
	 * @param n
	 *            integer
	 * @return sum of all digits
	 */
	public static int sumDigits(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		/* convert to string */
		String s = String.valueOf(n);

		if (s.length() == 1) {
			return Integer.parseInt(s);
		}
		return Character.getNumericValue(s.charAt(0))
				+ sumDigits(Integer.parseInt(s.substring(1)));

	}

	/**
	 * find the number of binary strings of length which do not have two
	 * consecutive zeros.
	 * 
	 * If n = 1 => (0, 1), there 2 strings have no consecutive zeros. n = 2 =>
	 * (01, 10, 11), there 3 strings have no consecutive zeros. When n = 3,
	 * strings which do not have consecutive zeros are the combinations of
	 * strings when n = 1 and n = 2. Hence, NUM(n) = NUM(n - 1) + NUM(n - 2)
	 * 
	 * @param n
	 *            length of binary string
	 * @return number of binary strings of length which do not have two
	 *         consecutive zeros.
	 */
	public static int countBinary(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}

		if (n == 1) {
			return 2;
		}
		if (n == 2) {
			return 3;
		}

		return countBinary(n - 1) + countBinary(n - 2);
	}

	/**
	 * Use the main method to write your tests. Delete this method when you're
	 * done.
	 */
	public static void main(String args[]) {

	}
}
