package com.github.yuan0122;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
 * merge K sorted arrays or linked lists
 * 
*/

public class KWayMerge {

	// Merge K sorted Linked List
	// Basic idea to solve this problem is to apply Best First Search
	// Use a minHeap to store all ListNodes, 
	// while the top node should always be the one with min value among all nodes in head
	public ListNode merge(List<ListNode> list) {
		assert list != null && !list.isEmpty();
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		// Declare a minHeap with right comparator
		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(list.size(), 
				new Comparator<ListNode>() {
					@Override
					public int compare(ListNode l1, ListNode l2) {
						return l1.value - l2.value;
					}
		});
		// push all head into minHeap
		for (ListNode head : list) {
			if (head != null) {
				minHeap.offer(head);
			}
		}
		// each time pop a node out, push its next node into minHeap
		ListNode current = dummy;
		while (!minHeap.isEmpty()) {
			current.next = minHeap.poll();
			if (current.next.next != null) {
				minHeap.offer(current.next.next);
			}
			current = current.next;
		}
		return dummy.next;
	}
	
	// Merge K sorted arrays
	// The high-level idea is same as merge K sorted Linked List
	// One more thing needs to be done is to store the position index of every element pushed into heap
	public static class Pair {
		int whichArray;
		int index;
		Pair(int whichArray, int index) {
			this.whichArray = whichArray;
			this.index = index;
		}
	}
	public int[] merge(final int[][] arrays) {
		assert arrays != null && arrays.length != 0;
		// Declare minHeap with right comparator
		PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>(arrays.length,
				new Comparator<Pair>() {
					@Override
					public int compare(Pair p1, Pair p2) {
						return arrays[p1.whichArray][p1.index] - arrays[p2.whichArray][p2.index];
					}
		});
		int length = 0;
		// Firstly, push the first elements of all arrays into heap
		// at the same time, calculate the final length after sorted
		for (int i = 0; i < arrays.length; ++i) {
			int[] array = arrays[i];
			if (array != null && array.length > 0) {
				minHeap.offer(new Pair(i, 0));
				length += array.length;
			}
		}
		int[] result = new int[length];
		// Then, each time an element is pop out, push its next element into heap if it exists
		for (int i = 0; i < length; ++i) {
			Pair temp = minHeap.poll();
			result[i] = arrays[temp.whichArray][temp.index];
			if (temp.index + 1 < arrays[temp.whichArray].length) {
				temp.index++;
				minHeap.offer(temp);
			}
		}
		return result;
		
	}
}
