/**
 * 
 * @author Yichao Xue <yichaox>
 * @section B
 * 
 * 
 */

public class StackQueueSolver {

	// O(n^2)
	public static int lastCustomer(int numPersons, int numToBack) {
		MyQueue<Integer> queue = new ArrayQueue<Integer>();
		int count =0;
		/* initialize */
		int size = numPersons;
		for (int i = 1; i <= numPersons; i++) {
			queue.enqueue(i);
		}
		
		while (size > 1) {
			for (int j = 0; j < numToBack; j++) {
				queue.enqueue(queue.dequeue());
				count++;
			}
			queue.dequeue();
			size--;
		}
		
		System.out.println("Person: " + numPersons + "  N: " + numToBack + " Runtime: " + count);
		return queue.dequeue();
	}

	// Runtime O(n)
	public static boolean areEqual(MyStack<String> stack1,
			MyStack<String> stack2) {
		/* same object */
		if (stack1.equals(stack2)) {
			return true;
		}

		boolean flag = false;
		MyStack<String> temp = new ArrayStack<String>();

		/* compare elements from two stacks */
		while (!stack1.isEmpty() && !stack2.isEmpty()) {
			if (!stack1.peek().equals(stack2.peek())) {
				break;
			}
			/* push into a temp stack */
			temp.push(stack1.pop());
			temp.push(stack2.pop());
		}

		/* two stacks have same number of elements */
		if (stack1.isEmpty() && stack2.isEmpty()) {
			flag = true;
		}

		/* restore two stacks */
		while (!temp.isEmpty()) {
			stack2.push(temp.pop());
			stack1.push(temp.pop());
		}

		return flag;
	}

	// Runtime O(n)
	public static MyStack<Integer> duplicateStack(MyStack<Integer> original) {
		MyStack<Integer> result = new ArrayStack<Integer>();
		MyQueue<Integer> temp = new ArrayQueue<Integer>();
		
		/*reverse the order of original elements*/
		while(!original.isEmpty()){
			temp.enqueue(original.pop());
		}
		while(!temp.isEmpty()){
			original.push(temp.dequeue());
		}
		
		/*copy element to new stack*/
		while(!original.isEmpty()){
			result.push(original.peek());
			/*put original elements to queue*/
			temp.enqueue(original.pop());
		}
		
		/*restore original*/
		while(!temp.isEmpty()){
			original.push(temp.dequeue());
		}
		
		return result; // remove this line when you are done
	}

	public static void main(String[] args) {
		lastCustomer(6, 4);
	}

}