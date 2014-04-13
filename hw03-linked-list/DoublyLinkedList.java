/**
 * 
 * @author Yichao Xue <yichaox>
 * @section B
 * 
 */

import java.util.*;

public class DoublyLinkedList<E> {
	// Do not change these fields.
	public DoubleNode<E> currNode;
	public int numElements;

	/**
	 * constructor for class
	 */
	public DoublyLinkedList() {
		currNode = null;
		numElements = 0;
	}

	/**
	 * get the size of DoublyLinkedList
	 * 
	 * @return - list's size
	 */
	public int size() {
		return numElements;
	}

	/**
	 * get current node's data
	 * 
	 * @return - current node's data
	 */
	public E get() {
		if (numElements == 0) {
			/* if list is empty, throw NoSuchElementException */
			throw new NoSuchElementException();
		}
		return currNode.data;
	}

	/**
	 * add a new node to the list
	 * 
	 * @param item
	 *            node's data
	 */
	public void add(E item) {
		DoubleNode<E> newNode = new DoubleNode<E>(item);

		if (currNode == null) {
			currNode = newNode;
			currNode.next = currNode;
			currNode.prev = currNode;
			numElements++;
		} else {
			/* add the new node after current node */
			newNode.prev = currNode;
			newNode.next = currNode.next;
			currNode.next.prev = newNode;
			currNode.next = newNode;

			currNode = newNode;
			numElements++;
		}
	}

	/**
	 * create a List from DoublyLinkedList
	 * 
	 * @return - list
	 */
	public List<E> toList() {
		List<E> list = new ArrayList<E>();

		DoubleNode<E> cursor = currNode;

		for (int i = 0; i < numElements; i++) {
			cursor = cursor.next;

			list.add(cursor.data);
		}
		return list;

	}

	/**
	 * check if the list contains a given item
	 * 
	 * @param item
	 * 
	 * @return - true if contain, false otherwise
	 */
	public boolean contains(E item) {
		DoubleNode<E> cursor = currNode;

		do {
			if (cursor.data.equals(item)) {
				return true;
			}
			cursor = cursor.next;
		} while (cursor != currNode);
		return false;
	}

	/**
	 * scroll the current node
	 * 
	 * @param n
	 *            steps of scrolling
	 */
	public void scroll(Integer n) {

		/* if the list is empty */
		if (numElements == 0) {
			throw new NoSuchElementException();
		}
		/*
		 * because the list is a circle, we can calculate the actual steps that
		 * list should scroll using "%"
		 */
		else if (n >= 0) {
			n = n % numElements;
		} else {
			n = n % numElements + numElements;
		}

		for (int i = 0; i < n; i++) {
			currNode = currNode.next;
		}
	}

	/**
	 * remove current node
	 * 
	 * @return - data of removed node
	 */
	public E remove() {
		if (numElements == 0) {
			throw new NoSuchElementException();
		}

		/* if there is only one node, get the data and set the node to null */
		if (numElements == 1) {
			E data = currNode.data;
			currNode = null;
			numElements--;
			return data;

		} else {
			/* remove the data and connect the remaining nodes */
			currNode.prev.next = currNode.next;
			currNode.next.prev = currNode.prev;
			E data = currNode.data;
			currNode = currNode.prev;

			numElements--;
			return data;
		}

	}
}
