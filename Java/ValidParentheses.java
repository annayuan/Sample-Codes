package com.github.yuan0122;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ValidParentheses {

	public static final char[] paren = { '(', ')', '[', ']', '{', '}' };

	public List<String> validParentheses(int l, int m, int n) {
		// write your solution here
		List<String> result = new ArrayList<String>();
		if (l < 0 || m < 0 || n < 0) {
			return result;
		}
		StringBuilder solution = new StringBuilder();
		Deque<Character> stack = new LinkedList<Character>();
		valid(new int[] { l, l, m, m, n, n }, 2 * (l + m + n), solution,
				result, stack);
		return result;
	}

	private void valid(int[] remain, int targetLen, StringBuilder solution,
			List<String> result, Deque<Character> stack) {
		if (solution.length() == targetLen) {
			result.add(solution.toString());
			return;
		}
		// 6 states, () [] {}
		for (int i = 0; i < 6; ++i) {
			// if we meet a left parenthese
			if (remain[i] > 0) {
				if (i % 2 == 0) {
					solution.append(paren[i]);
					remain[i]--;
					stack.offerFirst(paren[i]);
					valid(remain, targetLen, solution, result, stack);
					stack.pollFirst();
					remain[i]++;
					solution.deleteCharAt(solution.length() - 1);
				} else {
					// if we meet a right parenthese, we need to check peek of
					// stack
					if (!stack.isEmpty() && stack.peekFirst() == paren[i - 1]) {
						solution.append(paren[i]);
						remain[i]--;
						stack.pollFirst();
						valid(remain, targetLen, solution, result, stack);
						stack.offerFirst(paren[i - 1]);
						remain[i]++;
						solution.deleteCharAt(solution.length() - 1);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		ValidParentheses solution = new ValidParentheses();
		int l = 1;
		int m = 1;
		int n = 1;
		System.out.println(solution.validParentheses(l, m, n));
	}
}
