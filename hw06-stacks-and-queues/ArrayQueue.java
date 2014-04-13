/**
 * 
 * @author Yichao Xue <yichaox>
 * @section B
 * 
 *
 */

import java.util.*;

public class ArrayQueue<E> implements MyQueue<E> {

	private E[] dataArray;
	private int front; // index of first item to remove
	private int back; // index of last item to remove
	private int numOfElements; // for convenience

	private static final int DEFAULT_SIZE = 10;

	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		this.dataArray = (E[]) new Object[DEFAULT_SIZE];
		this.numOfElements = 0;
		this.back = -1;
		this.front = 0;
	}

	/**
	 * Returns true if this queue no elements.
	 * 
	 * @return true if this queue is empty, false otherwise.
	 * 
	 */
	public boolean isEmpty() {
		return (numOfElements == 0);
	}

	/**
	 * Returns, but does not remove, the element at the front of this queue.
	 * 
	 * @return The element at the front of this queue.
	 * @throws NoSuchElementException
	 *             if the queue is empty.
	 * 
	 */
	public E peek() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return dataArray[front];
		}
	}

	/**
	 * Adds the specified element to the back of this queue.
	 * 
	 * @param element
	 *            to add on to the back of this queue.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void enqueue(E element) {
		if (numOfElements == dataArray.length) {
			E[] temp = (E[]) new Object[dataArray.length * 2];

			/* copy to temp */
			int i, j;
			for (i = front, j = 0; i < front + numOfElements; i++, j++) {
				temp[j] = dataArray[i % numOfElements];
			}

			/* reset front and back */
			front = 0;
			back = dataArray.length - 1;

			dataArray = temp;
			dataArray[++back] = element;
			numOfElements++;
		} else {
			if (back == dataArray.length - 1) {
				back = -1;
			}
			dataArray[++back] = element;
			numOfElements++;
		}
	}

	/**
	 * Removes and returns the element at the front of this queue.
	 * 
	 * @return The element removed from the front of this queue.
	 * @throws NoSuchElementException
	 *             if the queue is empty.
	 * 
	 */
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			E e = dataArray[front++];
			/* front is the end of array, bring front back to index 0 */
			if (front == dataArray.length) {
				front = 0;
			}
			numOfElements--;
			return e;
		}
	}

	/**
	 * Returns a String representation of this queue in the format described in
	 * the writeup
	 * 
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("front: " + front + " back: " + back + "\n");

		sb.append("front [ ");
		for (int i = front; i < dataArray.length && dataArray[i] != null; i++) {
			sb.append(dataArray[i] + " ");
		}

		if (front > back) {
			for (int j = 0; j <= back; j++) {
				sb.append(dataArray[j] + " ");
			}
		}

		sb.append("] back");

		return sb.toString();
	}

}
