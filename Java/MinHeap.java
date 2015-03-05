package classes;

import java.util.Arrays;

public class MinHeap {

	public static final int DEFAULT_CAPACITY = 128;

	  private int[] array; // the heap array, length is capacity
	  private int size; // the current heap’s size, <= capacity at any time

	  public MinHeap() {
	    this(DEFAULT_CAPACITY);
	  }

	  public MinHeap(int capacity) {
	    // Not allow capacity as 0.
	    if (capacity <= 0) {
	      throw new IllegalArgumentException("Initial capacity has to be larger than 0");
	    }
	    this.array = new int[capacity];
	    this.size = 0;
	  }

	  // initialize the heap with elements from the given array and a possible capacity.
	  public MinHeap(int[] array, int capacity) {
	    if (array == null || array.length == 0) {
	      throw new IllegalArgumentException("Can not initialize with null or empty array");
	    }
	    if (capacity < array.length) {
	      throw new IllegalArgumentException("Initial capacity has to be larger than or equals to the array’s length");
	    }
	    size = array.length;
	    this.array = Arrays.copyOf(array, capacity);
	    heapify(); // O(n)
	  }
	  
	  public int peek() {
		  // check empty
		  emptyCheck();
		  return array[0];
		  // return peekAt(0);
	  }
	  
	  public int poll() {
		  emptyCheck();
		  swap(0, size - 1);
		  size--;
		  shiftDown(0);
		  return array[size];
		  // return pollAt(0);
	  }
	  
	  public boolean offer(int element) {
		  // check full
		  if (isFull()) {
			  return false;
		  }
		  size++;
		  /*array[size - 1] = element;
		  shiftUp(size - 1);*/
		  update(size - 1, element);
		  return true;
	  }
	  
	  public int update(int index, int newElement) {
		  // check index is valid [0, size - 1]
		  boundaryCheck(index);
		  int result = array[index];
		  array[index] = newElement;
		  // shiftUp and shiftDown are exclusive, only one method will be called
		  shiftUp(index);
		  shiftDown(index);
		  return result;
	  }
	  
	  public int pollAt(int index) {
		// check index valid [0, size - 1]
		  boundaryCheck(index);
		  size--;
		  return update(index, array[size]);
	  }
	  
	  public int peekAt(int index) {
		  // check index valid [0, size - 1]
		  boundaryCheck(index);
		  return array[index];
	  }
	  
	  public int size() {
		  return size;
	  }
	  
	  public boolean isEmpty() {
		  return size == 0;
	  }
	  
	  public boolean isFull() {
		  return size == array.length;
	  }
	  
	  private void emptyCheck() {
		  if (isEmpty()) {
			  throw new ArrayIndexOutOfBoundsException("");
		  }
	  }
	  
	  /*private void fullCheck() {
		  
	  }*/
	  
	  private void boundaryCheck(int index) {
		  if (index < 0 || index > size - 1) {
			  throw new ArrayIndexOutOfBoundsException("");
		  }
	  }
	  
	  // O(n)
	  private void heapify() {
		  for (int index = size / 2 - 1; index >= 0; --index) {
			  shiftDown(index);
		  }
	  }
	  
	  // from current index, current index's left child,
	  // current index's right child, pick the one with smallest value,
	  // if the smallest value's index is not current index,
	  // we need to do swap and percolateDown on the smallest value's index.
	  private void shiftDown(int index) {
		  while (index < size) {
			  int left = 2 * index + 1;
			  int right = 2 * index + 2;
			  // find the index having the smallest value
			  int min = index;
			  // compare to left
			  if (left < size && array[left] < array[min]) {
				  min = left;
			  }
			  // compare to right
			  if (right < size && array[right] < array[min]) {
				  min = right;
			  }
			  // swap if necessary
			  if (min == index) {
				  break;
			  }
			  swap(index, min);
			  index = min;
		  }
	  }
	  
	  private void shiftUp(int index) {
		  while (index > 0) {
			  // compare itself with parent
			  int parent = (index - 1) / 2;
			  if (array[index] >= array[parent]) {
				  break;
			  }
			  // swap if necessary (array[index] < array[parent])
			  swap(index, parent);
			  index = parent;
		  }
	  }
	  
	  private void swap(int indexOne, int indexTwo) {
		  int temp = array[indexOne];
		  array[indexOne] = array[indexTwo];
		  array[indexTwo] = temp;
	  }
}
