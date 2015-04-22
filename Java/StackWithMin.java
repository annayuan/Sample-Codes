package com.github.yuan0122;

import java.util.Deque;
import java.util.LinkedList;

/*
 * Construct a stack with min() method which always returns current min value.
*/
public class StackWithMin {

	Deque<Integer> stack;
	Deque<Integer> minStack;

	public StackWithMin() {
		stack = new LinkedList<Integer>();
		minStack = new LinkedList<Integer>();
	}

	public void push(int element) {
		if (minStack.isEmpty() || element <= minStack.peekFirst()) {
			minStack.offerFirst(element);
		}
		stack.offerFirst(element);
	}

	public Integer pop() {
		if (stack.isEmpty()) {
			return null;
		} else if (stack.peekFirst() == minStack.peekFirst()) {
			minStack.pollFirst();
		}
		return stack.pollFirst();
	}

	public Integer top() {
		return stack.pollFirst();
	}

	public Integer min() {
		return minStack.pollFirst();
	}
}
