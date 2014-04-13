/*
 * 
 * Do not change this class!
 *
 *
 */


public class DoubleNode<E> {
	public DoubleNode<E> prev;
	public E data;
	public DoubleNode<E> next;

	public DoubleNode(E data) {
		this.data = data;
	}
	
	public boolean equals(DoubleNode<E> otherNode) {
		return this.data.equals(otherNode.data);
	}
}
