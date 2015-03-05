package com.github.yuan0122;


public class TreeNode {

	static final String NULL_NODE = "#";
	
	public int value;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int value) {
		this.value = value;
	}
	/**
	* Construct the binary tree using preorder
	traversal sequence.
	*
	* Note: the null node will be represented by a special value "#".
	*
	* If null node is ignored in the preorder
	traversal sequence,
	* the constructed binary tree would not be UNIQUE.
	*
	* Example:
	* 2
	* / \
	* 1 3
	* / \ / \
	* null null null null
	*
	* The preorder
	traversal sequence is:
	* {"2", "1", "#", "#", "3", "#", "#"}
	* Using the above sequence we can reconstruct the binary tree.
	*
	* @param preOrder
	* the preOrder traversal sequence
	* @return the root of the constructed binary tree
	*/
	public static TreeNode reConstruct(String[] preOrder) {
		if (preOrder == null || preOrder.length == 0) {
			return null;
		}
		return reConstruct(preOrder, 0).node;
	}
	/*
	* The real implementation of reconstruction
	from preOrder traverse.
	*/
	private static Result reConstruct(String[] preOrder, int left) {
		if (preOrder[left].equals(NULL_NODE)) {
		return new Result(left + 1, null);
		}
		TreeNode root = new TreeNode(Integer.parseInt(preOrder[left]));
		Result leftResult = reConstruct(preOrder, left + 1);
		root.left = leftResult.node;
		Result rightResult = reConstruct(preOrder, leftResult.index);
		root.right = rightResult.node;
		rightResult.node = root;
		return rightResult;
	}
	/*
	* Helper class for storing the intermediate information used in
	* reConstruct(String[] preOrder, int left) method.
	*/
	private static class Result {
		int index;
		TreeNode node;
		Result(int index, TreeNode node) {
			this.index = index;
			this.node = node;
		}
	}
}

