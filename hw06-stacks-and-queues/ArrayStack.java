/**
 * 
 * @author Yichao Xue <yichaox>
 * @section B
 * 
 *
 */

import java.util.*;

public class ArrayStack<E> implements MyStack<E> {

	private E[] dataArray;
	private int top;

	/* default size */
	private static final int DEFAULT_SIZE = 10;

	/**
	 * Creates an empty stack.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		this.dataArray = (E[]) new Object[DEFAULT_SIZE];
		this.top = -1; // initially empty
	}

	/**
	 * Determines if the stack is empty or not.
	 * 
	 * @return true if the stack is empty or false otherwise
	 * 
	 */
	public boolean isEmpty() {
		return (top == -1);
	}

	/**
	 * Returns but does not remove the top element of the stack if the stack is
	 * not empty.
	 * 
	 * @return The top element of the stack
	 * @throws NoSuchElementException
	 *             if the stack is empty
	 * 
	 */
	public E peek() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return dataArray[top];
		}
	}

	/**
	 * Pushes the given element on this stack
	 * 
	 * @param element
	 *            The element of type E to push on the stack.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void push(E element) {
		/* increase size */
		if (top == dataArray.length - 1) {

			E[] temp = (E[]) new Object[dataArray.length * 2];
			/* deep copy */
			for (int i = 0; i < dataArray.length; i++) {
				temp[i] = dataArray[i];
			}
			dataArray = temp;
		}

		dataArray[++top] = element;
	}

	/**
	 * Returns and removes the top element of the stack if the stack is not
	 * empty.
	 * 
	 * @return The top element of the stack
	 * @throws NoSuchElementException
	 *             if the stack is empty
	 * 
	 */
	public E pop() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return dataArray[top--];
		}
	}

	/**
	 * Returns a String representation of the stack in the following format top
	 * [ 3 5 ] bottom
	 * 
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		for (int i = top; i >= 0; i--) {
			sb.append(dataArray[i] + " ");
		}
		sb.append("] bottom");

		return sb.toString();
	}

}
