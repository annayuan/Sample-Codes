package session;

public class FindRange {
	
	//public TreeNode prev;
	public String findRange(TreeNode root) {
		if (root == null) {
			return "Invalid";
		} else if (root.left == null && root.right == null && root.value == 0) {
			return "[1, Infinity)";
		}
		StringBuilder builder = new StringBuilder();
		//TreeNode prev = null;
		TreeNode prev = new TreeNode(-1);
		prev = helper(root, prev, builder);
		// helper(root, prev, builder);
		if (prev.value == 0 && builder.charAt(builder.length() - 1) != ']') {
			builder.append("Infinity)");
		}
		return builder.toString();
	}
	public TreeNode helper(TreeNode root, TreeNode prev, StringBuilder builder) {
		if (root == null) {
			return prev;
			//return;
		}
		helper(root.left, prev, builder);
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
				//return;
			}
		}
		prev.value = root.value;
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
		
		/*int num = 123;
		System.out.println(solution.intToString(num));*/
		
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
