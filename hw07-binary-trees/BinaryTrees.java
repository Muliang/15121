/**
 * 
 * @author Yichao Xue <yichaox>
 * @section Section B
 *
 */

// You may not import any additional classes and packages.
import java.util.*;

public class BinaryTrees {

	/**
	 * count the number of leaf nodes in a given level
	 * 
	 * @param t
	 * @param level
	 * @return
	 */
	public static int countLeavesAtLevel(TreeNode<Integer> t, int level) {
		if (t == null || level < 0) {
			return 0;
		}

		/* at the specific level */
		if (level == 0) {
			if (t.right == null && t.left == null) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return countLeavesAtLevel(t.left, level - 1)
					+ countLeavesAtLevel(t.right, level - 1);
		}
	}

	/**
	 * Determine is a tree is perfect or not. using non-recursion, a modified
	 * breadth first search.
	 * 
	 * @param t
	 * @return
	 */
	public static boolean isPerfect(TreeNode<Integer> t) {

		if (t == null) {
			return false;
		}

		Queue<TreeNode<Integer>> queue = new LinkedList<TreeNode<Integer>>();

		/* enqueue root node */
		queue.add(t);

		int level = 0;
		int currentLevelNodeNum = 1;
		int nextLevelNodeNum = 0;

		while (queue.size() != 0) {
			TreeNode<Integer> tempNode = queue.remove();
			currentLevelNodeNum--;

			/* add right child to queue */
			if (tempNode.right != null) {
				queue.add(tempNode.right);
				nextLevelNodeNum++;
			}

			/* add left child to queue */
			if (tempNode.left != null) {
				queue.add(tempNode.left);
				nextLevelNodeNum++;
			}

			if (currentLevelNodeNum == 0) {
				/* next level has no node */
				if (nextLevelNodeNum == 0) {
					return true;
				}

				/* number of node of next level does not equal 2 ^ (level + 1) */
				if (nextLevelNodeNum != (1 << (level + 1))) {
					return false;
				}

				currentLevelNodeNum = nextLevelNodeNum;
				nextLevelNodeNum = 0;
				level++;
			}
		}

		return true;
	}

	/**
	 * Using XOR
	 * 
	 * @param t
	 * @return
	 */
	public static boolean isFull(TreeNode<Integer> t) {
		if (t == null) {
			return false;
		}
		return !(isFull(t.left) ^ isFull(t.right));
	}

	/**
	 * Use BFS to traverse two trees
	 * 
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static boolean isSubset(TreeNode<String> t1, TreeNode<String> t2) {
		if (t1 == null && t2 == null) {
			return true;
		}
		/* t1 or t2 is null */
		if (t1 == null || t2 == null) {
			return false;
		}

		Queue<TreeNode<String>> q1 = new LinkedList<TreeNode<String>>();
		q1.add(t1);

		while (q1.size() != 0) {
			boolean flag = false;
			TreeNode<String> node1 = q1.remove();

			/* add node1's child nodes to queue1 */
			if (node1.right != null) {
				q1.add(node1.right);
			}
			if (node1.left != null) {
				q1.add(node1.left);
			}

			Queue<TreeNode<String>> q2 = new LinkedList<TreeNode<String>>();
			q2.add(t2);
			while (q2.size() != 0) {
				TreeNode<String> node2 = q2.remove();

				/* add node2's child nodes to queue2 */
				if (node2.right != null) {
					q2.add(node2.right);
				}
				if (node2.left != null) {
					q2.add(node2.left);
				}

				if (node1.data.equals(node2.data)) {
					flag = true;
				}
			}

			/* if flag is false, data of node1 is not in the tree2 */
			if (!flag) {
				return false;
			}
		}
		return true;
	}

	/**
	 * check if two trees are equal
	 * 
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static boolean areEqual(TreeNode<String> t1, TreeNode<String> t2) {
		if (t1 == null && t2 == null) {
			return true;
		}

		if (t1 == null || t2 == null) {
			return false;
		}

		if (!t1.data.equals(t2.data)) {
			return false;
		}

		/* recursive call */
		return areEqual(t1.left, t2.left) && areEqual(t1.right, t2.right);
	}

	/**
	 * Using DFS, duplicate left subtree of root and set it to the right
	 * 
	 * @param t
	 * @return
	 */
	public static TreeNode<Integer> mirror(TreeNode<Integer> t) {
		if (t == null) {
			return null;
		}
		
		if(t.left == null){
			return t;
		}
		
		TreeNode<Integer> root = t;
		TreeNode<Integer> left = t.left;

		Stack<TreeNode<Integer>> leftStack = new Stack<TreeNode<Integer>>();
		Stack<TreeNode<Integer>> rightStack = new Stack<TreeNode<Integer>>();

		TreeNode<Integer> right = new TreeNode<Integer>(left.data);
		leftStack.push(left);
		rightStack.push(right);

		while (leftStack.size() != 0) {
			TreeNode<Integer> oldNode = leftStack.pop();
			TreeNode<Integer> newNode = rightStack.pop();

			/* create a mirror of left */
			if (oldNode.left != null) {
				newNode.right = new TreeNode<Integer>(oldNode.left.data);
				leftStack.push(oldNode.left);
				rightStack.push(newNode.right);
			}

			/* create a mirror of right */
			if (oldNode.right != null) {
				newNode.left = new TreeNode<Integer>(oldNode.right.data);
				leftStack.push(oldNode.right);
				rightStack.push(newNode.left);
			}
		}

		root.right = right;
		return root;
	}

	/**
	 * determine if a tree is a BST
	 * 
	 * @param t
	 * @return
	 */
	public static boolean isBinarySearchTree(TreeNode<Integer> t) {
		return isBSTHelper(t, Integer.MAX_VALUE, Integer.MIN_VALUE);
	}

	/**
	 * helper function
	 * 
	 * @param t
	 * @param max
	 * @param min
	 * @return
	 */
	private static boolean isBSTHelper(TreeNode<Integer> t, int max, int min) {
		if (t == null) {
			return true;
		}

		if (t.data > max || t.data < min) {
			return false;
		}

		/*
		 * current data is the max value of left subtree and min value of right
		 * subtree
		 */
		return isBSTHelper(t.left, t.data, min)
				&& isBSTHelper(t.right, max, t.data);
	}
}
