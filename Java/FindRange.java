package com.github.yuan0122;

/*
 * Given a binary search tree with all nodes' value >= 1, 
 * every TreeNode follow the definition of BST, except for one TreeNode,
 * which is assigned with value "0".
 * Find this node and find the possible range of this TreeNode's value.
 * 
*/
public class FindRange {

	// The basic idea to solve this problem is to use Binary Tree InOrder Traversal with recursion
	// Need to be very carefully deal with all corner cases
	public String findRange(TreeNode root) {
		if (root == null) {
			return "Invalid";
		} else if (root.left == null && root.right == null && root.value == 0) {
			return "[1, Infinity)";
		}
		StringBuilder builder = new StringBuilder();
		TreeNode prev = new TreeNode(-1);
		prev = helper(root, prev, builder);
		if (prev.value == 0 && builder.charAt(builder.length() - 1) != ']') {
			builder.append("Infinity)");
		}
		return builder.toString();
	}
	// InOrder traversal
	public TreeNode helper(TreeNode root, TreeNode prev, StringBuilder builder) {
		if (root == null) {
			return prev;
		}
		// Traverse to left subtree
		helper(root.left, prev, builder);
		// Do something part
		//if root value is 0, this is the target node
		if (root.value == 0) {
			if (prev.value == -1) {
				builder.append("[1, ");
			} else {
				int num = prev.value + 1;
				builder.append('[');
				builder.append(intToString(num));
				builder.append(", ");
			}
		} else {
			if (prev.value == 0) {
				int num = root.value - 1;
				builder.append(intToString(num));
				builder.append(']');
				return prev;
			}
		}
		prev.value = root.value;
		// Traverse to right subtree
		helper(root.right, prev, builder);
		return prev;
	}
	// 12
	private String intToString(int num) {
		StringBuilder builder = new StringBuilder();
		while (num >= 1) {
			builder.append((char)(num % 10 + '0'));
			num /= 10;
		}
		return new String(builder.reverse());
	}
	public static void main(String[] args) {
		FindRange solution = new FindRange();
		
		TreeNode root = null;
		System.out.println(solution.findRange(root));
		
		TreeNode root1 = TreeNode.reConstruct(new String[] {"0", "#", "#"});
		System.out.println(solution.findRange(root1));
		
		TreeNode root2 = TreeNode.reConstruct(new String[] {
				"10", "5", "2", "#", "#", "7", "#", "#", "0", "12", "#", "#", "18", "#", "#"});
		System.out.println(solution.findRange(root2));
			
		TreeNode root3 = TreeNode.reConstruct(new String[] {
				"4", "2", "1", "#", "#", "#", "0", "8", "#", "#", "#"});
		System.out.println(solution.findRange(root3));
	}
}
