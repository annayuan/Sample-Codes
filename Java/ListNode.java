package com.github.yuan0122;

public class ListNode {

	int value;
	ListNode next;
	
	public ListNode(int value) {
		this.value = value;
	}
	
	public static ListNode arrayToList(int[] array) {
		if (array == null || array.length == 0) {
			return null;
		}
		ListNode head = new ListNode(array[0]);
		ListNode current = head;
		for (int index = 1; index < array.length; index++) {
			current.next = new ListNode(array[index]);
			current = current.next;
		}
		return head;
	}
	
	public static ListNode arrayToListWithCircle(int[] array, int preLength) {
		if (array == null || array.length == 0) {
			return null;
		}
		ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
		ListNode current = dummyHead;
		ListNode circleStart = null;
		for (int index = 0; index < array.length; index++) {
			current.next = new ListNode(array[index]);
			current = current.next;
			if (index == preLength) {
				circleStart = current;
				}
		}
		current.next = circleStart;
		ListNode head = dummyHead.next;
		dummyHead.next = null;
		return head;
	}
	
	public static void printList(ListNode head) {
		StringBuilder builder = new StringBuilder();
		while (head != null) {
			builder.append(head.value).append("->");
			head = head.next;
		}
		builder.append("null");
		System.out.println(builder.toString());
	}
	
	public static void printListNode(ListNode node) {
		if (node == null) {
			System.out.println("null");
		} else {
			System.out.println("[" + node.value + "]");
		}
	}
}
