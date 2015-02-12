package anna.java.pratice;

import java.util.HashMap;

public class PreInOrderReconstruct {

	public static class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;
		TreeNode (int value) {
			this.value = value;
		}
	}
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
		root.left = fromPreInOrder(preOrder, pLeft + 1, pLeft + leftLength,
						inOrder, iLeft, iIndex - 1, inOrderMap);
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
