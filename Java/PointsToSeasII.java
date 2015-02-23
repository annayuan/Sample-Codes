package session;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;

public class PointsToSeasII {

	public class Index {
		int row;
		int col;
		public Index(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	public int point(final int[][] input) {
		assert input != null && input.length != 0 && input[0].length != 0;		
		int row = input.length;
		int col = input[0].length;
		PriorityQueue<Index> minHeap = new PriorityQueue<Index>(row * col, new Comparator<Index>(){
			@Override
			public int compare(Index i1, Index i2) {
				return input[i1.row][i1.col] - input[i2.row][i2.col];
			}
		});
		int count = 0;
		int result = 0;
		int[][] num = new int[row][col];
		boolean[][] visited = new boolean[row][col];
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < col; ++j) {
				if (input[i][j] == 0 && num[i][j] != -1) {    // Yeah, this is right thing to check, but it seems you don't update it in a right way. 
					Index seed = new Index(i, j);
					minHeap.offer(seed);
					//boolean[][] visited = new boolean[row][col];
					for (boolean[] temp: visited) {
						Arrays.fill(temp, false);
					}
					// Couple things about this visited matrix:
					search(input, visited, num, minHeap);    //   1) The type can just be boolean instead of int.
					count++;                                 //   2) You are creating a new matrix every time. A better way is to create this matrix
				}                                            //       outside the for loop. And at end of each iteration, reset this matrix to all 0. This 
			}                                                //       approach is better in terms of memory usage and performance. 
		}
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < col; ++j) {
				if (num[i][j] == count) {
					System.out.println("[" + i + "," + j + "]: " + input[i][j]);
					result++;
				}
			}
		}
		return result;
	}

	public void search(int[][] input, boolean[][] visited, int[][] num, PriorityQueue<Index> minHeap) {
		while (!minHeap.isEmpty()) {
			Index temp = minHeap.poll();
			int i = temp.row;
			int j = temp.col;
			int value = input[i][j];
			//visited[i][j] = true;    
			if (input[i][j] == 0) {
				num[i][j] = -1;
			} else {
				num[i][j]++;
			}
            // Update num matrix at here. And if the input[i][j] is 0, set num[i][j] = -1.
			if (i - 1 >= 0 && input[i - 1][j] >= value
					&& visited[i - 1][j] != true) {
				minHeap.offer(new Index(i - 1, j));
				// This can be removed because you will update this when the cell is poped from heap. 
				// Same as above. 
				visited[i-1][j] = true;
			}
			if (i + 1 < input.length && input[i + 1][j] >= value
					&& visited[i + 1][j] != true) {
				minHeap.offer(new Index(i + 1, j));
				visited[i+1][j] = true;
			}
			if (j - 1 >= 0 && input[i][j - 1] >= value
					&& visited[i][j - 1] != true) {
				minHeap.offer(new Index(i, j - 1));
				visited[i][j-1] = true;
			}
			if (j + 1 < input[0].length && input[i][j + 1] >= value
					&& visited[i][j + 1] != true) {
				minHeap.offer(new Index(i, j + 1));
				visited[i][j+1] = true;
			}
		}
	}
	
	public static void main(String[] args) {
		PointsToSeasII solution = new PointsToSeasII();
		
		int[][] input = new int[][] {{0,0,0,1,2,3,0},
									 {0,1,2,2,4,3,2},
									 {2,1,1,3,3,2,0},
									 {0,3,3,3,2,3,3,}};
		System.out.println(solution.point(input));
		System.out.println();
		input = new int[][] {{1, 0, 1, 2, 2, 4}, 
							 {2, 1, 0, 0, 2, 3},
							 {1, 2, 2, 0, 1, 2},
							 {2, 3, 1, 2, 0, 2}};
		System.out.println(solution.point(input));
	}
}
