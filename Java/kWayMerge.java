package anna.java.practice;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
 * merge K sorted array/lists
*/

public class kWayMerge {

	public ListNode merge(List<ListNode> list) {
		assert list != null && !list.isEmpty();
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(list.size(), 
				new Comparator<ListNode>() {
					@Override
					public int compare(ListNode l1, ListNode l2) {
						return l1.value - l2.value;
					}
		});
		for (ListNode head : list) {
			if (head != null) {
				minHeap.offer(head);
			}
		}
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
		PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>(arrays.length,
				new Comparator<Pair>() {
					@Override
					public int compare(Pair p1, Pair p2) {
						return arrays[p1.whichArray][p1.index] - arrays[p2.whichArray][p2.index];
					}
		});
		int length = 0;
		for (int i = 0; i < arrays.length; ++i) {
			int[] array = arrays[i];
			if (array != null && array.length > 0) {
				minHeap.offer(new Pair(i, 0));
				length += array.length;
			}
		}
		int[] result = new int[length];
		for (int i = 0; i < length; ++i) {
			Pair temp = minHeap.poll();
			result[i] = arrays[temp.whichArray][temp.index];
			if (temp.index + 1 < arrays[temp.whichArray].length) {
				temp.index++;
				//minHeap.offer(new Pair(temp.whichArray, temp.index));
				minHeap.offer(temp);
			}
		}
		return result;
		
	}
}
