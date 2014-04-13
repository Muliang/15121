/**
 * 
 * @author Yichao Xue <yichaox>
 * @section B
 * 
 */

// You may not import any additional classes or packages.

import java.util.ArrayList;

public class SinglyLinkedList {

	private int numElements;
	public SingleNode head;

	public SinglyLinkedList() {
		numElements = 0;
		head = null;
	}

	/**
	 * The add method creates a SingleNode given data and inserts it at the
	 * beginning of the SinglyLinkedList.
	 * 
	 * Do not change this method!
	 */
	public void add(String newData) {
		SingleNode node = new SingleNode(newData);
		node.next = head;
		head = node;
		numElements++;
	}

	/**
	 * Do not change this method!
	 */
	public int size() {
		return numElements;
	}

	/**
	 * concatenate combines the data attributes from all SingleNodes in the
	 * SinglyLinkedList
	 * 
	 * @return - a string that combines all data attributes from all SingleNodes
	 */
	public String concatenate() {
		SingleNode cursor = head;
		String s = ""; // initial value is empty

		while (cursor != null) {
			s = s + cursor.data; // put two strings together
			cursor = cursor.next; // move to next node
		}
		return s;
	}

	/**
	 * insert a new node with insertData after a node whose data equals findData
	 * 
	 * @param insertData
	 *            data for new inserted node
	 * @param findData
	 *            data of node that new node should follow
	 */
	public void insertAfter(String insertData, String findData) {
		SingleNode cursor = head;

		while (cursor != null) {
			/* search the occurrence of findData */
			if (cursor.data.equals(findData)) {
				SingleNode newNode = new SingleNode(insertData);
				/* insert new node */
				newNode.next = cursor.next;
				cursor.next = newNode;
				break;
			}
			cursor = cursor.next;
		}
	}

	/**
	 * constructs a SinglyLinkedList from the given ArrayList
	 * 
	 * @param arrayList
	 *            origin arrayList for new SinglyLinkedList
	 */
	public void buildList(ArrayList<String> arrayList) {
		for (int i = arrayList.size() - 1; i >= 0; i--) {
			add(arrayList.get(i));
		}
	}

	/**
	 * compare if two SinglyLinkedLists equal
	 * 
	 * @param otherList
	 *            SinglyLinkedList that to be compared
	 * @return - true if two SinglyLinkedLists equal, otherwise false
	 */
	public boolean equals(SinglyLinkedList otherList) {
		/* if two sizes are different, return false */
		if (otherList.size() != numElements) {
			return false;
		}

		SingleNode cursor1 = this.head;
		SingleNode cursor2 = otherList.head;

		/* Traverse two lists */
		while (cursor1 != null && cursor2 != null) {
			if (!cursor1.data.equals(cursor2.data)) {
				return false;
			}
			cursor1 = cursor1.next;
			cursor2 = cursor2.next;
		}

		return true;
	}

	/**
	 * bring a node to the front of the list
	 * 
	 * @param index
	 *            index of node
	 */
	public void bringToFront(int index) {
		/* illegal index or bring the first node */
		if (index >= numElements || index <= 0) {
			return;
		}

		int currentIndex = 0;
		SingleNode cursor = head;

		while (cursor != null) {
			if (currentIndex == index - 1) {
				/* node to be moved to the front */
				SingleNode temp = cursor.next;
				cursor.next = cursor.next.next;
				temp.next = head;
				head = temp;
				return;
			}
			cursor = cursor.next;
			currentIndex++;
		}
	}

	/**
	 * remove all the nodes whose data's length equals a given value
	 * 
	 * @param length
	 *            length of data
	 */
	public void removeAll(int length) {
		if (numElements == 0) {
			return;
		}

		SingleNode cursor = head;
		SingleNode temp;

		while (cursor != null && cursor.next != null) {
			temp = cursor.next;
			/* remove nodes until data's length doen't equal length */
			while (temp != null && temp.data.length() == length) {
				cursor.next = temp.next;
				temp = temp.next;
			}

			/* move to next node */
			cursor = cursor.next;
		}

		/* check if the head node should be removed or not */
		if (head.data.length() == length) {
			head = head.next;
		}

	}

	/**
	 * reverse the order of the list
	 */
	public void reverse() {
		if (numElements == 0) {
			return;
		}

		SingleNode cursor = head;
		SingleNode end = null;
		SingleNode temp;

		/* find the last node */
		while (cursor != null) {
			end = cursor;
			cursor = cursor.next;
		}
		/*
		 * move the head node to between end node and end's next node until head
		 * and end are the same
		 */
		while (!head.equals(end)) {
			temp = head;
			head = temp.next;
			temp.next = end.next;
			end.next = temp;
		}
	}

	/**
	 * Pretty print SinglyLinkedLists.
	 * 
	 * Do not modify this method!
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();

		if (head == null) {
			sb.append("[HEAD] -> null -> [TAIL]");
		} else {
			SingleNode nodeRef = head;
			sb.append("[HEAD] -> ");
			while (nodeRef != null) {
				sb.append(nodeRef.data);
				sb.append(" -> ");
				nodeRef = nodeRef.next;
			}
			sb.append("[TAIL]");
		}

		return sb.toString();
	}
}
