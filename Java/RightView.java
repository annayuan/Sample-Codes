package com.laioffer.midterm;

import java.util.LinkedList;
import java.util.Queue;


/*
 * Given a Binary Tree, print Right view of it. 
 * Right view of a Binary Tree is set of nodes visible when tree is visited from Right side.
 * 
 */

public class RightView {
	
	public void rightViewI(TreeNode root) {
		if (root == null) {
			return;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		System.out.println(root.value);
		while (!queue.isEmpty()) {
			int size = queue.size();
			int i = 0;
			while (i < size) {
				TreeNode temp = queue.poll();
				if (temp.left != null) {
					queue.offer(temp.left);
				}
				if (temp.right != null) {
					queue.offer(temp.right);
				}
				i++;
				if (i == size - 1) {
					TreeNode mostRight = queue.poll();
					System.out.println(mostRight.value);
					if (mostRight.left != null) {
						queue.offer(mostRight.left);
					}
					if (mostRight.right != null) {
						queue.offer(mostRight.right);
					}
				}
				i++;
			}			
		}	
	}
	
	public int maxLevel = 0;
	public int level = 1;
	public void rightViewII(TreeNode root) {
		rightViewRec(root, level, maxLevel);
	}
	
	private void rightViewRec(TreeNode root, int level, int maxLevel) {
		if (root == null) {
			return;
		}
		if (maxLevel < level) {
			System.out.println(root.value);
			maxLevel = level;
		}
		rightViewRec(root.right, level + 1, maxLevel);
		rightViewRec(root.left, level + 1, maxLevel);
	}

	public static void main(String[] args) {
		RightView solution = new RightView();
		
		TreeNode root = null;
		solution.rightViewII(root);
		System.out.println("___________________________");
		root = TreeNode.reConstruct(new String[] {"2", "1", "#", "#", "3", "#", "#"});
		solution.rightViewII(root);
		System.out.println("___________________________");
		root = TreeNode.reConstruct(new String[] {"4", "2", "1", "#", "#", "3", "#", "#", "7", "#", "#"});
		solution.rightViewII(root);
		System.out.println("___________________________");
	}
}
