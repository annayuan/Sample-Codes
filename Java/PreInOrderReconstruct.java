package com.github.yuan0122;

import java.util.HashMap;

/*
 * Given two arrays, which are InOrder and PreOrder traversal of a binary search tree
 * Reconstruct the BST with them.
 * 
*/

public class PreInOrderReconstruct {

	public static class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;
		TreeNode (int value) {
			this.value = value;
		}
	}
	
	// The first element of preOrder array will always be the root 
	// Find this value in inOrder array, 
	// then in inOrder array, everything at left will be in left subtree, everything at right will be in right subtree
	// do it recursively.
	// To find the index in inOrder array more efficiently, use a HashMap
	public TreeNode fromPreInOrder(int[] preOrder, int[] inOrder) {
		assert preOrder != null && inOrder != null;
		assert preOrder.length == inOrder.length;
		HashMap<Integer, Integer> inOrderMap = indexMap(inOrder);
		return fromPreInOrder(preOrder, 0, preOrder.length - 1, 
				inOrder, 0, inOrder.length - 1, inOrderMap);
	}
	private TreeNode fromPreInOrder(int[] preOrder, int pLeft, int pRight,
								int[] inOrder, int iLeft, int iRight,
								HashMap<Integer, Integer> inOrderMap) {
		if (pLeft > pRight) {
			return null;
		}
		TreeNode root = new TreeNode(preOrder[pLeft]);
		int iIndex = inOrderMap.get(root.value);
		int leftLength = iIndex - iLeft;
		int rightLength = iRight - iIndex;
		// everything before iIndex will be in left subtree
		root.left = fromPreInOrder(preOrder, pLeft + 1, pLeft + leftLength,
						inOrder, iLeft, iIndex - 1, inOrderMap);
		// everything after iIndex will be in right subtree
		root.right = fromPreInOrder(preOrder, pRight - rightLength + 1, pRight,
						inOrder, iIndex + 1, iRight, inOrderMap);
		return root;
	}
	private HashMap<Integer, Integer> indexMap(int[] inOrder) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < inOrder.length; ++i) {
			map.put(inOrder[i], i);
		}
		return map;
	}
}
