package com.github.yuan0122;

import java.util.Deque;
import java.util.LinkedList;

/*
 * Construct a queue by two stacks
*/

public class QueueByTwoStacks {

	Deque<Integer> input;
	Deque<Integer> output;

	public QueueByTwoStacks() {
		input = new LinkedList<Integer>();
		output = new LinkedList<Integer>();
	}

	public Integer size() {
		return input.size() + output.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void moveElement() {
		if (output.size() == 0) {
			while (!input.isEmpty()) {
				output.push(input.pollFirst());
			}
		}
	}

	public Integer poll() {
		if (isEmpty()) {
			return null;
		}
		moveElement();
		return output.pollFirst();
	}

	public Integer peek() {
		if (isEmpty()) {
			return null;
		}
		moveElement();
		return output.peekFirst();
	}

	public void offer(int element) {
		input.offerFirst(element);
	}

}
