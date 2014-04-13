/**
 * 
 * @author Yichao Xue <yichaox>
 * @section Section B
 *
 */

// You may not import any additional classes and packages.
import java.util.*;

public class Roster {
	public StudentNode root;
	public int numStudents;

	public Roster() {
		root = null;
		numStudents = 0;
	}

	public int size() {
		return numStudents;
	}

	/**
	 * Add a course to a specific student
	 * 
	 * @param name
	 * @param course
	 */
	public void addCourse(String name, String course) {
		/* search entire tree, if found, add course */
		StudentNode currentStudent = root;
		StudentNode preNode = null;

		/* empty tree */
		if (root == null) {
			StudentNode newNode = new StudentNode(name);
			newNode.courses.add(course);
			root = newNode;
			numStudents++;
			return;
		}

		while (currentStudent != null) {
			/* store pre-node */
			preNode = currentStudent;

			/* found */
			if (currentStudent.name.equals(name)) {
				currentStudent.courses.add(course);
				return;
			}
			/* traverse to next node */
			currentStudent = name.compareTo(currentStudent.name) < 0 ? currentStudent.left
					: currentStudent.right;
		}
		/* if not found, create a new node, insert it */
		StudentNode newNode = new StudentNode(name);
		newNode.courses.add(course);
		numStudents++;

		if (name.compareTo(preNode.name) < 0) {
			preNode.left = newNode;
		} else {
			preNode.right = newNode;
		}
	}

	/**
	 * remove a course from all students
	 * 
	 * @param course
	 */
	public void dropCourseFromAll(String course) {
		if (root == null) {
			System.out.println("Roster is empty, can't drop course " + course);
			return;
		}

		Queue<StudentNode> queue = new LinkedList<StudentNode>();
		queue.add(root);

		while (queue.size() != 0) {
			StudentNode temp = queue.remove();

			if (temp.courses.contains(course)) {
				temp.courses.remove(course);
			}

			if (temp.left != null) {
				queue.add(temp.left);
			}
			if (temp.right != null) {
				queue.add(temp.right);
			}
		}
	}

	public void deleteStudent(String aName) {
		if (root == null) {
			return;
		}
		/* first, find the node */
		StudentNode currentNode = root;
		
		if(aName.equals(root.name) && root.left == null){
			root = root.right;
			numStudents--;
			return;
		}
		
		if(aName.equals(root.name) && root.right == null){
			root = root.left;
			numStudents--;
			return;
		}
		
		StudentNode preNode = null;
		while (currentNode != null) {
			/* found */
			if (aName.equals(currentNode.name)) {
				/* leaf node */
				if (currentNode.left == null && currentNode.right == null) {
					deleteNode(aName, preNode, null);
					return;
				}

				/* node of order 1 */
				/* left child node is null */
				if (currentNode.left == null && currentNode.right != null) {
					deleteNode(aName, preNode, currentNode.right);
					return;
				}
				/* right child node is null */
				if (currentNode.right == null && currentNode.left != null) {
					deleteNode(aName, preNode, currentNode.left);
					return;
				}
				
				/*find the inorder successor*/
				StudentNode successor = currentNode.right;
				StudentNode preSuccessor = currentNode;
				
				while(successor.left != null){
					preSuccessor = successor;
					successor = successor.left;
				}
				
				/*replace the node to be deleted*/
				currentNode.name = successor.name;
				currentNode.courses = successor.courses;
				
				/*inorder successor must not have left child node*/
				if(successor.left == null && successor.right ==null){
					deleteNode(successor.name, preSuccessor, null);
					return;
				}else{
				deleteNode(successor.name, preSuccessor, successor.right);
				return;}
			}
			
			preNode = currentNode;
			/* traverse to next node */
			currentNode = aName.compareTo(currentNode.name) < 0 ? currentNode.left
					: currentNode.right;
		}
	}

	/**
	 * helper function
	 * @param name
	 * @param preNode
	 * @param postNode
	 */
	private void deleteNode(String name, StudentNode preNode, StudentNode postNode){
		if(name.compareTo(preNode.name) < 0){
			preNode.left = postNode;
		}else{
			preNode.right = postNode;
		}
		numStudents--;
	}
	/**
	 * inorder traverse the tree
	 */
	public String toString() {
		if (root == null) {
			return "There are no students registered";
		}

		StringBuilder sb = new StringBuilder();
		/* inorder traverse the tree */
		Stack<StudentNode> stack = new Stack<StudentNode>();
		StudentNode currentNode = root;

		while (true) {
			/* push all the left child nodes to stack */
			while (currentNode != null) {
				stack.push(currentNode);
				currentNode = currentNode.left;
			}

			if (stack.isEmpty()) {
				break;
			}

			currentNode = stack.pop();
			if(currentNode.courses.isEmpty()){
				sb.append(currentNode.name+": no courses taken\n");
			}else{
			sb.append(currentNode.name + " has taken:");
			for (String course : currentNode.courses) {
				sb.append(" " + course + ",");
			}
			/* replace last "," */
			sb.replace(sb.length() - 1, sb.length(), "\n");}

			/* traverse to right child node */
			currentNode = currentNode.right;
		}

		return sb.toString();
	}

	// Do not change anything below this line.

	public void display() {
		new TreeDisplay<String>().setRoot(copy(root));
	}

	private static TreeNode<String> copy(StudentNode node) {
		if (node == null)
			return null;
		return new TreeNode<String>(node.name + ":" + node.courses,
				copy(node.left), copy(node.right));
	}
}
